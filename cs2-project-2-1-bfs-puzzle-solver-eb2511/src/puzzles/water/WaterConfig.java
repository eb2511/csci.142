package puzzles.water;

import puzzles.solver.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WaterConfig implements Configuration {
    private int amount;
    private ArrayList<Integer> bucketsFull;
    private ArrayList<Integer> buckets;

    public WaterConfig(int amount,ArrayList<Integer> buckets){//water config from water.java
        this.amount = amount;
        this.bucketsFull = buckets;
        this.buckets=new ArrayList<Integer>();
        for(int i =0;i<buckets.size();i++){
            this.buckets.add(0);
        }
    }

    public WaterConfig(WaterConfig other,ArrayList<Integer>buckets){//water config for neighbours
        this.amount = other.amount;
        this.bucketsFull=other.bucketsFull;
        this.buckets=buckets;
    }

    @Override
    public boolean isSolution() {//if there is a bucket == amount wanted
        for (int bucket : this.buckets) {
            if (bucket == this.amount) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Configuration> smallNeighbours(){//small part of getNeighbours, does fill and dump
        LinkedList<Configuration> neighbours = new LinkedList<Configuration>();
        for (int j = 0;j<this.buckets.size();j++){
            ArrayList<Integer> bucketsNew = (ArrayList<Integer>) this.buckets.clone();
            bucketsNew.set(j,0);
            neighbours.add(new WaterConfig(this,bucketsNew));
            ArrayList<Integer> bucketsNew2 = (ArrayList<Integer>) this.buckets.clone();
            bucketsNew2.set(j,this.bucketsFull.get(j));
            neighbours.add(new WaterConfig(this,bucketsNew2));
        }
        return neighbours;
    }

    public LinkedList<Configuration> pourNeighbours(){//second half of getNeighbours, does dump a to b
        LinkedList<Configuration> neighbours = new LinkedList<Configuration>();
        for (int j = 0;j<this.buckets.size();j++){
            if(buckets.get(j)!=0){
                for (int i = 0; i<this.buckets.size();i++){
                    int emptySpace = bucketsFull.get(i)-buckets.get(i);
                    if(i==j){}
                    else if(buckets.get(j)>emptySpace){
                        ArrayList<Integer> bucketsNew = (ArrayList<Integer>) this.buckets.clone();
                        bucketsNew.set(i,bucketsFull.get(i));
                        bucketsNew.set(j,buckets.get(j)-emptySpace);
                        neighbours.add(new WaterConfig(this,bucketsNew));
                    }else{
                        ArrayList<Integer> bucketsNew = (ArrayList<Integer>) this.buckets.clone();
                        bucketsNew.set(i,buckets.get(i)+buckets.get(j));
                        bucketsNew.set(j,0);
                        neighbours.add(new WaterConfig(this,bucketsNew));
                    }
                }
            }
        }
        return neighbours;
    }


    @Override
    public List<Configuration> getNeighbours() {//getNeighbours
        LinkedList<Configuration> neighbours = new LinkedList<>();
        neighbours.addAll(this.smallNeighbours());
        neighbours.addAll(this.pourNeighbours());
        return neighbours;
    }

    @Override
    public boolean equals(Object other){//if hashcode is the same
        if(other instanceof WaterConfig){
            WaterConfig o = (WaterConfig) other;
            return this.hashCode() == o.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode(){//if bucket[0,0], returns 00, not 0 bc i'm fancy
        String code="";
        for(int i : this.buckets){
            code+=i;
        }
        return Integer.parseInt(code);
    }

    @Override
    public String toString(){//for printing solution steps
        return this.buckets.toString();
    }

}
