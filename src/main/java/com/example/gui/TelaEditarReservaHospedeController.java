package com.example.gui;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaEditarReservaHospedeController {

    @FXML
    private TextField txtNumeroHospedes;

    @FXML
    private TextField txtValorBase;

    @FXML
    private ChoiceBox<String> txtPagamentos;

    @FXML
    private ChoiceBox<String> cbxItensReserva;

    @FXML
    private DatePicker dtpEntrada;

    @FXML
    private DatePicker dtpSaida;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnVoltar;

    @FXML
    public void initialize() {
        txtPagamentos.setItems(FXCollections.observableArrayList("PIX", "Cartão de Crédito", "Boleto"));
        cbxItensReserva.setItems(FXCollections.observableArrayList("Café da manhã", "Spa", "Serviço de Quarto"));
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/telas/ReservasHospede.fxml"));
            AnchorPane pane = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Reservas do Hóspede");
            stage.setScene(new Scene(pane));
            stage.show();

            Stage atual = (Stage) btnVoltar.getScene().getWindow();
            atual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao voltar: " + e.getMessage());
        }
    }

    @FXML
    private void finalizarEdicao() {
        try {
            int numeroHospedes = Integer.parseInt(txtNumeroHospedes.getText());
            double valorBase = Double.parseDouble(txtValorBase.getText());
            String formaPagamento = txtPagamentos.getValue();
            String itemReserva = cbxItensReserva.getValue();
            LocalDate entrada = dtpEntrada.getValue();
            LocalDate saida = dtpSaida.getValue();

            if (formaPagamento == null || itemReserva == null || entrada == null || saida == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Preencha todos os campos.");
                return;
            }

            // Aqui entraria a lógica real de atualização da reserva

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Reserva editada com sucesso!");

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Número inválido para hóspedes ou valor base.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
