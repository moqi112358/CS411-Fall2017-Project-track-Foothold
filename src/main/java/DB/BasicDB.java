package DB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import com.mysql.jdbc.Driver;
import exception.MyLog;

public class BasicDB {
    //static String driver = "com.mysql.jdbc.Driver";
    static String host = "localhost:3306/test";
    static String usr = "root";
    static String pass = "";




    public  Connection getConn() throws SQLException{
        try {
            return ((DataSource) (((Context) new InitialContext().lookup("java:comp/env")))
                    .lookup("jdbc/fh")).getConnection();
        } catch (NamingException e) {
            MyLog.write(e.getLocalizedMessage());
            throw new SQLException(("Context Lookup Naming Exception: " + e.getMessage()));
        }
    }


}
