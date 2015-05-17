package crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import elastic.ElasticClient;
import org.elasticsearch.client.Client;

/**
 * Created by nazanin on 5/5/15.
 */
public class MyCrawlerManager {
    String crawlStorageFolder = "data";
    public int numberOfCrawlers = 7;
    CrawlConfig config;
    PageFetcher pageFetcher;
    RobotstxtConfig robotstxtConfig;
    RobotstxtServer robotstxtServer;

    public MyCrawlerManager(){

        config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxPagesToFetch(10);
        pageFetcher = new PageFetcher(config);
        robotstxtConfig = new RobotstxtConfig();
        robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    }

    public CrawlController instantiateController() throws Exception{
        //Create ElasticSearch client
        Client client = new ElasticClient().getClient();
        System.out.print("Client created");
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        controller.addSeed("http://web.mit.edu/");
        controller.setCustomData(client);
        return  controller;
    }

    public void addSeeds(CrawlController controller, String[] urls) {
        for(String s : urls){
            controller.addSeed(s);
        }
    }

}
