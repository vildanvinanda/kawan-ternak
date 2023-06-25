package com.example.projectkt;

public class ArtikelModel {
    String judulartikel, isiartikel, tglartikel, gambarartikel;

    public ArtikelModel() {

    }

    public ArtikelModel(String judulartikel, String isiartikel, String tglartikel, String gambarartikel) {
        this.judulartikel = judulartikel;
        this.isiartikel = isiartikel;
        this.tglartikel = tglartikel;
        this.gambarartikel = gambarartikel;
    }

    public String getJudulartikel() {
        return judulartikel;
    }

    public void setJudulartikel(String judulartikel) {
        this.judulartikel = judulartikel;
    }

    public String getIsiartikel() {
        return isiartikel;
    }

    public void setIsiartikel(String isiartikel) {
        this.isiartikel = isiartikel;
    }

    public String getTglartikel() {
        return tglartikel;
    }

    public void setTglartikel(String tglartikel) {
        this.tglartikel = tglartikel;
    }

    public String getGambarartikel() {
        return gambarartikel;
    }

    public void setGambarartikel(String gambarartikel) {
        this.gambarartikel = gambarartikel;
    }
}
