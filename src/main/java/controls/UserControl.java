package controls;
import java.util.ArrayList;
import java.util.List;
import modules.*;
import DB.*;

public class UserControl {
    private UserDB userDB = new UserDB();

    public User getUserInfo(Long uid){
        return userDB.getUser(uid);
    }

    public Owner getOwnerInfo(Long uid){
        return (Owner) userDB.getUser(uid);
    }

    public HouseBean getHouseBean(Long l){
        return  new HouseDB().getHouseBean(l);
    }
}
