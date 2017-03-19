
package Automaton_Projekt;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents Von Neumann neighborhood strategy
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class VonNeumanNeighborhood implements CellNeighborhood{
    private int height;
    private int width;
    private boolean wrapup;
    
    /**
     * Constructor
     * @param hei height of the board
     * @param wid width of the board
     * @param wrap wrapup option
     */
    public VonNeumanNeighborhood(int hei,int wid,boolean wrap) {
        height = hei;
        width = wid;
        wrapup = wrap;
    }

    @Override
    public Set<CellCoordinates> cellNeighbors(CellCoordinates coords) {
        Set<CellCoordinates> neighborsCoordinates = new HashSet();
        int leftneighbor = 0;
        int rightneighbor = 0;
        int topneighbor = 0;
        int bottomneighbor = 0;
        if (wrapup) {            
            leftneighbor = (coords.getX()-1) % width;
            rightneighbor = (coords.getX()+1) % width;
            topneighbor = (coords.getY()-1) % height;
            bottomneighbor = (coords.getY()+1) % height;
            if (leftneighbor == -1 )
                leftneighbor = width-1;
            if (topneighbor == -1 )
                topneighbor = height-1;
            neighborsCoordinates.add(new Coords2D(leftneighbor,coords.getY()));
            neighborsCoordinates.add(new Coords2D(coords.getX(),topneighbor));
            neighborsCoordinates.add(new Coords2D(coords.getX(),bottomneighbor));
            neighborsCoordinates.add(new Coords2D(rightneighbor,coords.getY()));
        }
        else {
            leftneighbor = (coords.getX()-1) % width;
            rightneighbor = (coords.getX()+1) % width;
            topneighbor = (coords.getY()-1) % height;
            bottomneighbor = (coords.getY()+1) % height;
            if (leftneighbor != -1 )
                neighborsCoordinates.add(new Coords2D(leftneighbor,coords.getY()));
            if (topneighbor != -1)
                neighborsCoordinates.add(new Coords2D(coords.getX(),topneighbor));
            if (bottomneighbor != 0)
                neighborsCoordinates.add(new Coords2D(coords.getX(),bottomneighbor));
            if (rightneighbor != 0) 
                neighborsCoordinates.add(new Coords2D(rightneighbor,coords.getY()));
            
        }       
        
        return neighborsCoordinates;
    }
    
}
