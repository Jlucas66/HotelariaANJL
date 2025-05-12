package com.example.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Quarto {
    private Enum tipoQuarto;
    private int capacidadeDeHospedes;
    private Double preco;
    private int numeroDeCamas;
    private ArrayList<Reserva> reservas;

    public Quarto(Enum tipoQuarto, int capacidadeDeHospedes, Double preco, int numeroDeCamas) {
        this.tipoQuarto = tipoQuarto;
        this.capacidadeDeHospedes = capacidadeDeHospedes;
        this.preco = preco;
        this.numeroDeCamas = numeroDeCamas;
        this.reservas = new ArrayList<>();
    }
    public Enum getTipoQuarto() {
        return tipoQuarto;
    }
    public void setTipoQuarto(Enum tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }
    public int getCapacidadeDeHospedes() {
        return capacidadeDeHospedes;
    }
    public void setCapacidadeDeHospedes(int capacidadeDeHospedes) {
        this.capacidadeDeHospedes = capacidadeDeHospedes;
    }
    public Double getPreco() {
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
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
}