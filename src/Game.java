
public class Game {

	private Board board;
	private Piece[][] pieces = new Piece[8][8];
	private Player player1 = new Player(Piece.Color.Black, "Player one");
	private Player player2 = new Player(Piece.Color.White, "Player two");
	private Computer comp = new Computer();

	public Game() {

		player1.setTurn(true);
		player2.setTurn(false);

		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column += 2) {
				if (row < 3) {
					if (row % 2 == 0) {
						pieces[column][row] = new Piece(Piece.Color.Black, column, row);
					} else {
						pieces[(column + 1)][row] = new Piece(Piece.Color.Black, column + 1, row);
					}

				} else if (row > 4) {
					if (row % 2 == 0) {
						pieces[column][row] = new Piece(Piece.Color.White, column, row);
					} else {
						pieces[(column + 1)][row] = new Piece(Piece.Color.White, column + 1, row);
					}

				}
			}

		}

	}

	public int getBoardSize() {
		return board.getLength();
	}

	public Piece getPiece(int x, int y) {
		if (pieces[x][y] != null) {
			return pieces[x][y];
		} else {
			return null;
		}

	}

	public boolean canMovePiece(int new_row, int new_column, int old_row, int old_column) {
		if (pieces[old_row][old_column] != null) {
			if ((new_row - old_row == 1 || new_row - old_row == -1)
					&& (new_column - old_column == 1 || new_column - old_column == -1)) {
				if (canMoveForward(new_row, new_column, old_row, old_column)) {
					return true;
				}

			} else if ((new_row - old_row == 2 || new_row - old_row == -2)
					&& (new_column - old_column == 2 || new_column - old_column == -2)
					&& ((pieces[(new_row + old_row) / 2][(new_column + old_column) / 2] != null
							&& pieces[(new_row + old_row) / 2][(new_column + old_column) / 2]
									.getColor() != pieces[old_row][old_column].getColor()))) {
				if (canMoveForward(new_row, new_column, old_row, old_column)) {
					return true;
				}

			}

		}
		return false;

	}

	public void movePiece(int new_row, int new_column, int old_row, int old_column) {

		if (this.canMovePiece(new_row, new_column, old_row, old_column)) {
			pieces[new_row][new_column] = pieces[old_row][old_column];
			pieces[old_row][old_column] = null;
			checkIfKing(new_row, new_column);

			if ((new_row - old_row == 2 || new_row - old_row == -2)
					&& (new_column - old_column == 2 || new_column - old_column == -2)) {
				if (whosTurn() == player1) {
					player2.removePiece();
				} else {
					player1.removePiece();
				}
				pieces[(new_row + old_row) / 2][(new_column + old_column) / 2] = null;
				if (checkIfDoubleJump(new_row, new_column) == false) {
					nextTurn();
				}
			}

			if ((new_row - old_row == 1 || new_row - old_row == -1)
					&& (new_column - old_column == 1 || new_column - old_column == -1)) {
				nextTurn();
			}
		}
	}

	public boolean isSpaceEmpty(int row, int column) {
		if (pieces[row][column] == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canMoveForward(int new_row, int new_column, int old_row, int old_column) {

		if (isSpaceEmpty(new_row, new_column)) {
			if ((new_column - old_column) > 0 && pieces[old_row][old_column].getColor() == Piece.Color.Black
					&& pieces[old_row][old_column].isKing() == false) {
				return true;
			}

			if ((new_column - old_column) < 0 && pieces[old_row][old_column].getColor() == Piece.Color.White
					&& pieces[old_row][old_column].isKing() == false) {
				return true;
			}
		}

		if (pieces[old_row][old_column].isKing() && isSpaceEmpty(new_row, new_column)) {
			return true;
		}

		return false;
	}

	public void checkIfKing(int row, int column) {
		if (pieces[row][column].getColor() == Piece.Color.Black && column == 7) {
			pieces[row][column].setKing(true);
		}

		if (pieces[row][column].getColor() == Piece.Color.White && column == 0) {
			pieces[row][column].setKing(true);

		}
	}

	public void nextTurn() {
		if (player1.getTurn() == true) {
			player1.setTurn(false);
			player2.setTurn(true);
		} else if (player2.getTurn() == true) {
			player2.setTurn(false);
			player1.setTurn(true);
		}
	}

	public Player whosTurn() {
		if (player1.getTurn() == true) {
			return player1;
		} else {
			return player2;
		}
	}

	public boolean checkIfDoubleJump(int row, int column) {

		int move1 = row + 2;
		int move2 = row - 2;
		int move3 = column + 2;
		int move4 = column - 2;

		if (move1 <= 7 && move1 >= 0 && move3 <= 7 && move3 >= 0) {
			if (canMovePiece(move1, move3, row, column)) {
				return true;
			}
		}

		if (move1 <= 7 && move1 >= 0 && move4 <= 7 && move4 >= 0) {
			if (canMovePiece(move1, move4, row, column)) {
				return true;
			}
		}

		if (move2 <= 7 && move2 >= 0 && move3 <= 7 && move3 >= 0) {
			if (canMovePiece(move2, move3, row, column)) {
				return true;
			}
		}

		if (move2 <= 7 && move2 >= 0 && move4 <= 7 && move4 >= 0) {
			if (canMovePiece(move2, move4, row, column)) {
				return true;
			}
		}

		return false;

	}

	public Player getplayer(int playernumber) {
		if (playernumber == 1) {
			return player1;
		} else {
			return player2;
		}
	}

	public Point getBestMove(int row, int column) {

		Point point = null;
		int jump1 = row + 2;
		int jump2 = row - 2;
		int jump3 = column + 2;
		int jump4 = column - 2;
		int move1 = row + 1;
		int move2 = row - 1;
		int move3 = column + 1;
		int move4 = column - 1;
		
		
		if (jump1 <= 7 && jump1 >= 0 && jump3 <= 7 && jump3 >= 0) {
			if (canMovePiece(jump1, jump3, row, column)) {
				if (point == null) {
					point = new Point(jump1, jump3);
				}
			}
		}
	
		
		if (jump1 <= 7 && jump1 >= 0 && jump4 <= 7 && jump4 >= 0) {
			if (canMovePiece(jump1, jump4, row, column)) {
				if (point == null) {
					point = new Point(jump1, jump4);
				} else if (isJumpable(point.getRow(), point.getColumn()) && !isJumpable(jump1, jump4)) {
					point = new Point(jump1, jump4);
				}
			}
		}
		
		if (jump2 <= 7 && jump2 >= 0 && jump3 <= 7 && jump3 >= 0) {
			if (canMovePiece(jump2, jump3, row, column)) {
				if (point == null) {
					point = new Point(jump2, jump3);
				} else if (isJumpable(point.getRow(), point.getColumn()) && !isJumpable(jump2, jump3)) {
					point = new Point(jump2, jump3);
				}
			}
		}
		
		if (jump2 <= 7 && jump2 >= 0 && jump4 <= 7 && jump4 >= 0) {
			if (canMovePiece(jump2, jump4, row, column)) {
				if (point == null) {
					point = new Point(jump2, jump4);
				} else if (isJumpable(point.getRow(), point.getColumn()) && !isJumpable(jump2, jump4)) {
					point = new Point(jump2, jump4);
				}
			}
		}
		
		if(point != null) {
			return point;
		}
		

		if (move1 <= 7 && move1 >= 0 && move3 <= 7 && move3 >= 0) {
			if (canMovePiece(move1, move3, row, column)) {
				if (point == null) {
					point = new Point(move1, move3);
				}
			}
		}

		if (move1 <= 7 && move1 >= 0 && move4 <= 7 && move4 >= 0) {
			if (canMovePiece(move1, move4, row, column)) {
				if (point == null) {
					point = new Point(move1, move4);
				} else if (isJumpable(point.getRow(), point.getColumn()) && !isJumpable(move1, move4)) {
					point = new Point(move1, move4);
				}
			}
		}

		if (move2 <= 7 && move2 >= 0 && move3 <= 7 && move3 >= 0) {
			if (canMovePiece(move2, move3, row, column)) {
				if (point == null) {
					point = new Point(move2, move3);
				} else if (isJumpable(point.getRow(), point.getColumn()) && !isJumpable(move2, move3)) {
					point = new Point(move2, move3);
				}
			}
		}

		if (move2 <= 7 && move2 >= 0 && move4 <= 7 && move4 >= 0) {
			if (canMovePiece(move2, move4, row, column)) {
				if (point == null) {
					point = new Point(move2, move4);
				} else if (isJumpable(point.getRow(), point.getColumn()) && !isJumpable(move2, move4)) {
					point = new Point(move2, move4);
				}
			}
		}

		return point;

	}

	public boolean isJumpable(int row, int column) {

		int move1 = row + 1;
		int move2 = row - 1; // All possible jumps
		int move3 = column + 1;
		int move4 = column - 1;

		if (move1 <= 7 && move1 >= 0 && move3 <= 7 && move3 >= 0) {
			if (!isSpaceEmpty(move1, move3)) {
				if (pieces[move1][move3].getColor() != whosTurn().getColor() && move1 - 2 <= 7 && move1 - 2 >= 0
						&& move3 - 2 <= 7 && move3 - 2 >= 0) {
					if (isSpaceEmpty(move1 - 2, move3 - 2))
						return true;
				}
			}
		}

		if (move1 <= 7 && move1 >= 0 && move4 <= 7 && move4 >= 0) {
			if (!isSpaceEmpty(move1, move4)) {
				if (pieces[move1][move4].getColor() != whosTurn().getColor() && move1 - 2 <= 7 && move1 - 2 >= 0
						&& move4 + 2 <= 7 && move4 + 2 >= 0) {
					if (isSpaceEmpty(move1 - 2, move4 + 2))
						return true;
				}
			}
		}

		if (move2 <= 7 && move2 >= 0 && move3 <= 7 && move3 >= 0) {
			if (!isSpaceEmpty(move2, move3)) {
				if (pieces[move2][move3].getColor() != whosTurn().getColor() && move2 + 2 <= 7 && move2 + 2 >= 0
						&& move3 - 2 <= 7 && move3 - 2 >= 0) {
					if (isSpaceEmpty(move2 + 2, move3 - 2))
						return true;
				}
			}
		}

		if (move2 <= 7 && move2 >= 0 && move4 <= 7 && move4 >= 0) {
			if (!isSpaceEmpty(move2, move4)) {
				if (pieces[move2][move4].getColor() != whosTurn().getColor() && move2 + 2 <= 7 && move2 + 2 >= 0
						&& move4 + 2 <= 7 && move4 + 2 >= 0) {
					if (isSpaceEmpty(move2 + 2, move4 + 2))
						return true;
				}
			}
		}

		return false;
	}

	public void movePieceNoConditions(int new_row, int new_column, int old_row, int old_column) {
		pieces[new_row][new_column] = pieces[old_row][old_column];
		pieces[old_row][old_column] = null;
		if ((new_row - old_row == 2 || new_row - old_row == -2)
				&& (new_column - old_column == 2 || new_column - old_column == -2)) {
			removePiece((new_row + old_row) / 2, (new_column + old_column) / 2);
		}
	}

	public void removePiece(int row, int column) {
		if (pieces[row][column] != null) {
			if (pieces[row][column].getColor() == getplayer(1).getColor()) {
				getplayer(1).removePiece();
				pieces[row][column] = null;
			} else {
				getplayer(2).removePiece();
				pieces[row][column] = null;
			}
		}
	}

	public void addPiece(int row, int column) {
		if (pieces[row][column] != null) {
			if (pieces[row][column].getColor() == getplayer(1).getColor()) {
				getplayer(1).addPiece();
				pieces[row][column] = new Piece(getplayer(1).getColor(), row, column);
			} else {
				getplayer(2).addPiece();
				pieces[row][column] = new Piece(getplayer(2).getColor(), row, column);
			}
		}
	}

	public void undoMove(int new_row, int new_column, int old_row, int old_column) {
		pieces[new_row][new_column] = pieces[old_row][old_column];
		pieces[old_row][old_column] = null;
		if ((new_row - old_row == 2 || new_row - old_row == -2)
				&& (new_column - old_column == 2 || new_column - old_column == -2)) {
			if (pieces[old_row][old_column] != null)
				if (pieces[old_row][old_column].getColor() == getplayer(1).getColor()) {
					pieces[(new_row + old_row) / 2][(new_column + old_column) / 2] = new Piece(getplayer(1).getColor(),
							(new_row + old_row) / 2, (new_column + old_column) / 2);
				} else {
					pieces[(new_row + old_row) / 2][(new_column + old_column) / 2] = new Piece(getplayer(2).getColor(),
							(new_row + old_row) / 2, (new_column + old_column) / 2);
				}
		}

	}

	public Computer getComp() {
		return comp;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public void setPieces(Piece[][] newPieces) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieces[i][j] = null;
				if (newPieces[i][j] != null) {
					if (newPieces[i][j].getColor() == getplayer(1).getColor()) {
						pieces[i][j] = new Piece(getplayer(1).getColor(), i, j);
					} else {
						pieces[i][j] = new Piece(getplayer(2).getColor(), i, j);
					}

				}
			}
		}
	}

}
