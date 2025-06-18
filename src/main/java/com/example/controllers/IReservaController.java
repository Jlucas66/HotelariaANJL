package com.example.controllers;

import com.example.models.Reserva;

public interface IReservaController {
    void cadastrarReserva(Reserva reserva);
    Reserva buscarReservaPorId(int id);
    void removerReserva(int id);
    void atualizarReserva(Reserva reserva);
}