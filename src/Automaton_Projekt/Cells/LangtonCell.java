
package Automaton_Projekt;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents Cell in Langton Ant automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class LangtonCell implements CellState{

    public BinaryState cellState;

    public Set<AntState> antState = new HashSet();

    public LangtonCell(BinaryState b) {
        cellState = b;
    }

    public void AddAnt(AntState st) {
        antState.add(st);
    }
}
