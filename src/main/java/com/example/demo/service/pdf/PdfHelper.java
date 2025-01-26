package com.example.demo.service.pdf;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import lombok.experimental.UtilityClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@UtilityClass
public class PdfHelper {

	private static final float FONT_SIZE = 9;
	private static final float POSITION_Y = 10;
	private static final DeviceRgb COLOR_GRAY = new DeviceRgb(102, 102, 102);

	public static byte[] toPagedPdf(byte[] pdfBytes) throws Exception {
		var pdfBytesOutputStream = new ByteArrayOutputStream();
		try (
			var pdfDocument = new PdfDocument(
				new PdfReader(new ByteArrayInputStream(pdfBytes)),
				new PdfWriter(pdfBytesOutputStream)
			);
			var document = new Document(pdfDocument)
		) {
			var numberOfPages = pdfDocument.getNumberOfPages();
			for (var pageNumber = 1; pageNumber <= numberOfPages; pageNumber++) {
				var pdfPage = pdfDocument.getPage(pageNumber);
				var paragraph = new Paragraph("Página " + pageNumber + " de " + numberOfPages)
					.setFontSize(FONT_SIZE)
					.setFontColor(COLOR_GRAY)
					.setTextAlignment(TextAlignment.CENTER)
					.setFixedPosition(pageNumber, 0, POSITION_Y, pdfPage.getPageSize().getWidth());
				document.add(paragraph);
			}
		}
		return pdfBytesOutputStream.toByteArray();
	}

}
