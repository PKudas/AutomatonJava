
package Automaton_Projekt;


import java.util.*;

/**
 * Represents 1 or 2 dimensional automaton
 * @author Patryk
 * @since 05/01/2016
 * @version 1.0
 */
public abstract class Automaton {
    private Map<CellCoordinates,CellState> cells ;
    private CellNeighborhood neighborsStrategy;
    private CellStateFactory stateFactory;

    /**
     * Constructor
     * @param factory Cell state factory
     * @param neigh Cell neighborhood strategy
     */
    public Automaton(CellStateFactory factory, CellNeighborhood neigh){
        stateFactory = factory;
        neighborsStrategy = neigh;
        cells = new HashMap<>();
    }

    /**
     * Returns automaton after single step
     * @return automaton after single step
     */
    public Automaton nextState(){
        
        Automaton nextAutomat = newInstance(stateFactory, neighborsStrategy);
        for(CellIterator iterator = nextAutomat.cellIterator(initialCoordinates()); iterator.hasNext();) {
            Set<CellCoordinates> neighbors = neighborsStrategy.cellNeighbors(iterator.currentCoordinates);
            Set<Cell> mappedNeighbors = mapCoordinates(neighbors);
            
            for (Cell c : mappedNeighbors) {
                c.state = getCellStateFromFactory(c.coords);
            }
            CellState newState;
            newState = nextCellState(new Cell(getCellStateFromFactory(iterator.currentCoordinates),iterator.currentCoordinates), mappedNeighbors);
            
            iterator.setState(newState);
            iterator.next();
        }
        nextAutomat.setFactoryToCells();
        
        
        return nextAutomat;
    }
    
    /**
     * Returns new automaton
     * @param factory Cell state factory
     * @param neighborhood Cell neighborhood strategy
     * @return new automaton
     */
    protected abstract Automaton newInstance(CellStateFactory factory, CellNeighborhood neighborhood);

    /**
     * Returns coordinates of the first Cell
     * @return coordinates of the first Cell
     */
    protected abstract CellCoordinates initialCoordinates();

    /**
     * Checks if there are next coordinates in automaton
     * @param coordinates current Cell coordinates
     * @return true if there are next coordinates or false if there are not
     */
    protected abstract boolean hasNextCoordinates(CellCoordinates coordinates);

    /**
     * Returns next coordinates
     * @param coordinates current Cell coordinates
     * @return next coordinates
     */
    protected abstract  CellCoordinates nextCoordinates(CellCoordinates coordinates);

    /**
     * Returns next state of current Cell
     * @param currentCell current Cell
     * @param neighborsStates  Set of neighbors Cells
     * @return next state of current Cell
     */
    protected abstract CellState nextCellState(Cell currentCell, Set <Cell> neighborsStates);
    /**
     * Returns Set of neighbors Cells
     * @param coordinates Set of neighbors coordinates
     * @return Set of neighbors Cells
     */
    private static Set<Cell> mapCoordinates(Set<CellCoordinates> coordinates){
        Set<Cell> coordOfNeighbors = new HashSet<>();
        for(CellCoordinates c : coordinates) {
            coordOfNeighbors.add(new Cell(null, c));
        }
        return coordOfNeighbors;

    }

    
    public void setFactoryToCells(){
        stateFactory = new GeneralStateFactory(cells);
    }

    /**
     * Inserts given Structure to map in factory, existing values of coordinates
     * are replaced
     * @param map map of the structure
     * @param height height of board
     * @param width width of board
     */
    public void insertStructure(Map<CellCoordinates,CellState> map,int height,int width) {
        Map<CellCoordinates,CellState> states = stateFactory.getMapFromFactory();
        for (Map.Entry<CellCoordinates,CellState> entry : map.entrySet()) {
            if (entry.getKey().getY() < height && entry.getKey().getY() > -1  && entry.getKey().getX() < width && entry.getKey().getX() > -1)
                states.put(entry.getKey(), entry.getValue());
        }
        stateFactory = new GeneralStateFactory(states);
    }
    /**
     * Iterator set on current Cell
     */
    private class CellIterator implements Iterator<Cell>{
        private CellCoordinates currentCoordinates;

        public CellIterator(CellCoordinates coordinates){
            currentCoordinates = coordinates;
        }

        @Override
        public boolean hasNext(){
            return hasNextCoordinates(currentCoordinates);

        }
        public Cell next() {
            currentCoordinates = nextCoordinates(currentCoordinates);
            return new Cell(getCellStateFromFactory(currentCoordinates),currentCoordinates);
        }
        public void setState(CellState state){
            cells.put(currentCoordinates, state);
        } 
    }

    /**
     *
     * @param coordinates
     * @return
     */
    public CellIterator cellIterator(CellCoordinates coordinates){
        return new CellIterator(coordinates);
    }

    /**
     * Returns Cell state from factory
     * @param coord Current Cell coordinates
     * @return Cell state from factory
     */
    public CellState getCellStateFromFactory (CellCoordinates coord) {
        return stateFactory.initialState(coord);
    }
    
    public Map<CellCoordinates,CellState> getCells() {
        return cells;
    }
    
    public CellStateFactory getFactory() {
        return stateFactory;
    }
}
