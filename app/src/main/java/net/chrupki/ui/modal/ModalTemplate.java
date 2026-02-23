package net.chrupki.ui.modal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.chrupki.ui.model.GlobalModel;
import net.chrupki.ui.styles.Styles;
import net.chrupki.ui.styles.theme.ButtonTheme;
import net.chrupki.ui.styles.theme.CardTheme;
import net.chrupki.ui.styles.theme.TextTheme;

import javax.swing.text.Style;

public abstract class ModalTemplate extends VBox {

    protected final Label titleLabel;
    protected final Button closeButton;
    protected final HBox actions;
    protected final Label errorLabel;

    public ModalTemplate(String title, Runnable onClose) {
        this.titleLabel = new Label(title);
        new Styles().apply(titleLabel, TextTheme.SUBTITLE);

        this.closeButton = new Button("×");
        new Styles().apply(closeButton, ButtonTheme.CANCEL);
        this.closeButton.setStyle("-fx-font-size: 20px; -fx-padding: 0 4;");

        this.closeButton.setOnAction(e -> onClose.run());

        this.errorLabel = new Label();
        this.errorLabel.visibleProperty().bind(GlobalModel.getErrorMessage().isNotNull());
        this.errorLabel.managedProperty().bind(GlobalModel.getErrorMessage().isNotNull());

        this.errorLabel.textProperty().bind(GlobalModel.getErrorMessage());
        this.errorLabel.setWrapText(true);
        this.errorLabel.setMaxWidth(Double.MAX_VALUE);
        this.errorLabel.setWrapText(true);

        new Styles().apply(this.errorLabel, TextTheme.TEXT_ERROR_BADGE);

        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, Priority.ALWAYS);

        HBox header = new HBox(titleLabel, titleSpacer, closeButton);
        header.setAlignment(Pos.CENTER_LEFT);

        Region actionSpacer = new Region();
        HBox.setHgrow(actionSpacer, Priority.ALWAYS);

        this.actions = new HBox(12, actionSpacer);
        this.actions.setAlignment(Pos.CENTER);

        setSpacing(16);
        setPadding(new Insets(18));
        setAlignment(Pos.CENTER_LEFT);

        setPrefWidth(360);
        setMaxWidth(360);

        new Styles().apply(this, CardTheme.NORMAL);

        getChildren().addAll(header, errorLabel);
    }

    protected void addActions(Button... additionalButtons) {
        actions.getChildren().addAll(additionalButtons);
        if (!getChildren().contains(actions)) {
            getChildren().add(actions);
        }
    }
}
