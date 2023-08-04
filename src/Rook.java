public class Rook extends ChessPiece {
    // Конструктор
    public Rook(String color) {
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


        return (line == toLine || column == toColumn) && !isPathBlocked(chessBoard, line, column, toLine, toColumn);
    }


    private boolean isPathBlocked(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine) {
            int direction = Integer.compare(toColumn, column);
            for (int i = column + direction; i != toColumn; i += direction) {
                if (chessBoard.board[line][i] != null) {
                    return true;
                }
            }
        } else {
            int direction = Integer.compare(toLine, line);
            for (int i = line + direction; i != toLine; i += direction) {
                if (chessBoard.board[i][column] != null) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String getSymbol() {
        return "R";
    }
}
