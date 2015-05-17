package elastic;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nazanin on 5/11/15.
 */
public class ElasticClient {
    private static final Logger logger = LoggerFactory.getLogger(ElasticClient.class);
    private String searchServerClusterName = "searchLibrary";
    private Client client;

    public Client getClient(){
        if(client == null){
            client = createElasticClient();
        }
        return client;
    };

    protected Client createElasticClient(){
        if(client == null){
            logger.info("Creating elastic search client...");
            try{
                Settings settings = ImmutableSettings.settingsBuilder().put(searchServerClusterName, false).build();
                Node node = NodeBuilder.nodeBuilder().settings(settings).node();
                client = node.client();

            }catch(Exception ex)
            {
                logger.error("Error occured while creating search client!", ex);
            }
        }
        return client;
    }

}
