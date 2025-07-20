package com.example.controllers;

import com.example.enums.TipoQuarto;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Quarto;
import com.example.repositories.QuartoRepository;

public class QuartoController{
    private QuartoRepository quartoRepository;

    public QuartoController() {
        this.quartoRepository = QuartoRepository.getInstance();
    }

    public Quarto[] listarTodosQuarto() throws DadosInvalidosException {
        return quartoRepository.findAll();
    }

    public Quarto buscarPorId(int id) {
        return quartoRepository.findById(id);
    }

    public void salvar(Quarto quarto) {
        quartoRepository.save(quarto);
    }

    public void deletar(int id) {
        quartoRepository.delete(id);
    }

    public void atualizar(Quarto quarto) {
        quartoRepository.update(quarto);
    }

    public void criarQuarto(TipoQuarto tipoQuarto, int capacidade, double preco, int camas) throws DadosInvalidosException {
        System.out.println("Quarto criado");
    }

}
