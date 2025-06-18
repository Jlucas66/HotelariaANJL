package com.example.controllers;

import com.example.models.Quarto;
import com.example.repositories.QuartoRepository;

public class QuartoController implements IQuartoController {
    private QuartoRepository quartoRepository;

    public QuartoController() {
        this.quartoRepository = QuartoRepository.getInstance();
    }

    public Quarto[] listarTodosQuarto() {
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

}
