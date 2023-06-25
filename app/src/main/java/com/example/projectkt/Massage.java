package com.example.projectkt;

public class Massage {
    private String muid;
    private String dari;
    private String username;
    private String pesan;
    private String waktu;
    private String profiluserchat;
    private String jam;
    private String image;
    private String type;
    private String nohp;
    private String gender;
    private String addresss;


    public Massage() {

    }

    public Massage(String muid, String dari, String username, String pesan, String waktu, String profiluserchat, String jam, String image, String type, String nohp, String gender, String addresss) {
        this.muid = muid;
        this.dari = dari;
        this.username = username;
        this.pesan = pesan;
        this.waktu = waktu;
        this.profiluserchat = profiluserchat;
        this.jam = jam;
        this.image = image;
        this.type = type;
        this.nohp = nohp;
        this.gender = gender;
        this.addresss = addresss;
    }

    public String getMuid() {
        return muid;
    }

    public void setMuid(String muid) {
        this.muid = muid;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getProfiluserchat() {
        return profiluserchat;
    }

    public void setProfiluserchat(String profiluserchat) {
        this.profiluserchat = profiluserchat;
    }
}


