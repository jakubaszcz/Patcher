package net.chrupki.ui.views;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.project.AppProject;

import java.io.IOException;

public class MainView {

    private static final String APP_NAME = "patcher";

    private static VBox projectBox;

    public static void setup(Stage stage) throws Exception {
        stage.setTitle(APP_NAME);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
    }

    public static void display(Stage stage) throws Exception {

        // Set up the application stage
        setup(stage);

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
                AppData.setCurrentProject(newV.get());
            }
        });

        Label projectLabel = new Label();
        Button projectButtonVersion = new Button("Create version");


        ListView<String> versionListView = new ListView<>();

        ObservableList<String> versions =
                FXCollections.observableArrayList(
                        VersionDAO.findAll(AppData.getPropertyCurrentProjectName().get())
                );

        versionListView.setItems(versions);

        projectLabel.textProperty().bind(
                Bindings.when(
                                AppData.getPropertyCurrentProjectName().isNull()
                        )
                        .then("Aucun projet sélectionné")
                        .otherwise("Version")
        );

        projectButtonVersion.textProperty().bind(
                Bindings.when(
                                AppData.getPropertyCurrentProjectName().isNull())
                        .then("Pas de projet sélectionné")
                        .otherwise("Create version"));

        projectButtonVersion.setOnAction(event -> {
            VersionDAO.insert(AppData.getPropertyCurrentProjectName().get());
        });
        projectBox = new VBox(10, projectLabel, projectButtonVersion);

        projectBox.visibleProperty().bind(
                AppData.getPropertyCurrentProjectPath().isNotNull()
        );
        projectBox.managedProperty().bind(
                projectBox.visibleProperty()
        );


        VBox root = new VBox(10,
                txtf,
                btn,
                comboBox,
                projectBox,
                versionListView
        );

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.show();
    }
}
