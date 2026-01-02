package net.chrupki.ui.components.label;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;

public class LabelProjectComponent extends VBox {

    private static final String BLANK = "No current project";

    public LabelProjectComponent() {
        Label projectLabel = new Label();

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
