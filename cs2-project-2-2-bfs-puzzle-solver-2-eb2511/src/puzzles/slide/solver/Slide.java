package puzzles.slide.solver;

import puzzles.common.solver.Solver;
import puzzles.slide.model.SlideConfig;

import java.io.FileNotFoundException;

public class Slide {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java Slide filename");
        }else{
            String fileName = args[0];
            System.out.println("File: "+fileName);
            SlideConfig slideConfig = new SlideConfig(fileName);
            Solver solver = new Solver(slideConfig);
            solver.solve(slideConfig);
        }
    }
}
