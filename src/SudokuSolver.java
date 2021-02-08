public interface SudokuSolver {

    /**
     * Tries to solve the current sudoku.
     * @return true if there is one or more solutions, false if no solution could be found.
     */
    boolean solve();

    /** Clears the entire sudoku board. */
    default void clear() {
        for(int i = 1; i <= 9; i++) {
            for(int k = 1; k <= 9; k++) {
                setCell(i, k, 0);
            }
        }
    }

    /** 
     * (Optional) Fills the board with random (valid) numbers.
     *
     * @throws UnsupportedOperationException if not implemented.
     */
    default void randomize() {
        throw new UnsupportedOperationException();
    }

    /**
     * @param row must be between 1-9 since the sudokuboard is 9x9
     * @param col must be between 1-9 since the sudokuboard is 9x9
     * @param val must be between 1-9 (following the rules of sudoku) or 0 if cell should be marked as unsolved.
     * @throws IllegalArgumentException if any of the parameters are out of range
     *         or the if val can't be placed at the current cell.
     */
    void setCell(int row, int col, int val) throws IllegalArgumentException;

    /**
     * @param row must be between 1-9 since the sudokuboard is 9x9
     * @param col must be between 1-9 since the sudokuboard is 9x9
     * @throws IllegalArgumentException if any of the parameters are out of range
     * @return Value at specified row and col
     */
    int getCell(int row, int col) throws IllegalArgumentException;
    
    
    /**
     * prints the sudoku board in the console  (kanske olagligt jag vet inte, använde för att kolla så JUnit testet blev korrekt)
     */
    void printSudoku(); 

}