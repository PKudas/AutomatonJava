
package Automaton_Projekt;

import java.util.Set;

/**
 * Represents neighborhood strategy of Cell
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public interface CellNeighborhood {

    /**
     * Returns Set of Cell coordinates of the neighbors
     * @param cell coordinates of current Cell
     * @return Set of Cell coordinates of the neighbors
     */
    public Set<CellCoordinates> cellNeighbors(CellCoordinates cell);
}
