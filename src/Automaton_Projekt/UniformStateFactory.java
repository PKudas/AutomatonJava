
package Automaton_Projekt;

import java.util.Map;

/**
 * Represents Uniform state factory
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class UniformStateFactory implements CellStateFactory {
    private CellState state;

    public UniformStateFactory(CellState st) {
        state = st;
    }

    @Override
    public CellState initialState(CellCoordinates cord) {
        return state;
    }
    @Override
    public Map<CellCoordinates, CellState> getMapFromFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
