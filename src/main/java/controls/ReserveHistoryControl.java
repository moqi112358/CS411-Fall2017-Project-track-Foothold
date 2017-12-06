package controls;
import java.text.ParseException;
import java.util.Date;
import DB.*;
import modules.*;
import java.util.ArrayList;
import java.util.List;
public class ReserveHistoryControl {
  public  List<Reserve> getUserResves(User user){
        ReserveDB rsd = new ReserveDB();
        return  rsd.getUserReservationList(user);
    }
}
