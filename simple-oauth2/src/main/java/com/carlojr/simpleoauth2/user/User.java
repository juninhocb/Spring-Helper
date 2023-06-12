package com.carlojr.simpleoauth2.user;

import java.io.Serializable;

public class User implements Serializable {

    private final static long serialVersionUID = 1L;
    private String username;
    private String password;
    private String dbName;

    public User(String username, String password, String dbName) {
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }
    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
