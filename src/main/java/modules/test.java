import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String args[]){
        String s = "  beach  ,    downtown asd adas  , hospital  ";
        String[] lst = s.split(",");
        ArrayList<String> rs = new ArrayList<String>();
        for (String i:lst){
            i = i.trim();
            if (i.contains(" ")){
                String[] sublst = i.split(" ");
                for(String j:sublst){
                    rs.add(j);
                }
            }else{
                rs.add(i);
            }
        }
        for(String m:rs){
            System.out.println(m);
        }
    }
}
