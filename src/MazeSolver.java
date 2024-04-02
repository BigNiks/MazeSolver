/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack<MazeCell> s = new Stack<>();
        MazeCell cell = maze.getStartCell();
        while (maze.isValidCell(cell.getRow(), cell.getCol())) {
            s.push(cell);
            cell = cell.getParent();
        }
        ArrayList<MazeCell> solution= new ArrayList<>();
        for (MazeCell m : s) {
            solution.add(m);
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> cell = new Stack<>();
        MazeCell position = maze.getStartCell();
        while (position.equals(maze.getEndCell()) == false) {
            if (position.isExplored() == false) {
                //North
                Stack(cell, position, position.getRow() - 1, position.getCol());
                //East
                Stack(cell, position, position.getRow(), position.getCol() + 1);
                //South
                Stack(cell, position, position.getRow() + 1, position.getCol());
                //West
                Stack(cell, position, position.getRow(), position.getCol() - 1);
                position = cell.pop();
            }
        }
        return this.getSolution();
    }

    public void Stack(Stack<MazeCell> cell, MazeCell position, int r, int c) {
        if (maze.isValidCell(r,c) == true) {
            cell.push(maze.getCell(r,c));
            maze.getCell(r,c).setExplored(true);
            maze.getCell(r,c).setParent(position);
        }
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> cell = new LinkedList<>();
        MazeCell position = maze.getStartCell();
        while (position.equals(maze.getEndCell()) == false) {
            //North
            Queue(cell,position,position.getRow() - 1, position.getCol());
            //East
            Queue(cell,position,position.getRow(), position.getCol() + 1);
            //South
            Queue(cell,position,position.getRow() + 1, position.getCol());
            //West
            Queue(cell,position,position.getRow(),position.getCol() - 1);
            position = cell.remove();
        }
        return this.getSolution();
    }

    public void Queue(Queue<MazeCell> cell, MazeCell position, int r, int c) {
        if (maze.isValidCell(r,c) == true) {
            cell.add(maze.getCell(r,c));
            maze.getCell(r,c).setExplored(true);
            maze.getCell(r,c).setParent(position);
        }
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
