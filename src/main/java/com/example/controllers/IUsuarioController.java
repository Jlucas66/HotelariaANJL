package com.example.controllers;

import com.example.enums.Cargo;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Usuario; 

public interface IUsuarioController {
    Usuario[] listarTodosUsuarios() throws DadosInvalidosException;
    Usuario buscarPorId(int id) throws DadosInvalidosException;
    void cadastrarFuncionarioFixo(String nome, String cpf, String email, Cargo cargo, double salarioFixo) throws DadosInvalidosException;
    void cadastrarFuncionarioComissionado(String nome, String cpf, String email, Cargo cargo, double reservasFeitas, double taxaPorReserva) throws DadosInvalidosException;
    void cadastrarFuncionarioFixoMaisComissao(String nome, String cpf, String email, Cargo cargo, double salarioFixo, double reservasFeitas, double taxaPorReserva) throws DadosInvalidosException;
    void salvar(Usuario usuario) throws DadosInvalidosException;
    void deletar(int id) throws DadosInvalidosException;
    void atualizar(Usuario usuario) throws DadosInvalidosException;
}
