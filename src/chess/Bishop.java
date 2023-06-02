package chess;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(int posx, int posy) {
        super("Bishop", posx, posy);
    }

    @Override
    public List<int[]> getNextPotentialPositions(String[][] pad) {
        ArrayList<int[]> potentialPositions = new ArrayList<>();

        int x = this.getPosx();
        int y = this.getPosy();
        try{
            while(pad[y][x] != null){
                potentialPositions.add(new int[] {x, y});
                x--;
                y--;
            }
        }catch(ArrayIndexOutOfBoundsException ignored){}

        x = this.getPosx() + 1;
        y = this.getPosy() + 1;
        try{
            while(pad[y][x] != null){
                potentialPositions.add(new int[] {x, y});
                x++;
                y++;
            }
        }catch(ArrayIndexOutOfBoundsException ignored){}

        x = this.getPosx() - 1;
        y = this.getPosy() + 1;
        try{
            while(pad[y][x] != null){
                potentialPositions.add(new int[] {x, y});
                x--;
                y++;
            }
        }catch(ArrayIndexOutOfBoundsException ignored){}

        x = this.getPosx() + 1;
        y = this.getPosy() - 1;
        try{
            while(pad[y][x] != null){
                potentialPositions.add(new int[] {x, y});
                x++;
                y--;
            }
        }catch(ArrayIndexOutOfBoundsException ignored){}

        return potentialPositions;
    }

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        Bishop bishop = new Bishop(0,3);
        System.out.println(pad[bishop.getPosy()][bishop.getPosx()]);
        System.out.println();
        List<int[]> list = bishop.getNextPotentialPositions(pad);
        for (int[] pair : list) {
            System.out.println(pad[pair[1]][pair[0]]);
        }
    }
}
