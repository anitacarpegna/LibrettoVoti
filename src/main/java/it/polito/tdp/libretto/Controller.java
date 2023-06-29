package it.polito.tdp.libretto;

import it.polito.tdp.libretto.model.Libretto;
import it.polito.tdp.libretto.model.Voto;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


public class Controller {
	
	private Libretto model;

	public void setModel(Libretto model) {
		this.model = model;
		txtResult.setText(this.model.toString());
	}
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttomInserisci;

    @FXML
    private ComboBox<Integer> cmbPunti;

    @FXML
    private DatePicker setData;

    @FXML
    private TextField txtNomeCorso;
    
    @FXML
    private TextArea txtResult;

    @FXML
    void handleInserisci(ActionEvent event) {
    	String corso = txtNomeCorso.getText();
    	Integer punti = cmbPunti.getValue();
    	LocalDate data = setData.getValue();
    	
    	Voto v = new Voto(corso, punti, data);
    	this.model.add(v);
    	
    	txtResult.setText(this.model.toString());
    }

    @FXML
    void initialize() {
        assert buttomInserisci != null : "fx:id=\"buttomInserisci\" was not injected: check your FXML file 'main.fxml'.";
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'main.fxml'.";
        assert setData != null : "fx:id=\"setData\" was not injected: check your FXML file 'main.fxml'.";
        assert txtNomeCorso != null : "fx:id=\"txtNomeCorso\" was not injected: check your FXML file 'main.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'main.fxml'.";
        
        for (int p=18; p<=30; p++)
        	cmbPunti.getItems().add(p);
    }

}

	
	


