package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;

public class ControllerImpl implements ClassicMvcController {
  private Model _model;
  private CellType corridor = CellType.CORRIDOR;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    _model = model;
  }
  /** Handles the click action to go to the next puzzle */
  @Override
  public void clickNextPuzzle() {
    if (_model.getActivePuzzleIndex() != _model.getPuzzleLibrarySize() - 1) {
      _model.setActivePuzzleIndex(_model.getActivePuzzleIndex() + 1);
    } else {
      _model.setActivePuzzleIndex(0);
    }
  }
  /** Handles the click action to go to the previous puzzle */
  @Override
  public void clickPrevPuzzle() {
    if (_model.getActivePuzzleIndex() != 0) {
      _model.setActivePuzzleIndex(_model.getActivePuzzleIndex() - 1);
    } else {
      _model.setActivePuzzleIndex(_model.getPuzzleLibrarySize() - 1);
    }
  }
  /** Handles the click action to go to a random puzzle */
  @Override
  public void clickRandPuzzle() {
    int randomNumberAsIndex = (int) ((Math.random() * (_model.getPuzzleLibrarySize())));
    if (randomNumberAsIndex == _model.getActivePuzzleIndex()) {
      clickRandPuzzle();
    } else {
      _model.setActivePuzzleIndex(randomNumberAsIndex);
    }
  }
  /** Handles the click action to reset the currently active puzzle */
  @Override
  public void clickResetPuzzle() {
    _model.resetPuzzle();
  }
  /**
   * Handles the click event on the cell at row r, column c
   *
   * @param r
   * @param c
   */
  @Override
  public void clickCell(int r, int c) {
    if (_model.getActivePuzzle().getCellType(r, c) == corridor) {
      if (_model.isLamp(r, c)) {
        _model.removeLamp(r, c);
      } else {
        _model.addLamp(r, c);
      }
    }
  }
}
