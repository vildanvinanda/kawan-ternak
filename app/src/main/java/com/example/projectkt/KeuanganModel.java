package com.example.projectkt;

public class KeuanganModel {

    String mid,pemasukan, qtyp, satuanp,hargap,totalp,tglpemasukan,type;

    public KeuanganModel(){

    }

    public KeuanganModel(String mid, String pemasukan, String qtyp, String satuanp, String hargap, String totalp, String tglpemasukan, String type) {
        this.mid = mid;
        this.pemasukan = pemasukan;
        this.qtyp = qtyp;
        this.satuanp = satuanp;
        this.hargap = hargap;
        this.totalp = totalp;
        this.tglpemasukan = tglpemasukan;
        this.type = type;
    }

    public String getTglpemasukan() {
        return tglpemasukan;
    }

    public void setTglpemasukan(String tglpemasukan) {
        this.tglpemasukan = tglpemasukan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPemasukan() {
        return pemasukan;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setPemasukan(String pemasukan) {
        this.pemasukan = pemasukan;
    }

    public String getQtyp() {
        return qtyp;
    }

    public void setQtyp(String qtyp) {
        this.qtyp = qtyp;
    }

    public String getSatuanp() {
        return satuanp;
    }

    public void setSatuanp(String satuanp) {
        this.satuanp = satuanp;
    }

    public String getHargap() {
        return hargap;
    }

    public void setHargap(String hargap) {
        this.hargap = hargap;
    }

    public String getTotalp() {
        return totalp;
    }

    public void setTotalp(String totalp) {
        this.totalp = totalp;
    }
}
