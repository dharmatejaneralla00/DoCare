package com.marveltech.docare;

public class BedsData {
    String hname,location,verify,hospitalid,address;

    public BedsData(String hname, String location, String verify, String hospitalid,String address) {
        this.hname = hname;
        this.location = location;
        this.verify = verify;
        this.hospitalid = hospitalid;
        this.address = address;
    }

    public String getHname() {
        return hname;
    }

    public String getLocation() {
        return location;
    }

    public String getVerify() {
        return verify;
    }

    public String getHospitalid() {
        return hospitalid;
    }

    public String getAddress() {
        return address;
    }
}
