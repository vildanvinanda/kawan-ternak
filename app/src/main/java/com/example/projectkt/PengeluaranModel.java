package com.example.projectkt;

public class PengeluaranModel {
    String midp,pengeluaran, qty, satuan,harga,total,tglpengeluaran, type;

    public PengeluaranModel() {

    }

    public PengeluaranModel(String midp, String pengeluaran, String qty, String satuan, String harga, String total, String tglpengeluaran, String type) {
        this.midp = midp;
        this.pengeluaran = pengeluaran;
        this.qty = qty;
        this.satuan = satuan;
        this.harga = harga;
        this.total = total;
        this.tglpengeluaran = tglpengeluaran;
        this.type = type;
    }

    public String getMidp() {
        return midp;
    }

    public void setMidp(String midp) {
        this.midp = midp;
    }

    public String getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(String pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTglpengeluaran() {
        return tglpengeluaran;
    }

    public void setTglpengeluaran(String tglpengeluaran) {
        this.tglpengeluaran = tglpengeluaran;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
