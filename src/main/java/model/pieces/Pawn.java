package model.pieces;

import model.BoardState;
import model.BoardState.Player;
import model.ChessPiecePosition;
import model.PiecePosition;

/**
 * Class for a pawn that decides
 * if a move is valid.
 *
 * @author Matt Stetter
 */
public class Pawn extends AbstractChessPiece {

  // Passes type and player owner to abstract constructor.
  public Pawn(Player player) {
    super(PieceType.PAWN, player);
  }

  // Returns true if the pawn move is valid.
  @Override
  public boolean isValidMove(PiecePosition p1, PiecePosition p2, BoardState board) {
    if (p1.equals(p2) || board.getPieceAt(p1) == null) {
      return false;
    }
    int startRow = p1.getRow();
    int startCol = p1.getColumn();
    int endRow = p2.getRow();
    int endCol = p2.getColumn();
    int playerIter = this.getPlayer() == Player.ONE ? 1 : -1;
    int pawnStart = this.getPlayer() == Player.ONE ? 1 : 6;
    Piece takePiece = board.getPieceAt(p2);
    if (startCol == endCol) {
      if (startRow == pawnStart) {
        if (takePiece == null) {
          if (endRow == startRow + playerIter) {
            return true;
          } else if (endRow == startRow + (2 * playerIter)) {
            return board.getPieceAt(new ChessPiecePosition(startRow + playerIter, startCol)) == null;
          } else {
            return false;
          }
        } else {
          return false;
        }
      } else {
        return endRow == startRow + playerIter;
      }
    } else if (endCol == startCol + 1
              || endCol == startCol - 1) {
      return (takePiece != null && endRow == startRow + playerIter
              && takePiece.getType() != null
              && takePiece.getPlayer() != this.getPlayer());
    } else {
      return false;
    }
  }
}
