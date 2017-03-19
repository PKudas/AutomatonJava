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
public class OneDimNeighborhoodJUnitTest {
    
    @Test
    public void NeighborsWithWrappingInTheMiddle() {
        int size = 4;
        boolean wrap = true;
	Set<CellCoordinates> neighbors = new HashSet();
        neighbors.add(new Coords1D(0));
        neighbors.add(new Coords1D(2));
        CellNeighborhood neigh = new OneDimNeighborhood(size,wrap);
        assertEquals(neighbors,neigh.cellNeighbors(new Coords1D(1)));
    }
    @Test
    public void NeighborsWithWrappingOnTheEdge() {
        int size = 4;
        boolean wrap = true;
	Set<CellCoordinates> neighbors = new HashSet();
        neighbors.add(new Coords1D(3));
        neighbors.add(new Coords1D(1));
        CellNeighborhood neigh = new OneDimNeighborhood(size,wrap);
        assertEquals(neighbors,neigh.cellNeighbors(new Coords1D(0)));
    }
}
