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

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        Queen queen = new Queen(1,1);
        System.out.println(pad[queen.getPosy()][queen.getPosx()]);
        System.out.println();
        List<int[]> list = queen.getNextPotentialPositions(pad);
        for (int[] pair : list) {
            System.out.println(pad[pair[1]][pair[0]]);
        }
    }
}
