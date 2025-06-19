package com.example.controllers;

import com.example.enums.Cargo;
import com.example.models.Funcionario;
import com.example.models.Hospede;
import com.example.models.Usuario;
import com.example.repositories.UsuarioRepository;

public class UsuarioController implements IUsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController() {
        this.usuarioRepository = UsuarioRepository.getInstance();
    }

    public void cadastrarHospede(String nome, String cpf, String email) {
        Hospede hospede = new Hospede(nome, cpf, email, "defaultAddress", "defaultPhone", "defaultDOB", "defaultNationality");
        usuarioRepository.save(hospede);
    }

    public void cadastrarFuncionario(String nome, String cpf, String email, Cargo cargo) {
        Funcionario funcionario = new Funcionario(nome, cpf, email, "defaultAddress", "defaultPhone", cargo);
        usuarioRepository.save(funcionario);
    }

    @Override
    public void atualizar(Usuario usuarioAtualizado) {
        usuarioRepository.update(usuarioAtualizado);
    }

    @Override
    public Usuario buscarPorId(int id) {
        return (Usuario) usuarioRepository.findById(id);
    }

    @Override
    public Usuario[] listarTodosUsuarios() {
        return (Usuario[]) usuarioRepository.findAll();
    }

    @Override
    public void deletar(int id) {
        usuarioRepository.delete(id);
    }

    @Override
    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
