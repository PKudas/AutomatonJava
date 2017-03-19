
package Automaton_Projekt;

/**
 * Represents Coordinates of the cell in 1 dimensional automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public final class Coords1D implements CellCoordinates{

    public int x;
    public Coords1D(int a) {
       x = a;
    }

    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return 0;
    }
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Coords1D) {
            Coords1D that = (Coords1D) obj;
            result = (this.getX() == that.getX() );            
        }
        return result;
    }        
    
    @Override
    public int hashCode() {
        return (41*(41 + 17*getX()));
    }
}
