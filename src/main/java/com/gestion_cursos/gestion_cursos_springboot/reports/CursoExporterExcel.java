package com.gestion_cursos.gestion_cursos_springboot.reports;

import com.gestion_cursos.gestion_cursos_springboot.entity.Curso;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.util.List;

public class CursoExporterExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Curso> cursos;

    public CursoExporterExcel(List<Curso> cursos){
        this.cursos = cursos;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Cursos");
    }

    private void writeHeaderLine(){
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Título", style);
        createCell(row, 2, "Descripción", style);
        createCell(row, 3, "Nivel", style);
        createCell(row, 4, "Estado de publicación", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style){
        Cell cell = row.createCell(columnCount);

        if(value instanceof Integer){
            cell.setCellValue((Integer)value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean)value);
        } else {
            cell.setCellValue(value.toString());
        }

        cell.setCellStyle(style);
    }

    private void writeDataLines(){
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Curso curso : cursos) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, curso.getId(), style);
            createCell(row, columnCount++, curso.getTitulo(), style);
            createCell(row, columnCount++, curso.getDescripcion(), style);
            createCell(row, columnCount++, curso.getNivel(), style);
            createCell(row, columnCount++, curso.isPublicado(), style);
        }
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=cursos.xlsx");
        response.setCharacterEncoding("UTF-8");

        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
