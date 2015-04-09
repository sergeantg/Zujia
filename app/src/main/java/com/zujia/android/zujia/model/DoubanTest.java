package com.zujia.android.zujia.model;

import java.util.List;

/**
 * Created by lenovo on 15-4-9.
 */
public class DoubanTest {
    private int start;
    private int count;
    private int total;
    private List<Tag> tags;

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getStart(){
        return start;
    }
}


