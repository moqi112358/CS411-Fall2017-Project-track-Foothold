package DB;
import com.sun.org.apache.regexp.internal.RE;
import exception.MyLog;
import modules.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class ReserveDB extends BasicDB{
    private static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");;
        public String getAllDatesAfter(Date now,Long houseId) {
            Connection conn = null;
            PreparedStatement ps = null;
            List<Date> dateList = new ArrayList<Date>();
            try {
                conn = getConn();
                //ps = conn.prepareStatement("SELECT Book_date, endDate FROM rent WHERE houseId = ? and Book_date > enddate order by Book_date;");
                ps = conn.prepareStatement("SELECT Book_date, endDate FROM rentDates WHERE houseId = ?  and ( Book_date >= ? or endDate >= ? ) order by Book_date ;");
                ps.setLong(1,houseId);
                ps.setDate(2,convertToSql(now));
                ps.setDate(3,convertToSql(now));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Date start = rs.getDate("Book_Date");
                    Date end = rs.getDate("endDate");
                    if (end == null) {
                        end = start;
                    }
                    dateList.add(start);
                    dateList.add(end);
                }
                rs.close();
                ps.close();
                conn.close();
                return getDateString(dateList);
            } catch (SQLException e) {
                MyLog.write(e.toString());
                System.err.println(e.toString());
                return null;

            }
        }

        public java.sql.Date convertToSql(Date date){
            return new java.sql.Date(date.getTime());
        }
        public String getDateString(List<Date> dates){
            int len = dates.size();
            StringBuffer sbg = new StringBuffer();
            sbg.append("[");
            Calendar today = Calendar.getInstance();
            for(int i =0 ;i < len;i+=2){
                Date start = dates.get(i);
                Date end = dates.get(i+1);
                Date temp = start;
                if(i==0) {
                    sbg.append("\"" + sdf.format(start) + "\"");
                }else {
                    sbg.append(",\"" + sdf.format(start) + "\"");
                }
                if(start.getTime()==end.getTime()){
                   continue;
                }
                while(temp.getTime() < end.getTime()){
                    today.setTime(temp);
                    today.add(Calendar.DAY_OF_YEAR, 1);
                    temp = today.getTime();
                    sbg.append(",\""+sdf.format(temp)+"\"");
                }
                sbg.append(",\""+sdf.format(start)+"\"");
            }
            sbg.append("]");
            return sbg.toString();

        }

        public boolean storeReservation(Reserve rsv){
            Connection conn;
            PreparedStatement ps = null;
            try {
                conn = getConn();
                ps = conn.prepareStatement("insert into rent (Book_date, endDate ,HouseId, price,userId) values (?,?,?,?,?);");
                ps.setDate(1,new java.sql.Date(rsv.getStart().getTime()));
                ps.setDate(2,new java.sql.Date(rsv.getEnd().getTime()));
                ps.setLong(3,rsv.getHousId());
                ps.setDouble(4,rsv.getPrice());
                ps.setLong(5,rsv.getUserId());
                ps.executeUpdate();
                ps.close();
                conn.close();
                return true;

            } catch (SQLException e) {
                MyLog.write(e.toString());
                System.err.println(e.toString());
                return false;

            }
        }

        public List<Reserve> getUserReservationList(User usr){
            Connection conn =null;
            PreparedStatement ps = null;
            List<Reserve> res = new ArrayList<Reserve>();
            try {

                conn = getConn();
                ps = conn.prepareStatement("select A.price, A.book_date, A.endDate, A.houseid, A.userid, houses.name, houses.street, rentId from (select rentid, rent.price as price, book_date, endDate,  rent.houseid, rent.userid  from rent where userid =? ) as A join houses on A.houseid = houses.HouseID ;");
                ps.setLong(1,usr.getUserID());
                ResultSet rs =ps.executeQuery();
                while(rs.next()) {
                    Date start = rs.getDate("Book_date");
                    Date end = rs.getDate("endDate");
                    long id = rs.getLong("HouseId");
                    double price = rs.getDouble("price");
                    long uid = rs.getLong("userId");
                    long rid = rs.getLong("rentId");
                    String name = rs.getString("name");
                    String street = rs.getString("street");
                    Reserve rsvs = Reserve.forShow(uid, start, end, price, id, name, street,rid);
                    res.add(rsvs);
                }
                ps.close();
                conn.close();
                return res;

            } catch (SQLException e) {
                MyLog.write(e.toString());
                System.err.println(e.toString());
                return null;

            }
        }

    }

