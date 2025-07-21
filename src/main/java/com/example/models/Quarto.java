package com.example.models;

import java.io.Serializable;

import com.example.enums.TipoQuarto;

public class Quarto implements Serializable{
    private TipoQuarto tipoQuarto;
    private int capacidadeDeHospedes;
    private Double preco;
    private int numeroDeCamas;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quarto(TipoQuarto tipoQuarto, int capacidadeDeHospedes, double preco, int numeroDeCamas) {
        this.id = 0;
        this.tipoQuarto = tipoQuarto;
        this.capacidadeDeHospedes = capacidadeDeHospedes;
        this.preco = preco;
        this.numeroDeCamas = numeroDeCamas;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getCapacidadeDeHospedes() {
        return capacidadeDeHospedes;
    }

    public void setCapacidadeDeHospedes(int capacidadeDeHospedes) {
        this.capacidadeDeHospedes = capacidadeDeHospedes;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getNumeroDeCamas() {
        return numeroDeCamas;
    }

    public void setNumeroDeCamas(int numeroDeCamas) {
        this.numeroDeCamas = numeroDeCamas;
    }

    @Override
    public String toString() {
        return "Quarto [ID=" + id +
                ", Capacidade de Hospedes=" + capacidadeDeHospedes +
                ", Preço=" + preco +
                ", Numero De camas=" + numeroDeCamas + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quarto other = (Quarto) obj;
        return id == other.id;
    }
}