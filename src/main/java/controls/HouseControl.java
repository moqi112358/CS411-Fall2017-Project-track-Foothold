package controls;
import java.util.ArrayList;
import java.util.List;
import modules.*;
import DB.*;

public class HouseControl {

   public  List <HouseBean> getHouseList(User user){
         return new HouseDB().getHouseBeans(user);
   }

   public HouseBean getHouseBean(Long l){
       return  new HouseDB().getHouseBean(l);
   }

   public List<Review> getReviewByhouseId(long id){
        return  new HouseDB().getReview(id);
   }
   public boolean storeReviewByid(Review rc){
       return  new HouseDB().storeReview(rc);
   }


}
