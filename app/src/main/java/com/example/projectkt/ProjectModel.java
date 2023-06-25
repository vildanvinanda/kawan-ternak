package com.example.projectkt;

public class ProjectModel {

    private String eddIsiArtikel,eddTitle,eddttl;
    private String uploadgambarartikel;

    public ProjectModel() {

    }

    public ProjectModel(String eddIsiArtikel, String eddTitle, String eddttl, String uploadgambarartikel) {
        this.eddIsiArtikel = eddIsiArtikel;
        this.eddTitle = eddTitle;
        this.eddttl = eddttl;
        this.uploadgambarartikel = uploadgambarartikel;
    }

    public String getEddttl() {
        return eddttl;
    }

    public void setEddttl(String eddttl) {
        this.eddttl = eddttl;
    }

    public String getEddTitle() {
        return eddTitle;
    }

    public void setEddTitle(String eddTitle) {
        this.eddTitle = eddTitle;
    }

    public String getEddIsiArtikel() {
        return eddIsiArtikel;
    }

    public void setEddIsiArtikel(String eddIsiArtikel) {
        this.eddIsiArtikel = eddIsiArtikel;
    }

    public String getUploadgambarartikel() {
        return uploadgambarartikel;
    }

    public void setUploadgambarartikel(String uploadgambarartikel) {
        this.uploadgambarartikel = uploadgambarartikel;
    }

    //    public ProjectModel(String eddttl, String eddTitle, String eddIsiArtikel, String uploadgambarartikel) {
//        this.eddttl = eddttl;
//        this.eddTitle = eddTitle;
//        this.eddIsiArtikel = eddIsiArtikel;
//        this.uploadgambarartikel = uploadgambarartikel;
//    }
//
//
//
//    public String getEddttl() {
//        return eddttl;
//    }
//
//    public void setEddttl(String eddttl) {
//        this.eddttl = eddttl;
//    }
//
//    public String getEddTitle() {
//        return eddTitle;
//    }
//
//    public void setEddTitle(String eddTitle) {
//        this.eddTitle = eddTitle;
//    }
//
//    public String getEddIsiArtikel() {
//        return eddIsiArtikel;
//    }
//
//    public void setEddIsiArtikel(String eddIsiArtikel) {
//        this.eddIsiArtikel = eddIsiArtikel;
//    }
//
//    public String getUploadgambarartikel() {
//        return uploadgambarartikel;
//    }
//
//    public void setUploadgambarartikel(String uploadgambarartikel) {
//        this.uploadgambarartikel = uploadgambarartikel;
//    }
}
