package com.example.controllers;

import com.example.models.Reserva;
import com.example.repositories.ReservaRepository;

public class ReservaController implements IReservaController {

    private ReservaRepository reservaRepository;

    public ReservaController() {
        this.reservaRepository = ReservaRepository.getInstance();
    }

    public void cadastrarReserva(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    public Reserva buscarReservaPorId(int id) {
        return reservaRepository.findById(id);
    }

    public void removerReserva(int id) {
        reservaRepository.delete(id);
    }

    public void atualizarReserva(Reserva reserva) {
        reservaRepository.update(reserva);
    }

    public Reserva[] listarTodasReservas() {
        return reservaRepository.findAll();
    }
}
