package com.example.controllers;

import com.example.models.Pagamento;

public interface IPagamentoController {
    Pagamento[] listarTodasPagamento();
    Pagamento buscarPorId(int id);
    void salvar(Pagamento pagamento);
    void deletar(int id);
    void atualizar(Pagamento pagamento);
    
}
