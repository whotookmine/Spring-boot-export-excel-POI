package com.fixdeecode.exporttoexcelinjavademo.domain;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProductExcelExportUtils {

    private List<ProductModel> productModels;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private CreationHelper createHelper;

    public ProductExcelExportUtils(List<ProductModel> productModels) {
        this.productModels = productModels;
        workbook = new XSSFWorkbook();
        this.createHelper = workbook.getCreationHelper();
    }

    private void createCell(Row rowIndex, int columnIndex, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnIndex);
        Cell cell = rowIndex.createCell(columnIndex);
        switch (value.getClass().getSimpleName()) {
            case "Integer" -> cell.setCellValue((Integer) value);
            case "String" -> cell.setCellValue((String) value);
            case "BigDecimal" -> cell.setCellValue(((BigDecimal) value).doubleValue());
            case "Date" -> cell.setCellValue((Date) value);
            default -> cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    private void createHeaderCell() {
        sheet = workbook.createSheet("Product Information");
        Row row_0 = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THICK);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        List<String> headerNames = Arrays.asList("ID", "Prod_Name", "Prod_Price", "In_Stock_Date");

        int columnIndex = 0;
        for (String headerName : headerNames) {
            createCell(row_0, columnIndex++, headerName, style);
        }
    }

    private void writeDataToExcel(){
        int rowIndex = 1;

        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLUE_GREY.getIndex());
        style.setRightBorderColor(IndexedColors.BLUE_GREY.getIndex());
        style.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setBorderBottom(BorderStyle.THIN);
        dateStyle.setBorderRight(BorderStyle.THIN);
        dateStyle.setBottomBorderColor(IndexedColors.BLUE_GREY.getIndex());
        dateStyle.setRightBorderColor(IndexedColors.BLUE_GREY.getIndex());
        dateStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        dateStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        dateStyle.setAlignment(HorizontalAlignment.RIGHT);

        for (ProductModel productModel : productModels) {
            int columnIndex = 0;
            XSSFRow row = sheet.createRow(rowIndex++);
            createCell(row,columnIndex++,productModel.getId(),style);
            createCell(row,columnIndex++,productModel.getName(),style);
            createCell(row,columnIndex++,productModel.getPrice(),style);
            createCell(row,columnIndex,productModel.getInStockDate(),dateStyle);
        }
    }

    public void exportToExcel(HttpServletResponse response) throws IOException {
        createHeaderCell();
        writeDataToExcel();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
