package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private Model _model;
  private ClassicMvcController _controller;

  public View(Model model, ClassicMvcController controller) {
    _model = model;
    _controller = controller;
  }

  /** Render the component and return the resulting Parent object */
  @Override
  public Parent render() {
    return createView();
  }

  public VBox createView() {
    VBox pane = new VBox();
    pane.getStylesheets().add("main.css");
    addComponents(pane);
    pane.setAlignment(Pos.CENTER);
    return pane;
  }

  public void renderComponents(
      VBox pane, FXComponent message, FXComponent puzzle, FXComponent controls) {
    pane.getChildren().add(controls.render());
    pane.getChildren().add(puzzle.render());
    pane.getChildren().add(message.render());
  }

  public void addComponents(VBox pane) {
    FXComponent message = new MessageView(_model, _controller);
    FXComponent puzzle = new PuzzleView(_model, _controller);
    FXComponent controls = new ControlView(_model, _controller);
    renderComponents(pane, message, puzzle, controls);
  }
}
