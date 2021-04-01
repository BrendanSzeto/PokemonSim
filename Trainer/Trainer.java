package Trainer;

import Pokemon.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

abstract class Trainer {
    String name;
    ArrayList<Monster> party = new ArrayList<Monster>();
    public abstract Move chooseMove(Monster currPokemon);                       //How the Trainer chooses on what move to use will depend
                                                                                //if they are the user (Player) or computer (CPU).
                                                                                //Therefore, make the function abstract and define it
                                                                                //in each class

    //Setter & Getters
    public void setTrainerName(String name) { this.name = name; }
    public String getTrainerName() { return this.name; }
    public Monster getMonster(int index) { return this.party.get(index); }

    //Function to add to ArrayList of Monsters
    public void addMonster(Monster monster) {
        this.party.add(monster);
    }

    //Function to use a Pokemon's Move on another Pokemon
    public void useMove(Move move, Monster attacker, Monster defender) {
        attacker.attack(move, attacker, defender);
    }

    //Function to take a turn in a battle
    public void turn(Trainer trainer, Monster trainerMonster, Monster opponentMonster) {
        //Get move from the Player, use it, print the new HP of the target Pokemon, and then check if it fainted
        Move move = trainer.chooseMove(trainerMonster);
        trainer.useMove(move, trainerMonster, opponentMonster);
        System.out.println("Enemy " + opponentMonster.getMonsterName() + " is at " + opponentMonster.getHP() + ".\n");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Interruption error");
            System.exit(0);
        }
    }
}