package com.example.repositorios;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Hospede;

public class HospedeRepository {
    private List<Hospede> hospedes = new ArrayList<>();

    public void adicionar(Hospede hospede) {
        hospedes.add(hospede);
    }

    public List<Hospede> listar() {
        return hospedes;
    }

    public boolean remover(String cpf) {
        return hospedes.removeIf(h -> h.getCpf().equals(h.getCpf()));
    }

    public Hospede buscarHospedePorCpf(String cpf) {
        for (Hospede h : hospedes) {
            if (h.getCpf().equals(cpf)) {
                return h;
            }
        }
        return null;
    }

    //CRUD básico feito, caso nessecite de alguma outra funcionalidade, inserir depois.
    

}
