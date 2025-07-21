package com.example.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaAdicaoServicosAdicionaisHospede {

    @FXML
    private Button btnSair;

    @FXML
    private Button btnConfirmarAdicao;

    @FXML
    private Button btnVoltar;

    @FXML
    private ListView<String> lvwReservas;

    @FXML
    private ChoiceBox<String> cbxItensAdicionais;

    @FXML
    public void initialize() {
        // Dados fictícios para ilustração. Substitua por dados reais.
        lvwReservas.setItems(FXCollections.observableArrayList(
                "Reserva 101 - João",
                "Reserva 102 - Maria",
                "Reserva 103 - Carlos"
        ));

        cbxItensAdicionais.setItems(FXCollections.observableArrayList(
                "Café da Manhã",
                "Massagem",
                "Serviço de Quarto",
                "Estacionamento"
        ));
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void voltar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/telas/MenuPrincipal.fxml"));
            AnchorPane root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Menu Principal");
            stage.setScene(new Scene(root));
            stage.show();

            Stage atual = (Stage) btnVoltar.getScene().getWindow();
            atual.close();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao voltar para o menu: " + e.getMessage());
        }
    }

    @FXML
    private void confirmarAdicao() {
        String reservaSelecionada = lvwReservas.getSelectionModel().getSelectedItem();
        String itemSelecionado = cbxItensAdicionais.getValue();

        if (reservaSelecionada == null || itemSelecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Selecione uma reserva e um item adicional.");
            return;
        }

        // Aqui entra a lógica de adição real ao banco ou lista
        mostrarAlerta(Alert.AlertType.INFORMATION, "Item Adicionado", 
                "Item '" + itemSelecionado + "' adicionado à " + reservaSelecionada + ".");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
