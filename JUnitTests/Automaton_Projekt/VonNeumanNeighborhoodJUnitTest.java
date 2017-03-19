package Automaton_Projekt;


import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class VonNeumanNeighborhoodJUnitTest {
    
    
    @Test
    public void NeighborsWithWrappingInTheMiddle() {
        int height = 4;
        int width = 4;
        boolean wrap = true;
	Set<CellCoordinates> neighbors = new HashSet();
        neighbors.add(new Coords2D(0,1));
        neighbors.add(new Coords2D(1,0));
        neighbors.add(new Coords2D(1,2));
        neighbors.add(new Coords2D(2,1));        
        CellNeighborhood neigh = new VonNeumanNeighborhood(height,width,wrap);
        assertEquals(neighbors,neigh.cellNeighbors(new Coords2D(1,1)));
    }
    @Test
    public void NeighborsWithWrappingOnTheEdge() {
        int height = 4;
        int width = 4;
        boolean wrap = true;
	Set<CellCoordinates> neighbors = new HashSet();
        neighbors.add(new Coords2D(0,1));
        neighbors.add(new Coords2D(1,0));
        neighbors.add(new Coords2D(0,3));
        neighbors.add(new Coords2D(3,0));        
        CellNeighborhood neigh = new VonNeumanNeighborhood(height,width,wrap);
        assertEquals(neighbors,neigh.cellNeighbors(new Coords2D(0,0)));
    }
    @Test
    public void NeighborsWithoutWrappingOnTheEdge() {
        int height = 4;
        int width = 4;
        boolean wrap = false;
	Set<CellCoordinates> neighbors = new HashSet();
        neighbors.add(new Coords2D(0,1));
        neighbors.add(new Coords2D(1,0));  
        CellNeighborhood neigh = new VonNeumanNeighborhood(height,width,wrap);
        assertEquals(neighbors,neigh.cellNeighbors(new Coords2D(0,0)));
    }
}
