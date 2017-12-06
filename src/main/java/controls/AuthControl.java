package controls;

import modules.User;
import DB.*;
public class AuthControl {

    public User login(String email, String pass){

        return new AuthDB().login(email,pass);
    }
    //-3 user already exists

    /*
-4 password not match
     */
    public String signup(User us){
        if(!us.getPassWord().equals(us.getPassWordConfirm())){
            return "The password not match!";
        }
        long type = new AuthDB().signup(us);
        if( type <1 ){//error
            if(type ==-3){
               return "Email already exists!";
            }else{
                return "Server encountered an error plz try again!";
            }
        }else{
            return us.getFirstName()+"you successfully registerd please login!";
        }
    }

    /*  -2 need to fill all
    -1 not match
    *  1 success
    *  0 datbase problem
    *
    */
    public int changePassWord(User u,String pass, String cpass){
        if(pass==null||cpass==null){
            return -2;
        }
            if(pass.equals(cpass)){
                return new AuthDB().changePassWorld(u,pass);
            }else{
                return  -1;
            }
    }


}
