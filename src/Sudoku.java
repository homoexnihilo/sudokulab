import java.util.*;
public class Sudoku implements SudokuSolver {
	int length;
	int[][] board;
	Random rand;
	public Sudoku(){
		length = 9;
		board = new int[length][length];
		rand = new Random();
	}
	
//	//egen testklass (kan raderas)
//	public void fillSudoku() {           //För min egen skull i main-test
//		for(int y=0; y<length; y++) {
//			for(int x=0; x<length; x++) {
//				setCell(x, y, rand.nextInt(10));
//			}
//		}
//	}
	
	//egen testklass (kan raderas)
	/**
	 * Prints the sudoku board to console. (used for testing)
	 */
	public void printSudoku() {          //För min egen skull i test
		for(int x=0; x<length; x++) {
			for(int y=0; y<length; y++) {
				System.out.print("["+getCell(x,y)+"]" + " ");
			}
			System.out.println("\n");
			
		}
	}
	
	/** Clears the entire sudoku board. */
	@Override
	public void clear() {
        for(int i = 0; i < 9; i++) {
            for(int k = 0; k < 9; k++) {
                setCell(i, k, 0);
            }
        }
    }
	
	/**
     * Tries to solve the current sudoku.
     * @return true if there is one or more solutions, false if no solution could be found.
     */
	@Override
	public boolean solve() {
		for(int x=0; x<9; x++) {					//här gör jag bara en precheck innan jag går in i rekursiva lösningen 
			for(int y=0; y<9; y++) {				//som säkerställer att alla värden som ligger i matrisen är "legal"
					int temp = getCell(x,y);		//sparar värdet som ligger i en cell o sätter samma cell till noll för att
					setCell(x,y,0);					//isLegal metoden inte ska kollidera med sig själv o ge tillbaka fel svar.
					if(temp != 0) {					//Sätter tillbaka värdena in i cellen sen och antingen fortsätter eller
						if(isLegal(x, y, temp)) {	//returnar false ifall något värde bryter mot reglerna.
							setCell(x, y, temp);
						}
						else {
							setCell(x, y, temp);
							return false;
						}
					}
				}
			}
		return solve(0,0);
	}
	
	private boolean solve(int r, int c) {
		//"basfall", om den kommit igenom hela matrisen skall true returnas för att avbryta rekursionen
		if(r==length-1 && c==length)
			return true;
		
		//ifall kolumn blir 9 så måste vi gå till nästa rad och börja om i col=0
		if(c==length) {
			r++;
			c=0;
		}
		
		//kollar ifall värdet på nuvarande position redan har ett värde som är ditsatt (värde större än 0)
		if(board[r][c] != 0) 
			return solve(r, c+1);
		
		//testar att sätta in värde 1-9 och sedan går vidare/hoppar tillbaka tills en lösning hittats/inte finns
		for(int val=1; val<10; val++) { //itererar mellan 1 till 9 för att placera i en cell
			if(isLegal(r, c, val)) { //kollar om man kan sätta värdet val(1-9) i nuvarande rad och kolumn
				board[r][c] = val;
				if(solve(r, c+1))  //kollar vidare i nästa kolumn för nästa möjlighet
					return true;
			}
			board[r][c] = 0; //sätter tillbaka värdet i cellen till 0 eftersom det inte funkade
		}
		return false;
	}

	/** 
     * (Optional) Fills the board with random (valid) numbers.
     *
     * @throws UnsupportedOperationException if not implemented.
     */
	public void randomize() {    //behöver en metod som kollar ifall det är tillåtet att sätta ett värde på en viss plats!! (nu isLegal)
		for(int x=0; x<length; x++) {
			for(int y=0; y<length; y++) {
				int rndInt = rand.nextInt(10);
				if(rand.nextInt(3) == 1) {
					if(isLegal(x, y, rndInt)) {
						setCell(x, y, rndInt);
					}
				}
			}
		}
	}
	
	public boolean isLegal(int row, int col, int val) {
		//kolla ifall samma tal finns i row
		for(int y=0; y<9; y++) {
			if(board[row][y] == val) 
				return false;
		}
		
		//kolla ifall samma tal finns i col
		for(int x=0; x<9; x++) {
			if(board[x][col] == val)
				return false;
		}
		
		//kolla ifall samma tal finns i "region", en liten 3x3 matris.
		
		int rowStart = row/3 * 3;	//för att komma till övre vänster hörn i den regionen(3x3 matrisen). 
		int colStart = col/3 * 3;	//	
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				if(board[i+rowStart][k+colStart] == val)
					return false;
			}
		}
		
		return true;    //om inga av de if satserna över är true så är talet ett giltigt tal.
	}
	
	/**
	 * Puts value val at specified row and column
	 * 
     * @param row must be between 1-9 since the sudokuboard is 9x9
     * @param col must be between 1-9 since the sudokuboard is 9x9
     * @param val must be between 1-9 (following the rules of sudoku) or 0 if cell should be marked as unsolved.
     * @throws IllegalArgumentException if any of the parameters are out of range
     *         or the if val can't be placed at the current cell.
     */
	@Override
	public void setCell(int row, int col, int val) throws IllegalArgumentException {
		board[row][col] = val;

	}
	
	/**
	 * Returns the value at specified row and col
	 * 
     * @param row must be between 1-9 since the sudokuboard is 9x9
     * @param col must be between 1-9 since the sudokuboard is 9x9
     * @throws IllegalArgumentException if any of the parameters are out of range
     * @return Value at specified row and col
     */
	@Override
	public int getCell(int row, int col) throws IllegalArgumentException {
		return board[row][col];
	}
	
	
	public static void main(String[] args) {  //egen mainmetod för test
//		Sudoku s = new Sudoku();
//		s.randomize();
//		s.printSudoku();
//		System.out.println();
//		System.out.println();
//		if(s.solve()) {
//			s.printSudoku();
//		}
//		else {
//			System.out.println("cant be solved");
//		}
////		s.clear();
//		System.out.println();
////		s.printSudoku();
		
		Sudoku s = new Sudoku();
		SudokuGUI gui = new SudokuGUI(s);
		
	}

}
