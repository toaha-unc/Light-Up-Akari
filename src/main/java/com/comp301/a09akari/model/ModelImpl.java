package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary _library;
  private int activePuzzleindex = 0;
  private int[][] lamps;
  private List<ModelObserver> observers = new ArrayList<ModelObserver>();
  private int Height;
  private int Width;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    _library = library;
    setActivePuzzleIndex(activePuzzleindex);
    Height = getActivePuzzle().getHeight();
    Width = getActivePuzzle().getWidth();
    lamps = new int[Height][Width];
  }
  /**
   * Adds a lamp if one doesn't already exist to the active puzzle in the cell at row r, column c.
   * Throws an IndexOutOfBoundsException if r or c is out of bounds, or an IllegalArgumentException
   * if the cell is not type CORRIDOR
   *
   * @param r
   * @param c
   */
  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || r >= Height || c < 0 || c >= Width) {
      throw new IndexOutOfBoundsException();
    } else if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps[r][c] = 1;
    notifyObservers();
  }
  /**
   * Removes a lamp if one exists from the active puzzle at the cell at row r, column c. Throws an
   * IndexOutOfBoundsException if r or c is out of bounds, or an IllegalArgumentException if the
   * cell is not type CORRIDOR
   *
   * @param r
   * @param c
   */
  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || r >= Height || c < 0 || c >= Width) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps[r][c] = 0;
    notifyObservers();
  }
  /**
   * Returns true only if, in the active puzzle, the cell location row r, column c is currently
   * illuminated by a nearby lamp in the same column or row (and which is not blocked by a wall or
   * clue). If the cell itself contains a lamp, this method should also return true. Throws an
   * IndexOutOfBoundsException if r or c is out of bounds, or an IllegalArgumentException if the
   * cell is not type CORRIDOR
   *
   * @param r
   * @param c
   */
  @Override
  public boolean isLit(int r, int c) {
    if (r < 0 || r >= Height || c < 0 || c >= Width) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (isLamp(r, c)) {
      return true;
    }
    for (int i = c + 1; i < Width; ++i) {
      if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    for (int i = c - 1; i >= 0; --i) {
      if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    for (int i = r + 1; i < Height; ++i) {
      if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    for (int i = r - 1; i >= 0; --i) {
      if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    return false;
  }
  /**
   * Returns true only if, in the active puzzle, the cell at row r, column c contains a user-placed
   * lamp. Throws an IndexOutOfBoundsException if r or c is out of bounds, or an
   * IllegalArgumentException if the cell is not type CORRIDOR
   *
   * @param r
   * @param c
   */
  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || r >= Height || c < 0 || c >= Width) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamps[r][c] == 1;
  }
  /**
   * Returns true only if, in the active puzzle, the cell at row r, column c contains a user-placed
   * lamp that is in direct view of another lamp (i.e. the lamp is illegally placed). Throws an
   * IndexOutOfBoundsException if r or c is out of bounds, or an IllegalArgumentException if the
   * cell does not currently contain a lamp
   *
   * @param r
   * @param c
   */
  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || r >= Height || c < 0 || c >= Width) {
      throw new IndexOutOfBoundsException();
    }
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }
    for (int i = c + 1; i < Width; ++i) {
      if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    for (int i = c - 1; i >= 0; --i) {
      if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    for (int i = r + 1; i < Height; ++i) {
      if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    for (int i = r - 1; i >= 0; --i) {
      if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }
    return false;
  }
  /** Getter method for the current active Puzzle instance */
  @Override
  public Puzzle getActivePuzzle() {
    return _library.getPuzzle(activePuzzleindex);
  }
  /** Getter method for the active puzzle index */
  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleindex;
  }
  /**
   * Setter method for the current active Puzzle index. If the passed index is out of bounds, this
   * method should throw an IndexOutOfBoundsException
   *
   * @param index
   */
  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException();
    }
    activePuzzleindex = index;
    Height = getActivePuzzle().getHeight();
    Width = getActivePuzzle().getWidth();
    lamps = new int[Height][Width];
    notifyObservers();
  }
  /** Getter method for the number of puzzles contained in the internal PuzzleLibrary */
  @Override
  public int getPuzzleLibrarySize() {
    return _library.size();
  }
  /** Resets the active puzzle by removing all lamps which have been placed */
  @Override
  public void resetPuzzle() {
    for (int r = 0; r < Height; ++r) {
      for (int c = 0; c < Width; ++c) {
        if (lamps[r][c] == 1) {
          lamps[r][c] = 0;
        }
      }
    }
    notifyObservers();
  }
  /**
   * Returns true if the active puzzle is solved (i.e. every clue is satisfied and every corridor is
   * illuminated)
   */
  @Override
  public boolean isSolved() {
    for (int r = 0; r < Height; ++r) {
      for (int c = 0; c < Width; ++c) {
        if (getActivePuzzle().getCellType(r, c) == CellType.CLUE && !isClueSatisfied(r, c)) {
          return false;
        }
        if (getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR && !isLit(r, c)) {
          return false;
        }
        if (getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR
            && isLamp(r, c)
            && isLampIllegal(r, c)) {
          return false;
        }
      }
    }
    return true;
  }
  /**
   * Returns true if the clue located at row r, column c of the active puzzle is satisfied (i.e. has
   * exactly the number of lamps adjacent as is specified by the clue). Throws an
   * IndexOutOfBoundsException if r or c is out of bounds, or an IllegalArgumentException if the
   * cell is not type CLUE
   *
   * @param r
   * @param c
   */
  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || r >= Height || c < 0 || c >= Width) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int have = 0;
    int clueNumber = getActivePuzzle().getClue(r, c);
    if (c + 1 < Width
        && getActivePuzzle().getCellType(r, c + 1) == CellType.CORRIDOR
        && isLamp(r, c + 1)) {
      ++have;
    }
    if (c - 1 >= 0
        && getActivePuzzle().getCellType(r, c - 1) == CellType.CORRIDOR
        && isLamp(r, c - 1)) {
      ++have;
    }
    if (r + 1 < Height
        && getActivePuzzle().getCellType(r + 1, c) == CellType.CORRIDOR
        && isLamp(r + 1, c)) {
      ++have;
    }
    if (r - 1 >= 0
        && getActivePuzzle().getCellType(r - 1, c) == CellType.CORRIDOR
        && isLamp(r - 1, c)) {
      ++have;
    }
    if (clueNumber == 0 && have == 0) {
      return true;
    } else if (clueNumber == 1 && have == 1) {
      return true;
    } else if (clueNumber == 2 && have == 2) {
      return true;
    } else if (clueNumber == 3 && have == 3) {
      return true;
    } else if (clueNumber == 4 && have == 4) {
      return true;
    } else {
      return false;
    }
  }
  /**
   * Adds an observer to the model
   *
   * @param observer
   */
  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }
  /**
   * Removes an observer from the model
   *
   * @param observer
   */
  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }
}
