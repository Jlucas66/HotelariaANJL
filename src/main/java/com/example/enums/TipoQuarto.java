package com.example.enums;

public enum TipoQuarto {
    SINGLE("Single", 1, 100.0),
    DOUBLE("Double", 2, 150.0),
    SUITE("Suite", 4, 300.0),
    FAMILY("Family", 6, 450.0);

    private final String descricao;
    private final int capacidade;
    private final double preco;

    TipoQuarto(String descricao, int capacidade, double preco) {
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getPreco() {
        return preco;
    }

    
}
