package com.example.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaAposLoginAdminController {

    @FXML private Button btnHospedes;
    @FXML private Button btnQuartos;
    @FXML private Button btnReservas;
    @FXML private Button btnPagamentos;
    @FXML private Button btnDescontos;
    @FXML private Button btnPromocoes;
    @FXML private Button btnRelatorios;
    @FXML private Button btnServicosAdicionais;
    @FXML private Button btnTarifas;
    @FXML private Button btnBackup;
    @FXML private Button btnSair;

    @FXML
    private void abrirHospedes() {
        abrirTela("/gui/telas/TelaHospedes.fxml");
    }

    @FXML
    private void abrirQuartos() {
        abrirTela("/gui/telas/TelaQuartos.fxml");
    }

    @FXML
    private void abrirReservas() {
        abrirTela("/gui/telas/TelaReservas.fxml");
    }

    @FXML
    private void abrirPagamentos() {
        abrirTela("/gui/telas/TelaPagamentos.fxml");
    }

    @FXML
    private void abrirDescontos() {
        abrirTela("/gui/telas/TelaDescontos.fxml");
    }

    @FXML
    private void abrirPromocoes() {
        abrirTela("/gui/telas/TelaPromocoes.fxml");
    }

    @FXML
    private void abrirRelatorios() {
        abrirTela("/gui/telas/TelaRelatorios.fxml");
    }

    @FXML
    private void abrirServicosAdicionais() {
        abrirTela("/gui/telas/TelaServicosAdicionais.fxml");
    }

    @FXML
    private void abrirTarifas() {
        abrirTela("/gui/telas/TelaTarifas.fxml");
    }

    @FXML
    private void gerarBackup() {
        // Aqui você pode implementar a lógica de gerar backup, por exemplo, salvar arquivos de dados.
        mostrarAlerta(Alert.AlertType.INFORMATION, "Backup", "Backup gerado com sucesso.");
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
            stage.setTitle("Sistema de Hotelaria - Admin");
            stage.show();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao abrir tela", "Não foi possível carregar a próxima tela.");
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
