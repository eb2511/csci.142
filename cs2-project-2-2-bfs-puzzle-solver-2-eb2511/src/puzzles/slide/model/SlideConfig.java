package puzzles.slide.model;

import puzzles.common.solver.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.hash;

public class SlideConfig  implements Configuration {
    private int row;//updpwn
    private int col;//leftright
    private int[][] grid;
    private static int EMPTY = 999;


    public int getRow() {
        return this.row;
    }
    public int getCol(){
        return this.col;
    }

    public int[][] getGrid() {
        return grid;
    }

    public SlideConfig(String fileName) throws FileNotFoundException {
        Scanner f = new Scanner(new File(fileName));
        this.row = f.nextInt();
        this.col = f.nextInt();
        this.grid=new int[row][col];
        for(int i = 0;i<this.row;i++){
            for(int j = 0; j<this.col;j++){
                String next = f.next();
                if(next.equals(".")){
                    this.grid[i][j]=EMPTY;
                }else {
                    this.grid[i][j] = Integer.parseInt(next);
                }
            }
        }

    }

    public SlideConfig(SlideConfig parent){
        this.row = parent.row;
        this.col = parent.col;
        this.grid=new int[this.row][this.col];
        for(int i = 0;i<this.row;i++){
            this.grid[i]=parent.grid[i].clone();
        }

    }

    @Override
    public boolean isSolution() {
        boolean result = true;
        int i = 0;
        int j = 0;
        while(!(i==this.row-1&&j==this.col-1)){
            if(j == this.col-1){
                if(this.grid[i][j]>this.grid[i+1][0]){return false;}
                i++;
                j=0;
            }else if(this.grid[i][j]>this.grid[i][j+1]){
                return false;
            }else{
                j++;
            }
        }

        return result;
    }

    public int[] findEmpty() {//empty==this.grid[emp[0]][emp[1]]
        int[] empty = new int[2];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if(this.grid[i][j]==EMPTY){
                    empty[0]=i;
                    empty[1]=j;
                    return empty;
                }
            }
        }
        return empty;
    }
    @Override
    public List<Configuration> getNeighbours() {
        List<Configuration> queue = new LinkedList<Configuration>();
        int[] emp = this.findEmpty();
        int eRol = emp[0];//empty where
        int eCol = emp[1];
        if(eCol!=this.col-1){//right->left
            SlideConfig nconfig = new SlideConfig(this);
            nconfig.grid[eRol][eCol]=this.grid[eRol][eCol+1];
            nconfig.grid[eRol][eCol+1] = EMPTY;
           // SlideConfig neighbour = new SlideConfig(this,ngrid);
                    queue.add(nconfig);
        }
        if(eCol!=0){//left-right
            SlideConfig nconfig = new SlideConfig(this);
            nconfig.grid[eRol][eCol]=this.grid[eRol][eCol-1];
            nconfig.grid[eRol][eCol-1] = EMPTY;
            queue.add(nconfig);
        }
        if(eRol!=0){//up->down
            SlideConfig nconfig = new SlideConfig(this);
            nconfig.grid[eRol][eCol]=this.grid[eRol-1][eCol];
            nconfig.grid[eRol-1][eCol] = EMPTY;
            queue.add(nconfig);
        }
        if(eRol!=this.row-1){//down->up
            SlideConfig nconfig = new SlideConfig(this);
            nconfig.grid[eRol][eCol]=this.grid[eRol+1][eCol];
            nconfig.grid[eRol+1][eCol] = EMPTY;
            queue.add(nconfig);

        }
        return queue;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof SlideConfig o){
            for(int i=0;i<this.row;i++){
                for (int j = 0;j<this.col;j++){
                    if(this.grid[i][j]!=o.grid[i][j]){return false;}
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
//        StringBuilder code= new StringBuilder();
//        for(int i = 0;i<this.row;i++){
//            for(int j = 0; j<this.col;j++){
//                code.append(Integer.toString(this.grid[i][j]));
//            }
//        }
//        return Integer.parseInt(code.toString());
        return Arrays.deepHashCode(this.grid);
    }



    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("\n");
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if(this.grid[i][j]==EMPTY){
                    str.append(".");
                }else{
                    str.append(this.grid[i][j]);
                }

                str.append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public SlideConfig copy(){
        SlideConfig slideConfig = new SlideConfig(this);
        return slideConfig;
    }

    public int getGridValue(int[] grid){
        int i = grid[0];
        int j = grid[1];
        return this.grid[i][j];
    }

    public SlideConfig setGridValue(int[] grid){
        int i = grid[0];
        int j = grid[1];
        int[] emp = this.findEmpty();
        int eRol = emp[0];//empty where
        int eCol = emp[1];
        SlideConfig nconfig = new SlideConfig(this);
        nconfig.grid[eRol][eCol]=this.grid[i][j];
        nconfig.grid[i][j]=EMPTY;
        return nconfig;
    }
}
