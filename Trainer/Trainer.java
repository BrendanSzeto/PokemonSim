package Trainer;

import Pokemon.*;
import java.util.ArrayList; // import the ArrayList class

abstract class Trainer {
    String name;
    ArrayList<Monster> party = new ArrayList<Monster>();
    public abstract Move chooseMove(Monster currPokemon);                       //How the Trainer chooses on what move to use will depend
                                                                                //if they are the user (Player) or computer (Opponent).
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
        attacker.attack(move, defender);
    }
}