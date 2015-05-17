/**
 * Created by nazanin on 4/16/15.
 */

import crawler.MyCrawler;
import crawler.MyCrawlerManager;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import elastic.ElasticClient;
import elastic.ElasticIndexer;
import org.elasticsearch.client.Client;


public class Application {

    public static void main(String[] args) throws Exception{
        //Create ElasticSearch client
        Client client = new ElasticClient().getClient();
        //Crawler4j: Start Crawling and index it into Cluster
        MyCrawlerManager manager = new MyCrawlerManager();
        CrawlController controller = manager.instantiateController();
        controller.start(MyCrawler.class, manager.numberOfCrawlers);
        controller.waitUntilFinish();
        System.out.print("Crawling is done!");

    }
}
