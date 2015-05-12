package elastic;

import model.PageInfo;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by nazanin on 5/11/15.
 */
public class ElasticIndexer implements Runnable {

    HashMap<String, PageInfo> pages;
    Client client;

     public ElasticIndexer(HashMap<String, PageInfo> pages){
         this.pages = pages;
         //get elastic search client
         this.client = new elastic.ElasticClient().getClient();
    }
    public void run() {

        Iterator it = pages.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            /*
            //Index data into the cluster
            try {
                IndexResponse response = client.prepareIndex("Pages", "Page", String.valueOf(docid))
                        .setSource(jsonBuilder()
                                        .startObject()
                                        .field("url", url)
                                        .field("text", text)
                                        .field("Html", html)
                                        .field("outgoingLinks", links)
                                        .endObject()
                        )
                        .execute()
                        .actionGet();

                // Index name
                String _index = response.getIndex();
                System.out.println(" _index  : " + _index );
                // Type name
                String _type = response.getType();
                System.out.println(" _type  : " + _type );
                // Document ID (generated or not)
                String _id = response.getId();
                System.out.println(" _id  : " + _id );
                // Version (if it's the first time you index this document, you will get: 1)
                long _version = response.getVersion();
                System.out.println(" _version  : " + _version );
                // isCreated() is true if the document is a new one, false if it has been updated
                boolean created = response.isCreated();
                System.out.println(" created  : " + created );

            }catch (Exception e) {
                e.printStackTrace();
            }*/
            it.remove(); // avoids a ConcurrentModificationException
        }

    }
}
