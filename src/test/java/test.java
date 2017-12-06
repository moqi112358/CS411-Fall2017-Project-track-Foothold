import junit.framework.TestCase;
import DB.AuthDB;
public class test  extends TestCase {
    public void testhello(){
        AuthDB ad = new AuthDB();
        String m = "12345";
        System.out.println(ad.MD5(m));
        System.out.println(ad.MD5(m));
        assertEquals(1,1);
    }

}
