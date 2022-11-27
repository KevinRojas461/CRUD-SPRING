package com.appspring.app.view;



	import java.util.List;
	import java.util.Map;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.springframework.stereotype.Component;
	import org.springframework.web.servlet.view.document.AbstractPdfView;

	import com.appspring.app.entity.Servicios;
	import com.lowagie.text.Document;
	import com.lowagie.text.Element;
	import com.lowagie.text.PageSize;
	import com.lowagie.text.Phrase;
	import com.lowagie.text.pdf.PdfPCell;
	import com.lowagie.text.pdf.PdfPTable;
	import com.lowagie.text.pdf.PdfWriter;

	@Component("listarServicios")

	public class ListadoServiciosPdf extends AbstractPdfView {

		private static final String[] header = { "Id", "Nombre"};

		@Override
		protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
				HttpServletRequest request, HttpServletResponse response) throws Exception {

			@SuppressWarnings("unchecked")
			List<Servicios> listadoServicios = (List<Servicios>) model.get("servicios");

			document.setPageSize(PageSize.LETTER.rotate());
			document.open();

			PdfPTable tablaTitulo = new PdfPTable(1);
			PdfPCell celda = null;
			celda = new PdfPCell(new Phrase("Listado Servicios"));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);

			tablaTitulo.addCell(celda);
			tablaTitulo.setSpacingAfter(30);

			PdfPTable tablaServicios = new PdfPTable(2);

			for (int i = 0; i < header.length; i++) {
				tablaServicios.addCell(header[i]);
			}

			listadoServicios.forEach(servicios -> {

				tablaServicios.addCell(servicios.getId().toString());
				tablaServicios.addCell(servicios.getNombre());
				
			});

			document.add(tablaTitulo);
			document.add(tablaServicios);

		}
	}