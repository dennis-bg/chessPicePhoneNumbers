package controllers;

import telephone.PhoneNumber;
import telephone.PhoneNumberGenerator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static String[][] generatePhonePad(){
        boolean confirmed, valid;
        String[][] phonePad;
        do{
            System.out.print("How many rows? : ");
            int rows = Integer.parseInt(sc.nextLine());
            System.out.print("How many columns? : ");
            int columns = Integer.parseInt(sc.nextLine());
            phonePad = new String[rows][columns];
            for(int i=0; i<rows; i++){
                for(int j=0; j<columns; j++){
                    System.out.print("Value for row " + i + " column " + j + " : ");
                    phonePad[i][j] = sc.nextLine();
                }
            }
            System.out.println();
            for(String[] row : phonePad){
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            String[] accept = {"Y", "N"};
            String input;
            do{
                System.out.print("Please confirm the above is your desired layout (Y/N) : ");
                input = sc.nextLine().toUpperCase();
                valid = Arrays.asList(accept).contains(input);
            }while(!valid);
            confirmed = input.equals("Y");
        }while(!confirmed);

        return phonePad;
    }

    private static String[][] getPhonePad() throws Exception {
        String[][] standardPhonePad = new String[][] {{"1", "2", "3"},{"4", "5", "6"},{"7", "8", "9"},{"*", "0", "#"}};
        for(String[] row : standardPhonePad){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        boolean valid, useStandardPad;
        String[] accept = {"Y", "N"};
        do{
            System.out.print("Would you like to use a standard phone pad layout (as seen above)? (Y/N, Q to quit) : ");
            String input = sc.nextLine().toUpperCase();
            if(input.equals("Q")){
                throw new Exception();
            }
            valid = Arrays.asList(accept).contains(input);
            useStandardPad = input.equals("Y");
        }while(!valid);

        return useStandardPad ? standardPhonePad : generatePhonePad();
    }

    private static String getInitialKey(){
        String initialKey;
        boolean valid;
        do{
            System.out.print("What key would you like to start at? : ");
            initialKey = sc.nextLine();
            valid = Arrays.asList(PhoneNumber.INVALIDSTARTINGNUMBER).indexOf(initialKey) == -1;
            if(!valid){
                System.out.println("Invalid starting key. You cannot start with the following keys : " + Arrays.toString(PhoneNumber.INVALIDSTARTINGNUMBER) + ". Please choose another.");
            }
        }while(!valid);

        return initialKey;
    }

    public static void main(String[] args) {

        while(true){
            System.out.println();
            String[][] phonePad;
            try{
                phonePad = getPhonePad();
            }catch(Exception e){
                break;
            }
            String initialKey = getInitialKey();

            PhoneNumberGenerator generator = new PhoneNumberGenerator(phonePad, initialKey);
            System.out.println();
            try{
                generator.generatePhoneNumbers();
            }catch(NullPointerException e){
                System.out.println(e.getMessage());
            }
            System.out.println();
            System.out.println("=======================================================================");
        }



    }
}
