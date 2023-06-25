package com.example.projectkt;

public class dataComunication {
    private String key,dari, pesan, tanggal,jenis;
    private long  waktu;

    public dataComunication(String key, String dari, String pesan, String tanggal,long waktu, String jenis) {
        this.key = key;
        this.dari = dari;
        this.pesan = pesan;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.jenis = jenis;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public long getWaktu() {
        return waktu;
    }

    public void setWaktu(long waktu) {
        this.waktu = waktu;
    }
}
