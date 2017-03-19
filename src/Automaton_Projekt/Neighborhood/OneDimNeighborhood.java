
package Automaton_Projekt;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents 1 dimensional neighborhood strategy
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class OneDimNeighborhood implements CellNeighborhood{
    private int size;
    private boolean wrapup;
    
    /**
     * Constructor
     * @param s size of the board
     * @param wrap wrapup option
     */
    public OneDimNeighborhood(int s, boolean wrap) {
        size = s;
        wrapup = wrap;
    }

    @Override
    public Set<CellCoordinates> cellNeighbors(CellCoordinates coords) {
        Set<CellCoordinates> neighborsCoordinates = new HashSet();
        int leftneighbor = 0;
        int rightneighbor = 0;
        if (wrapup) {            
            leftneighbor = (coords.getX()-1) % size;
            rightneighbor = (coords.getX()+1) % size;
            if (leftneighbor == -1 )
                leftneighbor = size-1;
        }
        else {
            leftneighbor = (coords.getX()-1) % size;
            rightneighbor = (coords.getX()+1) % size;
        }
        neighborsCoordinates.add(new Coords1D(leftneighbor));
        neighborsCoordinates.add(new Coords1D(rightneighbor));
        return neighborsCoordinates;
    }
    
}
