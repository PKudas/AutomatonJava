
package Automaton_Projekt;
/**
 * Represents Coordinates of the cell in 2 dimensional automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public final class Coords2D implements CellCoordinates {

    public int x;

    public int y;

    public Coords2D(int a, int b) {
       x = a;
       y = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
     @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Coords2D) {
            Coords2D that = (Coords2D) obj;
            result = (this.getX() == that.getX() && this.getY() == that.getY());            
        }
        return result;
    }        
    
    @Override
    public int hashCode() {
        return (41*(41 + 17*getX() + 37*getY()));
    }
}
