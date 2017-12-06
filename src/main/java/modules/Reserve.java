package modules;

import java.util.Date;

public class Reserve {
    Date start;
    Date end;
    double price;
    long reserveId;
    long userId;
    long housId;
    String houseName;
    String houseAddress;
    long rentId;

    public long getRentId() {
        return rentId;
    }

    public static Reserve forStore(long userId, double price, Date start, Date end, long housId){
            return new Reserve(start,end,price,userId,housId);
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public double getPrice() {
        return price;
    }

    public long getReserveId() {
        return reserveId;
    }

    public long getUserId() {
        return userId;
    }

    public long getHousId() {
        return housId;
    }

    public String getHouseName() {
        return houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public static Reserve forShow(long userId, Date start, Date end, double price, long housId, String houseName, String houseAddress,long rid){
        return new Reserve( userId, start,  end,  price,    housId,  houseName,  houseAddress,rid);
    }
    public Reserve(long userId,Date start, Date end, double price,   long housId, String houseName, String houseAddress,long rentid) {
        this.start = start;
        this.end = end;
        this.price = price;
        this.userId = userId;
        this.housId = housId;
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.rentId = rentid;
    }
    public Reserve(Date start, Date end, double price,long userId,long houseid) {
        this.start = start;
        this.end = end;
        this.price = price;
        this.userId = userId;
        this.housId = houseid;
    }

}
