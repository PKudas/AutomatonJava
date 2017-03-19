
package Automaton_Projekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents Langton Ant automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class LangtonAnt extends Automaton2Dim {
    
    /**
     *
     * @param factory Cell state factory
     * @param neigh Cell neighborhood strategy
     * @param w width of the board
     * @param h height of the board
     */
    public LangtonAnt(CellStateFactory factory, CellNeighborhood neigh, int w, int h) {
        super(factory, neigh, w, h);
    }
    @Override
    protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
        return new LangtonAnt(factory,neighborhood,getWidth(),getHeight());
    }

    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates) {
       LangtonCell LCell = new LangtonCell(((LangtonCell) currentCell.state).cellState);
        if (currentCell.state instanceof LangtonCell ) {            
            if (!((LangtonCell) currentCell.state).antState.isEmpty()) {
                for (AntState ant : ((LangtonCell) currentCell.state).antState) {
                    if(((LangtonCell) currentCell.state).cellState == BinaryState.DEAD)
                        LCell.cellState = BinaryState.ALIVE;
                    else
                        LCell.cellState = BinaryState.DEAD;
                }
            }
        }
        for (Cell c : neighborsStates) {
            LangtonCell TmpCellState = ((LangtonCell) c.state);
            if(c.state instanceof LangtonCell) {
                if(!TmpCellState.antState.isEmpty()) {
                    for (AntState ant : TmpCellState.antState) {
                        AntState AntDirection = AntState.NONE;
                        if (TmpCellState.cellState == BinaryState.DEAD) {
                            if (ant == AntState.EAST) {
                                AntDirection = AntState.NORTH;
                            }
                            else if (ant == AntState.NORTH) {
                                AntDirection = AntState.WEST;
                            }
                            else if (ant == AntState.WEST) {
                                AntDirection = AntState.SOUTH;
                            }
                            else if (ant == AntState.SOUTH) {
                                AntDirection = AntState.EAST;
                            }
                        }
                        else if (TmpCellState.cellState == BinaryState.ALIVE) {
                            if (ant == AntState.EAST) {
                                AntDirection = AntState.SOUTH;
                            }
                            else if (ant == AntState.NORTH) {
                                AntDirection = AntState.EAST;
                            }
                            else if (ant == AntState.WEST) {
                                AntDirection = AntState.NORTH;
                            }
                            else if (ant == AntState.SOUTH) {
                                AntDirection = AntState.WEST;
                            }
                        }
                        if (AntDirection == AntState.NORTH) {
                            int topneighbor = 0;
                            topneighbor = (c.coords.getY()-1) % getHeight();
                            if (topneighbor == -1 )
                                topneighbor = getHeight()-1;
                            if (topneighbor == currentCell.coords.getY() && c.coords.getX() == currentCell.coords.getX()) {
                                LCell.antState.add(AntState.NORTH);
                            }
                        }
                        else if (AntDirection == AntState.WEST) {
                            int leftneighbor = 0;
                            leftneighbor = (c.coords.getX()-1) % getWidth();
                            if (leftneighbor == -1 )
                                leftneighbor = getWidth()-1;
                            if (leftneighbor == currentCell.coords.getX() && c.coords.getY() == currentCell.coords.getY()) {
                                LCell.antState.add(AntState.WEST);
                            }
                        }
                        else if (AntDirection == AntState.SOUTH) {
                            int bottomneighbor = 0;
                            bottomneighbor = (c.coords.getY()+1) % getHeight();
                            if (bottomneighbor == currentCell.coords.getY() && c.coords.getX() == currentCell.coords.getX()) {
                                LCell.antState.add(AntState.SOUTH);
                            }
                        }
                        else if (AntDirection == AntState.EAST) {
                            int rightneighbor = 0;
                            rightneighbor = (c.coords.getX()+1) % getWidth();
                            if (rightneighbor == currentCell.coords.getX() && c.coords.getY() == currentCell.coords.getY()) {
                                LCell.antState.add(AntState.EAST);
                            }
                        }
                    }
                    
                }
    
            }
        }
        return LCell;
        
    }
}    
