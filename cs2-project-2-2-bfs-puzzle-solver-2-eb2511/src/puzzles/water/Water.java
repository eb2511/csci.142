package puzzles.water;

import puzzles.common.solver.Solver;

import java.util.ArrayList;

public class Water {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(("Usage: java Water amount bucket1 bucket2 ..."));
        }else{
            int amount=Integer.parseInt(args[0]);
            ArrayList<Integer> buckets= new ArrayList<Integer>();
            for(int i=1; i<=args.length-1;i++){
                buckets.add(Integer.parseInt(args[i]));
            }
            WaterConfig waterConfig = new WaterConfig(amount,buckets);
            System.out.println("Amount="+amount+", Buckets="+buckets);
            puzzles.common.solver.Solver solver = new Solver(waterConfig);
            solver.solve(waterConfig);
        }
    }
}
