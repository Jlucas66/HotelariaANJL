package com.example.controllers;

public interface IFuncionarioController {
    Object[] listarTodosFuncionarios();
    Object buscarPorId(int id);
    void salvar(Object funcionario);
    void deletar(int id);
    void atualizar(Object funcionario);
}
