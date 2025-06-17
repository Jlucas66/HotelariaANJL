package com.example.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Funcionario;

public class FuncionarioRepository {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void adicionar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public List<Funcionario> listar() {
        return funcionarios;
    }

    public Funcionario buscarPorCpf(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }

    public boolean remover(String cpf) {
        return funcionarios.removeIf(f -> f.getCpf().equals(cpf));
    }
}