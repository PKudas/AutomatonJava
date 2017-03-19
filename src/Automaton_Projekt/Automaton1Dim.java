
package Automaton_Projekt;

/**
 * Represents 1 dimensional automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public abstract class Automaton1Dim extends Automaton  {
    private int size;
    
    /**
     * Constructor
     * @param factory Cell state factory
     * @param neigh Cell neighborhood strategy
     * @param s size of the board
     */
    public Automaton1Dim (CellStateFactory factory, CellNeighborhood neigh,int s) {
        super(factory,neigh);
        size = s;
    }

    
    public int getSize() {
        return size;
    }
    @Override
    protected boolean hasNextCoordinates(CellCoordinates coord) {
        return coord.getX() < size;
    }
    @Override
    protected CellCoordinates initialCoordinates() {
        return new Coords1D(0);
    }
    @Override
    protected CellCoordinates nextCoordinates(CellCoordinates coord) {
        
        return new Coords1D(coord.getX()+1);
    } 
}
