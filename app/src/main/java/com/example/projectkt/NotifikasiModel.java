package com.example.projectkt;

public class NotifikasiModel {

    String idn, judulnotif, logonotif, isintif, tglnotif,imgnotifikasi;

    private NotifikasiModel() {

    }

    public NotifikasiModel(String idn, String judulnotif, String logonotif, String isintif, String tglnotif, String imgnotifikasi) {
        this.idn = idn;
        this.judulnotif = judulnotif;
        this.logonotif = logonotif;
        this.isintif = isintif;
        this.tglnotif = tglnotif;
        this.imgnotifikasi = imgnotifikasi;
    }

    public String getImgnotifikasi() {
        return imgnotifikasi;
    }

    public void setImgnotifikasi(String imgnotifikasi) {
        this.imgnotifikasi = imgnotifikasi;
    }

    public String getTglnotif() {
        return tglnotif;
    }

    public void setTglnotif(String tglnotif) {
        this.tglnotif = tglnotif;
    }

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public String getJudulnotif() {
        return judulnotif;
    }

    public void setJudulnotif(String judulnotif) {
        this.judulnotif = judulnotif;
    }

    public String getLogonotif() {
        return logonotif;
    }

    public void setLogonotif(String logonotif) {
        this.logonotif = logonotif;
    }

    public String getIsintif() {
        return isintif;
    }

    public void setIsintif(String isintif) {
        this.isintif = isintif;
    }
}
