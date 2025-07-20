package com.example.gui;

import com.example.models.ItemReserva;
import com.example.models.Reserva;
import com.example.models.Quarto;
import com.example.controllers.ReservaController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

public class TelaEdicaoReservasController {

    @FXML private TextField txtResponsavel;
    @FXML private TextField txtNumeroHospedes;
    @FXML private TextField txtValorBase;
    @FXML private TextField txtPagamento;
    @FXML private DatePicker dpEntrada;
    @FXML private DatePicker dpSaida;

    @FXML private TableView<ItemReserva> tblItensReserva;
    @FXML private TableColumn<ItemReserva, String> colQuarto;

    @FXML private Button btnFinalizarEdicao;
    @FXML private Button btnVoltar;

    private Reserva reservaAtual;
    private ReservaController reservaController = new ReservaController();
    private ObservableList<ItemReserva> itensObservable;

    public void setReserva(Reserva reserva) {
        this.reservaAtual = reserva;
        preencherCampos();
    }

    @FXML
    private void initialize() {
        colQuarto.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue().getQuarto().getNumeroDeCamas() + " - " + data.getValue().getQuarto().getPreco())
        );

        itensObservable = FXCollections.observableArrayList();
        tblItensReserva.setItems(itensObservable);
    }

    private void preencherCampos() {
        if (reservaAtual == null) return;

        txtResponsavel.setText(reservaAtual.getHospedeResponsavel().getCpf());
        txtNumeroHospedes.setText(String.valueOf(reservaAtual.getNumeroDeHospedes()));
        txtValorBase.setText(String.valueOf(reservaAtual.getValorBase()));
        txtPagamento.setText(String.valueOf(reservaAtual.getPagamento()));

        if (reservaAtual.getDataEntrada() != null)
            dpEntrada.setValue(reservaAtual.getDataEntrada().toLocalDate());

        if (reservaAtual.getDataSaida() != null)
            dpSaida.setValue(reservaAtual.getDataSaida().toLocalDate());

        itensObservable.setAll(reservaAtual.getItensReserva());
    }

    @FXML
    private void finalizarEdicao() {
        try {
            reservaAtual.setHospedeResponsavel(txtResponsavel.getText());
            reservaAtual.setNumeroDeHospedes(Integer.parseInt(txtNumeroHospedes.getText()));
            reservaAtual.setValorBase(Double.valueOf(txtValorBase.getText()));
            reservaAtual.setPagamento(Double.parseDouble(txtPagamento.getText()));

            if (dpEntrada.getValue() != null)
                reservaAtual.setDataEntrada(Date.valueOf(dpEntrada.getValue()));
            if (dpSaida.getValue() != null)
                reservaAtual.setDataSaida(Date.valueOf(dpSaida.getValue()));

            reservaAtual.setItensReserva(new ArrayList<>(itensObservable));

            reservaController.atualizarReserva(reservaAtual);

            mostrarAlerta(AlertType.INFORMATION, "Sucesso", "Reserva atualizada com sucesso.");
            fechar();
        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Erro", "Erro ao atualizar reserva: " + e.getMessage());
        }
    }

    @FXML
    private void voltar() {
        fechar();
    }

    private void fechar() {
        Stage stage = (Stage) btnFinalizarEdicao.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
