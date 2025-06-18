package com.example.models;

import java.util.ArrayList;
import java.util.Date;

public class Reserva {
    private int id;
    private Date dataEntrada;
    private Date dataSaida;
    private Hospede hospedeResponsavel;
    private int numeroDeHospedes;
    private Double valorBase;
    private Pagamento pagamento;
    private ArrayList<Quarto> quartosReservado;

    public Reserva(int id, Date dataEntrada, Date dataSaida, Hospede hospedeResponsavel, int numeroDeHospedes,
            Double valorBase) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.hospedeResponsavel = hospedeResponsavel;
        this.numeroDeHospedes = numeroDeHospedes;
        this.valorBase = valorBase;
        this.quartosReservado = new ArrayList<>();
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Hospede getHospedeResponsavel() {
        return hospedeResponsavel;
    }

    public void setHospedeResponsavel(Hospede hospedeResponsavel) {
        this.hospedeResponsavel = hospedeResponsavel;
    }

    public int getNumeroDeHospedes() {
        return numeroDeHospedes;
    }

    public void setNumeroDeHospedes(int numeroDeHospedes) {
        this.numeroDeHospedes = numeroDeHospedes;
    }

    public Double getValorBase() {
        return valorBase;
    }

    public void setValorBase(Double valorBase) {
        this.valorBase = valorBase;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public ArrayList<Quarto> getQuartosReservado() {
        return quartosReservado;
    }

    public void setQuartosReservado(ArrayList<Quarto> quartosReservado) {
        this.quartosReservado = quartosReservado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reserva [ID=" + id +
                ", Hospede=" + hospedeResponsavel.getNome() +
                ", Nº Hospedes=" + numeroDeHospedes +
                ", Valor=" + valorBase +
                ", DataEntrada=" + dataEntrada +
                ", DataSaida=" + dataSaida +
                ", Quartos=" + quartosReservado.size() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reserva other = (Reserva) obj;
        return id == other.id;
    }

}