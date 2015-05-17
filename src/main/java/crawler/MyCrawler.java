package crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import elastic.ElasticClient;
import elastic.ElasticIndexer;
import model.PageInfo;
import model.UrlData;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by nazanin on 5/5/15.
 * Web Crawling using Crawler4j
 */
public class MyCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp3|zip|gz))$");
    Client elasticClient;

    @Override
    public void onStart() {
        elasticClient = (Client) myController.getCustomData();
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://web.mit.edu/");
    }
    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page){
        HashMap<String, model.PageInfo> urlMap = new HashMap<String, model.PageInfo>();
        String url = page.getWebURL().getURL();
        int docid = page.getWebURL().getDocid();
        System.out.println("docid: " + docid);
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            urlMap.put(url, new model.PageInfo(docid, text, html, links, new Date()));
            //System.out.println("Text : " + text);
            System.out.println("Text length: " + text.length());
            System.out.println("Html length: " + html.length());
            System.out.println("Number of outgoing links: " + links.size());

            //Give it to the Elastic search indexer
            Thread t = new Thread(new ElasticIndexer(urlMap, elasticClient));
            t.start();
        }
    }

}
