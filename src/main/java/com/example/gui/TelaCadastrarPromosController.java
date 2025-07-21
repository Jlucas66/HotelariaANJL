package com.example.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastrarPromosController {

    @FXML
    private TextField valorDescontoField;

    @FXML
    private TextField promocaoField;

    @FXML
    private Button finalizarCadastroButton;

    @FXML
    private Button btnVoltar;

    @FXML
    private void initialize() {
        // Configuração inicial dos componentes
        configurarBotoes();
    }

    private void configurarBotoes() {
        finalizarCadastroButton.setOnAction(event -> {
            finalizarCadastro();
        });

        btnVoltar.setOnAction(event -> {
            voltarTela();
        });
    }

    private void finalizarCadastro() {
        String valorDesconto = valorDescontoField.getText();
        String promocao = promocaoField.getText();
        
        if (valorDesconto.isEmpty() || promocao.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }
        
        System.out.println("Cadastro finalizado com:");
        System.out.println("Valor do Desconto: " + valorDesconto);
        System.out.println("Promoção: " + promocao);
        
        // Aqui você pode adicionar a lógica para salvar no banco de dados
    }

    private void voltarTela() {
        // Lógica para voltar para a tela anterior
        System.out.println("Voltando para tela anterior...");
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
}    
