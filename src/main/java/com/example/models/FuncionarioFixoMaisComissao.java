package com.example.models;

import com.example.enums.Cargo;

public class FuncionarioFixoMaisComissao extends Funcionario {
    private double salarioBase;
    private double reservasFeitas;
    private double taxaPorReserva;

    public FuncionarioFixoMaisComissao(String usuario, String nome, String cpf, String email, String senha, Cargo cargo, double salarioFixo, double reservasFeitas, double taxaPorReserva) {
        super(usuario, nome, cpf, email, senha, cargo);
        this.salarioBase = salarioFixo;
        this.reservasFeitas = reservasFeitas;
        this.taxaPorReserva = taxaPorReserva;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (reservasFeitas * taxaPorReserva);
    }

    public double getSalarioFixo() {
        return salarioBase;
    }

    public void setSalarioFixo(double salarioFixo) {
        this.salarioBase = salarioFixo;
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
