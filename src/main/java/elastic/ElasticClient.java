package elastic;

import model.ElasticSearchReservedWords;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
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
    }
    protected Client createElasticClient(){
        if(client == null){
            logger.info("Creating elastic search client...");
            try{
                Settings settings = ImmutableSettings.settingsBuilder().put(
                        ElasticSearchReservedWords.CLUSTER_NAME.getText(), searchServerClusterName).build();
                TransportClient transportClient = new TransportClient(settings);
                transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
                if(transportClient.connectedNodes().size() == 0)
                {
                    logger.error("There are no active nodes available for the transport, it will be automatically added once nodes are live!");
                }
                client = transportClient;

            }catch(Exception ex)
            {
                logger.error("Error occured while creating search client!", ex);
            }
        }
        return client;
    }

    public void addNewNode(String name)
    {
        TransportClient transportClient = (TransportClient) client;
        transportClient.addTransportAddress(new InetSocketTransportAddress(name, 9300));
    }

    public void removeNode(String name)
    {
        TransportClient transportClient = (TransportClient) client;
        transportClient.removeTransportAddress(new InetSocketTransportAddress(name, 9300));
    }

}
