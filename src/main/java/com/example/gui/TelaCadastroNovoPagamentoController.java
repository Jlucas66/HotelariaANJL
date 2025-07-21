package com.example.gui;

import java.util.Arrays;

import com.example.controllers.PagamentoController;
import com.example.controllers.ReservaController;
import com.example.controllers.UsuarioController;
import com.example.enums.FormaDePagamento;
import com.example.enums.ServicosAdicionais;
import com.example.models.Hospede;
import com.example.models.Pagamento;
import com.example.models.Reserva;
import com.example.models.Usuario;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroNovoPagamentoController {

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
