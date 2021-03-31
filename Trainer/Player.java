package Trainer;

import java.util.Scanner;
import Pokemon.*;

public class Player extends Trainer {
    //Player class needs to manually choose an available move from their Pokemon's move list
    public Move chooseMove(Monster currPokemon) {
        //Print out the Pokemon's move options
        System.out.println("What will " + currPokemon.getMonsterName() + " do?");
        for (int i = 0; i < currPokemon.getMoveList().size(); i++) {
            System.out.println("[" + (i+1) + "] " + currPokemon.getMove(i).getMoveName());
        }
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        return (currPokemon.getMove((Integer.parseInt(input))-1));
    }
}
