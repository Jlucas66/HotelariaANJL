package com.example.controllers;

import com.example.models.ItemReserva;
import com.example.models.Quarto;
import com.example.models.Reserva;
import com.example.repositories.QuartoRepository;
import com.example.repositories.ReservaRepository;

public class ReservaController {

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

    public void adicionarItemReserva(int reservaId, Quarto quarto, int dias) {
        Reserva reserva = reservaRepository.findById(reservaId);
        reserva.adicionarItem(quarto, dias);
        reservaRepository.save(reserva);
    }

    public boolean reservarQuartos(Reserva reserva) {
        QuartoRepository quartoRepository = QuartoRepository.getInstance();
        for (ItemReserva item : reserva.getItensReserva()) {
            Quarto quarto = item.getQuarto();
            if (!quartoRepository.temReservaAtiva(quarto.getId())) {
                Quarto[] disponiveis = quartoRepository.findDisponiveisPorPeriodo(reserva.getDataEntrada(),
                        reserva.getDataSaida());
                boolean disponivel = false;
                for (Quarto q : disponiveis) {
                    if (q.getId() == quarto.getId()) {
                        disponivel = true;
                        break;
                    }
                }
                if (!disponivel) {
                    return false;
                }
            } else {
                return false;
            }
        }
        reservaRepository.save(reserva);
        return true;
    }

    public void confirmarReserva(int reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId);
        if (reserva != null) {
            reserva.setStatus("Confirmada");
            reservaRepository.update(reserva);
        }
    }

    public void cancelarReserva(int reservaId, boolean reembolso) {
        Reserva reserva = reservaRepository.findById(reservaId);
        if (reserva != null) {
            reserva.setStatus("Cancelada");
            // lógica de reembolso aqui, se necessário
            reservaRepository.update(reserva);
        }
    }

}
