package com.example;

import com.example.controllers.Fachada;
import com.example.enums.Cargo;
import com.example.enums.TipoQuarto;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.*;
import com.example.repositories.*;

public class Main {
    public static void main(String[] args) throws DadosInvalidosException {
        try {
            PagamentoRepository.getInstance().carregarPagamentosDeArquivo();
            ReservaRepository.getInstance().carregarReservasDeArquivo();
            QuartoRepository.getInstance().carregarQuartosDeArquivo();
            UsuarioRepository.getInstance().carregarUsuariosDeArquivo();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        Fachada fachada = new Fachada();

        System.out.println("--- Quartos carregados ---");
        for (Quarto q : QuartoRepository.getInstance().findAll()) {
            System.out.println(q);
        }

        System.out.println("--- Reservas carregadas ---");
        for (Reserva r : ReservaRepository.getInstance().findAll()) {
            System.out.println(r);
        }

        System.out.println("--- Pagamentos carregados ---");
        for (Pagamento p : PagamentoRepository.getInstance().findAll()) {
            System.out.println(p);
        }

        System.out.println("--- Usuários carregados ---");
        for (Usuario u : UsuarioRepository.getInstance().findAll()) {
            System.out.println(u.getUsuario() + " | " + u.getEmail());
        }

        if (UsuarioRepository.getInstance().findAll().length == 0) {
            fachada.cadastrarHospede("João Silva", "11111111111", "joao@email.com", "Rua A, 123",
                    "81999999999", "semReserva", "cartao", "joaosilva", "senha123");

            fachada.cadastrarHospede("Maria Souza", "22222222222", "maria@email.com", "Rua B, 456",
                    "81988888888", "semReserva", "dinheiro", "mariasouza", "senha456");

            fachada.cadastrarHospede("Carlos Mendes", "45678912300", "carlos@email.com", "Av. Recife, 789",
                    "81977777777", "semReserva", "pix", "carluxo", "senha789");

            fachada.cadastrarHospede("Ana Paula", "78945612399", "ana@email.com", "Rua das Flores, 101",
                    "81966666666", "semReserva", "cartao", "aninha", "senha456");
        }

        System.out.println("\n--- Edição de Hóspede ---");
        Usuario joao = fachada.buscarUsuarioPorId(1);
        joao.setUsuario("João S. Atualizado");
        joao.setEmail("joao.novo@email.com");
        fachada.atualizarUsuario(joao);

        System.out.println("\n--- Consulta de Hóspede ---");
        Usuario maria = fachada.buscarUsuarioPorId(2);
        System.out.println("Hóspede: " + maria.getUsuario() + " | Email: " + maria.getEmail());

        System.out.println("\n--- Exclusão de Hóspede ---");
        fachada.deletarUsuario(2);

        if (QuartoRepository.getInstance().findAll().length == 0) {
            fachada.salvarQuarto(new Quarto(TipoQuarto.SINGLE, 2, 150.0, 1));
            fachada.salvarQuarto(new Quarto(TipoQuarto.FAMILY, 4, 300.0, 2));
        }

        System.out.println("\n--- Edição de Quarto ---");
        Quarto quarto1 = fachada.buscarQuartoPorId(1);
        quarto1.setPreco(160.0);
        fachada.atualizarQuarto(quarto1);

        System.out.println("\n--- Consulta de Quartos ---");
        for (Quarto q : fachada.listarTodosQuarto()) {
            System.out.println(q);
        }

        System.out.println("\n--- Exclusão de Quarto ---");
        if (fachada.podeExcluirQuarto(2)) {
            fachada.deletarQuarto(2);
        }

        System.out.println("\n--- Cadastro de Reserva ---");
        Hospede hospedeJoao = (Hospede) fachada.buscarUsuarioPorId(1);
        Reserva reserva = new Reserva(new java.util.Date(),
                new java.util.Date(System.currentTimeMillis() + 2L * 24 * 60 * 60 * 1000), hospedeJoao, 2, 0.0, false);
        reserva.adicionarItem(quarto1, 2);
        fachada.cadastrarReserva(reserva);

        System.out.println("\n--- Confirmação de Reserva ---");
        fachada.confirmarReserva(1);

        System.out.println("\n--- Alteração de Reserva ---");
        reserva.setNumeroDeHospedes(3);
        fachada.atualizarReserva(reserva);

        System.out.println("\n--- Consulta de Reservas ---");
        for (Reserva r : fachada.listarTodasReservas()) {
            System.out.println(r);
        }

        System.out.println("\n--- Cancelamento de Reserva ---");
        fachada.cancelarReserva(1, true);

        System.out.println("\n--- Cadastro de Pagamento ---");
        Pagamento pagamento = new Pagamento(null, null, reserva.calcularValorTotal(), reserva, hospedeJoao);
        pagamento.setStatus("PAGO");
        fachada.salvarPagamento(pagamento);

        System.out.println("\n--- Aplicação de Desconto ---");
        double valorComDesconto = fachada.aplicarDesconto(pagamento, 6);
        System.out.println("Valor com desconto: " + valorComDesconto);

        System.out.println("\n--- Adição de Serviço Adicional ---");
        fachada.adicionarServicoAdicional(pagamento, 50.0);

        System.out.println("\n--- Consulta de Pagamentos por Hóspede ---");
        for (Pagamento p : fachada.buscarPagamentosPorHospede("11111111111")) {
            System.out.println(p);
        }

        System.out.println("\n--- Consulta de Pagamentos por Status ---");
        for (Pagamento p : fachada.buscarPagamentosPorStatus("PAGO")) {
            System.out.println(p);
        }

        System.out.println("\n--- Relatório de Reservas por Período ---");
        Relatorio relatorio = new Relatorio();
        int reservasPeriodo = relatorio.gerarRelatorioReservasPorPeriodo("2025-07-01", "2025-07-31");
        System.out.println("Reservas no período: " + reservasPeriodo);

        System.out.println("\n--- Cadastro de Funcionário ---");
        fachada.cadastrarFuncionarioFixo("Carlos", "11111111111", "carlos@email.com", Cargo.RECEPCIONISTA,
                2500.0);

        System.out.println("\n--- Autenticação de Funcionário ---");
        Usuario funcionario = fachada.buscarUsuarioPorEmailESenha("carlos@email.com", "senhaDefault");
        if (funcionario != null) {
            System.out.println("Funcionário autenticado: " + funcionario.getUsuario());
        }

        System.out.println("\n--- Teste: Pagamento com múltiplos quartos ---");
        Hospede hospedeAna = (Hospede) fachada.buscarUsuarioPorId(4);

        fachada.salvarQuarto(new Quarto(TipoQuarto.SUITE, 3, 400.0, 2));
        fachada.salvarQuarto(new Quarto(TipoQuarto.DOUBLE, 2, 250.0, 1));
        Quarto quartoSuite = fachada.buscarQuartoPorId(3);
        Quarto quartoDouble = fachada.buscarQuartoPorId(4);

        Reserva novaReserva = new Reserva(
                new java.util.Date(),
                new java.util.Date(System.currentTimeMillis() + 3L * 24 * 60 * 60 * 1000),
                hospedeAna,
                2,
                0.0,
                false
        );

        novaReserva.adicionarItem(quartoSuite, 3);
        novaReserva.adicionarItem(quartoDouble, 2);
        fachada.cadastrarReserva(novaReserva);

        double valorReserva = novaReserva.calcularValorTotal();
        System.out.println("Valor total da nova reserva: R$ " + valorReserva);

        Pagamento pagamentoAna = new Pagamento(null, null, valorReserva, novaReserva, hospedeAna);
        pagamentoAna.setStatus("PAGO");
        fachada.salvarPagamento(pagamentoAna);

        double valorComDescontoAna = fachada.aplicarDesconto(pagamentoAna, 6);
        System.out.println("Valor com desconto: R$ " + valorComDescontoAna);

        fachada.adicionarServicoAdicional(pagamentoAna, 70.0);
        System.out.println("Valor total após serviço adicional: R$ " + pagamentoAna.getValorTotal());

        String inicio = "2025-07-01";
        String fim = "2025-07-31";

        int totalReservas = relatorio.gerarRelatorioReservasPorPeriodo(inicio, fim);
        relatorio.salvarRelatorioEmPdf(
            "Relatório de Reservas",
            "Total de reservas entre " + inicio + " e " + fim + ": " + totalReservas,
            "relatorio_reservas.pdf"
        );

        double faturamento = relatorio.gerarRelatorioFaturamentoPorPeriodo(inicio, fim);
        relatorio.salvarRelatorioEmPdf(
            "Relatório de Faturamento",
            "Faturamento total entre " + inicio + " e " + fim + ": R$ " + faturamento,
            "relatorio_faturamento.pdf"
        );

        double ocupacaoSuite = relatorio.gerarRelatorioOcupacaoQuartos(inicio, fim, TipoQuarto.SUITE);
        relatorio.salvarRelatorioEmPdf(
            "Relatório de Ocupação - Suíte",
            "Taxa de ocupação de suítes entre " + inicio + " e " + fim + ": " + ocupacaoSuite + "%",
            "relatorio_ocupacao_suite.pdf"
        );

        int canceladas = relatorio.gerarRelatorioCancelamentos(inicio, fim);
        relatorio.salvarRelatorioEmPdf(
            "Relatório de Cancelamentos",
            "Total de reservas canceladas entre " + inicio + " e " + fim + ": " + canceladas,
            "relatorio_cancelamentos.pdf"
        );

        try {
            PagamentoRepository.getInstance().salvarPagamentosEmArquivo();
            ReservaRepository.getInstance().salvarReservasEmArquivo();
            QuartoRepository.getInstance().salvarQuartosEmArquivo();
            UsuarioRepository.getInstance().salvarUsuariosEmArquivo();
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}
