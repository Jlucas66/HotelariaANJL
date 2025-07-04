package com.example.controllers;

import com.example.enums.Cargo;
import com.example.models.Usuario; 

public interface IUsuarioController {
    Usuario[] listarTodosUsuarios();
    Usuario buscarPorId(int id);
    void cadastrarFuncionarioFixo(String nome, String cpf, String email, Cargo cargo, double salarioFixo);
    void cadastrarFuncionarioComissionado(String nome, String cpf, String email, Cargo cargo, double reservasFeitas, double taxaPorReserva);
    void cadastrarFuncionarioFixoMaisComissao(String nome, String cpf, String email, Cargo cargo, double salarioFixo, double reservasFeitas, double taxaPorReserva);
    void salvar(Usuario usuario);
    void deletar(int id);
    void atualizar(Usuario usuario);
}
