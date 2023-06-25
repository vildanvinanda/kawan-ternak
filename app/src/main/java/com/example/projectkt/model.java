package com.example.projectkt;

public class model {
    private String arid, judul, isiartikel, imageartikel;
    private long tanggalterbit;

    public model(){

    }

    public model(String arid, String judul, String isiartikel, String imageartikel, long tanggalterbit) {
        this.arid = arid;
        this.judul = judul;
        this.isiartikel = isiartikel;
        this.imageartikel = imageartikel;
        this.tanggalterbit = tanggalterbit;
    }

    public String getArid() {
        return arid;
    }

    public void setArid(String arid) {
        this.arid = arid;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public long getTanggalterbit() {
        return tanggalterbit;
    }

    public void setTanggalterbit(long tanggalterbit) {
        this.tanggalterbit = tanggalterbit;
    }

    public String getIsiartikel() {
        return isiartikel;
    }

    public void setIsiartikel(String isiartikel) {
        this.isiartikel = isiartikel;
    }

    public String getImageartikel() {
        return imageartikel;
    }

    public void setImageartikel(String imageartikel) {
        this.imageartikel = imageartikel;
    }
}
