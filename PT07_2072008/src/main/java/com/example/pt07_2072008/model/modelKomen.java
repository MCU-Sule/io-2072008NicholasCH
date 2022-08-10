package com.example.pt07_2072008.model;

public class modelKomen {
    private String nama;
    private String komen;

    public modelKomen(String nama, String komen) {
        this.nama = nama;
        this.komen = komen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }

    @Override
    public String toString() {
        return nama + " - " + komen;
    }
}
