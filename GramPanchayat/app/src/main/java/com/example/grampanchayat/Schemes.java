package com.example.grampanchayat;

public class Schemes {
    private String title;
    private String desc;
    private String category;
    private String eligiblity;

    public Schemes()
    {

    }

    public Schemes(String title, String desc, String category, String eligiblity) {
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.eligiblity = eligiblity;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEligiblity() {
        return eligiblity;
    }

    public void setEligiblity(String eligiblity) {
        this.eligiblity = eligiblity;
    }
}
