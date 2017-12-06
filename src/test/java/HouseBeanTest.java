import junit.framework.TestCase;
import modules.*;

import java.lang.reflect.Field;

public class HouseBeanTest extends TestCase {
    HouseBean hb = new HouseBean();

    Field f[] = HouseBean.class.getDeclaredFields();

    public void testField(){
        for(int i =0;i<f.length;i++){

            System.out.println(f[i].getName());
        }
    }
}
