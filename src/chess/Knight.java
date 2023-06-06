package chess;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

    public Knight(int posx, int posy) {
        super("Knight", posx, posy);
    }

    @Override
    public List<int[]> getNextPotentialPositions(String[][] pad) {
        ArrayList<int[]> potentialPositions = new ArrayList<>();
        int x = this.getPosx();
        int y= this.getPosy();

        int[][] allDirections = new int[][]{{x-1, y-2}, {x+1, y-2}, {x-1, y+2}, {x+1, y+2}, {x-2, y+1}, {x-2, y-1}, {x+2, y+1}, {x+2, y-1}};

        for(int[] pair : allDirections){
            try{
                if(pad[pair[1]][pair[0]] != null){
                    potentialPositions.add(pair);
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
        }

        return potentialPositions;
    }

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        Knight knight = new Knight(2,2);
        System.out.println(pad[knight.getPosy()][knight.getPosx()]);
        System.out.println();
        List<int[]> list = knight.getNextPotentialPositions(pad);
        for (int[] pair : list) {
            System.out.println(pad[pair[1]][pair[0]]);
        }
    }
}
