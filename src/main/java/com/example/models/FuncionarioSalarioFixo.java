package com.example.models;

import com.example.enums.Cargo;

public class FuncionarioSalarioFixo extends Funcionario {
    private double salarioFixo;

    public FuncionarioSalarioFixo(String usuario, String nome, String cpf, String email, String senha, Cargo cargo, double salarioFixo) {
        super(usuario, nome, cpf, email, senha, cargo);
        this.salarioFixo = salarioFixo;
    }

    @Override
    public double calcularSalario() {
        return salarioFixo;
    }

    public double getSalarioFixo() {
        return salarioFixo;
    }

    public void setSalarioFixo(double salarioFixo) {
        this.salarioFixo = salarioFixo;
    }
    
}
