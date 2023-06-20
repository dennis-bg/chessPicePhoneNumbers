package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {


    public King(int posx, int posy) {
        super("King", posx, posy);
    }

    @Override
    public ArrayList<int[]> getNextPotentialPositions(String[][] pad) {
        ArrayList<int[]> potentialPositions = new ArrayList<>();
        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                if(i == 0 && j == 0) continue;
                int posX = this.getPosx() + i;
                int posY = this.getPosy() + j;
                try{
                    if(pad[posY][posX] != null){
                        potentialPositions.add(new int[] {posX, posY});
                    }
                }catch(ArrayIndexOutOfBoundsException ignored){}


            }
        }
        return potentialPositions;
    }
}
