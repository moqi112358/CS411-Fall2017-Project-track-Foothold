package DB;

import com.sun.org.apache.regexp.internal.RE;
import exception.MyLog;
import modules.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseDB extends BasicDB {

    public boolean uploadHouse(HouseBean hb) throws SQLException{
        Connection conn = getConn();
        try {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = null;
            pstmt = hb.getPreParedStatement(pstmt,conn);
            pstmt.executeUpdate();
            Long id =getLastInsert(conn);
            hb.setHouseId(id);
            if(!uploadPictures(hb,conn)){
                throw new SQLException("error");
            }
            conn.commit();
            conn.close();
           return  true;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            conn.rollback();
            return false;
        }
    }
    public boolean uploadPictures(HouseBean hb,Connection conn) {
        try {

            for (int i = 0; i < hb.getNames().size(); i++) {

                PreparedStatement pstmt = null;
                pstmt = conn.prepareStatement("insert into  housePictures (houseid,picture_url,picture_origin_name) VALUES (?,?,?);");
                pstmt.setLong(1, hb.getHouseId());
                pstmt.setString(2, hb.getUrls().get(i));
                pstmt.setString(3, hb.getNames().get(i));
                pstmt.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            MyLog.write(e.toString());
            System.err.print(e.toString());
            return false;

        }
    }

    public HouseBean getHouseBean(long houseId){
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("select * from houses where houseid = ?");
            pstmt.setLong(1, houseId);
            ResultSet rs =  pstmt.executeQuery();
            HouseBean tempbe = null;
            if(rs.next()){
                 tempbe  = new HouseBean(rs);
                setPictures(tempbe) ;
            }

            conn.close();
            return tempbe;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return  null;
        }
    }
    public List<HouseBean> getHouseBeans(User user){
        List<HouseBean> hbs = new ArrayList<HouseBean>();
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("select * from houses where Userid = ?");
            pstmt.setLong(1, user.getUserID());
            ResultSet rs =  pstmt.executeQuery();

            while(rs.next()){
               HouseBean tempbe = new HouseBean(rs);
               setPictures(tempbe) ;
                hbs.add(tempbe);
            }
            conn.close();
            return hbs;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return  null;
        }
    }

    public boolean setPictures(HouseBean hb){
        try {

            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("select * from housePictures where HouseID = ?");
            pstmt.setLong(1, hb.getHouseId());
            ResultSet rs =  pstmt.executeQuery();

            while(rs.next()){
                String url = rs.getString("picture_url");
                String name = rs.getString("picture_origin_name");
                hb.getUrls().add(url);
                hb.getNames().add(name);
            }
            conn.close();
            return true;
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return  false;
        }
    }

    public boolean deletePicture(List<String> pics,HouseBean hb){
        try {
            Connection conn = getConn();
            PreparedStatement pstmt = null;
            if(pics!=null)
            for(String s : pics) {

                pstmt = conn.prepareStatement("delete  from housePictures where HouseID = ? and picture_url = ?");
                pstmt.setLong(1, hb.getHouseId());
                pstmt.setString(2, s);
               pstmt.executeUpdate();
            }
            conn.close();
            return  true;
        }
    catch (SQLException e){
        MyLog.write(e.toString());
        System.err.println(e.toString());
        return  false;
    }

    }

    public boolean  updateHouse(HouseBean hb,List<String> pics) throws SQLException {
        Connection conn = getConn();
        try {

            PreparedStatement pstmt = null;

            pstmt = hb.getPreParedUpdate(pstmt,conn);
            pstmt.executeUpdate();
            uploadPictures(hb,conn);
            conn.close();
            return   deletePicture(pics,hb);
        }catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return false;
        }




    }
    public static long getLastInsert(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        long result = rs.getLong(1);
        rs.close();
        return result;


    }
    public List<House> getSearchResult(String searchContent, int index){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM houses WHERE Street LIKE '%");
            sql.append(searchContent);
            sql.append("%' LIMIT 30 OFFSET ");
            sql.append(Integer.toString(index));
            System.out.println(sql.toString());

            conn = getConn();
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            List<House> houseList = new ArrayList<House>();
            while (rs.next()) {
                long houseId = rs.getLong("HouseID");
                String housePicLink = rs.getString("Thumbnail_url");
                String name = rs.getString("Name");
                String street = rs.getString("Street");

                House h = new House(houseId, housePicLink, street, name);
                houseList.add(h);
            }
            rs.close();
            ps.close();
            conn.close();
            System.out.println("house list done");
            return houseList;
        } catch (SQLException e) {
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return null;
        }
    }

    public int getSearchResultCount(String searchContent){
        Connection conn = null;
        PreparedStatement ps = null;
        System.out.println("start counting");
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM houses WHERE Street LIKE '%");
            sql.append(searchContent);
            sql.append("%'");

            conn = getConn();
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            List<House> houseList = new ArrayList<House>();
            int count = 0;
            while (rs.next()) {
                count+=1;
            }
            rs.close();
            ps.close();
            conn.close();
            System.out.println("counting done");
            return count;
        } catch (SQLException e) {
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return -1;
        }
    }

    public List<House> getSearchResultWithTags(String searchContent, int index, ArrayList<String> tags){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM houses,tags WHERE tags.houseID=houses.houseID ");
            sql.append("and tags.houseID in (SELECT houseID FROM houses WHERE Street LIKE '%");
            sql.append(searchContent);
            sql.append("%') ");

            int tagCount = tags.size();
            for(String tag:tags){
                sql.append("AND tags.tag like '%");
                sql.append(tag);
                sql.append("%' ");
            }
            sql.append("LIMIT 30 OFFSET ");
            sql.append(Integer.toString(index));
            System.out.println(sql.toString());

            conn = getConn();
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            List<House> houseList = new ArrayList<House>();
            while (rs.next()) {
                long houseId = rs.getLong("HouseID");
                String housePicLink = rs.getString("Thumbnail_url");
                String name = rs.getString("Name");
                String street = rs.getString("Street");

                House h = new House(houseId, housePicLink, street, name);
                houseList.add(h);
            }
            rs.close();
            ps.close();
            conn.close();
            System.out.println("house list done");
            return houseList;
        } catch (SQLException e) {
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return null;
        }
    }

    public long getSearchResultCountWithTags(String searchContent, ArrayList<String> tags){
        Connection conn = null;
        PreparedStatement ps = null;
        System.out.println("start counting");
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) as Count FROM houses,tags WHERE tags.houseID=houses.houseID ");
            sql.append("and tags.houseID in (SELECT houseID FROM houses WHERE Street LIKE '%");
            sql.append(searchContent);
            sql.append("%') ");

            int tagCount = tags.size();
            for(String tag:tags){
                sql.append("AND tags.tag like '%");
                sql.append(tag);
                sql.append("%' ");
            }

            conn = getConn();
            ps = conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            List<House> houseList = new ArrayList<House>();
            long count = 0;
            while (rs.next()) {
                count = rs.getLong("Count");
            }
            rs.close();
            ps.close();
            conn.close();
            System.out.println("counting done");
            return count;
        } catch (SQLException e) {
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return -1;
        }
    }

    public List<Review> getReview(long id){
        List<Review> rss = new ArrayList<Review>() ;
        try {


            Connection conn = getConn();
            PreparedStatement pstmt = null;
                         pstmt = conn.prepareStatement("select comments, houseID, firstName  from review join users on review.ReviewerId = users.UserID where HouseID = ? ORDER by Review_id DESC limit 25;");
                    pstmt.setLong(1, id);

                ResultSet rs= pstmt.executeQuery();
                while(rs.next()){
                    Review rv = Review.forShow(rs.getString("comments"),rs.getString("firstName"));
                    rss.add(rv);
                }

            conn.close();
            return  rss;
        }
        catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return  rss;
        }
    }

    public boolean storeReview(Review rv){
        try {


            Connection conn = getConn();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("insert into review (comments,houseid,reviewerid) VALUES (?,?,?)");
            pstmt.setString(1,rv.getReview());
            pstmt.setLong(2, rv.getHouseId());
            pstmt.setLong(3, rv.getReveiwerId());
            pstmt.executeUpdate();
            conn.close();
            return  true;
        }
        catch (SQLException e){
            MyLog.write(e.toString());
            System.err.println(e.toString());
            return  false;
        }
    }
}
