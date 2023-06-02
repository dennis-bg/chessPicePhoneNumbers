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

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        Pawn pawn = new Pawn(1,1);
        System.out.println(pad[pawn.getPosy()][pawn.getPosx()]);
        System.out.println();
        List<int[]> list = pawn.getNextPotentialPositions(pad);
        for (int[] pair : list) {
            System.out.println(pad[pair[1]][pair[0]]);
        }

        System.out.println();
        System.out.println(pad[pawn.getPosy()][pawn.getPosx()]);
        System.out.println();
        List<int[]> newList = pawn.getNextPotentialPositions(pad);
        for (int[] pair : newList) {
            System.out.println(pad[pair[1]][pair[0]]);
        }
    }
}
