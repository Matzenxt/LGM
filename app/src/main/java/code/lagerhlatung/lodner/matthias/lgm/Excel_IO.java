package code.lagerhlatung.lodner.matthias.lgm;


import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

class Excel_IO {

    public void ExportSortiment(DataBaseHelper dataBaseHelper, int pSNr) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.ExcelSortimentExport(pSNr);

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getString(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getString(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getInt(3));

                rowNum++;
            }
        }
        ps.close();
        dataBaseHelper.close();

        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "LGM_Export_Sortiment.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //TODO: Aufrage ob Sortiment vorhanden ist
    public void ExportBestellung(DataBaseHelper dataBaseHelper, int pBNr, String pVerkaeufer, String pOrt){
        Cursor cursor = dataBaseHelper.checkSortToBestell(pBNr);

        //Aufrage ob Sortiment zu Bestellung von Verkäufer vorhanen
        if (cursor.getCount() == 0) {
            //Kein Sortiment vorhanden "Standart" Export
            ExportBestellungOhneSortiment(dataBaseHelper, pBNr, pVerkaeufer, pOrt);

        } else {
            //Sortiment vorhanden zu Bestellung
            //Verwendet nur das erste Sortiment von Verkäufer

            cursor.moveToFirst();
            ExportBestellungMitSortiment(dataBaseHelper, cursor.getInt(0)-1, pBNr - 1, pVerkaeufer, pOrt);

        }
        cursor.close();
        dataBaseHelper.close();
    }

    public void ExportBestellungOhneSortiment(DataBaseHelper dataBaseHelper, int pBNr, String pVerkäufer, String pOrt) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.ExcelBestellungExport(pBNr);

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getString(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getString(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getInt(3));

                rowNum++;
            }
        }

        ps.close();
        dataBaseHelper.close();


        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pVerkäufer + "_" + pOrt +".xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExportBestellungMitSortiment(DataBaseHelper dataBaseHelper, int pSNr, int pBNr, String pVerkäufer, String pOrt){
        //TODO: Vorlage laden statt neues zu ersten, anschliesen Vorlage als neue Datei speichern
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        int rowNum = 5;

        pBNr = pBNr +0;
        pSNr = pSNr +0;

        Cursor ps = dataBaseHelper.ExcelExportBestellungGleichSortiment(pBNr, pSNr);

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(rowNum);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keine Produkte aufgenommen, die im Sortiment sind.");

        } else {
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getString(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getString(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getInt(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getInt(3));

                Cell cell4 = row.createCell(6);
                cell4.setCellValue(ps.getInt(4));

                rowNum++;
            }
        }

/*
        rowNum = rowNum + 10;
        ps = dataBaseHelper.ExcelExportBestellungAberSortiment(pBNr, pSNr);

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Alle Produkte vom Sortiment wurden aufgenommen.");

        } else {
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getString(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getString(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getInt(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getInt(3));

                Cell cell4 = row.createCell(6);
                cell4.setCellValue(ps.getInt(4));

                rowNum++;
            }
        }

*/

        rowNum = rowNum + 10;
        Log.d(null, pBNr + "_____________" + pSNr);

        ps = dataBaseHelper.ExcelExportBestellungNichtSortiment(pBNr, pSNr);

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(rowNum);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keine Produkte aufgenommen die nicht im Sortiment sind.");

        } else {
            Cursor temp = null;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getString(0));

                temp = dataBaseHelper.getProdukt(ps.getInt(1));
                temp.moveToFirst();

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(temp.getString(1));


                Cell cell2 = row.createCell(4);
                cell2.setCellValue(temp.getInt(2));

                Cell cell3 = row.createCell(5);
                //cell3.setCellValue(ps.getInt(3));
                cell3.setCellValue(0);

                Cell cell4 = row.createCell(6);
                //cell4.setCellValue(ps.getInt(4));
                cell4.setCellValue(ps.getInt(2));

                rowNum++;
            }
        }

        ps.close();
        dataBaseHelper.close();


        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pVerkäufer + "_" + pOrt +".xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







    //Adminstartions Bereich:
        //Import Bereich:

    public void ImportProdukte(DataBaseHelper dataBaseHelper) {
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "LGMBackProdukte.xls")));

            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            String pname;
            long barcode;
            double menge;

            Cell currentCell;

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                currentCell = cellIterator.next();
                pname = currentCell.getStringCellValue();

                currentCell = cellIterator.next();
                barcode = (long) currentCell.getNumericCellValue();

                currentCell = cellIterator.next();
                menge = currentCell.getNumericCellValue();


                dataBaseHelper.addProdukt(pname, String.valueOf(menge), String.valueOf(barcode));
            }
            dataBaseHelper.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //Adminstartions Bereich:
    //Export Bereich:

    public void ExportAll(DataBaseHelper dataBaseHelper) {
        HSSFWorkbook workbook = new HSSFWorkbook();

        Cursor ps;

        HSSFSheet sheet1 = workbook.createSheet();
        workbook.setSheetName(workbook.getSheetIndex(sheet1), "Produkt_Table");
        ps = dataBaseHelper.getProduktListContent();

        if (ps.getCount() == 0) {
            Row row = sheet1.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet1.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getString(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getInt(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getString(3));

                rowNum++;
            }
        }

        HSSFSheet sheet2 = workbook.createSheet();
        workbook.setSheetName(workbook.getSheetIndex(sheet2), "Verkaeufer_Table");
        ps = dataBaseHelper.getVerkaeuferListContent();

        if (ps.getCount() == 0) {
            Row row = sheet2.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet2.createRow(rowNum + 5);

                Cell cell0 = row.createCell(2);
                cell0.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getString(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getString(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getString(3));

                Cell cell4 = row.createCell(6);
                cell4.setCellValue(ps.getString(4));

                Cell cell5 = row.createCell(7);
                cell5.setCellValue(ps.getString(5));

                Cell cell6 = row.createCell(8);
                cell6.setCellValue(ps.getString(6));

                rowNum++;
            }
        }

        HSSFSheet sheet3 = workbook.createSheet();
        workbook.setSheetName(workbook.getSheetIndex(sheet3), "Sortiment_Table");
        ps = dataBaseHelper.getSortimentListContent();

        if (ps.getCount() == 0) {
            Row row = sheet3.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet3.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getString(2));

                rowNum++;
            }
        }

        HSSFSheet sheet4 = workbook.createSheet();
        workbook.setSheetName(workbook.getSheetIndex(sheet4), "ProduktSortiment_Table");
        ps = dataBaseHelper.getProduktSortimentContentAll();

        if (ps.getCount() == 0) {
            Row row = sheet4.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet4.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getInt(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getInt(3));

                rowNum++;
            }
        }

        HSSFSheet sheet5 = workbook.createSheet();
        workbook.setSheetName(workbook.getSheetIndex(sheet5), "Bestellungen_Table");
        ps = dataBaseHelper.getBestellListContent();

        if (ps.getCount() == 0) {
            Row row = sheet5.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet5.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getString(2));

                rowNum++;
            }
        }

        HSSFSheet sheet6 = workbook.createSheet();
        workbook.setSheetName(workbook.getSheetIndex(sheet6), "ProduktBestellungen_Table");
        ps = dataBaseHelper.getProduktBestellungContentAll();

        if (ps.getCount() == 0) {
            Row row = sheet6.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet6.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getInt(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getInt(3));

                rowNum++;
            }
        }

        ps.close();
        dataBaseHelper.close();


        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "LGM_BackUp.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExportTableProdukte(DataBaseHelper dataBaseHelper) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.getProduktListContent();

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getString(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getInt(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getString(3));

                rowNum++;
            }
        }
        ps.close();
        dataBaseHelper.close();

        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Produkt_Table.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ExportTableVerkaeufer(DataBaseHelper dataBaseHelper) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.getVerkaeuferListContent();

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum + 5);

                Cell cell0 = row.createCell(2);
                cell0.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getString(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getString(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getString(3));

                Cell cell4 = row.createCell(6);
                cell4.setCellValue(ps.getString(4));

                Cell cell5 = row.createCell(7);
                cell5.setCellValue(ps.getString(5));

                Cell cell6 = row.createCell(8);
                cell6.setCellValue(ps.getString(6));

                rowNum++;
            }
        }

        ps.close();
        dataBaseHelper.close();



        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Verkaeufer_Table.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ExportTableSortimente(DataBaseHelper dataBaseHelper) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.getSortimentListContent();

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getString(2));

                rowNum++;
            }
        }

        ps.close();
        dataBaseHelper.close();

        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Sortiment_Table.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExportTableSortimentProdukte(DataBaseHelper dataBaseHelper) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.getProduktSortimentContentAll();

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getInt(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getInt(3));

                rowNum++;
            }
        }

        ps.close();
        dataBaseHelper.close();


        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "SortimentProdukt_Table.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExportTableBestellungen(DataBaseHelper dataBaseHelper) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.getBestellListContent();

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getString(2));

                rowNum++;
            }
        }

        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Bestellung_Table.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExportTableBestellungProdukte(DataBaseHelper dataBaseHelper) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.getProduktBestellungContentAll();

        if (ps.getCount() == 0) {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        } else {
            int rowNum = 0;
            while (ps.moveToNext()) {
                Row row = sheet.createRow(rowNum + 5);

                Cell cell = row.createCell(2);
                cell.setCellValue(ps.getInt(0));

                Cell cell1 = row.createCell(3);
                cell1.setCellValue(ps.getInt(1));

                Cell cell2 = row.createCell(4);
                cell2.setCellValue(ps.getInt(2));

                Cell cell3 = row.createCell(5);
                cell3.setCellValue(ps.getInt(3));

                rowNum++;
            }
        }

        try {
            workbook.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "BestellProdukt_Table.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
