
package Automaton_Projekt;

import java.util.Set;

/**
 * Represents 1 dimensional automaton depending on Wolfram Rule
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public class Automaton1D extends Automaton1Dim {
    private int [] Rule;
    private boolean wrap;
    
    /**
     * Constructor
     * @param factory Cell state factory
     * @param neigh Cell neighborhood strategy
     * @param s size of the board
     * @param rule Wolfram Rule of 1 Dimensional automaton
     * @param w wrapup option
     */
    public Automaton1D(CellStateFactory factory, CellNeighborhood neigh,int s,int [] rule,boolean w) {
        super(factory,neigh,s);
        wrap = w;
        Rule = new int[8];
        System.arraycopy(rule, 0, Rule, 0, 8);
    }
    @Override
    protected Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood) {
        return new Automaton1D(factory,neighborhood,getSize(),Rule,wrap);
    }

    @Override
    protected CellState nextCellState(Cell currentCell, Set<Cell> neighborsStates) {
        CellState returnState = currentCell.state;
        //System.out.println(currentCell.state);
        Cell leftneighbor = new Cell(OneDimState.ZERO,new Coords1D(0));
        Cell rightneighbor = new Cell(OneDimState.ZERO,new Coords1D(0));
        int leftneighborcord = 0;
        int rightneighborcord = 0;
        if (wrap) {            
            leftneighborcord = (currentCell.coords.getX()-1) % getSize();
            rightneighborcord = (currentCell.coords.getX()+1) % getSize();
            if (leftneighborcord == -1 )
                leftneighborcord = getSize()-1;
        }
        else {
            leftneighborcord = (currentCell.coords.getX()-1) % getSize();
            rightneighborcord = (currentCell.coords.getX()+1) % getSize();
            if (leftneighborcord == -1 || rightneighborcord == 0)
                return OneDimState.ZERO;
        }
        for (Cell c : neighborsStates) {
            if (c.coords.getX() == leftneighborcord) {
                leftneighbor.coords = c.coords;
                leftneighbor.state = c.state;
            }
            if (c.coords.getX() == rightneighborcord) {
                rightneighbor.coords = c.coords;
                rightneighbor.state = c.state;
            }
        }
        if(leftneighbor.state == OneDimState.ONE && currentCell.state == OneDimState.ONE && rightneighbor.state == OneDimState.ONE)
            returnState = Change(0);
        else if(leftneighbor.state == OneDimState.ONE && currentCell.state == OneDimState.ONE && rightneighbor.state == OneDimState.ZERO)
            returnState = Change(1);
        else if(leftneighbor.state == OneDimState.ONE && currentCell.state == OneDimState.ZERO && rightneighbor.state == OneDimState.ONE)
            returnState = Change(2);
        else if(leftneighbor.state == OneDimState.ONE && currentCell.state == OneDimState.ZERO && rightneighbor.state == OneDimState.ZERO)
            returnState = Change(3);
        else if(leftneighbor.state == OneDimState.ZERO && currentCell.state == OneDimState.ONE && rightneighbor.state == OneDimState.ONE)
            returnState = Change(4);
        else if(leftneighbor.state == OneDimState.ZERO && currentCell.state == OneDimState.ONE && rightneighbor.state == OneDimState.ZERO)
            returnState = Change(5);
        else if(leftneighbor.state == OneDimState.ZERO && currentCell.state == OneDimState.ZERO && rightneighbor.state == OneDimState.ONE)
            returnState = Change(6);
        else if(leftneighbor.state == OneDimState.ZERO && currentCell.state == OneDimState.ZERO && rightneighbor.state == OneDimState.ZERO)
            returnState = Change(7);
        
        return returnState;
    }

    /**
     * Returns Cell state depending on Wolfram Rule
     * @param i index of Rule array
     * @return Cell state depending on Wolfram Rule
     */
    public CellState  Change(int i) {
        if (Rule[i] == 1)
            return OneDimState.ONE;
        else
            return OneDimState.ZERO;
    }
    
}
