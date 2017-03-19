
package Automaton_Projekt;
import java.util.*;
/**
 *
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }

    /**
     * Returns Array created from the map
     * @param s map of Cell coordinates and Cell state
     * @param height heigh of the board
     * @param width width of the board
     * @return Array created from the map
     */
    public static CellState [] [] MapToArray(Map<CellCoordinates, CellState> s,int height, int width) {
        CellState [][] Board = new CellState [height][width];
        for(Map.Entry<CellCoordinates,CellState> entry : s.entrySet()) {
            Board[entry.getKey().getY()][entry.getKey().getX()] = entry.getValue();
        }
        return Board;
    }
    
}
