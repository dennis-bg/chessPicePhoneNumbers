package telephone;

import chess.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PhoneNumberGenerator {

    private String[][] phonePad;
    private String initialKey;

    public PhoneNumberGenerator(String[][] phonePad, String initialKey) {
        this.phonePad = phonePad;
        this.initialKey = initialKey;
    }

    private int[] getInitialCoordinates(){
        for(int i = 0; i<phonePad.length; i++){
            for(int j=0; j<phonePad[i].length; j++){
                if(phonePad[i][j].equals(initialKey)){
                    return new int[] {j, i};
                }
            }
        }
        return null;
    }

    private int generatePhoneNumbersForPiece(ChessPiece piece, String phoneNumber, HashMap<String, List<int[]>> possiblePositions){
        if(phoneNumber.length() == PhoneNumber.VALIDLENGTH){
            return 1;
        }

        String currNum = "" + phoneNumber.charAt(phoneNumber.length()-1);
        List<int[]> nextPossiblePositions;
        if(possiblePositions.containsKey(currNum)){
            nextPossiblePositions = possiblePositions.get(currNum);
        } else {
            nextPossiblePositions = piece.getNextPotentialPositions(phonePad);
            possiblePositions.put(currNum, nextPossiblePositions);
        }

        if(nextPossiblePositions.size() == 0){
            return 0;
        }

        int sum = 0;

        for(int[] pair : nextPossiblePositions){
            piece.setPosx(pair[0]);
            piece.setPosy(pair[1]);
            String nextNum = phonePad[piece.getPosy()][piece.getPosx()];
            if(Arrays.asList(PhoneNumber.INVALIDCHARACTERS).indexOf(nextNum) != -1){
                continue;
            }
            sum += generatePhoneNumbersForPiece(piece, phoneNumber + nextNum, possiblePositions);
        }
        return sum;
    }

    private ChessPiece getChessPiece(ChessPiece.ChessPieces piece, int initX, int initY){
        switch(piece){
            case KING:
                return new King(initX, initY);
            case QUEEN:
                return new Queen(initX, initY);
            case KNIGHT:
                return new Knight(initX, initY);
            case BISHOP:
                return new Bishop(initX, initY);
            case ROOK:
                return new Rook(initX, initY);
            case PAWN:
                return new Pawn(initX, initY);
        }
        return null;
    }

    public void generatePhoneNumbers(){
        int[] initialCoordinates = getInitialCoordinates();
        assert initialCoordinates != null;
        for(ChessPiece.ChessPieces chessPiece : ChessPiece.ChessPieces.values()){
            ChessPiece piece = getChessPiece(chessPiece, initialCoordinates[0], initialCoordinates[1]);
            HashMap<String, List<int[]>> possiblePositions = new HashMap<>();
            int total = generatePhoneNumbersForPiece(piece, initialKey, possiblePositions);
            assert piece != null;
            System.out.println(piece.getName() + " : " + total);
        }
    }

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        PhoneNumberGenerator generator = new PhoneNumberGenerator(pad, "2");
        generator.generatePhoneNumbers();
    }

}
