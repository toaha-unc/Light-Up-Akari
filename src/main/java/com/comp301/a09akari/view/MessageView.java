package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MessageView implements FXComponent {
  private Model _model;
  private ClassicMvcController _controller;
  private int puzzleSize;
  private int puzzleNumber;
  private String initialMessage;
  private String completedMessage;

  public MessageView(Model model, ClassicMvcController controller) {
    _model = model;
    _controller = controller;
    puzzleSize = _model.getPuzzleLibrarySize();
    puzzleNumber = _model.getActivePuzzleIndex() + 1;
    initialMessage = "Puzzle " + puzzleNumber + " of " + puzzleSize;
    completedMessage = "Completed Puzzle " + puzzleNumber + " of " + puzzleSize;
  }

  /** Render the component and return the resulting Parent object */
  @Override
  public Parent render() {
    return createMessage();
  }

  public Pane createMessage() {
    Pane pane = new StackPane();
    addToMainPane(pane, createbackgroundPane(), createLabel());
    return pane;
  }

  public Pane createbackgroundPane() {
    Pane pane = new StackPane();
    pane.getStyleClass().add("stackPaneMessage");
    return pane;
  }

  public Label createLabel() {
    Label label;
    if (!_model.isSolved()) {
      label = new Label(initialMessage);
    } else {
      label = new Label(completedMessage);
    }
    label.getStyleClass().add("labelMessage");
    return label;
  }

  public void addToMainPane(Pane pane1, Pane pane2, Label label) {
    pane1.getChildren().add(pane2);
    pane1.getChildren().add(label);
  }
}
