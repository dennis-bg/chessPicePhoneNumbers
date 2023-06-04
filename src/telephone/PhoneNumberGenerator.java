package telephone;

import chess.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PhoneNumberGenerator {

    private String[][] phonePad;
    private String initialKey;
    private String fileName;
    private HashSet<String> phoneNumbers;

    public PhoneNumberGenerator(String[][] phonePad, String initialKey, String fileName) {
        this.phonePad = phonePad;
        this.initialKey = initialKey;
        this.fileName = fileName;
        phoneNumbers = new HashSet<>();
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

    private void generatePhoneNumbersForPiece(ChessPiece piece, String phoneNumber, HashMap<String, List<int[]>> possiblePositions){
        if(phoneNumber.length() == PhoneNumber.VALIDLENGTH){
            phoneNumbers.add(phoneNumber);
            return;
        }

        String hash = "" + piece.getPosx() + piece.getPosy();
        List<int[]> nextPossiblePositions;
        if(possiblePositions.containsKey(hash)){
            nextPossiblePositions = possiblePositions.get(hash);
        } else {
            nextPossiblePositions = piece.getNextPotentialPositions(phonePad);
            possiblePositions.put(hash, nextPossiblePositions);
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
            generatePhoneNumbersForPiece(piece, phoneNumber + nextNum, possiblePositions);
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

    public void generatePhoneNumbers() throws NullPointerException, IOException {
        int[] initialCoordinates = getInitialCoordinates();
        if(initialCoordinates == null){
            throw new NullPointerException("There is no such key on this phone pad");
        }

        FileWriter writer = new FileWriter(fileName);
        for(String[] row : phonePad){
            writer.write(Arrays.toString(row) + '\n');
        }
        writer.write("\nStarting key : " + initialKey + "\n\n");
        for(ChessPiece.ChessPieces chessPiece : ChessPiece.ChessPieces.values()){
            phoneNumbers.clear();
            ChessPiece piece = getChessPiece(chessPiece, initialCoordinates[0], initialCoordinates[1]);
            HashMap<String, List<int[]>> possiblePositions = new HashMap<>();
            generatePhoneNumbersForPiece(piece, initialKey, possiblePositions);
            assert piece != null;
            writer.write(piece.getName() + " : " + phoneNumbers.size() + '\n');
        }
        writer.close();
        System.out.println("Answers written to " + fileName);
    }

    public static void main(String[] args) {
        String[][] pad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
//        PhoneNumberGenerator generator = new PhoneNumberGenerator(pad, "2");
//        generator.generatePhoneNumbers();
    }

}
