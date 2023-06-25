package com.example.projectkt;

public class User {
    private String username, email, nohp, password;

    public User(String username, String email, String nohp, String password){

    }

    public User (String username, String email, String nohp){
        this.username = username;
        this.email = email;
        this.nohp = nohp;
        this.password = password;

    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        username = username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        email = email;
    }
    public String getNohp(){
        return nohp;
    }
    public void setNohp(String nohp){
        nohp = nohp;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        password = password;
    }



}
