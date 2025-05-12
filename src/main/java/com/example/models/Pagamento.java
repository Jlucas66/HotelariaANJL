package com.example.models;

import com.example.models.Reserva;
import com.example.models.Hospede;

public class Pagamento {
    private Enum formaDePagamento;
    private Enum servicosAdicionais;
    private Double valorTotal;
    private Reserva reserva;
    private Hospede hospede;
    private String status;

    public Pagamento(Enum formaDePagamento, Enum servicosAdicionais, Double valorTotal, Reserva reserva, Hospede hospede) {
        this.formaDePagamento = formaDePagamento;
        this.servicosAdicionais = servicosAdicionais;
        this.valorTotal = valorTotal;
        this.reserva = reserva;
        this.hospede = hospede;
        this.status = "Pendente";
    }
    public Enum getFormaDePagamento() {
        return formaDePagamento;
    }
    public void setFormaDePagamento(Enum formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
    public Enum getServicosAdicionais() {
        return servicosAdicionais;
    }
    public void setServicosAdicionais(Enum servicosAdicionais) {
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
}