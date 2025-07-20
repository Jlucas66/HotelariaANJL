package com.example.controllers;

import com.example.models.Pagamento;
import com.example.repositories.PagamentoRepository;

public class PagamentoController implements IPagamentoController {

    private PagamentoRepository pagamentoRepository;

    public PagamentoController() {
        this.pagamentoRepository = PagamentoRepository.getInstance();
    }

    public Pagamento[] listarTodasPagamento() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(int id) {
        return pagamentoRepository.findById(id);
    }

    public void salvar(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);
    }

    public void deletar(int id) {
        pagamentoRepository.delete(id);
    }

    public void atualizar(Pagamento pagamento) {
        pagamentoRepository.update(pagamento);
    }

    public Pagamento[] buscarPagamentosPorHospede(String cpf) {
    return pagamentoRepository.findByHospedeCpf(cpf);
}

    public Pagamento[] buscarPagamentosPorPeriodo(String dataInicio, String dataFim) {
        return pagamentoRepository.findByPeriodo(dataInicio, dataFim);
    }

    public Pagamento[] buscarPagamentosPorStatus(String status) {
        return pagamentoRepository.findByStatus(status);
    }

    public double aplicarDesconto(Pagamento pagamento, int diasEstadia) {
        double desconto = 0;
        if (diasEstadia > 5) {
            desconto = pagamento.getValorTotal() * 0.10;
        }
        return pagamento.getValorTotal() - desconto;
    }

    public void adicionarServicoAdicional(Pagamento pagamento, double valorServico) {
        pagamento.setValorTotal(pagamento.getValorTotal() + valorServico);
    }

}
