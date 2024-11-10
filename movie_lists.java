import java.util.ArrayList;

public class movie_lists implements Comparable<movie_lists> {
    private String listName;
    private ArrayList<people> peopleList;

    public movie_lists(String pName) {
        this.listName = pName;
        this.peopleList = new ArrayList<>();
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ArrayList<people> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(ArrayList<people> peopleList) {
        this.peopleList = peopleList;
    }

    @Override
    public int compareTo(movie_lists o) {
        return this.listName.compareTo(o.getListName());
    }
}
