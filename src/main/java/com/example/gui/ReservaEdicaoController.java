package com.example.gui;

import com.example.controllers.IReservaController;
import com.example.controllers.ReservaController;
import com.example.exceptions.DadosInvalidosException;
import com.example.controllers.IQuartoController;
import com.example.controllers.QuartoController;
import com.example.models.Quarto;
import com.example.models.Reserva;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class ReservaEdicaoController {

    @FXML private TextField txtResponsavel;
    @FXML private TextField txtNumeroHospedes;
    @FXML private TextField txtValorBase;
    @FXML private TextField txtPagamento;
    @FXML private DatePicker dpEntrada;
    @FXML private DatePicker dpSaida;
    @FXML private ComboBox<Quarto> cbItensReserva;
    @FXML private Button btnFinalizar;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    private ReservaController reservaController = new ReservaController();
    private QuartoController quartoController = new QuartoController();
    private Reserva reservaAtual;

    public void setReserva(Reserva reserva) {
        this.reservaAtual = reserva;
        preencherCampos();
    }

    @FXML
    private void initialize() {
        carregarQuartos();
    }

    private void carregarQuartos() throws DadosInvalidosException {
        Quarto[] quartos = quartoController.listarTodosQuarto(); 
        cbItensReserva.getItems().addAll(quartos);
    }

    private void preencherCampos() {
        if (reservaAtual == null) return;

        txtResponsavel.setText(reservaAtual.getResponsavel());
        txtNumeroHospedes.setText(String.valueOf(reservaAtual.getNumeroHospedes()));
        txtValorBase.setText(String.valueOf(reservaAtual.getValorBase()));
        txtPagamento.setText(String.valueOf(reservaAtual.getPagamento()));
        dpEntrada.setValue(reservaAtual.getDataEntrada());
        dpSaida.setValue(reservaAtual.getDataSaida());

        // Seleciona o primeiro quarto da reserva, se houver
        if (reservaAtual.getQuartos() != null && !reservaAtual.getQuartos().isEmpty()) {
            cbItensReserva.setValue(reservaAtual.getQuartos().get(0));
        }
    }

    @FXML
    private void finalizarEdicao() {
        try {
            reservaAtual.setResponsavel(txtResponsavel.getText());
            reservaAtual.setNumeroHospedes(Integer.parseInt(txtNumeroHospedes.getText()));
            reservaAtual.setValorBase(Double.parseDouble(txtValorBase.getText()));
            reservaAtual.setPagamento(Double.parseDouble(txtPagamento.getText()));
            reservaAtual.setDataEntrada(dpEntrada.getValue());
            reservaAtual.setDataSaida(dpSaida.getValue());

            Quarto quartoSelecionado = cbItensReserva.getValue();
            reservaAtual.getQuartos().clear();
            reservaAtual.getQuartos().add(quartoSelecionado);

            reservaController.atualizarReserva(reservaAtual);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Reserva atualizada com sucesso.");

            fecharTela();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao atualizar reserva: " + e.getMessage());
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

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
