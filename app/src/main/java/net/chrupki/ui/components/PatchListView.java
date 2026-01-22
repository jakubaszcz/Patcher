package net.chrupki.ui.components;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import net.chrupki.ui.views.pages.project.dto.PatchDTO;
import net.chrupki.ui.model.ProjectModel;


public class PatchListView extends VBox {

    private VBox view = new VBox();

    public PatchListView(ProjectModel model) {

        ObservableList<PatchDTO> patches = ProjectModel.getPatches();

        patches.addListener((ListChangeListener<PatchDTO>) change -> {
            while (change.next()) {

                if (change.wasRemoved()) {
                    view.getChildren().clear();
                }

                if (change.wasAdded()) {
                    for (PatchDTO v : change.getAddedSubList()) {
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
