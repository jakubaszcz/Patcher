package net.chrupki.ui.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import net.chrupki.model.Patch;
import net.chrupki.ui.model.ProjectModel;


public class PatchListView extends VBox {

    private VBox view = new VBox();

    public PatchListView(ProjectModel model) {

        ObservableList<Patch> patches = model.getPatches();

        patches.addListener((ListChangeListener<Patch>) change -> {
            while (change.next()) {

                if (change.wasRemoved()) {
                    view.getChildren().clear();
                }

                if (change.wasAdded()) {
                    for (Patch v : change.getAddedSubList()) {
                        view.getChildren().add(
                                new PatchContainer(model, v.getContent(), v.getType(), v.getId(), v.getVid())
                        );
                    }
                }
            }
        });

        this.getChildren().addAll(view);
    }
}
