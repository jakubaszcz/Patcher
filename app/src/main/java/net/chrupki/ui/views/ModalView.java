package net.chrupki.ui.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class ModalView extends StackPane {

    private StackPane overlay = null;

    public ModalView() {
        Pane backdrop = new Pane();
        backdrop.setStyle("-fx-background-color: rgba(0,0,0,0.1);");

        VBox popup = new VBox(12);
        popup.setAlignment(Pos.CENTER);
        popup.setStyle("""
            -fx-background-color: #2b2b2b;
            -fx-padding: 20;
            -fx-background-radius: 10;
        """);

        Label title = new Label("Overlay example");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 16;");

        Button close = new Button("Close overlay");
        close.setOnAction(e -> overlay.setVisible(false));

        popup.getChildren().addAll(title, close);

        overlay = new StackPane(backdrop, popup);
        overlay.setVisible(true);
        overlay.managedProperty().bind(overlay.visibleProperty());

        StackPane.setAlignment(popup, Pos.CENTER);

        backdrop.setOnMouseClicked(e -> overlay.setVisible(false));

        this.getChildren().add(overlay);
    }
}
