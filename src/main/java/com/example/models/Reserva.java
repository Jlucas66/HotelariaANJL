package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Reserva implements Serializable{
    private int id;
    private Date dataEntrada;
    private Date dataSaida;
    private Hospede hospedeResponsavel;
    private int numeroDeHospedes;
    private Double valorBase;
    private Pagamento pagamento;
    private boolean confirmada;
    private String status;
    private ArrayList<ItemReserva> itensReserva;

    public Reserva(Date dataEntrada, Date dataSaida, Hospede hospedeResponsavel, int numeroDeHospedes,
            Double valorBase, boolean confirmada) {
        this.id = 0;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.hospedeResponsavel = hospedeResponsavel;
        this.numeroDeHospedes = numeroDeHospedes;
        this.valorBase = valorBase;
        this.confirmada = false;
        this.itensReserva = new ArrayList<>();
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

    public ArrayList<ItemReserva> getItensReserva() {
        return itensReserva;
    }

    public void setQuartosReservado(ArrayList<ItemReserva> itensReserva) {
        this.itensReserva = itensReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

    @Override
    public String toString() {
        return "Reserva [ID=" + id +
                ", Hospede=" + hospedeResponsavel.getNome() +
                ", Nº Hospedes=" + numeroDeHospedes +
                ", Valor=" + valorBase +
                ", DataEntrada=" + dataEntrada +
                ", DataSaida=" + dataSaida +
                ", Itens Reservados=" + itensReserva.size() + "]";
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

    public void adicionarItem(Quarto quarto, int dias) {
        ItemReserva item = new ItemReserva(quarto, dias);
        itensReserva.add(item);
    }

    public double calcularValorTotal() {
        return itensReserva.stream().mapToDouble(ItemReserva::getPrecoTotal).sum();
    }

    public void setItensReserva(ArrayList<ItemReserva> itensReserva) {
        this.itensReserva = itensReserva;
    }

    public enum StatusReserva {
    PENDENTE, CONFIRMADA, CANCELADA, EM_ANDAMENTO, FINALIZADA
}
    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

}