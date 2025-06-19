package com.example;

import com.example.controllers.PagamentoController;
import com.example.controllers.QuartoController;
import com.example.controllers.ReservaController;
import com.example.controllers.UsuarioController;
import com.example.enums.Cargo;
import com.example.models.Hospede;
import com.example.models.Pagamento;
import com.example.models.Quarto;
import com.example.models.Reserva;
import com.example.models.Usuario;


public class Main {
    public static void main(String[] args) {
        // Instanciando controllers
        PagamentoController pagamentoController = new PagamentoController();
        QuartoController quartoController = new QuartoController();
        ReservaController reservaController = new ReservaController();
        UsuarioController usuarioController = new UsuarioController();

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

        //cadastrando usuários
        System.out.println("\n Teste: Cadastrar Hóspede");
        usuarioController.cadastrarHospede("João da Silva", "111.222.333-44", "joao@email.com");

        System.out.println("\n Teste: Cadastrar Funcionário");
        usuarioController.cadastrarFuncionario("Maria Souza", "555.666.777-88", "maria@email.com", Cargo.RECEPCIONISTA);

        // Listar todos os usuários
        System.out.println("\n Teste: Listar todos os usuários");
        Usuario[] usuarios = usuarioController.listarTodosUsuarios();
        for (Usuario u : usuarios) {
            System.out.println(u.getUsuario() + " - " + u.getEmail());
        }

        //buscando usuário por id
        System.out.println("\n Teste: Buscar usuário por ID (ID 1)");
        Usuario usuario = usuarioController.buscarPorId(1);
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario.getUsuario());
        } else {
            System.out.println("Usuário não encontrado.");
        }

        //Atualizando usuário
        System.out.println("\n Teste: Atualizar usuário (ID 1)");
        if (usuario != null) {
            usuario.setUsuario("João Atualizado");
            usuarioController.atualizar(usuario);
        }

        //removendo usuário por id
        System.out.println("\n Teste: Remover usuário (ID 2)");
        usuarioController.deletar(2);


        //listando usuários após remoção
        System.out.println("\n Estado final dos usuários:");
        for (Usuario u : usuarioController.listarTodosUsuarios()) {
            System.out.println("ID: " + u.getId() + " | Nome: " + u.getUsuario());
        }
    }
}