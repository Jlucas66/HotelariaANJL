package com.example.models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

import com.example.enums.TipoQuarto;
import com.example.repositories.PagamentoRepository;
import com.example.repositories.QuartoRepository;
import com.example.repositories.ReservaRepository;

public class Relatorio {

    public int gerarRelatorioReservasPorPeriodo(String dataInicio, String dataFim) {
    Reserva[] reservas = ReservaRepository.getInstance().findByPeriodo(dataInicio, dataFim);
    return reservas.length;
}

public double gerarRelatorioFaturamentoPorPeriodo(String dataInicio, String dataFim) {
    Pagamento[] pagamentos = PagamentoRepository.getInstance().findByPeriodo(dataInicio, dataFim);
    double total = 0;
    for (Pagamento p : pagamentos) {
        total += p.getValorTotal();
    }
    return total;
}

public double gerarRelatorioOcupacaoQuartos(String dataInicio, String dataFim, TipoQuarto tipo) {
    Quarto[] quartos = QuartoRepository.getInstance().findByTipo(tipo);
    int totalQuartos = quartos.length;
    int ocupados = 0;
    try {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date inicio = sdf.parse(dataInicio);
        java.util.Date fim = sdf.parse(dataFim);
        for (Quarto q : quartos) {
            if (ReservaRepository.getInstance().quartoOcupadoNoPeriodo(q, inicio, fim)) {
                ocupados++;
            }
        }
    } catch (Exception e) {
    }
    return totalQuartos == 0 ? 0 : (double) ocupados / totalQuartos * 100;
}

public int gerarRelatorioCancelamentos(String dataInicio, String dataFim) {
    Reserva[] reservas = ReservaRepository.getInstance().findCanceladasPorPeriodo(dataInicio, dataFim);
    return reservas.length;
}

public void salvarRelatorioEmPdf(String titulo, String conteudo, String nomeArquivo) {
    Document documento = new Document();
    try {
        PdfWriter.getInstance(documento, new FileOutputStream(nomeArquivo));
        documento.open();
        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font corpoFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        Paragraph tituloParagrafo = new Paragraph(titulo, tituloFont);
        tituloParagrafo.setAlignment(Element.ALIGN_CENTER);
        documento.add(tituloParagrafo);

        documento.add(new Paragraph(" "));
        documento.add(new Paragraph(conteudo, corpoFont));

    } catch (Exception e) {
        System.out.println("Erro ao gerar PDF: " + e.getMessage());
    } finally {
        documento.close();
    }
}
}
