import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSudoku {
	
	SudokuSolver sBoard;
	@BeforeEach
	void setUp() throws Exception {
		sBoard = new Sudoku();
	}

	@AfterEach
	void tearDown() throws Exception {
		sBoard = null;
	}

	/**
	 * Test solve empty sudoku
	 */
	@Test
	void testSolveEmptySudoku() {
		assertTrue(sBoard.solve(), "Can't solve empty board");
	}
	
	/**
	 * Test solve sudoku from figure 1
	 */
	@Test
	void testSolveSudokuFromFigure1() {
		sBoard.setCell(0, 2, 8);
		sBoard.setCell(0, 5, 9);
		sBoard.setCell(0, 7, 6);
		sBoard.setCell(0, 8, 2);
		
		sBoard.setCell(1, 8, 5);
		
		sBoard.setCell(2, 0, 1);
		sBoard.setCell(2, 2, 2);
		sBoard.setCell(2, 3, 5);
		
		sBoard.setCell(3, 3, 2);
		sBoard.setCell(3, 4, 1);
		sBoard.setCell(3, 7, 9);
		
		sBoard.setCell(4, 1, 5);
		sBoard.setCell(4, 6, 6);
		
		sBoard.setCell(5, 0, 6);
		sBoard.setCell(5, 7, 2);
		sBoard.setCell(5, 8, 8);
		
		sBoard.setCell(6, 0, 4);
		sBoard.setCell(6, 1, 1);
		sBoard.setCell(6, 3, 6);
		sBoard.setCell(6, 5, 8);
		
		sBoard.setCell(7, 0, 8);
		sBoard.setCell(7, 1, 6);
		sBoard.setCell(7, 4, 3);
		sBoard.setCell(7, 6, 1);
		
		sBoard.setCell(8, 6, 4);
		
		assertTrue(sBoard.solve(), "Couldnt find solution");
		
		sBoard.printSudoku();
	}
	
	/**
	 * Test solve unsolvable sudoku
	 */
	@Test
	void testSolveUnsolvableSudoku() {
		//tar från testfallen vi fick med
		
		sBoard.setCell(0, 0, 1);
		sBoard.setCell(0, 1, 2);
		sBoard.setCell(0, 2, 3);
		
		sBoard.setCell(1, 0, 4);
		sBoard.setCell(1, 1, 5);
		sBoard.setCell(1, 2, 6);
		
		sBoard.setCell(2, 3, 7);
		
		assertFalse(sBoard.solve(), "Could be solved");
	}
	
}
