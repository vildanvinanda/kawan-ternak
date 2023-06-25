package com.example.projectkt;

public class FaqModel {
    private String pertanyaan;
    private String jawaban;

    public FaqModel() {

    }

    public FaqModel(String jawaban, String pertanyaan) {
        this.jawaban = jawaban;
        this.pertanyaan = pertanyaan;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
