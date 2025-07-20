package com.example.gui;

import com.example.controllers.QuartoController;
import com.example.enums.TipoQuarto;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Quarto;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCriarQuartoController {

    @FXML private TextField txtTipo;
    @FXML private TextField txtCapacidade;
    @FXML private TextField txtPreco;
    @FXML private TextField txtCamas;

    @FXML private Button btnFinalizar;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    private QuartoController quartoController = new QuartoController();

    @FXML
    private void finalizarCriacao() {
        try {
            String tipo = txtTipo.getText();
            int capacidade = Integer.parseInt(txtCapacidade.getText());
            double preco = Double.parseDouble(txtPreco.getText());
            int camas = Integer.parseInt(txtCamas.getText());

            TipoQuarto tipoQuarto = TipoQuarto.valueOf(tipo.toUpperCase());
            Quarto novoQuarto = new Quarto(0, tipoQuarto, capacidade, preco, camas);
            try {
                quartoController.criarQuarto(tipoQuarto, capacidade, preco, camas);
            } catch (DadosInvalidosException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de dados", "Dados inválidos: " + e.getMessage());
                return;
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Quarto cadastrado com sucesso!");
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de formato", "Verifique os campos numéricos.");
        }
    }

    @FXML
    private void voltar() {
        fecharTela();
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    private void fecharTela() {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    private void limparCampos() {
        txtTipo.clear();
        txtCapacidade.clear();
        txtPreco.clear();
        txtCamas.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
