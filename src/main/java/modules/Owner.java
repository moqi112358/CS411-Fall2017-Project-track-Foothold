package modules;

import java.util.List;
import java.util.ArrayList;
public class Owner extends  User{
    private String host_location;
    private String host_picture_url = "/pic/undifined.jpg";
    private int host_listings_count;
    private String phone;
    private String description;
    public Owner(long userID, String firstName, String lastName, String userEmail) {
        super(userID, firstName, lastName, userEmail);
    }

    public Owner(User user){
        super(user.getUserID(),user.firstName,user.lastName,user.email);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {

        return description;
    }

    public User fillOwnerPart(String host_location, String host_location_url, int host_listings_count, String phone, String description) {
        this.host_location = host_location;
        this.host_picture_url  = host_location_url;
        this.host_listings_count = host_listings_count;
        this.phone = phone;
        this.description = description;

        return this;
    }

    public void setHost_location(String host_location) {
        this.host_location = host_location;
    }

    public void setHost_picture_url(String host_location_url) {
        this.host_picture_url  = host_location_url;
    }

    public void setHost_listings_count(int host_listings_count) {
        this.host_listings_count = host_listings_count;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHost_location() {

        return host_location;
    }

    public String getHost_picture_url() {
        return host_picture_url ;
    }

    public int getHost_listings_count() {
        return host_listings_count;
    }

    public String getPhone() {
        return phone;
    }




}
