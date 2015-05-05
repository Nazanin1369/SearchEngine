/**
 * Created by nazanin on 4/16/15.
 */

import crawler.Crawler;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;


import static org.elasticsearch.node.NodeBuilder.nodeBuilder;


public class Application {

    public static void main(String[] args) throws Exception{
        new Crawler("http://online.cs.mum.edu/", 64).startCrawling();
    }

    public static void elasticSearch(){
        Node node = nodeBuilder().clusterName("nazaninEngine").node();
        Client client = node.client();
    }
}
