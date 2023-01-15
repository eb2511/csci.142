module BFSPuzzleSolver {
    requires transitive javafx.controls;
    requires java.desktop;
    requires jdk.internal.le;
    requires jdk.jfr;
    exports puzzles.common;
    exports puzzles.common.solver;
    exports puzzles.slide.gui;
    exports puzzles.slide.model;
}