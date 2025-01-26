package com.example.demo.controller;

import com.example.demo.service.RelatorioDeAtividadeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("relatorios")
public class RelatoriosController {

    @Resource
    private RelatorioDeAtividadeService relatorioDeAtividadeService;

    @GetMapping(path = "atividades",  produces = "application/octet-stream")
    public ResponseEntity<ByteArrayResource> baixarRelatorioDeAtividades() {

        byte[] pdfBytes = relatorioDeAtividadeService.baixarRelatorioDeAtividades();
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        // Configura os cabeçalhos para download do arquivo
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_atividades.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
    }

}
