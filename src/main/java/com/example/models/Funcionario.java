package com.example.models;

import com.example.enums.Cargo;

public class Funcionario extends Usuario {
    private String nome;
    private Enum cargo;
    private String cpf;
    private String email;
    private String senha;

    public Funcionario(String usuario, String nome, String cpf, String email, String senha, Cargo cargo) {
        super(usuario, email, senha);
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Enum getCargo() {
        return cargo;
    }
    public void setCargo(Enum cargo) {
        this.cargo = cargo;
    }
}