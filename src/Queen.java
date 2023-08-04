public class Queen extends ChessPiece {
    // Конструктор
    public Queen(String color) {
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


        return (line == toLine || column == toColumn || Math.abs(toLine - line) == Math.abs(toColumn - column))
                && !isPathBlocked(chessBoard, line, column, toLine, toColumn);
    }


    private boolean isPathBlocked(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int directionLine = Integer.compare(toLine, line);
        int directionColumn = Integer.compare(toColumn, column);
        int currentLine = line + directionLine;
        int currentColumn = column + directionColumn;

        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return true;
            }
            currentLine += directionLine;
            currentColumn += directionColumn;
        }
        return false;
    }


    @Override
    public String getSymbol() {
        return "Q";
    }
}
