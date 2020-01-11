package com.example.matthias.lgm_v1_dummy;


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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Excel_IO {

    public void ExportSortiment(DataBaseHelper dataBaseHelper, int pSNr) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.ExcelSortimentExport(pSNr);
        int numRows = ps.getCount();


        if(numRows == 0){
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        }else{
            int rowNum = 0;
            while (ps.moveToNext()){
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

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String name = "TestDatei.xls";
        path.mkdir();

        File file = new File(path, name);

        try {
            workbook.write(file);
            workbook.close();
            Log.d("Excel", "Erflgrei Gespeichert in: " + path);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Excel", "Fehler beim schpeichern der xls");
        }
    }

    public void ExportBestellung(DataBaseHelper dataBaseHelper, int pBNr, String pVerkäufer, String pOrt) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        Cursor ps = dataBaseHelper.ExcelBestellungExport(pBNr);
        int numRows = ps.getCount();


        if(numRows == 0){
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Keinen Eintrag gefunden");

        }else{
            int rowNum = 0;
            while (ps.moveToNext()){
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

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String name = pVerkäufer + "_" + pOrt + ".xls";
        path.mkdir();

        File file = new File(path, name);

        try {
            workbook.write(file);
            workbook.close();
            Log.d("Excel", "Erflgrei Gespeichert in: " + path);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Excel", "Fehler beim schpeichern der xls");
        }
    }


    public void ImportProdukte(DataBaseHelper dataBaseHelper){
        try {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String name = "LGMBackProdukte.xls";
            File file = new File(path, name);

            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new HSSFWorkbook(excelFile);
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
                System.out.println(currentCell.getStringCellValue());

                currentCell = cellIterator.next();
                barcode = (long) currentCell.getNumericCellValue();
                System.out.println(barcode);

                currentCell = cellIterator.next();
                menge = currentCell.getNumericCellValue();
                System.out.println(currentCell.getNumericCellValue());
                System.out.println();

                dataBaseHelper.addProdukt(pname, String.valueOf(menge), String.valueOf(barcode));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
