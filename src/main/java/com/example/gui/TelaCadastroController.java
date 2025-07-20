package com.example.gui;

import com.example.controllers.IUsuarioController;
import com.example.controllers.UsuarioController;
import com.example.enums.Cargo;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Hospede;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroController {

    @FXML
    private TextField txtNome, txtCpf, txtEmail, txtSalarioFixo, txtReservasFeitas, txtTaxaReserva;

    @FXML
    private PasswordField txtSenha, txtConfirmarSenha;

    @FXML
    private ComboBox<String> comboTipoUsuario;

    @FXML
    private ComboBox<Cargo> comboCargo;

    @FXML
    private Button btnCadastrar, btnSair;

    private IUsuarioController usuarioController = new UsuarioController();

    @FXML
    public void initialize() {
        comboTipoUsuario.getItems().addAll("Hospede", "Funcionario Fixo", "Funcionario Comissionado", "Funcionario Fixo + Comissão");
        comboTipoUsuario.setValue("Hospede");

        comboCargo.getItems().setAll(Cargo.values());
        comboCargo.setDisable(true); // Desativa até ser necessário

        comboTipoUsuario.setOnAction(e -> {
            String tipo = comboTipoUsuario.getValue();
            boolean isFuncionario = !tipo.equals("Hospede");
            comboCargo.setDisable(!isFuncionario);
            txtSalarioFixo.setDisable(!tipo.contains("Fixo"));
            txtReservasFeitas.setDisable(!tipo.contains("Comissionado") && !tipo.contains("Comissão"));
            txtTaxaReserva.setDisable(!tipo.contains("Comissionado") && !tipo.contains("Comissão"));
        });
    }

    @FXML
    private void cadastrar() {
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        String confirmar = txtConfirmarSenha.getText().trim();
        String tipo = comboTipoUsuario.getValue();
        Cargo cargo = comboCargo.getValue();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmar.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Preencha todos os campos obrigatórios.");
            return;
        }

        if (!senha.equals(confirmar)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de senha", "As senhas não coincidem.");
            return;
        }

        try {
            switch (tipo) {
                case "Hospede":
                    Hospede hospede = new Hospede(nome, cpf, email, senha, tipo, tipo, tipo);
                    usuarioController.salvar(hospede);
                    break;
                case "Funcionario Fixo":
                    validarFuncionario(cargo, txtSalarioFixo);
                    double salario = Double.parseDouble(txtSalarioFixo.getText());
                    usuarioController.cadastrarFuncionarioFixo(nome, cpf, email, cargo, salario);
                    break;
                case "Funcionario Comissionado":
                    validarFuncionario(cargo, txtReservasFeitas, txtTaxaReserva);
                    double reservas = Double.parseDouble(txtReservasFeitas.getText());
                    double taxa = Double.parseDouble(txtTaxaReserva.getText());
                    usuarioController.cadastrarFuncionarioComissionado(nome, cpf, email, cargo, reservas, taxa);
                    break;
                case "Funcionario Fixo + Comissão":
                    validarFuncionario(cargo, txtSalarioFixo, txtReservasFeitas, txtTaxaReserva);
                    double salarioF = Double.parseDouble(txtSalarioFixo.getText());
                    double resFeitas = Double.parseDouble(txtReservasFeitas.getText());
                    double taxaR = Double.parseDouble(txtTaxaReserva.getText());
                    usuarioController.cadastrarFuncionarioFixoMaisComissao(nome, cpf, email, cargo, salarioF, resFeitas, taxaR);
                    break;
                default:
                    mostrarAlerta(Alert.AlertType.ERROR, "Tipo inválido", "Selecione um tipo de usuário válido.");
                    return;
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, "Cadastro realizado", "Usuário cadastrado com sucesso!");
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro numérico", "Preencha corretamente os campos numéricos.");
        } catch (DadosInvalidosException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de cadastro", e.getMessage());
        }
    }

    private void validarFuncionario(Cargo cargo, TextField... campos) {
        if (cargo == null) {
            throw new IllegalArgumentException("Selecione um cargo.");
        }
        for (TextField campo : campos) {
            if (campo.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Preencha todos os campos de funcionário.");
            }
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtCpf.clear();
        txtEmail.clear();
        txtSenha.clear();
        txtConfirmarSenha.clear();
        txtSalarioFixo.clear();
        txtReservasFeitas.clear();
        txtTaxaReserva.clear();
        comboTipoUsuario.setValue("Hospede");
        comboCargo.setValue(null);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}
