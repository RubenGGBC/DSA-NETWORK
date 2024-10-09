public class Relationships implements Comparable<Relationships>{
    ;
    private String friend_orig;
    private String friend_dest;

    public Relationships(String pOrig, String pDest) {
        this.friend_orig = pOrig;
        this.friend_dest = pDest;
    }

    public String getFriend_dest() {
        return friend_dest;
    }

    public void setFriend_dest(String friend_dest) {
        this.friend_dest = friend_dest;
    }

    public String getFriend_orig() {
        return friend_orig;
    }

    public void setFriend_orig(String friend_orig) {
        this.friend_orig = friend_orig;
    }

    @Override
    public int compareTo(Relationships o) {
        return 0;
    }
}
