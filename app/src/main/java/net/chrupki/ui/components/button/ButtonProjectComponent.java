package net.chrupki.ui.components.button;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.VersionDAO;

public class ButtonProjectComponent extends VBox {

    private static final String BUTTON_TEXT = "Create version";
    private static final String BLANK = "No project selected";

    public ButtonProjectComponent() {
        Button button = new Button();

        button.textProperty().bind(
                Bindings.when(
                                AppData.getPropertyCurrentProjectName().isNull())
                        .then(BLANK)
                        .otherwise(BUTTON_TEXT));

        button.setOnAction(event -> {
            VersionDAO.insert(AppData.getPropertyCurrentProjectName().get());
        });

        this.getChildren().add(button);
    }
}
