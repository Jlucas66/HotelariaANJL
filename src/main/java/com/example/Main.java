package com.example;

import com.example.controllers.*;
import com.example.models.Hospede;
import com.example.models.Pagamento;
import com.example.models.Quarto;
import com.example.models.Reserva;

public class Main {
    public static void main(String[] args) {
        // Instanciando controllers
        PagamentoController pagamentoController = new PagamentoController();
        QuartoController quartoController = new QuartoController();
        ReservaController reservaController = new ReservaController();

        // Criando e salvando um quarto
        Quarto quarto = new Quarto(0, null, 0, null, 0);
        quartoController.salvar(quarto);

        // Listando quartos
        System.out.println("Quartos cadastrados:");
        for (Quarto q : quartoController.listarTodosQuarto()) {
            if (q != null) System.out.println(q);
        }

         // Criando um hóspede
        Hospede hospede = new Hospede("usuario1", "João Silva", "12345678900", "joao@email.com", "81999999999", "Rua A, 123", "senha123");

        // Criando e salvando uma reserva
        Reserva reserva = new Reserva(0, null, null, hospede, 0, null);
        reservaController.cadastrarReserva(reserva);

        // Listando reservas
        System.out.println("\nReservas cadastradas:");
        for (Reserva r : reservaController.listarTodasReservas()) {
            if (r != null) System.out.println(r);
        }

        // Criando e salvando um pagamento
        Pagamento pagamento = new Pagamento(0, null, null, null, reserva, null);
        pagamentoController.salvar(pagamento);

        // Listando pagamentos
        System.out.println("\nPagamentos cadastrados:");
        for (Pagamento p : pagamentoController.listarTodasPagamento()) {
            if (p != null) System.out.println(p);
        }

        // Buscar por ID
        System.out.println("\nBuscar quarto por ID 1: " + quartoController.buscarPorId(1));
        System.out.println("Buscar reserva por ID 1: " + reservaController.buscarReservaPorId(1));
        System.out.println("Buscar pagamento por ID 1: " + pagamentoController.buscarPorId(1));

        // Atualizar um quarto
        quarto.setPreco(550.0);
        quartoController.atualizar(quarto);
        System.out.println("\nQuarto atualizado: " + quartoController.buscarPorId(1));

        // Remover reserva
        reservaController.removerReserva(1);
        System.out.println("\nReservas após remoção:");
        for (Reserva r : reservaController.listarTodasReservas()) {
            if (r != null) System.out.println(r);
        }
    }
}