package com.appspring.app.view;



	import java.util.List;
	import java.util.Map;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.CellStyle;
	import org.apache.poi.ss.usermodel.Font;
	import org.apache.poi.ss.usermodel.HorizontalAlignment;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.springframework.stereotype.Component;
	import org.springframework.web.servlet.view.document.*;

	import com.appspring.app.entity.Servicios;



	@Component("listarServiciosExcel")

	public class ListadoServiciosExcel  extends AbstractXlsxView {

		private static final String[] header = { "Id", "Nombre" };

		@Override
		protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			@SuppressWarnings("unchecked")
			List<Servicios> listadoServicios = (List<Servicios>) model.get("servicios");

			Sheet sheet = workbook.createSheet("ListadoServicios");
			sheet.setFitToPage(true);

			// EstiloNegrillaCentrado
			Font font = workbook.createFont();
			font.setBold(true);
			CellStyle style = workbook.createCellStyle();
			style.setFont(font);
			style.setAlignment(HorizontalAlignment.CENTER);

			Row rowHeader = sheet.createRow(0);

			for (int i = 0; i < header.length; i++) {
				// sheet.setColumnWidth(i, 3000);
				Cell cell = rowHeader.createCell(i);
				cell.setCellValue(header[i]);
				cell.setCellStyle(style);
			}

			int rowCount = 1;

		

			for (Servicios servicios : listadoServicios) {
				Row row = sheet.createRow(rowCount++);
				row.createCell(0).setCellValue(servicios.getId());
				row.createCell(1).setCellValue(servicios.getNombre());
			;
			}

			sheet.setColumnWidth(0, 3000);
			sheet.setColumnWidth(4, 3000);

			for (int i = 1; i <= 3; i++) {
				sheet.autoSizeColumn(i);
			}

			response.setHeader("Content-Disposition", "attachment; filename=listadoServiciosExcel.xlsx");

		}

	}
