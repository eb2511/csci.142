package puzzles.solver;

import puzzles.clock.ClockConfig;

import java.util.*;

public class Solver {
    private LinkedList<Configuration> queue;
    private int count;
    private HashMap<Configuration, Configuration> map;
    private Configuration config;

    public Solver(Configuration config){
        this.queue = new LinkedList<Configuration>();
        this.count = 1;
        this.config = config;
        this.map = new HashMap<Configuration, Configuration>();
    }

    public void first(Configuration start){ //before looping, add step 0 in
        this.queue.add(start);
        this.map.put(start,null);
    }

    public void search(){//do bfs
        while(true){
            if(this.queue.isEmpty()){//exhausts
                System.out.println("No solution");
                this.config = null;
                break;
            }
            if (this.queue.peek().isSolution()){//is solution, add end, get out
                this.adding(this.queue.peek(),this.config);
                this.config=this.queue.getFirst();
                break;
            }
                this.config = this.queue.remove();//if not solution, add step, loop
                List<Configuration> neighbours = this.config.getNeighbours();
                for (Configuration neighbour : neighbours) {
                    this.adding(neighbour, this.config);
                }}
    }

    private LinkedList<Configuration> givePath(){//prints the path and all that
        LinkedList<Configuration> path = new LinkedList<Configuration>();
        path.add(this.config);
        while (this.map.get(this.config) != null){
            path.add(0,this.map.get(this.config));
            this.config = this.map.get(this.config);
        }
        if(this.config==null){
            System.out.println("Total configs: "+this.count);
            System.out.println("Unique configs: "+this.map.size());
            return null;
        }
        System.out.println("Path: "+ path);
        System.out.println("Total configs: "+this.count);
        System.out.println("Unique configs: "+this.map.size());
        for(int i = 0; i<path.size();i++){
            System.out.println("Step "+i+": "+path.get(i));
        }
        return path;
    }

    private void adding(Configuration k, Configuration v){//adds neighbours into maps and queues
        this.count++;
        if(!this.map.containsKey(k)){
            this.queue.add(k);
            this.map.put(k,v);
        }

    }


    public List<Configuration> solve(Configuration start) {//solve function called by water and clock
        this.first(start);
        this.search();
        return this.givePath();
    }
}
