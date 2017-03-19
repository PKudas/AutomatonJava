
package Automaton_Projekt;

/**
 * Represents Coordinates of Cell
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public interface CellCoordinates {

    public int getX();
    public int getY();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
}
