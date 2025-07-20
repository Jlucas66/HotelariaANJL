package com.example.gui;

import com.example.controllers.QuartoController;
import com.example.enums.TipoQuarto;
import com.example.models.Quarto;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaEditarQuartoController {

    @FXML private TextField txtTipo;
    @FXML private TextField txtCapacidade;
    @FXML private TextField txtPreco;
    @FXML private TextField txtCamas;

    @FXML private Button btnFinalizar;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    private QuartoController quartoController = new QuartoController();
    private Quarto quartoAtual;

    public void setQuarto(Quarto quarto) {
        this.quartoAtual = quarto;
        preencherCampos(quarto);
    }

    private void preencherCampos(Quarto quarto) {
        txtTipo.setText(quarto.getTipoQuarto().toString());
        txtCapacidade.setText(String.valueOf(quarto.getNumeroDeCamas()));
        txtPreco.setText(String.valueOf(quarto.getPreco()));
        txtCamas.setText(String.valueOf(quarto.getNumeroDeCamas()));
    }

    @FXML
    private void finalizarEdicao() {
        try {
            String tipo = txtTipo.getText();
            int capacidade = Integer.parseInt(txtCapacidade.getText());
            double preco = Double.parseDouble(txtPreco.getText());
            int camas = Integer.parseInt(txtCamas.getText());

            quartoAtual.setTipoQuarto(TipoQuarto.valueOf(tipo.toUpperCase()));
            quartoAtual.setNumeroDeCamas(capacidade);
            quartoAtual.setPreco(preco);
            quartoAtual.setNumeroDeCamas(camas);

            quartoController.atualizar(quartoAtual);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Quarto atualizado com sucesso!");
            fecharTela();
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de formato", "Preencha os campos numéricos corretamente.");
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

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
