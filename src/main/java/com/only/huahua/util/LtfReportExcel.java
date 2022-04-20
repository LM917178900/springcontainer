package com.only.huahua.util;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: LtfReportExcel
 * @author: leiming5
 * @date: 2022/2/23 15:47
 */
@Component
public class LtfReportExcel {

    Workbook workbook;
    String fileName = "LTF Report";
    Sheet sheet;

    public void download(HttpServletResponse response) {

        String dateStr = "lemin test";
        response.setHeader("content-disposition", "attachment;filename=sheet_" + dateStr + ".xlsx");
        response.setContentType("application/octet-stream");

        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(fileName);
        sheet.protectSheet("123456");// 锁定全表

        Cell cell = sheet.createRow(0).createCell(0);
        setCell(cell);

        sheet.createRow(1).createCell(0).setCellValue("test2");


        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCell(Cell cell) {
        RichTextString richStringCellValue = setText();
        cell.setCellValue(richStringCellValue);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setLocked(false);// 解锁当前；
        cell.setCellStyle(cellStyle);
    }


    public XSSFRichTextString setText(){

        XSSFRichTextString textString = new XSSFRichTextString();
        textString.append("0123456789012345678901234567890");
        textString.append("\n");

        Font font0 = buildFont(workbook, (short) 10, true, IndexedColors.BROWN.getIndex());
        textString.applyFont(3,13,font0);
        textString.append("\n");

        Font font = buildFont(workbook, (short) 10, false, IndexedColors.BLACK.getIndex());
        textString.append("color1", (XSSFFont) font);
        textString.append("\n");

        Font font2 = buildFont(workbook, (short) 16, false, IndexedColors.RED.getIndex());
        textString.append("color1", (XSSFFont) font2);
        textString.append("\n");

        textString.append("END !");

        return textString;
    }

    public  Font buildFont(Workbook workbook, short fontSize, boolean isBold, short fontColor) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setBold(isBold);
        font.setColor(fontColor);
        return font;
    }

}
