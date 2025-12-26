package net.chrupki;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.chrupki.project.AppProject;
import net.chrupki.utils.AppData;
import net.chrupki.utils.AppPaths;
import javafx.beans.binding.Bindings;


import java.io.IOException;

public class Patcher extends Application {

    private VBox projectBox;

    @Override
    public void start(Stage stage) throws IOException {

        // --- Init ---
        AppProject.FetchProject();
        AppPaths.GetDataDir();

        stage.setTitle("Text Input App");
        stage.setMinWidth(600);
        stage.setMinHeight(400);

        TextField txtf = new TextField();
        txtf.setPromptText("Project");

        Button btn = new Button("Create Project");
        btn.setOnAction(e -> {
            if (!txtf.getText().isBlank()) {
                AppProject.CreateProject(txtf.getText());
            }
        });

        ComboBox<StringProperty> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                AppProject.FetchAllProjectPropertiesName()
        );

        comboBox.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                AppData.SetCurrentProject(newV.get());
            }
        });

        Label projectLabel = new Label();
        Button projectButtonVersion = new Button("Create version");


        projectLabel.textProperty().bind(
                Bindings.when(
                                AppData.GetPropertyCurrentProjectName().isNull()
                        )
                        .then("Aucun projet sélectionné")
                        .otherwise(AppData.GetPropertyCurrentProjectName())
        );

        projectButtonVersion.textProperty().bind(
                Bindings.when(
                        AppData.GetPropertyCurrentProjectName().isNull())
                        .then("Pas de projet sélectionné")
                        .otherwise("Create version"));


        projectBox = new VBox(10, projectLabel, projectButtonVersion);

        projectBox.visibleProperty().bind(
                AppData.GetPropertyCurrentProjectPath().isNotNull()
        );
        projectBox.managedProperty().bind(
                projectBox.visibleProperty()
        );

        VBox root = new VBox(10,
                txtf,
                btn,
                comboBox,
                projectBox
        );

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
