package crawler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by nazanin on 5/4/15.
 */
public class Crawler implements LinkManager {

    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
    private String url;
    private ForkJoinPool mainPool;

    public Crawler(String startingURL, int maxThreads) {
        this.url = startingURL;
        mainPool = new ForkJoinPool(maxThreads);
    }

    public void startCrawling() throws Exception {
        mainPool.invoke(new LinkExtractorWorker(this.url, this));
    }

    @Override
    public int getVisitedLinksCount() {
        return visitedLinks.size();
    }

    @Override
    public boolean isVisited(String link) {
        return visitedLinks.contains(link);
    }

    @Override
    public void makeVisited(String link) {
        visitedLinks.add(link);
    }
}




