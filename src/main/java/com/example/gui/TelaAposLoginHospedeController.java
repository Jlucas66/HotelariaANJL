package com.example.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaAposLoginHospedeController {

    @FXML
    private Button btnSair;

    @FXML
    private Button btnReservas;

    @FXML
    private Button btnServicosAdicionais;

    @FXML
    private Button btnVoltar;

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/com/TelaLogin.fxml"));
            AnchorPane pane = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Tela de Login");
            stage.setScene(new Scene(pane));
            stage.show();

            Stage atual = (Stage) btnVoltar.getScene().getWindow();
            atual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao voltar: " + e.getMessage());
        }
    }

    @FXML
    private void abrirMinhasReservas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/com/TelaReservasHospede.fxml"));
            AnchorPane pane = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Minhas Reservas");
            stage.setScene(new Scene(pane));
            stage.show();

            Stage atual = (Stage) btnReservas.getScene().getWindow();
            atual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao abrir tela de reservas: " + e.getMessage());
        }
    }

    @FXML
    private void abrirServicosAdicionais() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/com/TelaServicosAdicionais.fxml"));
            AnchorPane pane = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Serviços Adicionais");
            stage.setScene(new Scene(pane));
            stage.show();

            Stage atual = (Stage) btnServicosAdicionais.getScene().getWindow();
            atual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao abrir tela de serviços: " + e.getMessage());
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
