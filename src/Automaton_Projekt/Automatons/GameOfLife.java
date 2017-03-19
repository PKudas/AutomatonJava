
package Automaton_Projekt;

import java.util.Set;

/**
 * Represents Game Of Life automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class GameOfLife extends Automaton2Dim {
    private Set<Integer> Born;
    private Set<Integer> Survives;
    
    /**
     * Constructor
     * @param factory Cell state factory
     * @param neigh Cell neighborhood strategy
     * @param w width of the board
     * @param h height of the board
     * @param b Born principles
     * @param s Surviving principles
     */
    public GameOfLife(CellStateFactory factory, CellNeighborhood neigh, int w, int h,Set<Integer> b,Set<Integer> s) {
        super(factory, neigh, w, h);
        Born = b;
        Survives = s;
    }

    @Override
    protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
        return new GameOfLife(factory,neighborhood,getWidth(),getHeight(),Born,Survives);
    }
    

    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates) {
        int alive = 0;
        
        for (Cell c : neighborsStates) {
            if (c.state == BinaryState.ALIVE)
                alive++;
        }
        if(currentCell.state == BinaryState.ALIVE) {
            if(Survives.contains(alive))
                return BinaryState.ALIVE;
            else
                return BinaryState.DEAD;
        }
        else if(currentCell.state == BinaryState.DEAD) {
            if(Born.contains(alive))
                return BinaryState.ALIVE;
            else
                return BinaryState.DEAD;
        }
        else 
            return BinaryState.DEAD;
    }

    
    
}
