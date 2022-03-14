package com.jyj.naversearch;

public class ListItem {
    private String title;
    private String descript;
    private String link;

    public ListItem(String title, String descript, String link) {
        this.title = title;
        this.descript = descript;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}