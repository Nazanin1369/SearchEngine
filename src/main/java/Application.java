/**
 * Created by nazanin on 4/16/15.
 */

import crawler.Crawler;
import crawler.MyCrawler;
import crawler.MyCrawlerManager;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;


import static org.elasticsearch.node.NodeBuilder.nodeBuilder;


public class Application {

    public static void main(String[] args) throws Exception{
        //new Crawler("https://nodejs.org/", 64).startCrawling();

        //Crawler4j
        MyCrawlerManager manager = new MyCrawlerManager();
        manager.instantiateController().start(MyCrawler.class, manager.numberOfCrawlers);
    }
}
