package com.example.repositories;

import com.example.models.Funcionario;

public class FuncionarioRepository implements IRepositories {
    private static FuncionarioRepository instance;

    private Funcionario[] funcionarios;
    int contador;

    private FuncionarioRepository() {
        contador = 0;
    }

    public static FuncionarioRepository getInstance() {
        if (instance == null) {
            instance = new FuncionarioRepository();
        }
        return instance;
    }

    @Override
    public Object[] findAll() {
        Funcionario[] funcionarios = new Funcionario[contador]; 
        for (int i = 0; i< contador; i++) {
            funcionarios[i] = this.funcionarios[i];
        }
        return funcionarios;
    }

    @Override
    public Object findById(int id) {
        for (int i = 0; i < contador; i++) {
            if (funcionarios[i].getId().equals(String.valueOf(id))) {
                return funcionarios[i];
            }
        }
        return null;
    }

    @Override
    public void save(Object entity) {
        if (contador >= funcionarios.length) {
            System.out.println("O repositório está cheio!");
            return;
        }
        if (entity instanceof Funcionario) {
            Funcionario funcionario = (Funcionario) entity;
            funcionario.setId(String.valueOf(contador + 1)); // Simplesmente incrementa o contador para o ID
            funcionarios[contador++] = funcionario;
            System.out.println("Funcionário salvo com ID: " + funcionario.getId());
        } else {
            System.out.println("Objeto inválido, não é um Funcionario.");
        }
    }

    @Override
    public void delete(int id) {
        int indexToRemove = -1;
        for (int i = 0; i < contador; i++) {
            if (funcionarios[i].getId().equals(String.valueOf(id))) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            for (int i = indexToRemove; i < contador - 1; i++) {
                funcionarios[i] = funcionarios[i + 1];
            }
            funcionarios[contador - 1] = null;
            contador--;
            System.out.println("Funcionário com ID " + id + " removido.");
        } else {
            System.out.println("Funcionário com ID " + id + " não encontrado.");
        }
    }

    @Override
    public void update(Object entity) {
        if (entity instanceof Funcionario) {
            Funcionario funcionario = (Funcionario) entity;
            for (int i = 0; i < contador; i++) {
                if (funcionarios[i].getId().equals(funcionario.getId())) {
                    funcionarios[i] = funcionario;
                    System.out.println("Funcionário com ID " + funcionario.getId() + " atualizado.");
                    return;
                }
            }
            System.out.println("Funcionário com ID " + funcionario.getId() + " não encontrado.");
        } else {
            System.out.println("Objeto inválido, não é um Funcionario.");
        }
    }
}
