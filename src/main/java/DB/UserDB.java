package DB;
import exception.MyLog;
import modules.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB extends BasicDB {

    public boolean creatUser(User u){
        return false;
    }

    public User getUser(long userID){
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("select userID, firstName, lastName, email from users where userid = ?");
            pstmt.setLong(1, userID);
            ResultSet rs =  pstmt.executeQuery();


            if(rs.next()){
                User us = new User(rs.getLong("userID"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"));
                conn.close();
                return getRealRole(us);
            }
            conn.close();
            return null;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return  null;
        }
    }

    public User getUser(String email){
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("select userID, firstName, lastName, email from users where email = ?");
            pstmt.setString(1, email);
            ResultSet rs =  pstmt.executeQuery();


            if(rs.next()){
              User us = new User(rs.getLong("userID"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"));
                conn.close();
                return getRealRole(us);
            }
            conn.close();
            return null;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
           return  null;
        }

    }

    public UserHistory getUserHistory(User u){
        return null;
    }


    public User getRealRole(User user){
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn
                    .prepareStatement("select * from owners left join hostsummary on owners.hostId = hostsummary.hostid where owners.hostid = ?;");
            long uid = user.getUserID();
            pstmt.setLong(1, uid);
            ResultSet rs =  pstmt.executeQuery();

            if(rs.next()){
                User us = (new Owner(user)).fillOwnerPart(rs.getString("host_location"),rs.getString("host_picture_url"),rs.getInt("host_listings_count"),rs.getString("phone"),rs.getString("comments"));
                conn.close();
                rs.close();
                return us;
            }else{
                conn.close();
                rs.close();
                return user;
            }

        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return null;
        }

    }

    public void updateUser( User usr,boolean firstOwner){
        try{
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            pstmt = conn.prepareStatement("update users set firstName = ? , lastName = ? where Userid = ?;");
            pstmt.setString(1, usr.getFirstName());
            pstmt.setString(2, usr.getLastName());
            pstmt.setLong(3, usr.getUserID());
            pstmt.executeUpdate();

            if(!firstOwner) {
                pstmt = conn.prepareStatement("update owners set name = ? , host_location = ?, host_picture_url = ? ,host_listings_count = ?, phone = ?  where hostId = ?;");
                pstmt.setLong(6,usr.getUserID());

                pstmt2 = conn.prepareStatement("update hostsummary set comments = ? where hostid = ?;");
            }
            else{
                pstmt = conn.prepareStatement("insert into owners (name  , host_location ,host_picture_url,host_listings_count , phone,hostid)  values (?,?,?,?,?,?) ;");
                pstmt2 = conn.prepareStatement("insert into hostsummary  (comments , hostid) values (?,?);");
            }
            if(usr.isOwner()){
                Owner mm = (Owner)usr;
                pstmt.setString(1,usr.getFirstName());
                pstmt.setString(2,mm.getHost_location());
                pstmt.setString(3,mm.getHost_picture_url());
                pstmt.setInt(4,mm.getHost_listings_count());
                pstmt.setString(5,mm.getPhone());
                pstmt.setLong(6,mm.getUserID());

                pstmt2.setString(1,mm.getDescription());
                pstmt2.setLong(2,mm.getUserID());


                pstmt.execute();
                pstmt2.execute();
            }



        }catch (SQLException e){
            System.err.println(e.toString());
        }
    }
}
