package com.example.controllers;

import com.example.models.Pagamento;
import com.example.repositories.PagamentoRepository;

public class PagamentoController implements IPagamentoController {
    
    private PagamentoRepository pagamentoRepository;

    public PagamentoController(){
        this.pagamentoRepository = PagamentoRepository.getInstance();
    }

    public Pagamento[] listarTodasPagamento() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(int id){
        return pagamentoRepository.findById(id);
    }

    public void salvar(Pagamento pagamento){
        pagamentoRepository.save(pagamento);
    }

    public void deletar(int id){
        pagamentoRepository.delete(id);
    }

    public void atualizar(Pagamento pagamento){
        pagamentoRepository.update(pagamento);
    }
    
}
