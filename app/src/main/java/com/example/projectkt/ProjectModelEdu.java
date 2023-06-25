package com.example.projectkt;

public class ProjectModelEdu {
    String gambar, isi, judul,tanggalupload, type;

    public ProjectModelEdu(){

    }

    public ProjectModelEdu(String gambar, String isi, String judul, String tanggalupload, String type) {
        this.gambar = gambar;
        this.isi = isi;
        this.judul = judul;
        this.tanggalupload = tanggalupload;
        this.type = type;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggalupload() {
        return tanggalupload;
    }

    public void setTanggalupload(String tanggalupload) {
        this.tanggalupload = tanggalupload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
