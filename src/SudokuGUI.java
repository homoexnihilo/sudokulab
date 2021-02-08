import java.awt.*;
import javax.swing.*;

public class SudokuGUI {
	SudokuSolver board;
	int[][] guiBoard;
	JTextField[][] map;
	JFrame frame;
	Container pane;
	JPanel container;
	JPanel gridPanel;
	JPanel buttonPanel;
	
	public SudokuGUI(Sudoku s) {
		board = s;
		guiBoard = new int[9][9];
		map = new JTextField[9][9];
		copyBoard(guiBoard, board);
		SwingUtilities.invokeLater(() -> createWindow("Sudoku", 800, 800));
		
	}
	
	private void copyBoard(int[][] guiBoard, SudokuSolver board) {
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				guiBoard[x][y] = board.getCell(x, y);
			}
		}
	}
	
	private void createWindow(String title, int width, int height) {
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(800,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = frame.getContentPane();
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		pane.add(container);
		gridPanel = new JPanel();
		buttonPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(9,9, 5, 5));
		buttonPanel.setMaximumSize(new Dimension(800, 50));
		container.add(gridPanel);
		container.add(buttonPanel);
		addTextFieldsToMap(); //lägger till massa textfält till en matris jag skapat för att hålla koll på varje textfält i griden.
		recolorGUI();
		JButton solveBtn = new JButton("Solve");
		JButton clearBtn = new JButton("Clear");
		buttonPanel.add(solveBtn);
		buttonPanel.add(clearBtn);
		updateGUI();
		frame.pack();
		frame.setVisible(true);
		
		solveBtn.addActionListener(e -> {
			boolean temp = false;
			for(int x=0; x<9; x++) {
				for(int y=0; y<9; y++) {
					if(map[x][y].getText().compareTo("") == 0) {
						board.setCell(x, y, 0);
					}
					else {
						try {						//Kollar så värdet i textrutan är mellan 1-9, om det är det så sätt det i cellen.
							if(Integer.parseInt(map[x][y].getText()) >= 1 && Integer.parseInt(map[x][y].getText()) <= 9) {
								board.setCell(x, y, Integer.parseInt(map[x][y].getText()));
							}
							else {				//om det är en siffra som inte är mellan 1-9 så visas dialog o temp = true så solve inte körs.
								JOptionPane.showMessageDialog(frame, "Illegal characters on board!");
								temp = true;
								break;
							}
						}						//om det som står i textrutan inte är en int så kommer man hit och samma sak sker som de över
						catch(IllegalArgumentException a) {
							JOptionPane.showMessageDialog(frame, "Illegal characters on board!");
							temp = true;
							break;
						}
					}
				}
			}
			if(!temp) {
				if(!board.solve()) 
					JOptionPane.showMessageDialog(frame, "Sudoku unsolvable!");
			}
			updateGUI();
		});
		
		clearBtn.addActionListener(e ->{
			board.clear();
			updateGUI();
		});
		
		
	}
	
	private void addTextFieldsToMap() {
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				JTextField field = new JTextField();
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setFont(new Font("SansSerif", Font.BOLD, 20));
				map[x][y] = field;
				gridPanel.add(field);
			}
		}
	}
	
	private void updateGUI() {
		for(int x=0; x<9; x++) {
			for(int y=0; y<9; y++) {
				if(board.getCell(x, y) == 0) {
					map[x][y].setText("");
				}else {
					map[x][y].setText(String.valueOf(board.getCell(x, y)));
				}
			}
		}

	}
	
	private void recolorGUI() {
		for(int x=0; x<3; x++) {
			for(int y=0; y<3; y++) {
				map[x][y].setBackground(Color.orange);
			}
		}
		
		for(int x=0; x<3; x++) {
			for(int y=6; y<9; y++) {
				map[x][y].setBackground(Color.orange);
			}
		}
		
		for(int x=3; x<6; x++) {
			for(int y=3; y<6; y++) {
				map[x][y].setBackground(Color.orange);
			}
		}
		
		for(int x=6; x<9; x++) {
			for(int y=0; y<3; y++) {
				map[x][y].setBackground(Color.orange);
			}
		}
		
		for(int x=6; x<9; x++) {
			for(int y=6; y<9; y++) {
				map[x][y].setBackground(Color.orange);
			}
		}
	}
	
	
	
	
	
	
}
