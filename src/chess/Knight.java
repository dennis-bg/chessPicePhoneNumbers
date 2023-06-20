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
}
