package com.example.models;

import com.example.repositories.ReservaRepository;

public class ItemReserva {
    private Quarto quarto;
    private int quantidadeDeDias;
    private double precoTotal;

    public ItemReserva(Quarto quarto, int quantidadeDeDias) {
        this.quarto = quarto;
        this.quantidadeDeDias = quantidadeDeDias;
        this.precoTotal = quarto.getPreco() * quantidadeDeDias;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public int getQuantidadeDeDias() {
        return quantidadeDeDias;
    }

    public void setQuantidadeDeDias(int quantidadeDeDias) {
        this.quantidadeDeDias = quantidadeDeDias;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    @Override
    public String toString() {
        return "ItemReserva [quarto=" + quarto + ", quantidadeDeDias=" + quantidadeDeDias + ", precoTotal=" + precoTotal
                + "]";
    }

    


}
