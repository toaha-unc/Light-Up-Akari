package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    stage.setTitle("@K@R!");
    Model akariModel = createAkariModel();
    ClassicMvcController akariController = createAkariController(akariModel);
    View akariView = createAkariView(akariModel, akariController);
    Scene akariScene = createAkariScene(akariView);
    setSceneOnStage(stage, akariScene);
    addObserver(stage, akariModel, akariScene, akariView);
    stage.show();
  }

  public Model createAkariModel() {
    Model akariModel = new ModelImpl(createSampleLibrary());
    return akariModel;
  }

  public ClassicMvcController createAkariController(Model model) {
    ClassicMvcController akariController = new ControllerImpl(model);
    return akariController;
  }

  public View createAkariView(Model model, ClassicMvcController controller) {
    View akariView = new View(model, controller);
    return akariView;
  }

  public Scene createAkariScene(View view) {
    Scene akariScene = new Scene(view.render());
    return akariScene;
  }

  public void setSceneOnStage(Stage stage, Scene scene) {
    stage.setScene(scene);
  }

  public void addObserver(Stage stage, Model akariModel, Scene akariScene, View akariView) {
    akariModel.addObserver(
        (Model model) -> {
          akariScene.setRoot(akariView.render());
          stage.sizeToScene();
        });
  }

  public PuzzleLibrary createSampleLibrary() {
    int[][] board_01 = SamplePuzzles.PUZZLE_01;
    int[][] board_02 = SamplePuzzles.PUZZLE_02;
    int[][] board_03 = SamplePuzzles.PUZZLE_03;
    int[][] board_04 = SamplePuzzles.PUZZLE_04;
    int[][] board_05 = SamplePuzzles.PUZZLE_05;
    Puzzle puzzle_01 = new PuzzleImpl(board_01);
    Puzzle puzzle_02 = new PuzzleImpl(board_02);
    Puzzle puzzle_03 = new PuzzleImpl(board_03);
    Puzzle puzzle_04 = new PuzzleImpl(board_04);
    Puzzle puzzle_05 = new PuzzleImpl(board_05);
    PuzzleLibrary newLibrary = new PuzzleLibraryImpl();
    newLibrary.addPuzzle(puzzle_01);
    newLibrary.addPuzzle(puzzle_02);
    newLibrary.addPuzzle(puzzle_03);
    newLibrary.addPuzzle(puzzle_04);
    newLibrary.addPuzzle(puzzle_05);
    return newLibrary;
  }
}
