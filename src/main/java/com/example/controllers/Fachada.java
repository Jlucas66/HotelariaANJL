package com.example.controllers;

import com.example.enums.Cargo;
import com.example.enums.TipoQuarto;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.*;

public class Fachada {

    private final UsuarioController usuarioController = new UsuarioController();
    private final ReservaController reservaController = new ReservaController();
    private final QuartoController quartoController = new QuartoController();
    private final PagamentoController pagamentoController = new PagamentoController();

    // UsuarioController
    public void cadastrarHospede(String nome, String cpf, String email, String endereco, String telefone, String reserva, String metodoPagamento, String usuario, String senha) throws DadosInvalidosException {
        usuarioController.cadastrarHospede(nome, cpf, email, endereco, telefone, reserva, metodoPagamento, usuario, senha);
    }

    public void cadastrarFuncionarioFixo(String nome, String cpf, String email, Cargo cargo, double salarioFixo) throws DadosInvalidosException {
        usuarioController.cadastrarFuncionarioFixo(nome, cpf, email, cargo, salarioFixo);
    }

    public void cadastrarFuncionarioComissionado(String nome, String cpf, String email, Cargo cargo, double reservasFeitas, double taxaPorReserva) throws DadosInvalidosException {
        usuarioController.cadastrarFuncionarioComissionado(nome, cpf, email, cargo, reservasFeitas, taxaPorReserva);
    }

    public void cadastrarFuncionarioFixoMaisComissao(String nome, String cpf, String email, Cargo cargo, double salarioFixo, double reservasFeitas, double taxaPorReserva) throws DadosInvalidosException {
        usuarioController.cadastrarFuncionarioFixoMaisComissao(nome, cpf, email, cargo, salarioFixo, reservasFeitas, taxaPorReserva);
    }

    public Usuario buscarUsuarioPorId(int id) throws DadosInvalidosException {
        return usuarioController.buscarPorId(id);
    }

    public Usuario[] listarTodosUsuarios() {
        return usuarioController.listarTodosUsuarios();
    }

    public void deletarUsuario(int id) throws DadosInvalidosException {
        usuarioController.deletar(id);
    }

    public void salvarUsuario(Usuario usuario) throws DadosInvalidosException {
        usuarioController.salvar(usuario);
    }

    public void atualizarUsuario(Usuario usuarioAtualizado) throws DadosInvalidosException {
        usuarioController.atualizar(usuarioAtualizado);
    }

    public Usuario buscarUsuarioPorEmailESenha(String email, String senha) throws DadosInvalidosException {
        return usuarioController.buscarPorEmailESenha(email, senha);
    }

    // ReservaController
    public void cadastrarReserva(Reserva reserva) {
        reservaController.cadastrarReserva(reserva);
    }

    public Reserva buscarReservaPorId(int id) {
        return reservaController.buscarReservaPorId(id);
    }

    public void removerReserva(int id) {
        reservaController.removerReserva(id);
    }

    public void atualizarReserva(Reserva reserva) {
        reservaController.atualizarReserva(reserva);
    }

    public Reserva[] listarTodasReservas() {
        return reservaController.listarTodasReservas();
    }

    public void adicionarItemReserva(int reservaId, Quarto quarto, int dias) {
        reservaController.adicionarItemReserva(reservaId, quarto, dias);
    }

    public boolean reservarQuartos(Reserva reserva) {
        return reservaController.reservarQuartos(reserva);
    }

    public void confirmarReserva(int reservaId) {
        reservaController.confirmarReserva(reservaId);
    }

    public void cancelarReserva(int reservaId, boolean reembolso) {
        reservaController.cancelarReserva(reservaId, reembolso);
    }

    // QuartoController
    public Quarto[] listarTodosQuarto() throws DadosInvalidosException {
        return quartoController.listarTodosQuarto();
    }

    public Quarto buscarQuartoPorId(int id) {
        return quartoController.buscarPorId(id);
    }

    public void salvarQuarto(Quarto quarto) {
        quartoController.salvar(quarto);
    }

    public void deletarQuarto(int id) {
        quartoController.deletar(id);
    }

    public void atualizarQuarto(Quarto quarto) {
        quartoController.atualizar(quarto);
    }

    public void criarQuarto(TipoQuarto tipoQuarto, int capacidade, double preco, int camas) throws DadosInvalidosException {
        quartoController.criarQuarto(tipoQuarto, capacidade, preco, camas);
    }

    public boolean podeExcluirQuarto(int quartoId) {
        return quartoController.podeExcluirQuarto(quartoId);
    }

    // PagamentoController
    public Pagamento[] listarTodosPagamentos() {
        return pagamentoController.listarTodasPagamento();
    }

    public Pagamento buscarPagamentoPorId(int id) {
        return pagamentoController.buscarPorId(id);
    }

    public void salvarPagamento(Pagamento pagamento) {
        pagamentoController.salvar(pagamento);
    }

    public void deletarPagamento(int id) {
        pagamentoController.deletar(id);
    }

    public void atualizarPagamento(Pagamento pagamento) {
        pagamentoController.atualizar(pagamento);
    }

    public Pagamento[] buscarPagamentosPorHospede(String cpf) {
        return pagamentoController.buscarPagamentosPorHospede(cpf);
    }

    public Pagamento[] buscarPagamentosPorPeriodo(String dataInicio, String dataFim) {
        return pagamentoController.buscarPagamentosPorPeriodo(dataInicio, dataFim);
    }

    public Pagamento[] buscarPagamentosPorStatus(String status) {
        return pagamentoController.buscarPagamentosPorStatus(status);
    }

    public double aplicarDesconto(Pagamento pagamento, int diasEstadia) {
        return pagamentoController.aplicarDesconto(pagamento, diasEstadia);
    }

    public void adicionarServicoAdicional(Pagamento pagamento, double valorServico) {
        pagamentoController.adicionarServicoAdicional(pagamento, valorServico);
    }

    public double calcularTarifa(Pagamento pagamento, boolean altaTemporada) {
        return pagamentoController.calcularTarifa(pagamento, altaTemporada);
    }
}
