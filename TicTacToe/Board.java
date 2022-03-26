import java.awt.*;

// The class that models the Tic-Tac-Toe game board.
public class Board {
	// Grid line width.
	public static final int GRID_WIDTH = 8;
	// Grid line half width.
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	// Initialising the 2D array of ROWS-by-COLS Cell instances.
	Cell[][] cells;

	/** Constructor to create the game board */
	public Board() {
		// Creating a new instance of the cells array using ROWS and COLS constants.
		cells = new Cell[GameMain.ROWS][GameMain.COLS];
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}

	/** Return true if it is a draw (i.e., no more EMPTY cells) */
	public boolean isDraw() {
		// Checking whether the game has ended in a draw.
		boolean isDraw = true;
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				if (cells[row][col].content == Player.Empty) {
					isDraw = false;
				}
			}
		}
		return isDraw;
	}

	/**
	 * Return true if the current player "thePlayer" has won after making their move
	 */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		// Checking if the player has 3-in-that-row.
		if (cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer
				&& cells[playerRow][2].content == thePlayer)
			return true;
		// Checking if the player has 3 in the playerCol.
		if (cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer
				&& cells[2][playerCol].content == thePlayer)
			return true;
		// Checking for 3-in-the-diagonal.
		if (cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer)
			return true;
		// Checking the diagonal in the other direction.
		if (cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer)
			return true;
		// If no winner, keep playing.
		return false;
	}

	/**
	 * Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		// Drawing the grid.
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF, GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,
					GRID_WIDTH, GRID_WIDTH);
		}
		for (int col = 1; col < GameMain.COLS; ++col) {
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 0, GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,
					GRID_WIDTH, GRID_WIDTH);
		}
		// Drawing the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col].paint(g);
			}
		}
	}
}