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

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        Rook rook = new Rook(1,2);
        System.out.println(pad[rook.getPosy()][rook.getPosx()]);
        System.out.println();
        List<int[]> list = rook.getNextPotentialPositions(pad);
        for (int[] pair : list) {
            System.out.println(pad[pair[1]][pair[0]]);
        }
    }
}
