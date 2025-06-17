package com.example.repositories;

import com.example.models.Quarto;

public class QuartoRepository implements IRepositories {

    private static QuartoRepository instance;

    private Quarto[] quartos;
    int contador;
    private int identificador = 1;

    private QuartoRepository() {
        quartos = new Quarto[100];
        contador = 0;

    }

    public static QuartoRepository getInstance() {
        if (instance == null) {
            instance = new QuartoRepository();
        }
        return instance;
    }

    @Override
    public Quarto[] findAll() {
        Quarto[] quarto = new Quarto[contador];
        for (int i = 0; i < contador; i++) {
            quarto[i] = quartos[i];
        }
        return quarto;
    }

    @Override
public Quarto findById(int id) {
    for (int i = 0; i < contador; i++) {
        if (quartos[i].getId() == id) {
            return quartos[i];
        }
    }
    return null;
}

    @Override
    public void save(Object entity) {
        if (contador >= quartos.length) {
            System.out.println("O repositório está cheio!");
            return;
        }
        if (entity instanceof Quarto) {
            Quarto quarto = (Quarto) entity;
            quarto.setId(identificador++);
            quartos[contador++] = quarto;
            System.out.println("Quarto salvo com ID: " + quarto.getId());
        } else {
            System.out.println("Objeto inválido, não é um Quarto.");
        }

    }

    @Override
    public void delete(int id) {
        int pos = -1;
        for (int i = 0; i < contador; i++) {
            if (quartos[i].getId() == id) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("Quarto com ID " + id + " não encontrado.");
            return;
        }
        for (int i = pos; i < contador - 1; i++) {
            quartos[i] = quartos[i + 1];
        }
        quartos[contador - 1] = null;
        contador--;
        System.out.println("Quarto com ID " + id + "removido.");
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof Quarto)) {
            System.out.println("Objeto inválido, não é um Quarto.");
            return;
        }
        Quarto quartoAtualizado = (Quarto) entity;
        int id = quartoAtualizado.getId();

        for (int i = 0; i < contador; i++) {
            if (quartos[i].getId() == id) {
                quartos[i] = quartoAtualizado;
                System.out.println("Quarto com ID " + id + " atualizado.");
                return;
            }
        }
        System.out.println("Quarto com ID " + id + " não encontrado para atualizar.");
    }

    
}
