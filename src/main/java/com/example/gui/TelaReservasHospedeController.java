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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaReservasHospedeController {

    @FXML
    private Button btnSair;

    @FXML
    private Button btnAcessarReserva;

    @FXML
    private Button btnConfirmarReserva;

    @FXML
    private Button btnExcluirReserva;

    @FXML
    private Button btnVoltar;

    @FXML
    private ListView<String> lvMinhasReservas;

    @FXML
    private TextField txtNumeroHospede;

    @FXML
    private ChoiceBox<String> cbxPagamento;

    @FXML
    private ChoiceBox<String> cbxItensReserva;

    @FXML
    private DatePicker dpkDataEntrada;

    @FXML
    private DatePicker dpkDataSaida;

    // Inicialização
    @FXML
    public void initialize() {
        cbxPagamento.setItems(FXCollections.observableArrayList("PIX", "Cartão de Crédito", "Boleto"));
        cbxItensReserva.setItems(FXCollections.observableArrayList("Café da manhã", "Spa", "Serviço de Quarto"));
        lvMinhasReservas.setItems(FXCollections.observableArrayList(
                "Reserva 1 - 01/08 a 03/08",
                "Reserva 2 - 10/08 a 15/08"
        ));
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void volta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/com/TelaPainelHospede.fxml"));
            AnchorPane pane = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Painel do Hóspede");
            stage.setScene(new Scene(pane));
            stage.show();

            Stage atual = (Stage) btnVoltar.getScene().getWindow();
            atual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao voltar: " + e.getMessage());
        }
    }

    @FXML
    private void acessarReserva() {
        String reservaSelecionada = lvMinhasReservas.getSelectionModel().getSelectedItem();
        if (reservaSelecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione uma reserva para acessar.");
            return;
        }

        mostrarAlerta(Alert.AlertType.INFORMATION, "Reserva", "Detalhes da reserva: " + reservaSelecionada);
    }

    @FXML
    private void confirmaReserrva() {
        try {
            int numeroHospedes = Integer.parseInt(txtNumeroHospede.getText());
            String formaPagamento = cbxPagamento.getValue();
            String itemReserva = cbxItensReserva.getValue();
            LocalDate entrada = dpkDataEntrada.getValue();
            LocalDate saida = dpkDataSaida.getValue();

            if (formaPagamento == null || itemReserva == null || entrada == null || saida == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Preencha todos os campos.");
                return;
            }

            // Aqui você incluiria a lógica de confirmação no sistema real

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Reserva confirmada com sucesso!");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Número de hóspedes inválido.");
        }
    }

    @FXML
    private void escluirReserva() {
        String reservaSelecionada = lvMinhasReservas.getSelectionModel().getSelectedItem();
        if (reservaSelecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione uma reserva para excluir.");
            return;
        }

        // Aqui você incluiria a lógica para excluir a reserva

        lvMinhasReservas.getItems().remove(reservaSelecionada);
        mostrarAlerta(Alert.AlertType.INFORMATION, "Exclusão", "Reserva excluída com sucesso!");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
