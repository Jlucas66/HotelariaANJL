package com.example.models;

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
}
