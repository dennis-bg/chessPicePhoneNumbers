package chess;

import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(int posx, int posy) {
        super("Bishop", posx, posy);
    }

    @Override
    public List<int[]> getNextPotentialPositions(String[][] pad) {
        return super.getDiagonalPositions(pad);
    }

}
