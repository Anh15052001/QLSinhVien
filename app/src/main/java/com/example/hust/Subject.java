package com.example.hust;

public class Subject {
    private int id;
    private String mssv;
    private String hoten;
    private String email;
    private String ngaysinh;


    public Subject(int id, String mssv, String hoten) {
        this.id = id;
        this.mssv = mssv;
        this.hoten = hoten;


    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return mssv;
    }

    public String getMota() {
        return hoten;
    }





    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.mssv = ten;
    }

    public void setMota(String mota) {
        this.hoten = mota;
    }


}
