package net.chrupki.ui.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import net.chrupki.model.Patch;
import net.chrupki.ui.model.ProjectModel;


public class PatchListView extends VBox {

    private VBox view = new VBox();

    public PatchListView(ProjectModel model) {

        ObservableList<Patch> patches = model.getPatches();

        patches.addListener((ListChangeListener<Patch>) change -> {
            while (change.next()) {

                System.out.println(change.wasAdded());

                if (change.wasRemoved()) {
                    view.getChildren().clear();
                }

                if (change.wasAdded()) {
                    for (Patch v : change.getAddedSubList()) {
                        view.getChildren().add(
                                new PatchContainer(v.getContent(), v.getType())
                        );
                    }
                }
            }
        });

        this.getChildren().addAll(view);
    }
}
