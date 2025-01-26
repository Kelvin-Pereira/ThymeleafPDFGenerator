package com.example.demo.service;

import com.example.demo.service.pdf.TemplateToPdfConverter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RelatorioDeAtividadeService {


    @Resource
    private TemplateToPdfConverter templateToPdfConverter;

    public byte[] baixarRelatorioDeAtividades() {
        try {
            return gerarRelatorioPDF();
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatorio de atividades");
        }
        return null;
    }

    private byte[] gerarRelatorioPDF() {
        var parametros = new HashMap<String, Object>();
        parametros.put("cpf", "123.456.789-10");
        return templateToPdfConverter.convert("relatorio.html", parametros);
    }

}
