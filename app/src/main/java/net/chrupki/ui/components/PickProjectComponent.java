package net.chrupki.ui.components;

import javafx.beans.property.StringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.project.AppProject;

import java.io.IOException;

public class PickProjectComponent extends VBox {
    public PickProjectComponent() throws IOException {
        ComboBox<StringProperty> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                AppProject.FetchAllProjectPropertiesName()
        );

        comboBox.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                AppData.setCurrentProject(newV.get());
            }
        });

        this.getChildren().add(comboBox);
    }
}
