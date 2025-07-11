package com.example.controllers;

import com.example.enums.Cargo;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Funcionario;
import com.example.models.FuncionarioComissionado;
import com.example.models.FuncionarioFixoMaisComissao;
import com.example.models.FuncionarioSalarioFixo;
import com.example.models.Hospede;
import com.example.models.Usuario;
import com.example.repositories.UsuarioRepository;

public class UsuarioController implements IUsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController() {
        this.usuarioRepository = UsuarioRepository.getInstance();
    }

    public void cadastrarHospede(String nome, String cpf, String email, String endereco, String telefone, String reserva, String metodoPagamento, String usuario, String senha) throws DadosInvalidosException {
        if (camposVazios(nome, cpf, email, endereco, telefone, reserva, metodoPagamento, usuario, senha)) {
            throw new DadosInvalidosException("Todos os campos do hóspede são obrigatórios.");
        }
        Hospede hospede = new Hospede(nome, cpf, email, "defaultAddress", "defaultPhone", "defaultDOB", "defaultNationality");
        usuarioRepository.save(hospede);
    }

    @Override
    public void cadastrarFuncionarioFixo(String nome, String cpf, String email, Cargo cargo, double salarioFixo) throws DadosInvalidosException {
        validarDadosBasicosFuncionario(nome, cpf, email, cargo);
        if (salarioFixo <= 0) {
            throw new DadosInvalidosException("Salário fixo deve ser maior que zero.");
        }
        Funcionario funcionario = new FuncionarioSalarioFixo("usuarioDefault", nome, cpf, email, "senhaDefault", cargo, salarioFixo);
        usuarioRepository.save(funcionario);
    }

    @Override
    public void cadastrarFuncionarioComissionado(String nome, String cpf, String email, Cargo cargo, double reservasFeitas, double taxaPorReserva) throws DadosInvalidosException {
        validarDadosBasicosFuncionario(nome, cpf, email, cargo);
        if (reservasFeitas < 0 || taxaPorReserva <= 0) {
            throw new DadosInvalidosException("Reservas feitas não pode ser negativa e taxa por reserva deve ser maior que zero.");
        }
        Funcionario funcionario = new FuncionarioComissionado("usuarioDefault", nome, cpf, email, "senhaDefault", cargo, reservasFeitas, taxaPorReserva);
        usuarioRepository.save(funcionario);
    }

    @Override
    public void cadastrarFuncionarioFixoMaisComissao(String nome, String cpf, String email, Cargo cargo, double salarioFixo, double reservasFeitas, double taxaPorReserva) throws DadosInvalidosException {
        validarDadosBasicosFuncionario(nome, cpf, email, cargo);
        if (salarioFixo <= 0 || reservasFeitas < 0 || taxaPorReserva <= 0) {
            throw new DadosInvalidosException("Valores salariais inválidos para funcionário fixo com comissão.");
        }
        Funcionario funcionario = new FuncionarioFixoMaisComissao("usuarioDefault", nome, cpf, email, "senhaDefault", cargo, salarioFixo, reservasFeitas, taxaPorReserva);
        usuarioRepository.save(funcionario);
    }

    private boolean camposVazios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void validarDadosBasicosFuncionario(String nome, String cpf, String email, Cargo cargo) throws DadosInvalidosException {
        if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty() || email == null || email.isEmpty() || cargo == null) {
            throw new DadosInvalidosException("Nome, CPF, email e cargo são obrigatórios para o funcionário.");
        }
    }

    @Override
    public Usuario buscarPorId(int id) throws DadosInvalidosException {
        if (id <= 0) {
            throw new DadosInvalidosException("ID inválido para busca.");
        }
        Usuario usuario = (Usuario) usuarioRepository.findById(id);
        if (usuario == null) {
            throw new DadosInvalidosException("Usuário não encontrado com o ID fornecido.");
        }
        return usuario;
    }

    @Override
    public Usuario[] listarTodosUsuarios() {
        Usuario[] usuarios = (Usuario[]) usuarioRepository.findAll();
        if (usuarios.length == 0) {
            System.out.println("Nenhum usuário cadastrado no sistema.");
        }
        return usuarios;
    }

    @Override
    public void deletar(int id) throws DadosInvalidosException {
        if (id <= 0) {
            throw new DadosInvalidosException("ID inválido para exclusão.");
        }
        Usuario usuario = (Usuario) usuarioRepository.findById(id);
        if (usuario == null) {
            throw new DadosInvalidosException("Usuário não encontrado para exclusão.");
        }
        usuarioRepository.delete(id);
    }

    @Override
    public void salvar(Usuario usuario) throws DadosInvalidosException{
        if (usuario == null) {
            throw new DadosInvalidosException("Usuário não pode ser nulo.");
        }
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizar(Usuario usuarioAtualizado) throws DadosInvalidosException {
        if (usuarioAtualizado == null || usuarioAtualizado.getId() == null) {
            throw new DadosInvalidosException("Usuário inválido para atualização.");
        }
        Usuario usuarioExistente = (Usuario) usuarioRepository.findById(Integer.parseInt(usuarioAtualizado.getId()));
        if (usuarioExistente == null) {
            throw new DadosInvalidosException("Usuário não encontrado para atualização.");
        }
        usuarioRepository.update(usuarioAtualizado);
    }
}
