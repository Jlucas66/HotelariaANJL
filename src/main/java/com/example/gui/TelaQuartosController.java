package com.example.gui;

import com.example.controllers.QuartoController;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Quarto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaQuartosController {

    @FXML private TableView<Quarto> tabelaQuartos;
    @FXML private Button btnNovoQuarto;
    @FXML private Button btnEditarQuarto;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    private final QuartoController quartoController = new QuartoController();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarQuartos();
    }

    private void configurarColunas() {
        TableColumn<Quarto, String> colunaTipo = new TableColumn<>("Tipo");
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Quarto, Integer> colunaCapacidade = new TableColumn<>("Capacidade");
        colunaCapacidade.setCellValueFactory(new PropertyValueFactory<>("capacidade"));

        TableColumn<Quarto, Double> colunaPreco = new TableColumn<>("preco");
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        TableColumn<Quarto, Integer> colunaCamas = new TableColumn<>("Camas");
        colunaCamas.setCellValueFactory(new PropertyValueFactory<>("numeroDeCamas"));

        tabelaQuartos.getColumns().addAll(colunaTipo, colunaCapacidade, colunaPreco, colunaCamas);
    }

    private void carregarQuartos() {
        try {
            ObservableList<Quarto> quartos = FXCollections.observableArrayList(quartoController.listarTodosQuarto());
            tabelaQuartos.setItems(quartos);
        } catch (DadosInvalidosException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao carregar quartos", e.getMessage());
        }
    }

    @FXML
    private void novoQuarto() {
        abrirTela("/gui/telas/TelaCriacaoQuartos.fxml");
    }

    @FXML
    private void editarQuarto() {
        Quarto selecionado = tabelaQuartos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleção necessária", "Selecione um quarto para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/telas/TelaEditarQuarto.fxml"));
            AnchorPane pane = loader.load();

            TelaEditarQuartoController controller = loader.getController();
            controller.setQuarto(selecionado);

            Stage stage = new Stage();
            stage.setTitle("Editar Quarto");
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a tela de edição.");
            e.printStackTrace();
        }
    }

    @FXML
    private void voltar() {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
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
            stage.setTitle("Gerenciar Quartos");
            stage.setScene(new Scene(pane));
            stage.show();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a tela.");
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
