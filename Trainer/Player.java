package Trainer;

import Pokemon.*;
import java.util.Scanner;

public class Player extends Trainer {
    //Player class needs to manually choose an available move from their Pokemon's move list
    public Move chooseMove(Monster currPokemon) {
        //Print out the Pokemon's move options
        System.out.println("What will " + currPokemon.getMonsterName() + " do?");
        for (int i = 0; i < currPokemon.getMoveList().size(); i++) {
            System.out.println("[" + (i+1) + "] " + currPokemon.getMove(i).getMoveName() + " , "
                                + currPokemon.getMove(i).getCurrPP() + "/" + currPokemon.getMove(i).getMaxPP());
            
        }

        //Ensure user input is valid
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            return (currPokemon.getMove((Integer.parseInt(input))-1));
            
        } catch (Exception e) {
            System.out.println("Invalid option. Please enter the number of the move you'd like to select.\n");
            return chooseMove(currPokemon);
        }
    }
}
