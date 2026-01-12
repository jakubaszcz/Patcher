package net.chrupki;

import javafx.application.Application;
import javafx.stage.Stage;
import net.chrupki.project.AppProject;
import net.chrupki.app.AppPath;
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
