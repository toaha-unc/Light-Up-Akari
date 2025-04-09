package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class ControlView implements FXComponent {
  private Model _model;
  private ClassicMvcController _controller;

  public ControlView(Model model, ClassicMvcController controller) {
    _model = model;
    _controller = controller;
  }
  /** Render the component and return the resulting Parent object */
  @Override
  public Parent render() {
    return createControls();
  }

  public HBox createControls() {
    HBox pane = paneMaker();
    addToPane(pane, prevButton(), nextButton(), randomButton(), resetButton());
    return pane;
  }

  public HBox paneMaker() {
    HBox pane1 = new HBox();
    pane1.getStyleClass().add("controlPane");
    return pane1;
  }

  public Button prevButton() {
    Button prev = new Button("Previous");
    prev.getStyleClass().add("prev");
    prev.setOnAction((ActionEvent event) -> _controller.clickPrevPuzzle());
    return prev;
  }

  public Button nextButton() {
    Button next = new Button("Next");
    next.getStyleClass().add("next");
    next.setOnAction((ActionEvent event) -> _controller.clickNextPuzzle());
    return next;
  }

  public Button randomButton() {
    Button random = new Button("Random");
    random.getStyleClass().add("random");
    random.setOnAction((ActionEvent event) -> _controller.clickRandPuzzle());
    return random;
  }

  public Button resetButton() {
    Button reset = new Button("Reset");
    reset.getStyleClass().add("reset");
    reset.setOnAction((ActionEvent event) -> _controller.clickResetPuzzle());
    return reset;
  }

  public void addToPane(HBox pane, Button prev, Button next, Button random, Button reset) {
    pane.getChildren().add(prev);
    pane.getChildren().add(next);
    pane.getChildren().add(random);
    pane.getChildren().add(reset);
  }
}
