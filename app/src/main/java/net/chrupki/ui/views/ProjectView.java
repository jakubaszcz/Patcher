package net.chrupki.ui.views;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.chrupki.app.AppData;
import net.chrupki.database.dao.VersionDAO;
import net.chrupki.ui.components.VersionListComponent;
import net.chrupki.ui.components.button.ButtonProjectComponent;
import net.chrupki.ui.components.label.LabelProjectComponent;
import net.chrupki.ui.model.ProjectModel;

public class ProjectView extends VBox {

    private static VBox view;

    public ProjectView() throws Exception {
        ProjectModel model = new ProjectModel();

        view = new VBox(10, new LabelProjectComponent(), new ButtonProjectComponent(model), new VersionListComponent(model));

        this.getChildren().add(view);
    }
}
