package chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(int posx, int posy) {
        super("Queen", posx, posy);
    }

    @Override
    public List<int[]> getNextPotentialPositions(String[][] pad) {
        ArrayList<int[]> potentialPositions = new ArrayList<>(super.getHorizontalVerticalPositions(pad));
        potentialPositions.addAll(super.getDiagonalPositions((pad)));
        return potentialPositions;
    }
}
