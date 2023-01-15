package puzzles.clock;

import puzzles.common.solver.Configuration;

import java.util.LinkedList;
import java.util.List;

public class ClockConfig implements Configuration {
    private int hours;
    private  int current;
    private int end;

    public ClockConfig(int hours, int start, int end){//initialize
        this.end = end;
        this.hours = hours;
        this.current = start;

    }

    @Override
    public boolean isSolution() {//is this the end?
        if(this.current==this.end){
            return true;
        }
        return false;
    }

    @Override
    public List<Configuration> getNeighbours() {//current+1, current-1, except 12 and 1 need more work.
        List<Configuration> queue = new LinkedList<Configuration>();
        if(this.current==1){
            queue.add(new ClockConfig(this.hours,12,this.end));
            queue.add(new ClockConfig(this.hours,2,this.end));
        }else if(this.current==12){
            queue.add(new ClockConfig(this.hours,11,this.end));
            queue.add(new ClockConfig(this.hours,1,this.end));
        }else {
            queue.add(new ClockConfig(this.hours,this.current-1,this.end));
            queue.add(new ClockConfig(this.hours,this.current+1,this.end));
        }
        return queue;
    }

    @Override
    public boolean equals(Object other){//if this current is other current.
        if(other instanceof ClockConfig){
            ClockConfig o = (ClockConfig) other;
            return this.current==o.current;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.current;
    }//current

    @Override
    public String toString(){
        return ""+this.current;
    }//for printing solutions.
}
