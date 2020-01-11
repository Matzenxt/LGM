package com.example.matthias.lgm_v1_dummy;


import java.io.Serializable;

public class Sortiment implements Serializable {

    private int SNr;

    private int VNr;

    private String Beschreibung;

    private ProduktSortimment[] produktSortimments;


    public Sortiment(int snr, int vnr, String beschreibung, DataBaseHelper dataBaseHelper){
        SNr = snr;
        VNr = vnr;

        Beschreibung = beschreibung;

        /*
        Cursor cursor = dataBaseHelper.getProduktSortimentContent(snr);

        int numRows = cursor.getCount();
        cursor.moveToFirst();

        if(numRows == 0){
        }else{
            int i = 0;
            while (cursor.moveToNext()){
                produktSortimments[i] = new ProduktSortimment(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), dataBaseHelper);
                i++;
            }
        }
        */
    }


    public void setSNr(int SNr) {
        this.SNr = SNr;
    }

    public void setVNr(int VNr) {
        this.VNr = VNr;
    }

    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }



    public int getSNr() {
        return SNr;
    }

    public int getVNr() {
        return VNr;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public ProduktSortimment getProduktSortimments(int pPostion){
        return produktSortimments[pPostion];
    }

}
