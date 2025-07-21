package com.example.gui;

import java.util.Arrays;

import com.example.controllers.IUsuarioController;
import com.example.controllers.UsuarioController;
import com.example.exceptions.DadosInvalidosException;
import com.example.models.Hospede;
import com.example.models.Usuario;

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

public class TelaListaHospedesController {

    @FXML private TableView<Hospede> tabelaHospedes;
    @FXML private Button btnAcessar;
    @FXML private Button btnExcluir;
    @FXML private Button btnVoltar;
    @FXML private Button btnSair;

    private final IUsuarioController usuarioController = new UsuarioController();
    private ObservableList<Hospede> listaHospedes;

    @FXML
    public void initialize() {
        configurarTabela();
        carregarHospedes();
    }

    private void configurarTabela() {
        TableColumn<Hospede, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Hospede, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<Hospede, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tabelaHospedes.getColumns().addAll(colNome, colCpf, colEmail);
    }

    private void carregarHospedes() {
        try {
            Usuario[] todos = usuarioController.listarTodosUsuarios();
            listaHospedes = FXCollections.observableArrayList();

            Arrays.stream(todos)
                  .filter(u -> u instanceof Hospede)
                  .forEach(u -> listaHospedes.add((Hospede) u));

            tabelaHospedes.setItems(listaHospedes);
        } catch (DadosInvalidosException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao carregar dados", e.getMessage());
        }
    }

    @FXML
    private void acessarHospede() {
        Hospede selecionado = tabelaHospedes.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum hóspede selecionado", "Selecione um hóspede para acessar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/telas/TelaEditarHospede.fxml"));
            AnchorPane pane = loader.load();

            TelaHospedeParaEdicaoController controller = loader.getController();
            controller.setHospede(selecionado);

            Stage stage = new Stage();
            stage.setTitle("Editar Hóspede");
            stage.setScene(new Scene(pane));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir a tela de edição.");
        }
    }

    @FXML
    private void excluirHospede() {
        Hospede selecionado = tabelaHospedes.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum hóspede selecionado", "Selecione um hóspede para excluir.");
            return;
        }

        try {
            usuarioController.deletar(Integer.parseInt(selecionado.getId()));
            listaHospedes.remove(selecionado);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Hóspede excluído", "O hóspede foi removido com sucesso.");
        } catch (DadosInvalidosException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao excluir", e.getMessage());
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

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
