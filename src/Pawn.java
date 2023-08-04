public class Pawn extends ChessPiece {

    public Pawn(String color) {
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


        int direction = this.getColor().equals("White") ? 1 : -1;


        if (this.getColor().equals("White")) {

            if (line == 1 && toLine == line + 2 * direction && column == toColumn && chessBoard.board[line + direction][column] == null && targetPiece == null) {
                return true;
            }
            if (toLine == line + direction && column == toColumn && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }

            if (toLine == line + direction && Math.abs(toColumn - column) == 1 && targetPiece != null) {
                return true;
            }
        } else {
            if (line == 6 && toLine == line + 2 * direction && column == toColumn && chessBoard.board[line + direction][column] == null && targetPiece == null) {
                return true;
            }
            if (toLine == line + direction && column == toColumn && chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
            if (toLine == line + direction && Math.abs(toColumn - column) == 1 && targetPiece != null) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String getSymbol() {
        return "P";
    }
}
