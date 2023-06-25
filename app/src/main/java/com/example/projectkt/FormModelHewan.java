package com.example.projectkt;

public class FormModelHewan {

    String qrcode, nokandang, namahewan, namapemilik, jk, status, kategori, klasifikasijenis, tgllahir, tglbeli, umur, hargabeli, belidari, peristiwa, statuskesehatan, temuan, treatment, hasil, imghewan, tglupload;

    public FormModelHewan(){

    }

    public FormModelHewan(String belidari, String hargabeli, String hasil, String imghewan, String jk,
                          String kategori, String klasifikasijenis,  String namahewan,
                          String namapemilik, String nokandang, String peristiwa, String qrcode, String status,  String statuskesehatan,
                          String temuan, String tglbeli, String tgllahir, String tglupload, String treatment, String umur ) {
        this.qrcode = qrcode;
        this.nokandang = nokandang;
        this.namahewan = namahewan;
        this.namapemilik = namapemilik;
        this.jk = jk;
        this.status = status;
        this.kategori = kategori;
        this.klasifikasijenis = klasifikasijenis;
        this.tgllahir = tgllahir;
        this.tglbeli = tglbeli;
        this.umur = umur;
        this.hargabeli = hargabeli;
        this.belidari = belidari;
        this.peristiwa = peristiwa;
        this.statuskesehatan = statuskesehatan;
        this.temuan = temuan;
        this.treatment = treatment;
        this.hasil = hasil;
        this.imghewan = imghewan;
        this.tglupload = tglupload;
    }

    public String getImghewan() {
        return imghewan;
    }

    public void setImghewan(String imghewan) {
        this.imghewan = imghewan;
    }

    public String getTglupload() {
        return tglupload;
    }

    public void setTglupload(String tglupload) {
        this.tglupload = tglupload;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getNokandang() {
        return nokandang;
    }

    public void setNokandang(String nokandang) {
        this.nokandang = nokandang;
    }

    public String getNamahewan() {
        return namahewan;
    }

    public void setNamahewan(String namahewan) {
        this.namahewan = namahewan;
    }

    public String getNamapemilik() {
        return namapemilik;
    }

    public void setNamapemilik(String namapemilik) {
        this.namapemilik = namapemilik;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKlasifikasijenis() {
        return klasifikasijenis;
    }

    public void setKlasifikasijenis(String klasifikasijenis) {
        this.klasifikasijenis = klasifikasijenis;
    }

    public String getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(String tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getTglbeli() {
        return tglbeli;
    }

    public void setTglbeli(String tglbeli) {
        this.tglbeli = tglbeli;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getHargabeli() {
        return hargabeli;
    }

    public void setHargabeli(String hargabeli) {
        this.hargabeli = hargabeli;
    }

    public String getBelidari() {
        return belidari;
    }

    public void setBelidari(String belidari) {
        this.belidari = belidari;
    }

    public String getPeristiwa() {
        return peristiwa;
    }

    public void setPeristiwa(String peristiwa) {
        this.peristiwa = peristiwa;
    }

    public String getStatuskesehatan() {
        return statuskesehatan;
    }

    public void setStatuskesehatan(String statuskesehatan) {
        this.statuskesehatan = statuskesehatan;
    }

    public String getTemuan() {
        return temuan;
    }

    public void setTemuan(String temuan) {
        this.temuan = temuan;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }
}
