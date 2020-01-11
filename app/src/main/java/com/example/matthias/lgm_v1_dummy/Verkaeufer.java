package com.example.matthias.lgm_v1_dummy;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Verkaeufer implements Serializable {

    private int VNr;

    private String Name;
    private String Inhaber;
    private String Ort;
    private String PLZ;
    private String Strasse;
    private String Nr;

    public Verkaeufer(int pId, String pName, String pInhaber, String pOrt, String pPLZ, String pStrasse, String pNr){
        VNr = pId;
        Name = pName;
        Inhaber = pInhaber;
        Ort = pOrt;
        PLZ = pPLZ;
        Strasse = pStrasse;
        Nr = pNr;
    }

    public void SetVNr(int id) {
        VNr = id;
    }

    public void SetName(String pName){
        Name = pName;
    }

    public void SetInhaber(String pInhaber){
        Inhaber = pInhaber;
    }

    public void SetOrt(String pOrt){
        Ort = pOrt;
    }

    public void SetPLZ(String pPLZ){
        PLZ = pPLZ;
    }

    public void SetStrasse(String pStrasse){
        Strasse = pStrasse;
    }

    public void SetNr(String pNr){
        Nr = pNr;
    }


    public int getVNr() {
        return VNr;
    }

    public String getName() {
        return Name;
    }

    public String getInhaber() {
        return Inhaber;
    }

    public String getOrt() {
        return Ort;
    }

    public String getPLZ() {
        return PLZ;
    }

    public String getStrasse() {
        return Strasse;
    }

    public String getNr() {
        return Nr;
    }
}
