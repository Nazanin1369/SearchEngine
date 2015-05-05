package crawler;

/**
 * Created by nazanin on 5/4/15.
 */
public interface LinkManager {
    int getVisitedLinksCount();
    boolean isVisited(String link);
    void makeVisited(String link);
}
