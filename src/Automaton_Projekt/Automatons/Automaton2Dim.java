
package Automaton_Projekt;

/**
 * Represents 2 dimensional automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public abstract class Automaton2Dim extends Automaton {
    private int width;
    private int height;

    /**
     *
     * @param factory Cell state factory
     * @param neigh Cell neighborhood strategy
     * @param w width of the board
     * @param h height of the board
     */
    public Automaton2Dim (CellStateFactory factory, CellNeighborhood neigh,int w, int h) {
        super(factory,neigh);
        width = w;
        height = h;
    }    
    public int getWidth() {
        return width;
    }    
    public int getHeight() {
        return height;
    }
    @Override
    protected boolean hasNextCoordinates(CellCoordinates coord) {
        if (coord.getX() < width && coord.getY() < height)
            return true;
        else return false;
    }
    @Override
    protected CellCoordinates initialCoordinates() {
        return new Coords2D(0,0);
    }
    @Override
    protected CellCoordinates nextCoordinates(CellCoordinates coord) {
        if (coord.getX() > width - 2)
            return new Coords2D(0,coord.getY()+1);
        else return new Coords2D(coord.getX()+1,coord.getY());
    } 
}
