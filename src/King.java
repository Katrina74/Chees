public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }


    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (line == toLine && column == toColumn) {
            return false;
        }


        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) || !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }


        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }


        int diffLine = Math.abs(toLine - line);
        int diffColumn = Math.abs(toColumn - column);
        return (diffLine <= 1 && diffColumn <= 1);
    }




    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {

        int direction = this.getColor().equals("White") ? -1 : 1;
        int[] pawnMoves = { -1, 1 };
        for (int move : pawnMoves) {
            int attackLine = line + direction;
            int attackColumn1 = column + move;
            int attackColumn2 = column - move;

            if (chessBoard.checkPos(attackLine) && chessBoard.checkPos(attackColumn1)) {
                ChessPiece attackingPiece = chessBoard.board[attackLine][attackColumn1];
                if (attackingPiece != null && attackingPiece instanceof Pawn && !attackingPiece.getColor().equals(this.getColor())) {
                    return true;
                }
            }

            if (chessBoard.checkPos(attackLine) && chessBoard.checkPos(attackColumn2)) {
                ChessPiece attackingPiece = chessBoard.board[attackLine][attackColumn2];
                if (attackingPiece != null && attackingPiece instanceof Pawn && !attackingPiece.getColor().equals(this.getColor())) {
                    return true;
                }
            }
        }


        int[] directions = { -1, 1 };
        for (int dir : directions) {

            int attackLine = line;
            int attackColumn = column + dir;
            while (chessBoard.checkPos(attackColumn)) {
                ChessPiece attackingPiece = chessBoard.board[attackLine][attackColumn];
                if (attackingPiece != null && (attackingPiece instanceof Rook || attackingPiece instanceof Queen) && !attackingPiece.getColor().equals(this.getColor())) {
                    return true;
                }
                if (attackingPiece != null) {
                    break;
                }
                attackColumn += dir;
            }


            int attackLine2 = line + dir;
            int attackColumn2 = column;
            while (chessBoard.checkPos(attackLine2)) {
                ChessPiece attackingPiece = chessBoard.board[attackLine2][attackColumn2];
                if (attackingPiece != null && (attackingPiece instanceof Rook || attackingPiece instanceof Queen) && !attackingPiece.getColor().equals(this.getColor())) {
                    return true;
                }
                if (attackingPiece != null) {
                    break;
                }
                attackLine2 += dir;
            }


            int attackLine3 = line + dir;
            int attackColumn3 = column + dir;
            while (chessBoard.checkPos(attackLine3) && chessBoard.checkPos(attackColumn3)) {
                ChessPiece attackingPiece = chessBoard.board[attackLine3][attackColumn3];
                if (attackingPiece != null && (attackingPiece instanceof Bishop || attackingPiece instanceof Queen) && !attackingPiece.getColor().equals(this.getColor())) {
                    return true;
                }
                if (attackingPiece != null) {
                    break;
                }
                attackLine3 += dir;
                attackColumn3 += dir;
            }
            int attackLine4 = line + dir;
            int attackColumn4 = column - dir;
            while (chessBoard.checkPos(attackLine4) && chessBoard.checkPos(attackColumn4)) {
                ChessPiece attackingPiece = chessBoard.board[attackLine4][attackColumn4];
                if (attackingPiece != null && (attackingPiece instanceof Bishop || attackingPiece instanceof Queen) && !attackingPiece.getColor().equals(this.getColor())) {
                    return true;
                }
                if (attackingPiece != null) {
                    break;
                }
                attackLine4 += dir;
                attackColumn4 -= dir;
            }
        }


        int[] knightMoves = { -2, -1, 1, 2 };
        for (int move1 : knightMoves) {
            for (int move2 : knightMoves) {
                if (Math.abs(move1) != Math.abs(move2)) {
                    int attackLine = line + move1;
                    int attackColumn = column + move2;
                    if (chessBoard.checkPos(attackLine) && chessBoard.checkPos(attackColumn)) {
                        ChessPiece attackingPiece = chessBoard.board[attackLine][attackColumn];
                        if (attackingPiece != null && attackingPiece instanceof Horse && !attackingPiece.getColor().equals(this.getColor())) {
                            return true;
                        }
                    }
                }
            }
        }


        int[] kingMoves = { -1, 0, 1 };
        for (int move1 : kingMoves) {
            for (int move2 : kingMoves) {
                if (move1 != 0 || move2 != 0) {
                    int attackLine = line + move1;
                    int attackColumn = column + move2;
                    if (chessBoard.checkPos(attackLine) && chessBoard.checkPos(attackColumn)) {
                        ChessPiece attackingPiece = chessBoard.board[attackLine][attackColumn];
                        if (attackingPiece != null && attackingPiece instanceof King && !attackingPiece.getColor().equals(this.getColor())) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    @Override
    public String getSymbol() {
        return "K";
    }
}
