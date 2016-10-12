package com.petru.gitsearch.entities;

/**
 * Created by Petru on 10/12/2016.
 */
public class User {

    private int id;
    private String login;
    private String avatarUrl;

    public User(int id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
