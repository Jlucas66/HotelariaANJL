package com.example.repositories;

import com.example.models.Hospede;

public class HospedeRepository implements IRepositories {
    private static HospedeRepository instance;

    private Hospede[] hospedes;
    private int contador;

    private HospedeRepository() {
        hospedes = new Hospede[100]; // Tamanho fixo inicial
        contador = 0;
    }

    public static HospedeRepository getInstance() {
        if (instance == null) {
            instance = new HospedeRepository();
        }
        return instance;
    }

    @Override
    public Object[] findAll() {
        Hospede[] lista = new Hospede[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = hospedes[i];
        }
        return lista;
    }

    @Override
    public Object findById(int id) {
        for (int i = 0; i < contador; i++) {
            if (hospedes[i].getId().equals(String.valueOf(id))) {
                return hospedes[i];
            }
        }
        return null;
    }

    @Override
    public void save(Object entity) {
        if (contador >= hospedes.length) {
            System.out.println("O repositório está cheio!");
            return;
        }
        if (entity instanceof Hospede) {
            Hospede hospede = (Hospede) entity;
            hospede.setId(String.valueOf(contador + 1));
            hospedes[contador++] = hospede;
            System.out.println("Hóspede salvo com ID: " + hospede.getId());
        } else {
            System.out.println("Objeto inválido, não é um Hóspede.");
        }
    }

    @Override
    public void delete(int id) {
        int indexToRemove = -1;
        for (int i = 0; i < contador; i++) {
            if (hospedes[i].getId().equals(String.valueOf(id))) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            for (int i = indexToRemove; i < contador - 1; i++) {
                hospedes[i] = hospedes[i + 1];
            }
            hospedes[contador - 1] = null;
            contador--;
            System.out.println("Hóspede com ID " + id + " removido.");
        } else {
            System.out.println("Hóspede com ID " + id + " não encontrado.");
        }
    }

    @Override
    public void update(Object entity) {
        if (entity instanceof Hospede) {
            Hospede hospede = (Hospede) entity;
            for (int i = 0; i < contador; i++) {
                if (hospedes[i].getId().equals(hospede.getId())) {
                    hospedes[i] = hospede;
                    System.out.println("Hóspede com ID " + hospede.getId() + " atualizado.");
                    return;
                }
            }
            System.out.println("Hóspede com ID " + hospede.getId() + " não encontrado.");
        } else {
            System.out.println("Objeto inválido, não é um Hóspede.");
        }
    }
}
