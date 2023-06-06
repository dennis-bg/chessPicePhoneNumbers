package telephone;

import chess.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PhoneNumberGenerator {

    private String[][] phonePad;
    HashSet<String> phoneNumbers;
    HashMap<String, List<int[]>> possiblePositionsMap;

    public PhoneNumberGenerator(String[][] phonePad) {
        this.phonePad = phonePad;
        phoneNumbers = new HashSet<>();
        possiblePositionsMap = new HashMap<>();
    }

    private void generatePhoneNumbersForPiece(ChessPiece piece, String phoneNumber){
        if(phoneNumber.length() == PhoneNumber.VALIDLENGTH){
            phoneNumbers.add(phoneNumber);
//            System.out.println(phoneNumber);
            return;
        }

        String hash = "" + piece.getPosx() + piece.getPosy();
        List<int[]> nextPossiblePositions;
        if(possiblePositionsMap.containsKey(hash)){
            nextPossiblePositions = possiblePositionsMap.get(hash);
        } else {
            nextPossiblePositions = piece.getNextPotentialPositions(phonePad);
            possiblePositionsMap.put(hash, nextPossiblePositions);
        }

        if(nextPossiblePositions.size() == 0){
            return;
        }

        for(int[] pair : nextPossiblePositions){
            piece.setPosx(pair[0]);
            piece.setPosy(pair[1]);
            String nextNum = phonePad[piece.getPosy()][piece.getPosx()];
            if(Arrays.asList(PhoneNumber.INVALIDCHARACTERS).indexOf(nextNum) != -1){
                continue;
            }
            generatePhoneNumbersForPiece(piece, phoneNumber + nextNum);
        }
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

    public void generatePhoneNumbers() throws NullPointerException {
        for(ChessPiece.ChessPieces chessPiece : ChessPiece.ChessPieces.values()){
            phoneNumbers.clear();
            possiblePositionsMap.clear();
            ChessPiece piece = getChessPiece(chessPiece, 0, 0);
            for(int i=0; i<phonePad.length; i++) {
                for (int j = 0; j < phonePad[i].length; j++) {
                    String start = phonePad[i][j];
                    if (Arrays.asList(PhoneNumber.INVALIDCHARACTERS).indexOf(start) != -1 || Arrays.asList(PhoneNumber.INVALIDSTARTINGNUMBER).indexOf(start) != -1) {
                        continue;
                    }
                    assert piece != null;
                    piece.setPosx(i);
                    piece.setPosy(j);
                    generatePhoneNumbersForPiece(piece, start);

                }
            }
            assert piece != null;
            System.out.println(piece.getName() + " : " + phoneNumbers.size());
        }
    }

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        PhoneNumberGenerator generator = new PhoneNumberGenerator(pad);
        generator.generatePhoneNumbers();
    }

}
