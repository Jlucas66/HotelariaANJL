package com.example.models;

import java.io.Serializable;

import com.example.enums.FormaDePagamento;
import com.example.enums.ServicosAdicionais;

public class Pagamento implements Serializable {
    private FormaDePagamento formaDePagamento;
    private ServicosAdicionais servicosAdicionais;
    private Double valorTotal;
    private Reserva reserva;
    private Hospede hospede;
    private String status;
    private int id;

    public Pagamento(FormaDePagamento formaDePagamento, ServicosAdicionais servicosAdicionais,
            Double valorTotal, Reserva reserva, Hospede hospede) {
        this.id = 0;
        this.formaDePagamento = formaDePagamento;
        this.servicosAdicionais = servicosAdicionais;
        this.valorTotal = valorTotal;
        this.reserva = reserva;
        this.hospede = hospede;
        this.status = "Pendente";
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public ServicosAdicionais getServicosAdicionais() {
        return servicosAdicionais;
    }

    public void setServicosAdicionais(ServicosAdicionais servicosAdicionais) {
        this.servicosAdicionais = servicosAdicionais;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[ID=" + id +
                ", Forma de pagamento=" + formaDePagamento +
                ", Servicos adicionais" + servicosAdicionais +
                ", Valor total=" + valorTotal +
                ", Status=" + status +
                ", Hospede=" + (hospede != null ? hospede.getNome() : "N/A") +
                ", ReservaID=" + (reserva != null ? reserva.getId() : "N/A") +
                "]";

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pagamento other = (Pagamento) obj;
        return id == other.id;
    }

}