package model;

import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Date;
import java.util.Set;

/**
 * Created by nazanin on 5/11/15.
 */
public class PageInfo {
    int docId;
    String text;
    String html;
    Set<WebURL> links;
    Date fetchDate;

    public PageInfo(int docId, String text, String html, Set<WebURL> links, Date fetchDate) {
        this.docId = docId;
        this.text = text;
        this.html = html;
        this.links = links;
        this.fetchDate = fetchDate;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Set<WebURL> getLinks() {
        return links;
    }

    public void setLinks(Set<WebURL> links) {
        this.links = links;
    }

    public Date getFetchDate() {
        return fetchDate;
    }

    public void setFetchDate(Date fetchDate) {
        this.fetchDate = fetchDate;
    }
}
