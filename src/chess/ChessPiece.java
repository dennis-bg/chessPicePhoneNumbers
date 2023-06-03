package chess;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {

    public enum ChessPieces {KING, QUEEN, KNIGHT, ROOK, BISHOP, PAWN}

    private String name;
    private int posx;
    private int posy;

    public ChessPiece(String name, int posx, int posy) {
        this.name = name;
        this.posx = posx;
        this.posy = posy;
    }

    public String getName() {
        return name;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    protected List<int[]> getDiagonalPositions(String[][] pad){
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

    protected List<int[]> getHorizontalVerticalPositions(String[][] pad){
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

    public abstract List<int[]> getNextPotentialPositions(String[][] pad);
}
