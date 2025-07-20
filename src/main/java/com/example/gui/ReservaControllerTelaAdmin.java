package com.example.gui;

import com.example.controllers.IReservaController;
import com.example.controllers.ReservaController;
import com.example.models.Reserva;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReservaControllerTelaAdmin {

    @FXML
    private TableView<Reserva> tabelaReservas;

    @FXML
    private Button btnAcessar, btnExcluir, btnConfirmar, btnCheckInOut, btnVoltar, btnSair;

    private ReservaController reservaController = new ReservaController();

    @FXML
    public void initialize() {
        atualizarTabela();
    }

    private void atualizarTabela() {
        tabelaReservas.getItems().setAll(reservaController.listarTodasReservas());
    }

    @FXML
    private void acessarReserva() {
        Reserva selecionada = tabelaReservas.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione uma reserva para acessar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/telas/TelaDetalhesReserva.fxml"));
            AnchorPane pane = loader.load();

            TelaEdicaoReservasController controller = loader.getController();
            controller.setReserva(selecionada);

            Stage stage = new Stage();
            stage.setTitle("Detalhes da Reserva");
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao carregar os detalhes da reserva.");
        }
    }

    @FXML
    private void excluirReserva() {
        Reserva selecionada = tabelaReservas.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione uma reserva para excluir.");
            return;
        }

        reservaController.removerReserva(selecionada.getId());
        atualizarTabela();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Reserva removida com sucesso.");
    }

    @FXML
    private void confirmarReserva() {
        Reserva selecionada = tabelaReservas.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione uma reserva para confirmar.");
            return;
        }

        selecionada.setConfirmada(true);
        reservaController.atualizarReserva(selecionada);
        atualizarTabela();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Confirmado", "Reserva confirmada com sucesso.");
    }

    @FXML
    private void realizarCheckInOut() {
        Reserva selecionada = tabelaReservas.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione uma reserva.");
            return;
        }

        long dias = java.time.temporal.ChronoUnit.DAYS.between(
            selecionada.getDataEntrada().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate(),
            selecionada.getDataSaida().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
        );

        mostrarAlerta(Alert.AlertType.INFORMATION, "Estadia", "Dias reservados: " + dias);
    }

    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/telas/TelaAposLoginAdmin.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(pane));
            stage.setTitle("Painel do Administrador");
            stage.show();

            // Fecha a tela atual
            Stage atual = (Stage) btnVoltar.getScene().getWindow();
            atual.close();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao voltar à tela anterior.");
        }
    }

    @FXML
    private void sair() {
        Stage atual = (Stage) btnSair.getScene().getWindow();
        atual.close();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
