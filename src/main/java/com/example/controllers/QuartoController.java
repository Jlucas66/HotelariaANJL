package com.example.controllers;

import com.example.models.ItemReserva;

import com.example.enums.TipoQuarto;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Quarto;
import com.example.models.Reserva;
import com.example.repositories.QuartoRepository;
import com.example.repositories.ReservaRepository;

public class QuartoController {
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

    public boolean podeExcluirQuarto(int quartoId) {
        return !quartoRepository.temReservaAtiva(quartoId);
    }

}
