package Pokemon;

import java.util.ArrayList; // import the ArrayList class

public class Monster {
    String name;
    String type1;
    String type2;
    int hp;
    ArrayList<Move> moveList = new ArrayList<Move>();

    //Construtors
    public Monster(String name, String type, int hp) {
        this.name = name;
        this.type1 = type;
        this.hp = hp;
    }
    public Monster(String name, String type1, String type2, int hp) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.hp = hp;
    }

    //Getters
    public String getMonsterName() { return this.name; }
    public int getHP() { return this.hp; }
    public ArrayList<Move> getMoveList() { return this.moveList; }
    public Move getMove(int index) { return this.moveList.get(index); }

    //Function to add to ArrayList of Moves
    public void addMove(Move move) {
        this.moveList.add(move);
    }

    //Function to "deal damage" to target Pokemon's health
    public void attack(Move move, Monster target) {
        System.out.println(this.name + " used " + move.name + "!");
        target.hp = target.hp - move.power;
        if (target.hp < 0) {
            target.hp = 0;
        }
        move.pp--;
    }

    //Function to check if Pokemon has HP remaining
    public Boolean isFainted() {
        if (this.hp > 0) {
            return false;
        } else {
            return true;
        }
    }
}