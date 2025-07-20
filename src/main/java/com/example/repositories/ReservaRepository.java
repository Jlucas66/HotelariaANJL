package com.example.repositories;

import java.util.ArrayList;

import com.example.models.ItemReserva;
import com.example.models.Quarto;
import com.example.models.Reserva;

public class ReservaRepository implements IRepositories {

    private static ReservaRepository instance;

    private Reserva[] reservas;
    int contador;
    private int identificador = 1;

    private ReservaRepository() {
        reservas = new Reserva[100];
        contador = 0;

    }

    public static ReservaRepository getInstance() {
        if (instance == null) {
            instance = new ReservaRepository();
        }
        return instance;
    }

    @Override
    public Reserva[] findAll() {
        Reserva[] reserva = new Reserva[contador];
        for (int i = 0; i < contador; i++) {
            reserva[i] = reservas[i];
        }
        return reserva;
    }

    @Override
    public Reserva findById(int id) {
        for (int i = 0; i < contador; i++) {
            if (reservas[i].getId() == id) {
                return reservas[i];
            }
        }
        return null;
    }

    @Override
    public void save(Object entity) {
        if (contador >= reservas.length) {
            System.out.println("O repositório está cheio!");
            return;
        }
        if (entity instanceof Reserva) {
            Reserva reserva = (Reserva) entity;
            reserva.setId(identificador++);
            reservas[contador++] = reserva;
            System.out.println("Reserva salvo com ID: " + reserva.getId());
        } else {
            System.out.println("Objeto inválido, não é um Reserva.");
        }

    }

    @Override
    public void delete(int id) {
        int pos = -1;
        for (int i = 0; i < contador; i++) {
            if (reservas[i].getId() == id) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("Reserva com ID " + id + " não encontrado.");
            return;
        }
        for (int i = pos; i < contador - 1; i++) {
            reservas[i] = reservas[i + 1];
        }
        reservas[contador - 1] = null;
        contador--;
        System.out.println("Reserva com ID " + id + "removido.");
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof Reserva)) {
            System.out.println("Objeto inválido, não é um Reserva.");
            return;
        }
        Reserva reservaAtualizada = (Reserva) entity;
        int id = reservaAtualizada.getId();

        for (int i = 0; i < contador; i++) {
            if (reservas[i].getId() == id) {
                reservas[i] = reservaAtualizada;
                System.out.println("Reserva com ID " + id + " atualizado.");
                return;
            }
        }
        System.out.println("Reserva com ID " + id + " não encontrado para atualizar.");
    }

    public Reserva[] findByStatus(String status) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        for (int i = 0; i < contador; i++) {
            if (reservas[i].getStatus() != null && reservas[i].getStatus().equalsIgnoreCase(status)) {
                resultado.add(reservas[i]);
            }
        }
        return resultado.toArray(new Reserva[0]);
    }

    public Reserva[] findCanceladasPorPeriodo(String dataInicio, String dataFim) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date inicio = sdf.parse(dataInicio);
            java.util.Date fim = sdf.parse(dataFim);
            for (int i = 0; i < contador; i++) {
                Reserva reserva = reservas[i];
                if ("Cancelada".equalsIgnoreCase(reserva.getStatus())) {
                    java.util.Date entrada = reserva.getDataEntrada();
                    java.util.Date saida = reserva.getDataSaida();
                    if (!(saida.before(inicio) || entrada.after(fim))) {
                        resultado.add(reserva);
                    }
                }
            }
        } catch (Exception e) {
            // Ignorar datas inválidas
        }
        return resultado.toArray(new Reserva[0]);
    }

    public boolean quartoOcupadoNoPeriodo(Quarto quarto, java.util.Date dataInicio, java.util.Date dataFim) {
        for (int i = 0; i < contador; i++) {
            Reserva reserva = reservas[i];
            for (ItemReserva item : reserva.getItensReserva()) {
                if (item.getQuarto().equals(quarto)) {
                    java.util.Date entrada = reserva.getDataEntrada();
                    java.util.Date saida = reserva.getDataSaida();
                    if (!(saida.before(dataInicio) || entrada.after(dataFim))) {
                        // Sobreposição de datas
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Reserva[] findByPeriodo(String dataInicio, String dataFim) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date inicio = sdf.parse(dataInicio);
            java.util.Date fim = sdf.parse(dataFim);
            for (int i = 0; i < contador; i++) {
                Reserva reserva = reservas[i];
                java.util.Date entrada = reserva.getDataEntrada();
                java.util.Date saida = reserva.getDataSaida();
                // Verifica sobreposição de datas
                if (!(saida.before(inicio) || entrada.after(fim))) {
                    resultado.add(reserva);
                }
            }
        } catch (Exception e) {
            // Ignorar datas inválidas
        }
        return resultado.toArray(new Reserva[0]);
    }

}
