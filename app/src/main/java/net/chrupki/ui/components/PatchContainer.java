package net.chrupki.ui.components;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;



public class PatchContainer extends VBox {

    private HBox box;

    private Label contentLabel;
    private Label typeLabel;

    public PatchContainer(String content, String type) {

        box = new HBox(10);
        contentLabel = new Label(content);
        typeLabel = new Label(type);

        box.getChildren().addAll(typeLabel, contentLabel);


        this.getChildren().add(box);
    }
}
