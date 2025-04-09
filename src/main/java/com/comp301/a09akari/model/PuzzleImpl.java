package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  int[][] _board;

  public PuzzleImpl(int[][] board) {
    if (board == null) {
      throw new IllegalArgumentException();
    }
    _board = board;
  }
  /** Getter method for the width of the puzzle (i.e. the number of columns it has) */
  @Override
  public int getWidth() {
    return _board[0].length;
  }
  /** Getter method for the height of the puzzle (i.e. the number of rows it has) */
  @Override
  public int getHeight() {
    return _board.length;
  }
  /**
   * Getter method for the type of the cell located at row r, column c. Throws an IndexOutOfBounds
   * exception if r or c is out of bounds
   *
   * @param r
   * @param c
   */
  @Override
  public CellType getCellType(int r, int c) {
    if (r >= getHeight() || r < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (c >= getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (_board[r][c] < 5) {
      return CellType.CLUE;
    } else if (_board[r][c] == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
  }
  /**
   * Getter method for the clue number of the cell located at row r, column c. Throws an
   * IndexOutOfBounds exception if r or c is out of bounds, or an IllegalArgumentException if the
   * cell is not type CLUE
   *
   * @param r
   * @param c
   */
  @Override
  public int getClue(int r, int c) {
    if (r >= getHeight() || r < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (c >= getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) == CellType.WALL || getCellType(r, c) == CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else {
      return _board[r][c];
    }
  }
}
