package net.chrupki.ui.components.modal;

import javafx.scene.layout.VBox;

public class ModalVersionForm extends VBox {

    public ModalVersionForm(ProjectModel model, BiConsumer<Integer, String> onSave, Runnable onClose) {

        setAlignment(Pos.CENTER);

        visibleProperty().bind(model.getEditVersionProperty());
        managedProperty().bind(model.getEditVersionProperty());


        Label label = new Label();

        TextField textField = new TextField();

        textField.setPromptText("Enter new version name");

        Button save = new Button("Save");

        Button close = new Button("Close");

        save.setOnAction(e -> {
            onSave.accept(AppContext.versionContext().getId().get(), textField.getText());
        });

        close.setOnAction(e -> {
            onClose.run();
        });

        label.textProperty().bind(AppContext.projectContext().getName());

        getChildren().addAll(label, textField, save, close);

    }
}
