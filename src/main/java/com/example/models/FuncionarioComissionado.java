package com.example.models;

import com.example.enums.Cargo;

public class FuncionarioComissionado extends Funcionario {
    private double reservasFeitas;
    private double taxaPorReserva;

    public FuncionarioComissionado(String usuario, String nome, String cpf, String email, String senha, Cargo cargo, double reservasFeitas, double taxaPorReserva) {
        super(usuario, nome, cpf, email, senha, cargo);
        this.reservasFeitas = reservasFeitas;
        this.taxaPorReserva = taxaPorReserva;
    }

    @Override
    public double calcularSalario() {
        return reservasFeitas * taxaPorReserva;
    }

    public double getReservasFeitas() {
        return reservasFeitas;
    }
    public void setReservasFeitas(double reservasFeitas) {
        this.reservasFeitas = reservasFeitas;
    }
    public double getTaxaPorReserva() {
        return taxaPorReserva;
    }
    public void setTaxaPorReserva(double taxaPorReserva) {
        this.taxaPorReserva = taxaPorReserva;
    }    
    
}
