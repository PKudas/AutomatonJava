
package Automaton_Projekt;

/**
 * Represents Cell in automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public final class Cell {

    
    public CellState state;    
    public CellCoordinates coords;

    public Cell(CellState initialState, CellCoordinates c) {
        state = initialState;
        coords = c;
    }
}
