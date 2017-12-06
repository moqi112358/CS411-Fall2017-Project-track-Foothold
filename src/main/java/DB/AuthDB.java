package DB;
import com.mysql.jdbc.MySQLConnection;
import modules.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exception.MyLog;
public class AuthDB extends BasicDB {

    private static MessageDigest passEncoder ;

    public boolean login(long userId, String pass) {
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn
                    .prepareStatement("select password from users where usrId = ?");
            pstmt.setLong(1, userId);
            ResultSet rs =  pstmt.executeQuery();

            boolean result = false;
            if(rs.next()){
               result = MD5(pass).equals( rs.getString("password"));
            }
            conn.close();
            return result;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return false;
        }

    }
    public User login(String email, String pass) {
        if(loginCheck( email,  pass)){
            User us = new UserDB().getUser(email);

           return us ;
        }
        return  null;

    }
    public boolean loginCheck(String email, String pass) {

        try {
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("select password from users where email = ?");
            pstmt.setString(1, email);
            ResultSet rs =  pstmt.executeQuery();

            boolean result = false;
            if(rs.next()){
                result = MD5(pass).equals( rs.getString("password"));
            }
            conn.close();
            return result;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return false;
        }

    }

    public long signup(User usr){
        if(checkEmailNot(usr.getUserEmail())) {

            try {
                Connection conn = getConn();
                PreparedStatement pstmt = conn.prepareStatement("insert into  users (userID,firstName,lastName,email,password) VALUES (?,?,?,?,?);");
                pstmt.setLong(1, usr.getUserID());
                pstmt.setString(2, usr.getFirstName());
                pstmt.setString(3, usr.getLastName());

                pstmt.setString(4, usr.getUserEmail());
                pstmt.setString(5, MD5(usr.getPassWord()));
                pstmt.executeUpdate();
                long re = getLastInsert(conn);
                conn.close();
                return re;
            } catch (SQLException e) {
                MyLog.write(e.toString());
                System.err.println(e.toString());
            }

            return -1;
        }else{
            return -3;
        }
    }
    public boolean checkEmailNot(String email)
        {

            try {
                Connection conn = getConn();
                PreparedStatement pstmt = conn.prepareStatement("select count(email) as num from  users where email = ?;");

                pstmt.setString(1, email);


               ResultSet rs = pstmt.executeQuery();
               if(rs.next()){
                   return rs.getInt("num")<1;
               }

                conn.close();
                return  true;
            }catch (SQLException e){
                MyLog.write(e.toString());
                System.err.println(e.toString());
                return false;
            }


        }

    public final static String MD5(String pwd) {
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = pwd.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    public static long getLastInsert(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        long result = rs.getLong(1);
        conn.close();
        rs.close();
        return result;


    }
    /*
    * -1 problem
    * 1 success
     */
    public int changePassWorld(User u,String pass){
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement("update   users set password = ? where userID = ?;");
            pstmt.setString(1, MD5(pass));
            pstmt.setLong(2,u.getUserID());
            pstmt.executeUpdate();
            conn.close();
            return  1;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
        }
        return 0;
    }


}
