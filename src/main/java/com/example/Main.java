package com.example;

import com.example.controllers.PagamentoController;
import com.example.controllers.QuartoController;
import com.example.controllers.ReservaController;
import com.example.controllers.UsuarioController;
import com.example.enums.Cargo;
import com.example.enums.TipoQuarto;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Hospede;
import com.example.models.Pagamento;
import com.example.models.Quarto;
import com.example.models.Relatorio;
import com.example.models.Reserva;
import com.example.models.Usuario;
import com.example.repositories.PagamentoRepository;
import com.example.repositories.QuartoRepository;
import com.example.repositories.ReservaRepository;
import com.example.repositories.UsuarioRepository;

public class Main {
    public static void main(String[] args) throws DadosInvalidosException {
        // Backup: carregar dados
        try {
            PagamentoRepository.getInstance().carregarPagamentosDeArquivo();
            ReservaRepository.getInstance().carregarReservasDeArquivo();
            QuartoRepository.getInstance().carregarQuartosDeArquivo();
            UsuarioRepository.getInstance().carregarUsuariosDeArquivo();
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        // Instanciando controllers
        PagamentoController pagamentoController = new PagamentoController();
        QuartoController quartoController = new QuartoController();
        ReservaController reservaController = new ReservaController();
        UsuarioController usuarioController = new UsuarioController();

        // Teste: imprimir dados carregados
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

        // CADASTROS SÓ SE O REPOSITÓRIO ESTIVER VAZIO
        
            System.out.println("\n--- Cadastro de Hóspede ---");
            usuarioController.cadastrarHospede("João Silva", "11111111111", "joao@email.com", "Rua A, 123",
                    "81999999999",
                    "semReserva", "cartao", "joaosilva", "senha123");
            usuarioController.cadastrarHospede("Maria Souza", "222222222222", "maria@email.com", "Rua B, 456",
                    "81988888888",
                    "semReserva", "dinheiro", "mariasouza", "senha456");
            usuarioController.cadastrarHospede("Carlos Mendes", "45678912300", "carlos@email.com", "Av. Recife, 789",
                    "81977777777",
                    "semReserva", "pix", "carluxo", "senha789");

            usuarioController.cadastrarHospede("Ana Paula", "78945612399", "ana@email.com", "Rua das Flores, 101",
                    "81966666666",
                    "semReserva", "cartao", "aninha", "senha456");

            System.out.println("\n--- Edição de Hóspede ---");
            Usuario joao = usuarioController.buscarPorId(1);
            joao.setUsuario("João S. Atualizado");
            joao.setEmail("joao.novo@email.com");
            usuarioController.atualizar(joao);

            System.out.println("\n--- Consulta de Hóspede ---");
            Usuario maria = usuarioController.buscarPorId(2);
            System.out.println("Hóspede: " + maria.getUsuario() + " | Email: " + maria.getEmail());

            System.out.println("\n--- Exclusão de Hóspede ---");
            usuarioController.deletar(2);

            // 2. Cadastro e Gerenciamento de Quartos
            System.out.println("\n--- Cadastro de Quartos ---");
            quartoController.salvar(new Quarto( TipoQuarto.SINGLE, 2, 150.0, 1));
            quartoController.salvar(new Quarto( TipoQuarto.FAMILY, 4, 300.0, 2));

            System.out.println("\n--- Edição de Quarto ---");
            Quarto quarto1 = quartoController.buscarPorId(1);
            quarto1.setPreco(160.0);
            quartoController.atualizar(quarto1);

            System.out.println("\n--- Consulta de Quartos ---");
            for (Quarto q : quartoController.listarTodosQuarto()) {
                System.out.println(q);
            }

            System.out.println("\n--- Exclusão de Quarto ---");
            if (quartoController.podeExcluirQuarto(2)) {
                quartoController.deletar(2);
            }

            // 3. Gerenciamento de Reservas
            System.out.println("\n--- Cadastro de Reserva ---");
            Hospede hospede = new Hospede("joaosilva", "João Silva", "12345678900", "joao@email.com", "81999999999",
                    "Rua A, 123", "senha123");
            Reserva reserva = new Reserva(new java.util.Date(),
                    new java.util.Date(System.currentTimeMillis() + 2L * 24 * 60 * 60 * 1000), hospede, 2, 0.0, false);
            reserva.adicionarItem(quarto1, 2);
            reservaController.cadastrarReserva(reserva);

            System.out.println("\n--- Confirmação de Reserva ---");
            reservaController.confirmarReserva(1);

            System.out.println("\n--- Alteração de Reserva ---");
            reserva.setNumeroDeHospedes(3);
            reservaController.atualizarReserva(reserva);

            System.out.println("\n--- Consulta de Reservas ---");
            for (Reserva r : reservaController.listarTodasReservas()) {
                System.out.println(r);
            }

            System.out.println("\n--- Cancelamento de Reserva ---");
            reservaController.cancelarReserva(1, true);

            // 5. Pagamento e Faturamento
            System.out.println("\n--- Cadastro de Pagamento ---");
            Pagamento pagamento = new Pagamento(null, null, reserva.calcularValorTotal(), reserva, hospede);
            pagamentoController.salvar(pagamento);

            System.out.println("\n--- Aplicação de Desconto ---");
            double valorComDesconto = pagamentoController.aplicarDesconto(pagamento, 6);
            System.out.println("Valor com desconto: " + valorComDesconto);

            System.out.println("\n--- Adição de Serviço Adicional ---");
            pagamentoController.adicionarServicoAdicional(pagamento, 50.0);

            System.out.println("\n--- Consulta de Pagamentos por Hóspede ---");
            for (Pagamento p : pagamentoController.buscarPagamentosPorHospede("12345678900")) {
                System.out.println(p);
            }

            System.out.println("\n--- Consulta de Pagamentos por Status ---");
            for (Pagamento p : pagamentoController.buscarPagamentosPorStatus("PAGO")) {
                System.out.println(p);
            }

            // 6. Relatórios e Análises (Exemplo)
            System.out.println("\n--- Relatório de Reservas por Período ---");
            Relatorio relatorio = new Relatorio();
            int reservasPeriodo = relatorio.gerarRelatorioReservasPorPeriodo("2025-07-01", "2025-07-31");
            System.out.println("Reservas no período: " + reservasPeriodo);

            // 7. Controle de Funcionários
            System.out.println("\n--- Cadastro de Funcionário ---");
            usuarioController.cadastrarFuncionarioFixo("Carlos", "11111111111", "carlos@email.com", Cargo.RECEPCIONISTA,
                    2500.0);

            System.out.println("\n--- Autenticação de Funcionário ---");
            Usuario funcionario = usuarioController.buscarPorEmailESenha("carlos@email.com", "senhaDefault");
            if (funcionario != null) {
                System.out.println("Funcionário autenticado: " + funcionario.getUsuario());
            }

            






System.out.println("\n--- Teste: Pagamento com múltiplos quartos ---");

// Buscar o hóspede existente (Ana Paula)
Usuario hospedeAna = usuarioController.buscarPorId(4);

// Criar novo quarto (caso não exista ainda)
quartoController.salvar(new Quarto( TipoQuarto.SUITE, 3, 400.0, 2));
quartoController.salvar(new Quarto( TipoQuarto.DOUBLE, 2, 250.0, 1));

Quarto quartoSuite = quartoController.buscarPorId(3);
Quarto quartoDouble = quartoController.buscarPorId(4);

// Criar nova reserva com dois quartos para Ana Paula
Reserva novaReserva = new Reserva(
    new java.util.Date(),
    new java.util.Date(System.currentTimeMillis() + 3L * 24 * 60 * 60 * 1000), // 3 dias depois
    new Hospede("aninha", "Ana Paula", "78945612399", "ana@email.com", "81966666666", "Rua das Flores, 101", "senha456"),
    2,
    0.0,
    false
);

novaReserva.adicionarItem(quartoSuite, 3); // 3 dias na suíte
novaReserva.adicionarItem(quartoDouble, 2); // 2 dias no double
reservaController.cadastrarReserva(novaReserva);

// Mostrar valor da reserva
double valorReserva = novaReserva.calcularValorTotal();
System.out.println("Valor total da nova reserva: R$ " + valorReserva);

// Criar pagamento com base nessa reserva
Pagamento pagamentoAna = new Pagamento(null, null, valorReserva, novaReserva, (Hospede) hospedeAna);
pagamentoController.salvar(pagamentoAna);

// Aplicar desconto para mais de 5 dias
double valorComDescontoAna = pagamentoController.aplicarDesconto(pagamentoAna, 6);
System.out.println("Valor com desconto: R$ " + valorComDescontoAna);

// Adicionar serviço adicional
pagamentoController.adicionarServicoAdicional(pagamentoAna, 70.0);
System.out.println("Valor total após serviço adicional: R$ " + pagamentoAna.getValorTotal());












        

        // 8. Backup de Dados (já implementado no início e fim da main)
        // Salvar dados antes de sair
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