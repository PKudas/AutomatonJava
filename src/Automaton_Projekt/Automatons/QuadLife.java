
package Automaton_Projekt;

import java.util.Set;

/**
 * Represents Quad Life automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class QuadLife extends Automaton2Dim {
    
    /**
     * Constructor
     * @param factory Cell state factory 
     * @param neigh Cell neighborhood strategy
     * @param w width of the board 
     * @param h height of the board
     */
    public QuadLife(CellStateFactory factory, CellNeighborhood neigh, int w, int h) {
        super(factory, neigh, w, h);
    }
    @Override
    protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
        return new QuadLife(factory,neighborhood,getWidth(),getHeight());
    }

    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates) {
        int alive = 0;
        int red = 0;
        int blue = 0;
        int green = 0;
        int yellow = 0;
        for (Cell c : neighborsStates) {
            if (c.state == QuadState.BLUE) {
                blue++;
                alive++;
            }    
            else if (c.state == QuadState.GREEN) {
                green++;
                alive++;
            }    
            else if (c.state == QuadState.RED) {
                red++;
                alive++;
            }
                
            else if (c.state == QuadState.YELLOW) {
                yellow++;
                alive++;
            }    
        }
        if(currentCell.state == QuadState.BLUE || currentCell.state == QuadState.GREEN || currentCell.state == QuadState.RED || currentCell.state == QuadState.YELLOW ) {
            if(alive == 3 || alive == 2) {
                return currentCell.state;
            }
            else
                return BinaryState.DEAD;
        }
        else if(currentCell.state == BinaryState.DEAD) {
            if(alive == 3) {
                if (blue == 2 || blue == 3)
                    return QuadState.BLUE;
                else if (green == 2 || green == 3)
                    return QuadState.GREEN;
                else if (red == 2 || red == 3)
                    return QuadState.RED;
                else if (yellow == 2 || yellow == 3)
                    return QuadState.YELLOW;
                else if (blue == 0)
                    return QuadState.BLUE;
                else if (green == 0)
                    return QuadState.GREEN;
                else if (red == 0)
                    return QuadState.RED;
                else if (yellow == 0)
                    return QuadState.YELLOW;
            }
            else
                return BinaryState.DEAD;
        }
        
        return BinaryState.DEAD;
    }
    
}
