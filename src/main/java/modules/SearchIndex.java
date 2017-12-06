package modules;

public class SearchIndex {
    private int index;
    private String searchContent;

    public SearchIndex(int index, String searchContent){
        this.index = index;
        this.searchContent = searchContent;
    }

    public int getIndex(){
        return this.index;
    }

    public String getSearchContent(){
        return this.searchContent;
    }

}
