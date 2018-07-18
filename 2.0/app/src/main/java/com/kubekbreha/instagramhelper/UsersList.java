package com.kubekbreha.instagramhelper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class UsersList {


    public static UsersList get() {
        return new UsersList();
    }

    private UsersList() {
    }

    public List<ListItem> getForecasts() {
        return Arrays.asList(
                new ListItem("0",0),
                new ListItem("1",1),
                new ListItem("2",2),
                new ListItem("3",3),
                new ListItem("4",4));
    }
}
