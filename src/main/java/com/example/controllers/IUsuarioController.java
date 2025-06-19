package com.example.controllers;

import com.example.enums.Cargo;
import com.example.models.Usuario; 

public interface IUsuarioController {
    Usuario[] listarTodosUsuarios();
    Usuario buscarPorId(int id);
    void cadastrarFuncionario(String nome, String cpf, String email, Cargo cargo);
    void salvar(Usuario usuario);
    void deletar(int id);
    void atualizar(Usuario usuario);
}
