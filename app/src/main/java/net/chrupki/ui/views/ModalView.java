package net.chrupki.ui.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppContext;
import net.chrupki.app.context.ProjectContext;
import net.chrupki.ui.model.ProjectModel;
import javafx.beans.binding.Bindings;


public class ModalView extends StackPane {

    private ProjectModel model;

    public ModalView(ProjectModel model) {
        this.model = model;

        Pane backdrop = new Pane();

        VBox popup = new VBox();
        popup.setAlignment(Pos.CENTER);
        popup.setStyle("""
            -fx-background-color: rgba(0,0,0,0.32);
            -fx-padding: 20;
        """);

        Label label = new Label();

        label.textProperty().bind(
                Bindings.when(model.getEditActiveProperty().not()).then("No project").otherwise(AppContext.projectContext().getName())
        );



        Button button = new Button("Close");

        button.setOnAction(e -> {
            model.setEditActiveProperty(false);
        });

        getChildren().addAll(backdrop, popup);

        popup.getChildren().addAll(button, label);

        visibleProperty().bind(model.getEditActiveProperty());

        managedProperty().bind(visibleProperty());

        StackPane.setAlignment(popup, Pos.CENTER);
    }
}
