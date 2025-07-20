package com.example.gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.controllers.UsuarioController;
import com.example.enums.FormaDePagamento;
import com.example.enums.ServicosAdicionais;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Hospede;
import com.example.models.Pagamento;
import com.example.models.Reserva;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaHospedeParaEdicao {

    @FXML private TextField txtCpf;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtEndereco;
    @FXML private TextField txtReserva;
    @FXML private TextField txtPagamento;
    @FXML private Button btnFinalizar;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    private final UsuarioController usuarioController = new UsuarioController();
    private Hospede hospedeAtual;

    public void setHospede(Hospede hospede) {
        this.hospedeAtual = hospede;

        // Preenche os campos com os dados do hóspede
        txtCpf.setText(hospede.getCpf());
        txtEmail.setText(hospede.getEmail());
        txtTelefone.setText(hospede.getTelefone());
        txtEndereco.setText(hospede.getEndereco());
        txtReserva.setText(hospede.getReservas().toString());
        txtPagamento.setText(hospede.getPagamentos().toString());
    }

    private ArrayList<Reserva> parseReservas(String reservasText) throws ParseException {
        ArrayList<Reserva> reservas = new ArrayList<>();
        if (reservasText != null && !reservasText.isEmpty()) {
            String[] reservasArray = reservasText.split(","); // Assuming comma-separated values
            for (String reserva : reservasArray) {
                String[] fields = reserva.trim().split(";");
                int id = Integer.parseInt(fields[0]);
                java.util.Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(fields[1]);
                java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(fields[2]);
                Hospede hospede = hospedeAtual; // Assuming the current hospede is used
                int roomNumber = Integer.parseInt(fields[3]);
                double price = Double.parseDouble(fields[4]);
                reservas.add(new Reserva(id, startDate, endDate, hospede, roomNumber, price, false));
            }
        }
        return reservas;
    }

    private ArrayList<Pagamento> parsePagamentos(String pagamentosText) {
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
        if (pagamentosText != null && !pagamentosText.isEmpty()) {
            String[] pagamentosArray = pagamentosText.split(",");
            for (String pagamento : pagamentosArray) {
                String[] fields = pagamento.trim().split(";"); 
                int id = Integer.parseInt(fields[0]);
                FormaDePagamento formaDePagamento = FormaDePagamento.valueOf(fields[1]); 
                ServicosAdicionais servicosAdicionais = ServicosAdicionais.valueOf(pagamento); 
                double valor = Double.parseDouble(fields[3]);
                Reserva reserva = new Reserva(Integer.parseInt(fields[4]), null, null, hospedeAtual, id, valor, false);
                pagamentos.add(new Pagamento(id, formaDePagamento, servicosAdicionais, valor, reserva, hospedeAtual));
            }
        }
        return pagamentos;
    }

    @FXML
    private void finalizarEdicao() {
        try {
            hospedeAtual.setCpf(txtCpf.getText());
            hospedeAtual.setEmail(txtEmail.getText());
            hospedeAtual.setTelefone(txtTelefone.getText());
            hospedeAtual.setEndereco(txtEndereco.getText());
            try {
                hospedeAtual.setReservas(parseReservas(txtReserva.getText()));
            } catch (ParseException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro ao atualizar", "Erro ao processar reservas: " + e.getMessage());
                return;
            }
            hospedeAtual.setPagamentos(parsePagamentos(txtPagamento.getText()));

        
            usuarioController.atualizar(hospedeAtual);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Informações do hóspede atualizadas com sucesso!");

            fecharTela();

        } catch (DadosInvalidosException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao atualizar", e.getMessage());
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
        Stage stage = (Stage) btnFinalizar.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
