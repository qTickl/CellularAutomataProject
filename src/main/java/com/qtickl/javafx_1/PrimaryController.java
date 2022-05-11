package com.qtickl.javafx_1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    private int _r = 1;

    public void run(ActionEvent e) {
        errorBox.setText("no errors");
        String IRTB = initRowTextBox.getText().strip();
        String RTB = ruleTextBox.getText().strip();

        /**
         * User error management
         */
        if (RTB.equals("")) {
            errorBox.setText("Please enter a valid rule");
            return;
        }
        if (IRTB.equals("")) {
            errorBox.setText("Please enter an initial row");
            return;
        }
        for (char c : IRTB.toCharArray()) {
            if (c != '0' && c != '1') {
                errorBox.setText("Initial row must contain only 1's and 0's");
                return;
            }
        }
        for (char c : IRTB.toCharArray()) {
            int k = Character.getNumericValue(c);
            if (!(k >= 0 && k < 10)) {
                errorBox.setText("Rule must be an 8 digit binary code (i.e. 10010100) or an integer (i.e. 30)");
                return;
            }
        }


        /**
         * Handles the initRowTextBox input
         */
        byte[] initRow = new byte[IRTB.length()];
        for (int i = 0; i < IRTB.length(); i++) {
            initRow[i] = (byte) Character.getNumericValue((IRTB.toCharArray()[i]));
        }

        /**
         * Handles the ruleTextBox input
         */
        boolean isBinRule = true;
        for (char c : RTB.toCharArray()) {
            byte val = (byte) Character.getNumericValue(c);
            if (val != 0 && val != 1) {
                isBinRule = false;
            }
        }
        Rule activeRule = new Rule(30);
        if (isBinRule) {
            byte[] b = new byte[RTB.length()];
            for (int i = 0; i < RTB.length(); i++) {
                b[i] = (byte) Character.getNumericValue((IRTB.toCharArray()[i]));
            }
            activeRule.setBinaryRule(b);
        } else {
            int n = Integer.parseInt(RTB);
            activeRule.setNumericalRule(n);
        }
        //*********************************************************//

        CellularAutomata activeCA = new CellularAutomata(activeRule, initRow);

        /**
         *  Initializes the gui model
         */
        DisplayCA display = new DisplayCA(activeCA);
        display.runAll(_r);

    }

    public void onSingleDot(ActionEvent e) {
        initRowTextBox.setText("0000000000000000000000001000000000000000000000000");
    }

    public void onAlternating(ActionEvent e) {
        initRowTextBox.setText("001100110011001100110011001100110011001100110011");
    }

    public void onBlocks(ActionEvent e) {
        initRowTextBox.setText("000000011111110000000111111100000001111111");
    }

    @FXML
    private Slider runIterations;
    @FXML
    private Label runIterationsLabel;
    @FXML
    private Label errorBox;
    @FXML
    private TextField ruleTextBox;

    @FXML
    private TextField initRowTextBox;

    public int getRowLength() {
        return _r;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runIterations.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                _r = (int) runIterations.getValue();
                runIterationsLabel.setText(Integer.toString(_r));
            }
        });
    }
}