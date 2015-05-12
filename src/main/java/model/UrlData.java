package model;

import java.util.ArrayList;

/**
 * Created by nazanin on 5/11/15.
 */
public class UrlData {

    private String url;
    private int inlinksCount;
    private ArrayList<String> outlinks;
    private ArrayList<String> inlinks;

    private long timeOfInsertion;
    private int level;

    private boolean visitedES = false;

    private boolean visitedQueue = false;


    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public int getInlinksCount() {
        return inlinksCount;
    }
    public void setInlinksCount(int inlinksCount) {
        this.inlinksCount = inlinksCount;
    }

    public ArrayList<String> getOutlinks() {
        return outlinks;
    }
    public void setOutlinks(ArrayList<String> outlinks) {
        this.outlinks = outlinks;
    }

    public ArrayList<String> getInlinks() {
        return inlinks;
    }
    public void setInlinks(ArrayList<String> inlinks) {
        this.inlinks = inlinks;
    }

    public boolean isVisitedES() {
        return visitedES;
    }

    public void setVisitedES(boolean visitedES) {
        this.visitedES = visitedES;
    }

    public long getTimeOfInsertion() {
        return timeOfInsertion;
    }

    public void setTimeOfInsertion(long timeOfInsertion) {
        this.timeOfInsertion = timeOfInsertion;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public boolean isVisitedQueue() {
        return visitedQueue;
    }
    public void setVisitedQueue(boolean visitedQueue) {
        this.visitedQueue = visitedQueue;
    }
}
