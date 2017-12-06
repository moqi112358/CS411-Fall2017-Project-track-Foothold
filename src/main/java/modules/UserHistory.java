package modules;

import java.util.Date;

public class UserHistory {
    Date startDate;
    Date endDate;
    Date reserveDate;
    long houseId;
    String houseName;
    double price;

    public UserHistory(Date startDate, Date endDate, Date reserveDate, long houseId, String houseName,double price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reserveDate = reserveDate;
        this.houseId = houseId;
        this.houseName = houseName;
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public long getHouseId() {
        return houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public double getPrice() {
        return price;
    }
}
