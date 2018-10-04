package com.example.jp.myapplication;

public class Adduser {
    private String fullname;
    private String Email;
    private String Password;
    private Adduser()
    {

    }

    public Adduser(String fullname, String email, String password) {
        this.fullname = fullname;
        Email = email;
        Password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}
