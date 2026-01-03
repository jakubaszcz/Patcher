package net.chrupki.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;


public class CreatePatchForm extends VBox {

    public CreatePatchForm() {
        HBox box = new HBox();

        TextField textField = new TextField();

        Button button = new Button("Create patch");

        ComboBox<String> comboBox = new ComboBox<>();

        box.getChildren().addAll(textField, comboBox, button);

        this.getChildren().add(box);
    }
}
