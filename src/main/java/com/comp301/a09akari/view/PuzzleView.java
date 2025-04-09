package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PuzzleView implements FXComponent {
  private Model _model;
  private ClassicMvcController _controller;
  private int Height;
  private int Width;
  private Button cell;
  private CellType corridor = CellType.CORRIDOR;
  private CellType wall = CellType.WALL;
  private CellType clue = CellType.CLUE;

  public PuzzleView(Model model, ClassicMvcController controller) {
    _model = model;
    _controller = controller;
    Height = _model.getActivePuzzle().getHeight();
    Width = _model.getActivePuzzle().getWidth();
    cell = null;
  }
  /** Render the component and return the resulting Parent object */
  @Override
  public Parent render() {
    return createBoard();
  }

  public GridPane createBoard() {
    GridPane board = new GridPane();
    fixCell(board);
    board.setAlignment(Pos.CENTER);
    return board;
  }

  public void createBulbImage(Button inputCell) {
    Image bulbImage = new Image("light-bulb.png");
    ImageView bulbImageView = new ImageView();
    bulbImageView.setImage(bulbImage);
    bulbImageView.setFitWidth(40);
    bulbImageView.setPreserveRatio(true);
    inputCell.setGraphic(bulbImageView);
  }

  public Button createCell(String string) {
    Button cell1 = new Button(string);
    cell1.setMinSize(50, 50);
    cell1.setMaxSize(50, 50);
    return cell1;
  }

  public void click(Button inputCell, int inputRow, int inputColumn) {
    final int R = inputRow;
    final int C = inputColumn;
    inputCell.setOnAction((ActionEvent event) -> _controller.clickCell(R, C));
  }

  public void fixCell(GridPane board) {
    for (int row = 0; row < Height; ++row) {
      for (int column = 0; column < Width; ++column) {
        if (_model.getActivePuzzle().getCellType(row, column) == corridor) {
          corridorFixer(board, row, column);
        } else if (_model.getActivePuzzle().getCellType(row, column) == wall) {
          wallFixer(board, row, column);
        } else if (_model.getActivePuzzle().getCellType(row, column) == clue) {
          clueFixer(board, row, column);
        }
      }
    }
  }

  public void styleFixer(int style) {
    if (style == 1) {
      cell.getStyleClass().add("unlitCorridor");
    } else if (style == 2) {
      cell.getStyleClass().add("litCorridor");
    } else if (style == 3) {
      cell.getStyleClass().add("legalLamp");
    } else if (style == 4) {
      cell.getStyleClass().add("illegalLamp");
    } else if (style == 5) {
      cell.getStyleClass().add("wall");
    } else if (style == 6) {
      cell.getStyleClass().add("clue");
    } else {
      cell.getStyleClass().add("satisfiedClue");
    }
  }

  public void litFixer(int row, int column) {
    if (!_model.isLit(row, column)) {
      styleFixer(1);
    } else {
      styleFixer(2);
    }
  }

  public void lampFixer(int row, int column) {
    if (_model.isLamp(row, column)) {
      if (!_model.isLampIllegal(row, column)) {
        createBulbImage(cell);
        styleFixer(3);
      } else {
        createBulbImage(cell);
        styleFixer(4);
      }
    }
  }

  public void corridorFixer(GridPane board, int row, int column) {
    cell = createCell("");
    board.add(cell, row, column);
    click(cell, row, column);
    litFixer(row, column);
    lampFixer(row, column);
  }

  public void wallFixer(GridPane board, int row, int column) {
    cell = createCell("");
    styleFixer(5);
    board.add(cell, row, column);
  }

  public void clueFixer(GridPane board, int row, int column) {
    cell = createCell("" + _model.getActivePuzzle().getClue(row, column));
    styleFixer(6);
    if (_model.isClueSatisfied(row, column)) {
      styleFixer(7);
    }
    board.add(cell, row, column);
  }
}
