package com.example.demo.service.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.Locale;
import java.util.Map;

@Component
public class TemplateToPdfConverter {

    @Resource
    private SpringTemplateEngine templateEngine;

    public byte[] convert(String templateName, Map<String, Object> parameters) {
        try {
            var pdfBytes = convertHtml(proccess(templateName, parameters));
            return PdfHelper.toPagedPdf(pdfBytes);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar gerar o relatório.", e);
        }
    }

    private byte[] convertHtml(String content) {
        var pdfBytesOutputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(content, pdfBytesOutputStream, new ConverterProperties());
        return pdfBytesOutputStream.toByteArray();
    }

    private String proccess(String templateName, Map<String, Object> parameters) {
        return templateEngine.process(templateName, new Context(Locale.forLanguageTag("pt-BR"), parameters));
    }

}
