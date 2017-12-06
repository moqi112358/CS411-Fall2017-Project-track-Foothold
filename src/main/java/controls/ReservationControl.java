package controls;

import java.text.ParseException;
import java.util.Date;
import DB.*;
import modules.*;
import java.util.ArrayList;
import java.util.List;


public class ReservationControl {
private static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");


    public boolean reserve(long houseId, long userID, String date){
    String dates[] = date.split("-");
    if(dates.length!=2){
        return false;
    }
        Date start, end;
    try {
       start = sdf.parse(dates[0]);
       end = sdf.parse(dates[1]);
    }catch (ParseException e){
        return false;
    }
    ReserveDB rsdb = new ReserveDB();
    HouseDB hdb = new HouseDB();
    Reserve rsv = Reserve.forStore(userID,hdb.getHouseBean(houseId).getPrice(),start,end,houseId);
    if(rsdb.storeReservation(rsv)){
        return true;
    }
    return  false;
    }

    public String getDates(long houseId){
        Date now = new Date();
        ReserveDB rsdb = new ReserveDB();
        return rsdb.getAllDatesAfter(now, houseId);

    }
    public String getminDate(){
        Date now = new Date();
        return sdf.format(now);
    }
    public List<Reserve> getUserReserves(User usr){
        ReserveDB rsdb = new ReserveDB();
        return rsdb.getUserReservationList(usr);
    }

}
