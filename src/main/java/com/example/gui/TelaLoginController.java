package com.example.gui;

import com.example.controllers.UsuarioController;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Funcionario;
import com.example.models.Hospede;
import com.example.models.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaLoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private ComboBox<String> comboTipoLogin;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnSair;

    private UsuarioController usuarioController = new UsuarioController();

    @FXML
    public void initialize() {
        comboTipoLogin.getItems().addAll("Hospede", "Administrador");
        comboTipoLogin.setValue("Hospede"); // valor padrão
    }

    @FXML
    private void entrar() {
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        String tipoSelecionado = comboTipoLogin.getValue();

        if (email.isEmpty() || senha.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Preencha todos os campos.");
            return;
        }

        try {
            Usuario usuario = usuarioController.buscarPorEmailESenha(email, senha);

            if (usuario == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Login falhou", "Usuário ou senha inválidos.");
                return;
            }

            if (tipoSelecionado.equals("Hospede") && usuario instanceof Hospede) {
                abrirTela("/gui/telas/TelaAposLoginHospede.fxml");
            } else if (tipoSelecionado.equals("Administrador") && usuario instanceof Funcionario) {
                abrirTela("/gui/telas/TelaAposLoginAdmin.fxml");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Tipo incorreto", "O usuário não possui permissão para esse tipo de acesso.");
            }
        } catch (DadosInvalidosException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de login", e.getMessage());
        }
    }

    @FXML
    private void cadastrar() {
        abrirTela("/gui/telas/TelaCadastro.fxml");
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    private void abrirTela(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            stage.setTitle("Sistema de Hotelaria");
            stage.show();

            // Fecha a tela de login atual se for navegação (exceto para cadastro)
            if (!caminhoFXML.contains("Cadastro")) {
                Stage loginStage = (Stage) btnEntrar.getScene().getWindow();
                loginStage.close();
            }

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao abrir tela", "Não foi possível abrir a próxima tela.");
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
