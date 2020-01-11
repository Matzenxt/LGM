package code.lagerhlatung.lodner.matthias.lgm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


class DataBaseHelper extends SQLiteOpenHelper {

    private static final String[] Verkaeufer = {"Verkaeufer", "_id", "Namen", "Inhaber", "Ort", "PLZ", "Strasse", "HausNummer"};
    private static final String[] Produkte = {"Produkte", "PNr", "Bezeichnung", "Fuellmenge", "Barcode"};

    private static final String[] Sortiment = {"Sortiment", "SNr", "VNr", "Beschreibung"};
    private static final String[] ProduktSortimment = {"ProduktSortimment", "PSNr", "SNr", "PNr", "Sollbestand"};

    private static final String[] Bestellung = {"Bestellung", "BNr", "VNr", "Datum"};
    private static final String[] ProduktBestellt = {"ProduktBesllt", "BPNr", "BNr", "PNr", "Bestand"};


    public DataBaseHelper(Context context) {
        super(context, "LGM.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Verkaeufer[0] + " (" + Verkaeufer[1] + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Verkaeufer[2] + " TEXT, " + Verkaeufer[3] + " TEXT, " + Verkaeufer[4] + " TEXT, " + Verkaeufer[5] + " TEXT, " + Verkaeufer[6] + " TEXT, " + Verkaeufer[7] + " TEXT)");
        db.execSQL("CREATE TABLE " + Produkte[0] + " (" + Produkte[1] + " INTEGER PRIMARY KEY, " + Produkte[2] + " Text, " + Produkte[3] + " INTEGER, " + Produkte[4] + " Text)");

        db.execSQL("CREATE TABLE " + Sortiment[0] + " (" + Sortiment[1] + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Sortiment[2] + " INTEGER, " + Sortiment[3] + " TEXT)");
        db.execSQL("CREATE TABLE " + ProduktSortimment[0] + " (" + ProduktSortimment[1] + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ProduktSortimment[2] + " INTEGER, " + ProduktSortimment[3] + " INTEGER, " + ProduktSortimment[4] + " INTEGER)");

        db.execSQL("CREATE TABLE " + Bestellung[0] + " (" + Bestellung[1] + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Bestellung[2] + " INTEGER, " + Bestellung[3] + " TEXT)");
        db.execSQL("CREATE TABLE " + ProduktBestellt[0] + " (" + ProduktBestellt[1] + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ProduktBestellt[2] + " INTEGER, " + ProduktBestellt[3] + " INTEGER, " + ProduktBestellt[4] + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + Verkaeufer[0]);
        db.execSQL("DROP IF TABLE EXISTS " + Produkte[0]);
        db.execSQL("DROP IF TABLE EXISTS " + Sortiment[0]);
        db.execSQL("DROP IF TABLE EXISTS " + ProduktSortimment[0]);
        db.execSQL("DROP IF TABLE EXISTS " + Bestellung[0]);
        db.execSQL("DROP IF TABLE EXISTS " + ProduktBestellt[0]);

        onCreate(db);
    }


    public boolean addSortiment(String pBezeichnung, int pVNr) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Sortiment[3], pBezeichnung);
        contentValues.put(Sortiment[2], pVNr);

        long result = db.insert(Sortiment[0], null, contentValues);

        return result != - 1;
    }

    public boolean updateSortiment(int pSNr, int pVNr, String pBeschreibung) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Sortiment[1], pSNr);
        contentValues.put(Sortiment[2], pVNr);
        contentValues.put(Sortiment[3], pBeschreibung);

        int result = db.update(Sortiment[0], contentValues, "SNr = " + pSNr, null);

        return result != - 1;
    }

    public Cursor getSortimentListContent() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + Sortiment[0] + ", " + Verkaeufer[0] + " Where " + Sortiment[2] + " = " + Verkaeufer[1] + " ORDER BY " + Sortiment[1] + " DESC", null);
    }

    public void deletSortiment(int pSNr, int pVNr, String pBeschreibung) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Sortiment[0], Sortiment[1] + " = ? AND " + Sortiment[2] + " = ? AND " + Sortiment[3] + " = ?", new String[] {String.valueOf(pSNr), String.valueOf(pVNr), pBeschreibung});

    }


    public Cursor getSortimentVerkaeuferName(int pVNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + Verkaeufer[2] + " FROM " + Verkaeufer[0] + " WHERE " + Verkaeufer[1] + " = " + String.valueOf(pVNr), null);
    }

    public Cursor getSortimentProduktAnzahl(int pSNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT COUNT(*) AS 'Anzahl Produkte' FROM " + ProduktSortimment[0] + " WHERE " + ProduktSortimment[2] + " = " + String.valueOf(pSNr - 1), null);
    }


    public boolean addProduktSortiment(int pSNr, int pPNr, int pSollBestand) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProduktSortimment[2], pSNr);
        contentValues.put(ProduktSortimment[3], pPNr);
        contentValues.put(ProduktSortimment[4], pSollBestand);

        long result = db.insert(ProduktSortimment[0], null, contentValues);

        return result != - 1;
    }

    public boolean updateProduktSortiment(int pPSNr, int pSollBestand) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProduktSortimment[4], pSollBestand);

        int result = db.update(ProduktSortimment[0], contentValues, "PSNr = " + pPSNr, null);

        return result != - 1;
    }

    public Cursor getProduktSortimentContent(int pSNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + ProduktSortimment[0] + " WHERE " + ProduktSortimment[2] + " = " + String.valueOf(pSNr - 1 + " ORDER BY " + ProduktSortimment[1] + " DESC"), null);
    }

    public Cursor getProduktSortimentContentAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + ProduktSortimment[0], null);
    }

    public void deletProduktSortiment(int pPSNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ProduktSortimment[0], ProduktSortimment[1] +  " = ?", new String[]{String.valueOf(pPSNr)});
    }


    public boolean addBestellung(int pVNr, String pDatum) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Bestellung[2], pVNr);
        //contentValues.put(Bestellung[3], new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime()));
        contentValues.put(Bestellung[3], pDatum);

        long result = db.insert(Bestellung[0], null, contentValues);

        return result != - 1;
    }

    public boolean updateBestellung(int pBNr, int pVNr, String pDatum) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Bestellung[1], pBNr);
        contentValues.put(Bestellung[2], pVNr);
        contentValues.put(Bestellung[3], pDatum);

        int result = db.update(Bestellung[0], contentValues, "BNr = " + pBNr, null);

        return result != - 1;
    }

    public Cursor getBestellListContent() {
        //SQLiteDatabase db = this.getWritableDatabase();
        //return db.rawQuery("SELECT * FROM " + Bestellung[0] + " ORDER BY " + Bestellung[1] + " DESC", null);
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + Bestellung[0] + ", " + Verkaeufer[0] + " Where " + Bestellung[2] + " = " + Verkaeufer[1] + " ORDER BY " + Bestellung[1] + " DESC", null);
    }

    public void deletBestellung(int pBNr, int pVNr, String pDatum) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Bestellung[0], Bestellung[1] + " = ? AND " + Bestellung[2] + " = ? AND " + Bestellung[3] + " = ?", new String[] {String.valueOf(pBNr), String.valueOf(pVNr), pDatum});

        //TODO: Löschen aller einzelen Einträge der Bestellung

    }


    public Cursor getBestellungVerkaeuferName(int pVNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + Verkaeufer[2] + " FROM " + Verkaeufer[0] + " WHERE " + Verkaeufer[1] + " = " + String.valueOf(pVNr), null);
    }

    public Cursor getBestellungProduktAnzahl(int pSNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT COUNT(*) AS 'Anzahl Produkte' FROM " + ProduktBestellt[0] + " WHERE " + ProduktBestellt[2] + " = " + String.valueOf(pSNr - 1), null);
    }


    public boolean addProduktBestellung(int pBnr, int pPNr, int pBestand) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProduktBestellt[2], pBnr);
        contentValues.put(ProduktBestellt[3], pPNr);
        contentValues.put(ProduktBestellt[4], pBestand);

        long result = db.insert(ProduktBestellt[0], null, contentValues);

        return result != - 1;
    }

    public boolean updateProduktBestellung(int pBPNr, int pBestand){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProduktBestellt[4], pBestand);

        int result = db.update(ProduktBestellt[0], contentValues, "BPNr = " + pBPNr, null);

        return result != - 1;
    }

    public Cursor getProduktBestellungContent(int pBNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + ProduktBestellt[0] + " WHERE " + ProduktBestellt[2] + " = " + String.valueOf(pBNr - 1) + " ORDER BY " + ProduktBestellt[1] + " DESC", null);
    }

    public Cursor getProduktBestellungContentAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + ProduktBestellt[0], null);
    }

    public void deletProduktBestellung(int pBPNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ProduktBestellt[0], ProduktBestellt[1] +  " = ?", new String[]{String.valueOf(pBPNr)});
    }


    public boolean addVerkaeufer(String name, String inhaber, String ort, String strasse, String plz, String nr) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Verkaeufer[2], name);
        contentValues.put(Verkaeufer[3], inhaber);
        contentValues.put(Verkaeufer[4], ort);
        contentValues.put(Verkaeufer[5], plz);
        contentValues.put(Verkaeufer[6], strasse);
        contentValues.put(Verkaeufer[7], nr);

        long result = db.insert(Verkaeufer[0], null, contentValues);

        return result != - 1;
    }

    public boolean updateVerkaeufer(int id, String name, String inhaber, String ort, String plz, String strasse, String hausnummer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Verkaeufer[2], name);
        contentValues.put(Verkaeufer[3], inhaber);
        contentValues.put(Verkaeufer[4], ort);
        contentValues.put(Verkaeufer[5], plz);
        contentValues.put(Verkaeufer[6], strasse);
        contentValues.put(Verkaeufer[7], hausnummer);

        int result = db.update(Verkaeufer[0], contentValues, "_id = " + id, null);

        return result != - 1;
    }

    public void deletVerkaeufer(int id, String name, String inhaber, String ort, String plz, String strasse, String hausnummer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Verkaeufer[0], Verkaeufer[1] + " = ? AND " + Verkaeufer[2] + " = ? AND " + Verkaeufer[3] + " = ? AND " + Verkaeufer[4] + " = ? AND " + Verkaeufer[5] + " = ? AND " + Verkaeufer[6] + " = ? AND " + Verkaeufer[7] + " = ?", new String[] {String.valueOf(id), name, inhaber, ort, plz, strasse, hausnummer});

        /*
        Cursor ps = db.rawQuery("SELECT " + Bestellung[0] + "." + Bestellung[1] + ", " + Bestellung[0] + "." + Bestellung[2] + ", "  +Bestellung[0] + "." + Bestellung[3] + " FROM " + Bestellung[0] + ", " + Verkaeufer[0] + " WHERE " + Verkaeufer[0] + "." + Verkaeufer[1] + " = " +Bestellung[0] +"."+ Bestellung[2], null);
        int numRows = ps.getCount();

        if (numRows != 0) {
            while (ps.moveToNext()) {
                deletBestellung(ps.getInt(0), ps.getInt(1), ps.getString(2));
            }
        }
        ps.close();
        */
    }

    public Cursor getVerkaeuferListContent() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + Verkaeufer[0], null);
    }

    public Cursor getVerkaeufer(int pVNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + Verkaeufer[0] + " WHERE " + Verkaeufer[1] + " = " + String.valueOf(pVNr), null);
    }

    public Cursor getVerkaeuferByBestellung(int pBNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT " + Verkaeufer[2] +", " + Verkaeufer[4] + " FROM " + Verkaeufer[0] + ", " + Bestellung[0] + " WHERE " + Verkaeufer[1] + " = " + String.valueOf(pBNr), null);
    }



    public void updateProdukt(int id, String name, String menge, String barcode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Produkte[2], name);
        contentValues.put(Produkte[3], menge);
        contentValues.put(Produkte[4], barcode);

        db.update(Produkte[0], contentValues, "PNr = " + id, null);
    }

    public boolean addProdukt(String name, String fuellmenge, String barcode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Produkte[2], name);
        contentValues.put(Produkte[3], fuellmenge);
        contentValues.put(Produkte[4], barcode);

        long result = db.insert(Produkte[0], null, contentValues);

        return result != - 1;
    }

    public Cursor getProduktListContent() {
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + Produkte[0] + " ORDER BY " + Produkte[0] + "." + Produkte[2] + ", " + Produkte[0] + "." + Produkte[3] + " ASC", null);
    }

    public Cursor getProdukt(int pPNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + Produkte[0] + " Where " + Produkte[1] + " = " + pPNr, null);
    }

    public Cursor getProduktByName(String suche) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + Produkte[0] + " Where " + Produkte[2] + " LIKE '%" + suche + "%' GROUP BY " + Produkte[4] + " ORDER BY " + Produkte[2] + ", " + Produkte[3] + " ASC", null);
    }

    public Cursor getProduktByBarcode(String pBarcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + Produkte[0] + " Where " + Produkte[4] + " = " + pBarcode + " ORDER BY " + Produkte[0] + "." + Produkte[2] + ", " + Produkte[0] + "." + Produkte[3] + " ASC", null);
    }

    public void deletProdukt(int id, String name, int fuellmenge, String barcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Produkte[0], Produkte[1] + " = ? AND " + Produkte[2] + " = ? AND " + Produkte[3] + " = ? AND " + Produkte[4] + " = ?", new String[] {String.valueOf(id), name, String.valueOf(fuellmenge), barcode});
    }



    //Excel Export Bereich
    public Cursor ExcelSortimentExport(int pSNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT P." + Produkte[2] + ", P." + Produkte[3] + ", P." + Produkte[4] + ", PS." + ProduktSortimment[4] +
                " FROM " + Produkte[0] + " P , " + ProduktSortimment[0] + " PS " +
                " Where P." + Produkte[1] + " = PS." + ProduktSortimment[3] + " AND PS." + ProduktSortimment[2] + " = " + String.valueOf(pSNr - 1), null);
    }

    public Cursor ExcelBestellungExport(int pBNr) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT P." + Produkte[2] + ", P." + Produkte[3] + ", P." + Produkte[4] + ", PB." + ProduktBestellt[4] +
                " FROM " + Produkte[0] + " P , " + ProduktBestellt[0] + " PB " +
                " Where P." + Produkte[1] + " = PB." + ProduktBestellt[3] + " AND PB." + ProduktBestellt[2] + " = " + String.valueOf(pBNr - 1), null);
    }


    //Gibt alle Sortiment Nummern zurück die zum Verkäufer der Bestellung passen, -> Spätere in verwendung nur erster Wert soll verwendet werden rest wird ignoriert
    public Cursor checkSortToBestell(int pBNr) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT S." + Sortiment[1] +
                " FROM " + Sortiment[0] + " S , " + Bestellung[0] + " B " +
                " WHERE S." + Sortiment[2] + " = B." + Bestellung[2] + " AND B." + Bestellung[1] + " = " + pBNr,
                null);
    }

    //Gibt Produkte zurück die in der Bestellung und Sortiment übereinstimmen.
    public Cursor ExcelExportBestellungGleichSortiment(int pBNr, int pSNr){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT P." + Produkte[4] + ", P." + Produkte[2] + ", P." + Produkte[3] + ", PS." + ProduktSortimment[4] + ", PB." + ProduktBestellt[4] +
                " FROM " + Produkte[0] + " P, " + ProduktSortimment[0] + " PS, " + ProduktBestellt[0] + " PB" +
                " WHERE PS." + ProduktSortimment[2] + " = " + pSNr + " AND PB." + ProduktBestellt[2] + " = " + pBNr +
                    " AND PB." + ProduktBestellt[3] + " = PS." + ProduktSortimment[3] + " AND PS." + ProduktSortimment[3] + " = P." + Produkte[1], null);
    }

    //Gibt Produkte zurück die in der Bestellung sind aber nicht im Sortiment enthalten sind.
    public Cursor ExcelExportBestellungNichtSortiment(int pBNr, int pSNr){
        SQLiteDatabase db = this.getReadableDatabase();

        Log.d(null, pBNr + "_____________" + pSNr);

        /*return db.rawQuery("SELECT P." + Produkte[4] + ", P." + Produkte[2] + ", P." + Produkte[3] + ", PS." + ProduktSortimment[4] + ", PB." + ProduktBestellt[4] +
                " FROM " + Produkte[0] + " P, " + ProduktSortimment[0] + " PS, " + ProduktBestellt[0] + " PB" +
                " WHERE PS." + ProduktSortimment[2] + " = " + pSNr + " AND PB." + ProduktBestellt[2] + " = " + pBNr +
                " AND PB." + ProduktBestellt[3] + " != PS." + ProduktSortimment[3] + " AND PS." + ProduktSortimment[3] + " = P." + Produkte[1], null);
        */


        /*  //Funktioniert so halb
        return db.rawQuery("SELECT * FROM " + ProduktBestellt[0] + " PB LEFT JOIN " + ProduktSortimment[0] + " PS ON PB." + ProduktBestellt[3] + " = PS." + ProduktSortimment[3] +
                " WHERE PS." + ProduktSortimment[3] + " IS NULL", null);
*/

/*
        return db.rawQuery("SELECT P." + Produkte[4] + ", P." + Produkte[2] + ", P." + Produkte[3] + ", PS." + ProduktSortimment[4] + ", PB." + ProduktBestellt[4] +
                " FROM " + Produkte[0] + " P, " + ProduktBestellt[0] + " PB LEFT JOIN " + ProduktSortimment[0] + " PS ON PB." + ProduktBestellt[3] + " = PS." + ProduktSortimment[3] +
                " WHERE PS." + ProduktSortimment[3] + " IS NULL AND P." + Produkte[1] + " = PB." + ProduktBestellt[3], null);
                // + " AND " + pBNr + " = PB." + ProduktBestellt[2] + " AND " + pSNr + " = PS." + ProduktSortimment[2]
*/

/*
        return db.rawQuery("SELECT P." + Produkte[4] + ", P." + Produkte[2] + ", P." + Produkte[3] + ", PS." + ProduktSortimment[4] + ", PB." + ProduktBestellt[4] +
                " FROM " + Produkte[0] + " P, " + ProduktBestellt[0] + " PB, " + ProduktSortimment[0] + " PS " +
                " WHERE PS." + ProduktSortimment[3] + " IS NULL AND P." + Produkte[1] + " = PB." + ProduktBestellt[3] +
                " AND PS." + ProduktSortimment[3] + " = PB." + ProduktBestellt[3] + " AND PB." + ProduktBestellt[2] + " = " + pBNr, null);

        */


        return db.rawQuery("select B.BNr, PB.PNr, PB.Bestand " +
                " from Bestellung as B left Join Sortiment as S on B.VNr = S.VNr " +
                " left Join ProduktSortimment as PS on S.SNr = (PS.SNr - 1) " +
                " left join ProduktBesllt as PB on PB.PNr = PS.PNr where B.BNR = PB.BNr = " + pBNr, null);


/*
        return db.rawQuery("select P.Barcode, P.Bezeichnung, P.Fuellmenge, PB.Bestand " +
                " from Produkte as P, Bestellung as B left Join Sortiment as S on B.VNr = S.VNr " +
                " left Join ProduktSortimment as PS on S.SNr = PS.SNr " +
                " left join ProduktBesllt as PB on PB.PNr = PS.PNr where B.BNR = PB.BNr = '" + (pBNr - 1) + "' AND PB.PNr = P.PNr", null);
*/


        /*
        return db.rawQuery("select P.Barcode, P.Bezeichnung, P.Fuellmenge, PB.Bestand " +
                " from Produkte as P, Bestellung as B, ProduktBesllt as PB, Sortiment as S, ProduktSortimment as PS " +
                " where PB.BNR = " + (pBNr -1) + " and PS.SNr = " + (pSNr - 1) + " and PS.PNr = PB.PNr" +
                " and PB.PNr = P.PNr", null);
*/

        //return ExcelExportBestellungGleichSortiment(pBNr, pSNr);


    }

    //Gibt Produkte zurück die nicht in der Bestellung entahlten sind aber im Sortiment sind.
    public Cursor ExcelExportBestellungAberSortiment(int pBNr, int pSNr){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("", null);
    }


}
