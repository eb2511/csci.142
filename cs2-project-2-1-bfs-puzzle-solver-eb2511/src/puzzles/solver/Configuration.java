package puzzles.solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface Configuration {
    public boolean isSolution();
    public List<Configuration> getNeighbours();
}
