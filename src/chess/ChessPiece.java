package chess;

import java.util.List;

public abstract class ChessPiece {

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

    public abstract List<int[]> getNextPotentialPositions(String[][] pad);
}
