package controls;
import DB.*;
import modules.*;

import java.util.ArrayList;
import java.util.List;

public class SearchControl {
    private HouseDB houseDB;

    public SearchControl(){
        houseDB = new HouseDB();
    }

    public List<House> getSearchResult(String searchContent, int index){
        return houseDB.getSearchResult(searchContent, index);
    }

    public int getSearchResultCount(String searchContent){
        return houseDB.getSearchResultCount(searchContent);
    }


    public List<House> getSearchResultWithTags(String searchContent, int index, String tags){
        String[] lst = tags.split(",");
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
        return houseDB.getSearchResultWithTags(searchContent, index, rs);
    }

    public long getSearchResultCountWithTags(String searchContent, String tags){
        String[] lst = tags.split(",");
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
        return houseDB.getSearchResultCountWithTags(searchContent, rs);
    }
}

