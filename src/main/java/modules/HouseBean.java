package modules;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;
public class HouseBean {
    private String
    name;
    private String space;
    private String description;
    private String notes;
    private String transit;
    private String access;
    private String interaction;
    private String house_rules;
    private String street;

    public String getAccommodates() {
        return accommodates;
    }

    public void setAccommodates(String accommodates) {
        this.accommodates = accommodates;
    }

    private String city;
    private String state;
    private String zipcode;
    private String property_type;
    private String room_type;
    private String bathrooms;
    private String bedrooms;
    private String beds;
    private String price;
    private String security_deposites;
    private String accommodates;
    private String thumbnail_url;
    long houseId;
    long userId;
  String[] houseTypeMap = {"Private room","Entire home/apt","Shared room"};
    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public long getHouseId() {

        return houseId;
    }

    public HouseBean(){

    }
    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getThumbnail_url() {

        return thumbnail_url;
    }

    User user;
    List <String> urls = new ArrayList<String>();
    List <String> names = new ArrayList<String>();;
    public HouseBean(Map request,List<String>urls,List <String> names,String thum,User user){
        this.urls = urls;
        this.names = names;
        this.user = user;


        try {
            Field f[] = HouseBean.class.getDeclaredFields();
            for (int i = 0; i < f.length; i++) {

                String type = f[i].getGenericType().toString();
                if (type.equals("class java.lang.String")) {
                    String nn = f[i].getName();
                    String nm = (String)request.get(nn);
                    if(nn!=null) {
                        f[i].set(this, nm);
                    }
                }
            }
            this.thumbnail_url = thum;
        }catch (IllegalAccessException e){

        }

    }
    public HouseBean( ResultSet rs){

        this.user = user;
        try {
            Field f[] = HouseBean.class.getDeclaredFields();
            for (int i = 0; i < f.length; i++) {
                String type = f[i].getGenericType().toString();
                if (type.equals("class java.lang.String")) {
                    String tn = f[i].getName();
                    String tem = rs.getString(tn);
                    f[i].set(this, tem);
                }
            }
            this.houseId = (long) rs.getLong("houseid");
            this.userId = (long) rs.getLong("userid");

        }catch (IllegalAccessException e){

        }catch(SQLException ex){
            System.out.println(ex.toString());
        }

    }

 public PreparedStatement getPreParedStatement(PreparedStatement ps,Connection conn){
        String m = "insert into houses ( userid, ";
        String e=" values (";
        List <String> values = new ArrayList<String>();
     try {
         Field f[] = HouseBean.class.getDeclaredFields();
         for (int i = 0; i < f.length; i++) {
             String type = f[i].getGenericType().toString();

             if (type.equals("class java.lang.String")){
                 if(i!=0){
                     m+=",";
                     e+=",";
                 }
                m+=f[i].getName();
                e+="?";
                 values.add((String)f[i].get(this));
             }

         }
         m+=")";
         e+=",?);";
         m = m + e;
         ps = conn.prepareStatement(m);
         ps.setLong(1, user.getUserID());

         int i=2;
         for(String s : values){
             ps.setString(i,s);
             i++;
         }
         return ps;

     }catch (IllegalAccessException ex1){

     }catch(SQLException ex2){
        System.err.println(ex2);
     }
     return null;
 }
    public HouseBean(String name, String space, String description, String notes, String transit, String access, String interaction, String house_rules, String street, String city, String state, String zipcode, String property_tpe, String room_type, String bathrooms, String bedrooms, String beds, String price, String security_deposites, String guests_includes, User user) {
        this.name = name;
        this.space = space;
        this.description = description;
        this.notes = notes;
        this.transit = transit;
        this.access = access;
        this.interaction = interaction;
        this.house_rules = house_rules;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.property_type = property_tpe;
        this.room_type = room_type;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.beds = beds;
        this.price = price;
        this.security_deposites = security_deposites;
        this.accommodates = guests_includes;
        this.user = user;
    }
 public  PreparedStatement getPreParedUpdate(PreparedStatement ps,Connection conn){
        String m = "update   houses set userid = ? ,";

        List <String> values = new ArrayList<String>();
        try {
            Field f[] = HouseBean.class.getDeclaredFields();
            for (int i = 0; i < f.length; i++) {
                String type = f[i].getGenericType().toString();

                if (type.equals("class java.lang.String")){
                    if(i!=0){
                        m+=",";
                    }
                    m+=( f[i].getName()+ " = ? ") ;
                    values.add((String)f[i].get(this));
                }

            }
            m+=" where houseid = ? ;" ;


            ps = conn.prepareStatement(m);
            ps.setLong(1, user.getUserID());
            int i=2;
            for(String s : values){
                ps.setString(i,s);
                i++;
            }
            ps.setLong(i, houseId);

            return ps;

        }catch (IllegalAccessException ex1){

        }catch(SQLException ex2){
            System.err.println(ex2);
        }
        return null;
    }
 public String checked(int i){
     try {
         if (i == Integer.parseInt(this.room_type)) {
             return "checked = \"checked\"";
         } else {
             return "";
         }
     }catch (Exception e){
         return "";
     }
 }
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {

        return names;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTransit(String transit) {
        this.transit = transit;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public void setHouse_rules(String house_rules) {
        this.house_rules = house_rules;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setProperty_tpe(String property_type) {
        this.property_type = property_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSecurity_deposites(String security_deposites) {
        this.security_deposites = security_deposites;
    }

    public void setGuests_includes(String accommodates) {
        this.accommodates = accommodates;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {

        return name;
    }

    public String getSpace() {
        return space;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public String getTransit() {
        return transit;
    }

    public String getAccess() {
        return access;
    }

    public String getInteraction() {
        return interaction;
    }

    public String getHouse_rules() {
        return house_rules;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getProperty_type() {
        return property_type;
    }

    public String getRoom_type() {
     if(room_type==null){
         return "";
     }
     try {
         return houseTypeMap[Integer.parseInt(room_type)];
     }catch (Exception e){
            return room_type;
        }
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public String getBeds() {
        return beds;
    }

    public double getPrice() {
     try{
        String p = this.price.replace("$","");
        return Double.parseDouble(p);
     }catch(Exception e){
         return 0.0;
     }
    }

    public String getSecurity_deposites() {
        return security_deposites;
    }

    public String getGuests_includes() {
        return accommodates;
    }

    public User getUser() {
        return user;
    }

    public long getOwnerID() {
        return userId;
    }
}
