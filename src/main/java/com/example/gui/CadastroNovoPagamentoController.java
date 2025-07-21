package com.example.gui;

import com.example.enums.FormaDePagamento;
import com.example.enums.ServicosAdicionais;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Hospede;
import com.example.models.ItemReserva;
import com.example.models.Pagamento;
import com.example.models.Reserva;
import com.example.models.Usuario;
import com.example.controllers.*;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.controlsfx.control.PropertySheet;

import javafx.scene.Scene;

public class CadastroNovoPagamentoController {

    // Campos de entrada
    @FXML private TextField txtHospede;
    @FXML private ComboBox<FormaDePagamento> cbFormaPagamento;
    @FXML private ComboBox<ServicosAdicionais> cbServicosAdicionais;
    @FXML private ComboBox<Reserva> cbReserva;
    @FXML private TextField txtStatus;

    // Botões
    @FXML private Button btnFinalizar;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    private final PagamentoController pagamentoController = new PagamentoController();
    private final ReservaController reservaController = new ReservaController();
    private final UsuarioController usuarioController = new UsuarioController();

    @FXML
    public void initialize() {
        cbFormaPagamento.setItems(FXCollections.observableArrayList(FormaDePagamento.values()));
        cbServicosAdicionais.setItems(FXCollections.observableArrayList(ServicosAdicionais.values()));
        cbReserva.setItems(FXCollections.observableArrayList(reservaController.listarTodasReservas()));
        txtStatus.setText("Pendente");
    }

    @FXML
    private void finalizarPagamento() {
        String nomeHospede = txtHospede.getText().trim();
        FormaDePagamento forma = cbFormaPagamento.getValue();
        ServicosAdicionais servico = cbServicosAdicionais.getValue();
        Reserva reservaSelecionada = cbReserva.getValue();
        String status = txtStatus.getText().trim();

        if (nomeHospede.isEmpty() || forma == null || servico == null || reservaSelecionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios",
                         "Preencha todos os campos antes de finalizar.");
            return;
        }
    
        Hospede hospede;
        Usuario[] todos = usuarioController.listarTodosUsuarios();
        hospede = Arrays.stream(todos)
                .filter(u -> u instanceof Hospede)
                .map(u -> (Hospede) u)
                .filter(h -> h.getNome().equalsIgnoreCase(nomeHospede))
                .findFirst()
                .orElse(null);
        if (hospede == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Hóspede não encontrado",
                    "Não existe hóspede cadastrado com esse nome.");
            return;
        }
    

        double valorBase = reservaSelecionada.getValorBase();
        double valorAdicional = reservaSelecionada.getPagamento().getValorTotal(); 
        double valorTotal = valorBase + valorAdicional;
    
        Pagamento pagamento = new Pagamento(
            forma,
            servico,
            valorTotal,
            reservaSelecionada,
            hospede
        );
        pagamento.setStatus(status);
    
        pagamentoController.salvar(pagamento);  
    
        mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso",
                      "Pagamento cadastrado com sucesso.");
        voltar();
    }
    

    @FXML
    private void voltar() {
        try {
            var loader = new javafx.fxml.FXMLLoader(
                getClass().getResource("/com/resources/com/TelaPagamentos.fxml")
            );
            var root = loader.load();
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Pagamentos");
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao voltar",
                         "Não foi possível carregar a tela de pagamentos.");
        }
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
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
