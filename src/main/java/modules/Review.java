package modules;

public class Review{
    String reviwerName;
    String review;
    long reveiwerId;
    long houseId;

    public String getReviwerName() {
        return reviwerName;
    }

    public String getReview() {
        return review;
    }

    public long getReveiwerId() {
        return reveiwerId;
    }

    public long getHouseId() {
        return houseId;
    }

    public Review( String review,String reviwerName) {
        this.reviwerName = reviwerName;
        this.review = review;
    }

    public Review(String review, long reveiwerId, long houseId) {

        this.review = review;
        this.reveiwerId = reveiwerId;
        this.houseId = houseId;
    }

    public static Review forStore(String review, long reveiwerId, long houseId){
        return new Review(review,reveiwerId,houseId);
    }
    public static Review forShow(String review,String name){
        return new Review(review,name);
    }


}
