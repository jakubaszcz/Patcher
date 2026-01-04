package net.chrupki.ui.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.PatchDAO;
import net.chrupki.model.Patch;
import net.chrupki.model.Version;
import net.chrupki.ui.model.ProjectModel;


public class PatchContainer extends VBox {
    public PatchContainer(ProjectModel model) throws Exception {
        HBox view = new HBox();


        ObservableList<Patch> patches = model.getPatches();

        patches.addListener((ListChangeListener<Patch>) change -> {
            while (change.next()) {

                System.out.println(change.wasAdded());

                if (change.wasRemoved()) {
                    view.getChildren().clear();
                }

                if (change.wasAdded()) {
                    for (Patch v : change.getAddedSubList()) {
                        view.getChildren().addAll(
                                new Label(v.getContent()),
                                new Button(v.getType())
                        );
                    }
                }
            }
        });

        patches.setAll(
                PatchDAO.findAll(AppData.getCurrentProjectName())
        );

        this.getChildren().addAll(view);
    }
}
