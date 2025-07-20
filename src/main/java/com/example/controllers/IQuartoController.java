package com.example.controllers;

import com.example.enums.TipoQuarto;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Quarto; 

public interface IQuartoController {
    Quarto[] listarTodosQuarto() throws DadosInvalidosException;
    Quarto buscarPorId(int id);
    void salvar(Quarto quarto);
    void deletar(int id);
    void atualizar(Quarto quarto);
    void criarQuarto(TipoQuarto tipoQuarto, int capacidade, double preco, int camas) throws DadosInvalidosException;
    
}
