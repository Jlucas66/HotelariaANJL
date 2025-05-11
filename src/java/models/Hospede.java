package com.example.hotelariaanjl.models;

import java.util.ArrayList;
import com.example.hotelariaanjl.models.Quarto;
import com.example.hotelariaanjl.models.Pagamento;
import com.example.hotelariaanjl.models.Hospede;

public class Hospede extends Usuario{
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    private ArrayList<Reserva> reserva;
    private ArrayList<Pagamento> pagamento;

    public Hospede(String usuario, String nome, String cpf, String email, String telefone, String endereco) {
        super(usuario, email, senha);
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.reserva = new ArrayList<>();
        this.pagamento = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Pagamento> getPagamentos() {
        return pagamento;
    }

    public void setPagamentos(ArrayList<Pagamento> pagamento) {
        this.pagamento = pagamento;
    }

    public ArrayList<Reserva> getReservas() {
        return reserva;
    }

    public void setReservas(ArrayList<Reserva> reserva) {
        this.reserva = reserva;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}