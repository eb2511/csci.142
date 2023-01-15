package puzzles.clock;

import puzzles.common.solver.Solver;

public class Clock {


    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Clock hours start stop");
        }else{
            System.out.println("Hours: "+args[0]+", Strart: "+args[1]+", End: "+args[2]);
            ClockConfig clock = new ClockConfig(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
            puzzles.common.solver.Solver solver = new Solver(clock);
            solver.solve(clock);

        }
    }
}