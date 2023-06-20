package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private boolean firstMove;

    public Pawn(int posx, int posy) {
        super("Pawn", posx, posy);
        firstMove = true;
    }

    @Override
    public List<int[]> getNextPotentialPositions(String[][] pad) {
        ArrayList<int[]> potentialPositions = new ArrayList<>();
        try{
            if(pad[this.getPosy()+1][this.getPosx()] != null){
                potentialPositions.add(new int[] {this.getPosx(), this.getPosy()+1});
            }
            if(firstMove){
                if(pad[this.getPosy()+2][this.getPosx()] != null){
                    potentialPositions.add(new int[] {this.getPosx(), this.getPosy()+2});
                }
                this.firstMove = false;
            }
        }catch(ArrayIndexOutOfBoundsException ignored){}
        return potentialPositions;
    }
}
