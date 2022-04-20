package com.only.huahua.util;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @description: ExcelTest2
 * @author: leiming5
 * @date: 2022/2/23 19:33
 */
@Component
public class ExcelTest2 {
    XSSFWorkbook workBook = new XSSFWorkbook();
    XSSFSheet sheet = workBook.createSheet("sheet名称");

    public void export(HttpServletResponse response) {

        XSSFCellStyle lockstyle = workBook.createCellStyle();
        lockstyle.setLocked(true);
        lockstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        lockstyle.setFillForegroundColor(IndexedColors.RED.getIndex());

        //设置列格式，注释1
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 4000); //设置宽度
        }
        int num = 987654321;
        int otherNum = -87654321;

        for (int i = 0; i < 10; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                XSSFCell cell = row.createCell(j);

                XSSFCellStyle unlockstyle = workBook.createCellStyle();
                unlockstyle.setLocked(false);
                DataFormat format = workBook.createDataFormat();
                if (j == 0 || j == 1) {
                    unlockstyle.setDataFormat(format.getFormat("0,;[Red](0,)"));
                } else if (j == 2) {
                    unlockstyle.setDataFormat(format.getFormat("0.0,"));
                } else if (j == 3) {
                    unlockstyle.setDataFormat(format.getFormat("0,"));
                } else if (j == 4) {
                    unlockstyle.setDataFormat(format.getFormat("[Green]0.0,"));
                } else if (j == 5) {
                    unlockstyle.setDataFormat(format.getFormat("[Red]0.0,"));
                }  else if (j == 6) {
                    unlockstyle.setDataFormat(format.getFormat("00.0,"));
                } else {
                    unlockstyle.setDataFormat(format.getFormat("[Green]0,"));
                }
                cell.setCellStyle(unlockstyle);//默认是锁定状态；将所有单元格设置为：未锁定；然后再对需要上锁的单元格单独锁定


                if (i == 0) {//这里可以根据需要进行判断;我这就将第2列上锁了
                    cell.setCellValue(num);
                } else {
                    cell.setCellValue(otherNum);
                }
            }

        }
        //sheet添加保护，这个一定要否则光锁定还是可以编辑的
        sheet.enableLocking();
        CTSheetProtection sheetProtection = sheet.getCTWorksheet().getSheetProtection();
        sheetProtection.setSelectLockedCells(false);
        sheetProtection.setSelectUnlockedCells(false);
        sheetProtection.setFormatCells(true);
        sheetProtection.setFormatColumns(true);
        sheetProtection.setFormatRows(true);
        sheetProtection.setInsertColumns(true);
        sheetProtection.setInsertRows(false);
        sheetProtection.setInsertHyperlinks(true);
        sheetProtection.setDeleteColumns(true);
        sheetProtection.setDeleteRows(true);
        sheetProtection.setSort(false);
        sheetProtection.setAutoFilter(false);
        sheetProtection.setPivotTables(true);
        sheetProtection.setObjects(true);
        sheetProtection.setScenarios(true);


        try (OutputStream out = response.getOutputStream()) {
            workBook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
