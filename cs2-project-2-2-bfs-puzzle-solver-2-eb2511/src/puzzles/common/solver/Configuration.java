package puzzles.common.solver;

import java.util.List;

public interface Configuration {
    public boolean isSolution();
    public List<Configuration> getNeighbours();
}
