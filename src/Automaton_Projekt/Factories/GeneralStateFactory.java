
package Automaton_Projekt;

import java.util.Map;

/**
 * Represents General State Factory 
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class GeneralStateFactory implements CellStateFactory {
    private Map<CellCoordinates,CellState> states;

    /**
     * Constructor 
     * @param mapa map of Cell coordinates and states
     */
    public GeneralStateFactory(Map<CellCoordinates,CellState> mapa) {
        states = mapa;
    }

    @Override
    public CellState initialState(CellCoordinates cord) {
        if (cord instanceof Coords2D) {
            return states.get(new Coords2D(cord.getX(),cord.getY()));
        }
        else {
            return states.get(new Coords1D(cord.getX()));
        
        }
    }

    /**
     * Returns map from the factory
     * @return map from the factory
     */
    public Map<CellCoordinates,CellState> getMapFromFactory() {
        return states;
    }
    
}
