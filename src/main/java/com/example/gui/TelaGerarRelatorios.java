package com.example.gui;

import java.time.LocalDate;

import com.example.models.Relatorio;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaGerarRelatorios {

    @FXML
    private Button btnSair;

    @FXML
    private Button btnReservas;

    @FXML
    private Button btnFaturamento;

    @FXML
    private Button btnOcupacao;

    @FXML
    private Button btnCancelamento;

    @FXML
    private Button btnServicosAdicionais;

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dtpInicio;

    @FXML
    private DatePicker dtpFinal;

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/telas/TelaAposLoginAdmin.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu Administrativo");
            stage.setScene(new Scene(pane));
            stage.show();

            Stage telaAtual = (Stage) btnVoltar.getScene().getWindow();
            telaAtual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao voltar para o menu: " + e.getMessage());
        }
    }

    private java.util.Date getDataInicio() {
        LocalDate localDate = dtpInicio.getValue();
        return localDate != null ? java.util.Date.valueOf(localDate) : null;
    }

    private java.util.Date getDataFim() {
        LocalDate localDate = dtpFinal.getValue();
        return localDate != null ? java.util.Date.valueOf(localDate) : null;
    }

    @FXML
    private void gerarRelatorioReservas() {
        Relatorio relatorio = new Relatorio();
        relatorio.gerarRelatorioReservasPorPeriodo(getDataInicio(), getDataFim());
        mostrarAlerta(Alert.AlertType.INFORMATION, "Relatório", "Relatório de reservas gerado com sucesso!");
    }

    @FXML
    private void gerarRelatiorioFaturamento() {
        Relatorio relatorio = new Relatorio();
        relatorio.gerarRelatorioFaturamentoPorPeriodo(getDataInicio(), getDataFim());
        mostrarAlerta(Alert.AlertType.INFORMATION, "Relatório", "Relatório de faturamento gerado com sucesso!");
    }

    @FXML
    private void gerarRelatorioOcupacao() {
        Relatorio relatorio = new Relatorio();
        relatorio.gerarRelatorioOcupacaoPorPeriodo(getDataInicio(), getDataFim());
        mostrarAlerta(Alert.AlertType.INFORMATION, "Relatório", "Relatório de ocupação gerado com sucesso!");
    }

    @FXML
    private void gerarRelatorioCancelamento() {
        Relatorio relatorio = new Relatorio();
        relatorio.gerarRelatorioCancelamentoPorPeriodo(getDataInicio(), getDataFim());
        mostrarAlerta(Alert.AlertType.INFORMATION, "Relatório", "Relatório de cancelamentos gerado com sucesso!");
    }

    @FXML
    private void gerarRelatorioServicosAdicionais() {
        Relatorio relatorio = new Relatorio();
        relatorio.gerarRelatorioServicosAdicionaisPorPeriodo(getDataInicio(), getDataFim());
        mostrarAlerta(Alert.AlertType.INFORMATION, "Relatório", "Relatório de serviços adicionais gerado com sucesso!");
    }
}
