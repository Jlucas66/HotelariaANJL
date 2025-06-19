package com.example.repositories;

import com.example.models.Usuario;

public class UsuarioRepository implements IRepositories {

    @Override
    public void update(Object entity) {
        if (entity instanceof Usuario) {
            Usuario usuarioToUpdate = (Usuario) entity;
            for (int i = 0; i < contador; i++) {
                if (usuarios[i].getId().equals(usuarioToUpdate.getId())) {
                    usuarios[i] = usuarioToUpdate;
                    System.out.println("Usuário com ID " + usuarioToUpdate.getId() + " atualizado.");
                    return;
                }
            }
            System.out.println("Usuário com ID " + usuarioToUpdate.getId() + " não encontrado.");
        } else {
            System.out.println("Objeto inválido, não é um Usuário.");
        }
    }

    private static UsuarioRepository instance;

    private Usuario[] usuarios;
    private int contador;

    private UsuarioRepository() {
        usuarios = new Usuario[100]; 
        contador = 0;
    }

    public static UsuarioRepository getInstance() {
        if (instance == null) {
            instance = new UsuarioRepository();
        }
        return instance;
    }

    @Override
    public Object[] findAll() {
        Usuario[] lista = new Usuario[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = usuarios[i];
        }
        return lista;
    }

    @Override
    public Object findById(int id) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i].getId().equals(String.valueOf(id))) {
                return usuarios[i];
            }
        }
        return null;
    }

    @Override
    public void save(Object entity) {
        if (contador >= usuarios.length) {
            System.out.println("O repositório está cheio!");
            return;
        }
        if (entity instanceof Usuario) {
            Usuario usuario = (Usuario) entity;
            usuario.setId(String.valueOf(contador + 1));
            usuarios[contador++] = usuario;
            System.out.println("Usuário salvo com ID: " + usuario.getId());
        } else {
            System.out.println("Objeto inválido, não é um Usuário.");
        }
    }

    @Override
    public void delete(int id) {
        int indexToRemove = -1;
        for (int i = 0; i < contador; i++) {
            if (usuarios[i].getId().equals(String.valueOf(id))) {
                indexToRemove = i;
                break;
            }
        }
        
        if (indexToRemove != -1) {
            for (int i = indexToRemove; i < contador - 1; i++) {
                usuarios[i] = usuarios[i + 1];
            }
            usuarios[contador - 1] = null;
            contador--;
            System.out.println("Usuário com ID " + id + " removido.");
        } else {
            System.out.println("Usuário com ID " + id + " não encontrado.");
        }
    }
    
}
