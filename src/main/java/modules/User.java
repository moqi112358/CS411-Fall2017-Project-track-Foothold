package modules;


public class User {
    protected long userID;
    protected String firstName;
    protected String lastName;
    protected String email;

    protected String passWord;

    public String getPassWord() {
        return passWord;
    }

    public String getPassWordConfirm() {
        return passWordConfirm;
    }

    private String passWordConfirm;

 // for databse
    public User(long userID, String firstName, String lastName, String userEmail) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = userEmail;

    }
    //for jsp
    public User(String firstName, String lastName,String email,String passWord,String passWordConfirm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passWord = passWord;
        this.passWordConfirm = passWordConfirm;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserEmail() {
        return email;
    }



    public long getUserID() {

        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserEmail(String userEmail) {
        this.email = userEmail;
    }
    public boolean isOwner(){
        return (this instanceof Owner);
    }



}
