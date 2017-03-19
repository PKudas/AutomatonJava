
package Automaton_Projekt;

import java.util.Map;

/**
 * Represents interface for the state factory of automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public interface CellStateFactory {

    /**
     * Returns Cell state from the factory
     * @param cord current Cell coordinates
     * @return Cell state from the factory
     */
    public CellState initialState(CellCoordinates cord);

    /**
     * Returns map of Cell coordinates and State from the factory
     * @return map of Cell coordinates and State from the factory
     */
    public Map<CellCoordinates,CellState> getMapFromFactory();
}
