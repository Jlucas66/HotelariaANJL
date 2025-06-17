package com.example.models;

public class Relatorio {

    public Relatorio gerarRelatorioFaturamento() {
        System.out.println("Gerando relatório de faturamento...");
        return new Relatorio();
    }

    public Relatorio gerarRelatorio() {
        System.out.println("Gerando relatório geral...");
        return new Relatorio();
    }

    public Relatorio gerarRelatorioOcupacaoDeQuartos() {
        System.out.println("Gerando relatório de ocupação de quartos...");
        return new Relatorio();
    }

    public Relatorio gerarRelatorioDeCancelamento() {
        System.out.println("Gerando relatório de cancelamentos...");
        return new Relatorio();
    }
}
