package com.example.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.controllers.PagamentoController;
import com.example.models.Hospede;
import com.example.models.Pagamento;
import com.example.models.Reserva;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaPagamentosController implements Initializable {

    // Tabela e colunas
    @FXML private TableView<Pagamento> tabelaPagamentos;
    @FXML private TableColumn<Pagamento, String> colFormaPagamento;
    @FXML private TableColumn<Pagamento, String> colServicosAdicionais;
    @FXML private TableColumn<Pagamento, Double> colValorTotal;
    @FXML private TableColumn<Pagamento, Integer> colReserva;
    @FXML private TableColumn<Pagamento, String> colHospede;
    @FXML private TableColumn<Pagamento, String> colStatus;

    // Botões
    @FXML private Button btnAcessarPagamento;
    @FXML private Button btnNovoPagamento;
    @FXML private Button btnGerarFatura;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    // Controller (simulado)
    private PagamentoController pagamentoController = new PagamentoController(); 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabela();
        carregarPagamentos();
    }

    private void configurarTabela() {
        colFormaPagamento.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getFormaDePagamento().toString()));

        colServicosAdicionais.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getServicosAdicionais().toString()));

        colValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

        colReserva.setCellValueFactory(cellData -> {
            Reserva reserva = cellData.getValue().getReserva();
            return new SimpleObjectProperty<>(reserva != null ? reserva.getId() : null);
        });

        colHospede.setCellValueFactory(cellData -> {
            Hospede hospede = cellData.getValue().getHospede();
            return new SimpleStringProperty(hospede != null ? hospede.getNome() : "N/A");
        });

        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void carregarPagamentos() {
        Pagamento[] lista = pagamentoController.listarTodasPagamento();
        tabelaPagamentos.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    private void acessarPagamento() {
        Pagamento pagamento = tabelaPagamentos.getSelectionModel().getSelectedItem();
        if (pagamento != null) {
            mostrarAlerta(AlertType.INFORMATION, "Pagamento", pagamento.toString());
            // aqui você pode abrir nova tela com detalhes se quiser
        } else {
            mostrarAlerta(AlertType.WARNING, "Atenção", "Selecione um pagamento.");
        }
    }

    @FXML
    private void gerarFatura() {
        Pagamento pagamento = tabelaPagamentos.getSelectionModel().getSelectedItem();
        if (pagamento != null) {
            // lógica para gerar fatura (ex: salvar PDF, simular cálculo etc)
            mostrarAlerta(AlertType.INFORMATION, "Fatura", "Fatura gerada para o pagamento ID: " + pagamento.getId());
        } else {
            mostrarAlerta(AlertType.WARNING, "Atenção", "Selecione um pagamento para gerar a fatura.");
        }
    }

    @FXML
    private void novoPagamento() {
        // lógica para abrir nova tela de cadastro de pagamento
        mostrarAlerta(AlertType.INFORMATION, "Novo Pagamento", "Tela de novo pagamento ainda não implementada.");
    }

    @FXML
    private void voltar() {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/com/TelaAposLoginAdmin.fxml"));
        Parent root = loader.load();


        Stage stage = (Stage) btnVoltar.getScene().getWindow();


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tela Anterior");
        stage.show();
    } catch (IOException e) {
        mostrarAlerta(AlertType.ERROR, "Erro", "Não foi possível voltar: " + e.getMessage());
    }
    }

    @FXML
    private void sair() {
        System.exit(0);
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String msg) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(msg);
        alerta.showAndWait();
    }
}
