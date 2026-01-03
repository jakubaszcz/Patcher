package net.chrupki.ui.components;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;

public class CurrentProjectLabel extends VBox {

    private static final String BLANK = "No current project";
    private final Label projectLabel = new Label();

    public CurrentProjectLabel() {

        projectLabel.textProperty().bind(
                Bindings.when(
                                AppData.getPropertyCurrentProjectName().isNull()
                        )
                        .then(BLANK)
                        .otherwise(AppData.getPropertyCurrentProjectName())
        );

        this.getChildren().add(projectLabel);
    }
}