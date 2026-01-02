package net.chrupki;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.chrupki.database.Database;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.project.AppProject;
import net.chrupki.app.AppData;
import net.chrupki.app.AppPath;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ListView;
import net.chrupki.ui.views.MainView;

public class Patcher extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        AppProject.FetchProject();
        AppPath.getDataDir();

        MainView.display(stage);

    }

    public static void main(String[] args) {
        launch();
    }
}
