package puzzles.slide.model;

import puzzles.common.Observer;
import puzzles.common.solver.Configuration;
import puzzles.common.solver.Solver;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SlideModel {
    /** the collection of observers of this model */
    private final List<Observer<SlideModel, SlideClientData>> observers = new LinkedList<>();

    /** the current configuration */
    private SlideConfig currentConfig;
    private SlideConfig startConfig;
    private List<Configuration> path;
    private int selectTime = 0;
    private int[] chosen;
    /**
     * The view calls this to add itself as an observer.
     *
     * @param observer the view
     */
    public void addObserver(Observer<SlideModel, SlideClientData> observer) {
        this.observers.add(observer);
    }

    /**
     * The model's state has changed (the counter), so inform the view via
     * the update method
     */
    private void alertObservers(SlideClientData data) {
        for (var observer : observers) {
            observer.update(this, data);
        }
    }

    public SlideModel(String filename) throws IOException {
        this.currentConfig=new SlideConfig(filename);
        this.startConfig = new SlideConfig(filename);
        this.selectTime=0;//so ppl can't cheat
        alertObservers(new SlideClientData(getGrids()));
    }
    public String reset(){
        this.currentConfig=this.startConfig.copy();
        this.selectTime=0;//so ppl can't cheat
        alertObservers(new SlideClientData(getGrids()));
        return "Puzzle reset!";
    }
    public int getRows(){
        return this.currentConfig.getRow();
    }
    public int getCols(){
        return this.currentConfig.getCol();
    }
    public int[][] getGrids(){
        return this.currentConfig.getGrid();
    }


    @Override
    public String toString(){
        return this.currentConfig.toString();
    }


    private void setConf(SlideConfig grids){
        this.currentConfig=grids;
        alertObservers(new SlideClientData(getGrids()));
    }
    public String hint() {
        if(this.currentConfig.isSolution()){
            return "Already solved!!!";
        }
        Solver solver = new Solver(this.currentConfig);
        this.path=solver.solve(this.currentConfig);
        setConf((SlideConfig) path.get(1));
        return "Next step!";
    }

    public boolean isNeighbour(int[] grid){
        if(grid.length!=this.chosen.length){return false;}
        if(grid[0]!=this.chosen[0]){
            if(grid[0]==this.chosen[0]+1||grid[0]==this.chosen[0]-1){
                if(grid[1]==this.chosen[1]){return true;}
            }
            return false;}
        if(grid[1]==this.chosen[1]+1||grid[1]==this.chosen[1]-1){return true;}
        return false;
    }


    public String selelct(int r, int c){
        if(this.currentConfig.isSolution()){
            return "Already solved!!!";
        }
        String str;
        int[] grid = new int[2];
        grid[0]=r;
        grid[1]=c;
        if(r<0||c<0||r>=this.getRows()||c>=this.getCols()) {//if out of grids
            str= "Invalid selection: " + "["+r+" ,"+c+"]";
        }
        if(this.selectTime==0){//choose time 0
            if(this.currentConfig.findEmpty()[0]==r&&this.currentConfig.findEmpty()[1]==c){
                str= "No number at: "+"["+r+" ,"+c+"]";//if choose empty first
            }else{
                this.chosen=grid;
                this.selectTime=1;//success choose
                str= ("Selected: ["+r+" ,"+c+"]");
            }
        }else{//choose time 1
            if(this.currentConfig.findEmpty()[0]==r&&this.currentConfig.findEmpty()[1]==c&&this.isNeighbour(grid)){
                this.currentConfig=this.currentConfig.setGridValue(this.chosen);
                this.selectTime=0;
                str= "Moved from ["+this.chosen[0]+" ,"+this.chosen[1]+"] to ["+r+" ,"+c+"]";
            }else{
                str= ("Can't move from ["+this.chosen[0]+" ,"+this.chosen[1]+"] to ["+r+" ,"+c+"]");//not neighbour or both not empty
            }
        }
        alertObservers(new SlideClientData(getGrids()));
        return str;
    }

    public void quit(){System.exit(0);}
    }



