package com.example.repositories;

import java.util.ArrayList;
import java.util.Date;

import com.example.models.Pagamento;

public class PagamentoRepository implements IRepositories {

    private static PagamentoRepository instance;

    private Pagamento[] pagamentos;
    int contador;
    private int identificador = 1;

    private PagamentoRepository() {
        pagamentos = new Pagamento[100];
        contador = 0;

    }

    public static PagamentoRepository getInstance() {
        if (instance == null) {
            instance = new PagamentoRepository();
        }
        return instance;
    }

    @Override
    public Pagamento[] findAll() {
        Pagamento[] pagamento = new Pagamento[contador];
        for (int i = 0; i < contador; i++) {
            pagamento[i] = pagamentos[i];
        }
        return pagamento;
    }

    @Override
public Pagamento findById(int id) {
    for (int i = 0; i < contador; i++) {
        if (pagamentos[i].getId() == id) {
            return pagamentos[i];
        }
    }
    return null;
}

    public void save(Object entity) {
        if (contador >= pagamentos.length) {
            System.out.println("O repositório está cheio!");
            return;
        }
        if (entity instanceof Pagamento) {
            Pagamento pagamento = (Pagamento) entity;
            pagamento.setId(identificador++);
            pagamentos[contador++] = pagamento;
            System.out.println("Pagamento salvo com ID: " + pagamento.getId());
        } else {
            System.out.println("Objeto inválido, não é um Pagamento.");
        }

    }

    @Override
    public void delete(int id) {
        int pos = -1;
        for (int i = 0; i < contador; i++) {
            if (pagamentos[i].getId() == id) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("Pagamento com ID " + id + " não encontrado.");
            return;
        }
        for (int i = pos; i < contador - 1; i++) {
            pagamentos[i] = pagamentos[i + 1];
        }
        pagamentos[contador - 1] = null;
        contador--;
        System.out.println("Pagamento com ID " + id + "removido.");
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof Pagamento)) {
            System.out.println("Objeto inválido, não é um Pagamento.");
            return;
        }
        Pagamento pagamentoAtualizado = (Pagamento) entity;
        int id = pagamentoAtualizado.getId();

        for (int i = 0; i < contador; i++) {
            if (pagamentos[i].getId() == id) {
                pagamentos[i] = pagamentoAtualizado;
                System.out.println("Pagamento com ID " + id + " atualizado.");
                return;
            }
        }
        System.out.println("Pagamento com ID " + id + " não encontrado para atualizar.");
    }

    public Pagamento[] findByHospedeCpf(String cpf) {
    ArrayList<Pagamento> resultado = new ArrayList<>();
    for (int i = 0; i < contador; i++) {
        Pagamento pagamento = pagamentos[i];
        if (pagamento.getHospede() != null && pagamento.getHospede().getCpf().equals(cpf)) {
            resultado.add(pagamento);
        }
    }
    return resultado.toArray(new Pagamento[0]);
}


public Pagamento[] findByPeriodo(String dataInicio, String dataFim) {
    ArrayList<Pagamento> resultado = new ArrayList<>();
    for (int i = 0; i < contador; i++) {
        Pagamento pagamento = pagamentos[i];
        if (pagamento.getReserva() != null) {
            Date entrada = pagamento.getReserva().getDataEntrada();
            Date saida = pagamento.getReserva().getDataSaida();
            // Supondo que dataInicio/dataFim são no formato "yyyy-MM-dd"
            try {
                Date inicio = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);
                Date fim = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dataFim);
                if (!(saida.before(inicio) || entrada.after(fim))) {
                    resultado.add(pagamento);
                }
            } catch (Exception e) {
                // Ignorar datas inválidas
            }
        }
    }
    return resultado.toArray(new Pagamento[0]);
}

public Pagamento[] findByStatus(String status) {
    ArrayList<Pagamento> resultado = new ArrayList<>();
    for (int i = 0; i < contador; i++) {
        Pagamento pagamento = pagamentos[i];
        if (pagamento.getStatus() != null && pagamento.getStatus().equalsIgnoreCase(status)) {
            resultado.add(pagamento);
        }
    }
    return resultado.toArray(new Pagamento[0]);
}

}
