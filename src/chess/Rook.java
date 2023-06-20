package chess;

import java.util.List;

public class Rook extends ChessPiece {

    public Rook(int posx, int posy) {
        super("Rook", posx, posy);
    }

    @Override
    public List<int[]> getNextPotentialPositions(String[][] pad) {
        return super.getHorizontalVerticalPositions(pad);
    }
}
