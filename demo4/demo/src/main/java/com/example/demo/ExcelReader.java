package com.example.demo;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    public static List<DataModel> readExcel(String filePath) throws IOException {
        List<DataModel> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // suppose data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();

            // Assuming the first row contains headers, skip it
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                DataModel dataModelOb = new DataModel();

                dataModelOb.setDate(getCellValue(row.getCell(0)));
                dataModelOb.setMonth(getMonthCellValue(row.getCell(1)));
                dataModelOb.setTeam(getCellValue(row.getCell(2)));
                dataModelOb.setPanelName(getCellValue(row.getCell(3)));
                dataModelOb.setRound(getCellValue(row.getCell(4)));
                dataModelOb.setSkill(getCellValue(row.getCell(5)));
                dataModelOb.setTime(getTimeCellValue(row.getCell(6)));
                dataModelOb.setCandidateCurrentLoc(getCellValue(row.getCell(7)));
                dataModelOb.setCandidatePreferredLoc(getCellValue(row.getCell(8)));
                dataModelOb.setCandidateName(getCellValue(row.getCell(9)));

                data.add(dataModelOb);
            }
        }

        return data;
    }

    private static String getCellValue(Cell cell) {
        return cell != null ? cell.toString() : "";
    }

    private static String getMonthCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    // Assuming the numeric value represents a date
                    // Format the date as "MMM-yy" using SimpleDateFormat
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yy");
                    return dateFormat.format(cell.getDateCellValue());
                default:
                    return "Oct-23";
            }
        } else {
            return "Nov-23";
        }
    }

    private static String getTimeCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    // Assuming the numeric value represents a date
                    // Format the date as "hh:mm a" using SimpleDateFormat
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    return dateFormat.format(cell.getDateCellValue());
                default:
                    return "";
            }
        } else {
            return "";
        }
    }
}
