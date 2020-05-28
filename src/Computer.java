
public class Computer {

	public static int evaluation(Game game) {

		return (game.getplayer(1).getPiecesLeft() - game.getplayer(2).getPiecesLeft());
	}

	int count = 0;

	public int miniMax(Game game, int depth, boolean isMaximizingPlayer) {

		if (game.getplayer(1).getPiecesLeft() == 0 || game.getplayer(2).getPiecesLeft() == 0) {
			return evaluation(game);
		}

		if (isMaximizingPlayer) {
			int BestValue = -12;
			int possibleValue = 0;

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (game.getPiece(i, j) != null) {
						if (game.getPiece(i, j).getColor() == game.getplayer(1).getColor()) {
							Point bestMove_ForPiece = game.getBestMove(i, j);

							if (bestMove_ForPiece != null) {
								game.movePieceNoConditions(bestMove_ForPiece.getRow(), bestMove_ForPiece.getColumn(), i,j);
								possibleValue = miniMax(game, depth + 1, false);
								//System.out.println(possibleValue);
								if (possibleValue > BestValue) {
									BestValue = possibleValue;
								}
								game.undoMove(i, j, bestMove_ForPiece.getRow(), bestMove_ForPiece.getColumn());
							}

						}
					}
				}
			}
		} else {
			int BestValue = 12;
		int possibleValue = 0;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (game.getPiece(i, j) != null) {
					if (game.getPiece(i, j).getColor() == game.getplayer(2).getColor()) {
						Point bestMove_ForPiece = game.getBestMove(i, j);

						if (bestMove_ForPiece != null) {
							game.movePieceNoConditions(bestMove_ForPiece.getRow(), bestMove_ForPiece.getColumn(), i, j);
							possibleValue = miniMax(game, depth + 1, true);
							if (possibleValue < BestValue) {
								BestValue = possibleValue;
							}
							game.undoMove(i, j, bestMove_ForPiece.getRow(), bestMove_ForPiece.getColumn());
						}
					}
				}
			}
		}

	}
		
		return evaluation(game);
	}

	

	public Move bestMoveforBoard(Game game) {
		
		Move possibleMove;
		Move bestMove = null;
		int bestValue = -12;
		int possibleValue;
		Point possiblePoint;
		int count = 0;
		for(int i = 0;i< 8;i++) {
			for(int j = 0;j<8;j++) {
				if(game.getPiece(i, j) != null) {
					if(game.getPiece(i, j).getColor() == game.getplayer(1).getColor()) {
						if(game.getBestMove(i, j) != null) {
							possiblePoint = game.getBestMove(i, j);
							possibleMove = new Move(possiblePoint,new Point(i,j));
							game.movePieceNoConditions(possiblePoint.getRow(),possiblePoint.getColumn(),i,j);
							possibleValue = miniMax(game,0,false);
							if(possibleValue > bestValue) {
								bestMove = possibleMove;
								bestValue = possibleValue;
								System.out.println("Change");
							}
							game.undoMove(i,j,possiblePoint.getRow(),possiblePoint.getColumn());
						}
					}
				}
			}
		}
		System.out.println(count);
		return bestMove;
	}
	

}
