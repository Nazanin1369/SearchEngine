package crawler;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by nazanin on 5/4/15.
 */
public class LinkExtractorWorker extends RecursiveAction {
    private String url;
    private LinkManager manager;
    /**
     * Used for statistics
     */
    private static final long t0 = System.nanoTime();

    public LinkExtractorWorker(String url, LinkManager manager) {
        this.url = url;
        this.manager = manager;
    }

    @Override
    public void compute() {
        if (!manager.isVisited(url)) {
            try {
                List<RecursiveAction> actions = new ArrayList<RecursiveAction>();
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

                for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);
                    if (!extracted.extractLink().isEmpty() && !manager.isVisited(extracted.extractLink()) && extracted.extractLink().contains("nodejs.org")) {
                        //System.out.println("extracted Link: " + extracted.extractLink());
                        actions.add(new LinkExtractorWorker(extracted.extractLink(), manager));
                    }
                }

                manager.makeVisited(url);

                if (manager.getVisitedLinksCount() == 5000) {
                    System.out.println("******Time for visit 5000 distinct links= " + (System.nanoTime() - t0));
                }
                if (manager.getVisitedLinksCount() == 10000) {
                    System.out.println("*****Time for visit 10000 distinct links= " + (System.nanoTime() - t0));
                }
                //invoke recursively
                invokeAll(actions);
            } catch (Exception e) {
                //ignore 404, unknown protocol or other server errors
            }
        }
    }
}
