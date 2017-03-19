
package Automaton_Projekt;

import java.util.Set;

/**
 * Represents WireWorld automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class WireWorld extends Automaton2Dim {

    /**
     * Constructor 
     * @param factory Cell state factory
     * @param neigh Cell neighborhood strategy
     * @param w width of the board
     * @param h height of the board
     */
    public WireWorld(CellStateFactory factory, CellNeighborhood neigh, int w, int h) {
        super(factory,neigh,w,h);
    }
    @Override
    protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
        return new WireWorld(factory,neighborhood,getWidth(),getHeight());
    }

    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates) {
        CellState returnState = currentCell.state;
        if (returnState == WireElectronState.ELECTRON_HEAD)
            returnState = WireElectronState.ELECTRON_TAIL;
        else if (returnState == WireElectronState.ELECTRON_TAIL)
            returnState = WireElectronState.WIRE;
        else if (returnState == WireElectronState.WIRE) {
            int head = 0;
        
            for (Cell c : neighborsStates) {
                if (c.state == WireElectronState.ELECTRON_HEAD)
                    head++;
            }
            if (head == 1 || head == 2) {
                returnState = WireElectronState.ELECTRON_HEAD;
            }
            else
                returnState = WireElectronState.WIRE;
        }
        return returnState;
    }
    
}
