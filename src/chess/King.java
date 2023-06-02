package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {


    public King(int posx, int posy) {
        super("King", posx, posy);
    }

    @Override
    public ArrayList<int[]> getNextPotentialPositions(String[][] pad) {
        ArrayList potentialPositions = new ArrayList<Integer>();
        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                int posX = this.getPosx() + i;
                int posY = this.getPosy() + j;
                try{
                    if(pad[posY][posX] != null){
                        potentialPositions.add(new int[] {posX, posY});
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    continue;
                }


            }
        }
        return potentialPositions;
    }

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        King king = new King(1, 0);
        System.out.println(pad[king.getPosy()][king.getPosx()]);
        System.out.println();
        List<int[]> list = king.getNextPotentialPositions(pad);
        for(int i=0; i<list.size(); i++){
            System.out.println(pad[list.get(i)[1]][list.get(i)[0]]);
        }
    }
}
