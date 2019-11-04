package com.example.student.btandroid_dialog;

public class TacGia {
    private String id, name, address, emal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmal() {
        return emal;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public TacGia(String id, String name, String address, String emal) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.emal = emal;
    }

    public TacGia() {
    }

    @Override
    public String toString() {
        return this.id + this.name + this.address + this.emal;
    }
}
