package com.kubekbreha.instagramhelper;

public class ListItem {

    private final String listName;
    private final int gradient;

    public ListItem(String listName, int gradient) {
        this.listName = listName;
        this.gradient = gradient;
    }

    public String getListName() {
        return listName;
    }

    public int getGradient() {
        return gradient;
    }
}
