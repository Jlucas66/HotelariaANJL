package com.example.controllers;

import com.example.models.Quarto;

public interface IQuartoController {
    Quarto[] listarTodosQuarto();
    Quarto buscarPorId(int id);
    void salvar(Quarto quarto);
    void deletar(int id);
    void atualizar(Quarto quarto);
}