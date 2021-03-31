package Trainer;

import Pokemon.*;

public class Opponent extends Trainer {
    //Opponent will randomly choose an available move from their current Pokemon's move list
    public Move chooseMove(Monster currPokemon) {
        int max = currPokemon.getMoveList().size();
        int randInt = (int)(Math.random() * max);
        return (currPokemon.getMove(randInt));
    }
    
}
