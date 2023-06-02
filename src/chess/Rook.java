package chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {

    public Rook(int posx, int posy) {
        super("Rook", posx, posy);
    }

    @Override
    public List<int[]> getNextPotentialPositions(String[][] pad) {
        ArrayList<int[]> potentialPositions = new ArrayList<>();

        int x = 0;
        int y = this.getPosy();
        try{
            while(pad[y][x] != null){
                potentialPositions.add(new int[] {x, y});
                x++;
            }
        }catch(ArrayIndexOutOfBoundsException ignored){}

        x = this.getPosx();
        y = 0;
        try{
            while(pad[y][x] != null){
                if(y != this.getPosy()){
                    potentialPositions.add(new int[] {x, y});
                }
                y++;
            }
        }catch(ArrayIndexOutOfBoundsException ignored){}

        return potentialPositions;
    }

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        Rook rook = new Rook(1,1);
        System.out.println(pad[rook.getPosy()][rook.getPosx()]);
        System.out.println();
        List<int[]> list = rook.getNextPotentialPositions(pad);
        for (int[] pair : list) {
            System.out.println(pad[pair[1]][pair[0]]);
        }
    }
}
