package Pokemon;

import java.util.ArrayList;
import java.lang.Math;

public class Monster {
    String name;
    String type1;
    String type2;
    int level;
    int hp;
    float attack;
    float defense;
    float speed;
    ArrayList<Move> moveList = new ArrayList<Move>();

    //Construtors
    public Monster(String name, String type, int level, int hp, float attack, float defense, float speed) {
        this.name = name;
        this.type1 = type;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }
    public Monster(String name, String type1, String type2, int level, int hp, float attack, float defense, float speed) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    //Getters
    public String getMonsterName() { return this.name; }
    public int getHP() { return this.hp; }
    public float getSpeed() { return this.speed; }
    public ArrayList<Move> getMoveList() { return this.moveList; }
    public Move getMove(int index) { return this.moveList.get(index); }

    //Function to check if Pokemon has HP remaining
    public Boolean isFainted() {
        if (this.hp > 0) {
            return false;
        } else {
            return true;
        }
    }

    //Function to add to ArrayList of Moves
    public void addMove(Move move) {
        this.moveList.add(move);
    }

    //Function to "deal damage" to target Pokemon's health
    public void attack(Move move, Monster user, Monster target) {
        System.out.println(this.name + " used " + move.name + "!");
        float modifier = 1.0f;

        //Determine if attack misses
        float rand = (float) Math.round(Math.random() * 100) / 100;                 //Random float between 0.01 - 1.0
        if (rand >= move.accuracy) {
            System.out.println("The attack missed!");
            return;
        }

        //Determine if attack is a critical hit
        rand = (float) Math.round(Math.random() * 100) / 100;                       //Random float between 0.01 - 1.0
        if (rand <= 1/24) {
            System.out.println("Critical hit!");
            modifier *= 1.5f;
        }

        //Determine random factor
        rand = (float) Math.round(((Math.random() * 0.15) + 0.85f) * 100) / 100;    //Random float between 0.85 - 1.0
        modifier *= rand;

        //Determine same type attack bonus
        if (move.type.equals(user.type1) || move.type.equals(user.type2)) {
            modifier *= 1.5f;
        }

        //Determine type bonus
        float effectiveBonus = typeEffectiveness(move.type, target.type1);
        if (effectiveBonus == 0.5f) {
            System.out.println("It wasn't very effective...");
        } else if (effectiveBonus == 2f) {
            System.out.println("It was super effective!");
        } else if (effectiveBonus == 0f) {
            System.out.println(target.name + " wasn't affected.");
        }
        modifier *= effectiveBonus;

        int damage = Math.round((((2 * user.level) / 5 + 2) * move.power * (user.attack / target.defense) / 50 + 2) * modifier );
        target.hp = target.hp - damage;
        if (target.hp < 0) {
            target.hp = 0;
        }
        move.currPP--;
    }

    //Function to determine type effectiveness of move against target
    public float typeEffectiveness(String moveType, String targetType) {
        switch (moveType) {
            case "Normal":
                if (targetType.equals("Rock") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Ghost")) {
                    return 0f;
                }
                break;

            case "Fire":
                if (targetType.equals("Fire") || targetType.equals("Water") || targetType.equals("Rock") || targetType.equals("Dragon")) {
                    return 0.5f;
                } else if (targetType.equals("Grass") || targetType.equals("Ice") || targetType.equals("Bug") || targetType.equals("Steel")) {
                    return 2f;
                }
                break;

            case "Water":
                if (targetType.equals("Water") || targetType.equals("Grass") || targetType.equals("Dragon")) {
                    return 0.5f;
                } else if (targetType.equals("Fire") || targetType.equals("Ground") || targetType.equals("Rock")) {
                    return 2f;
                }
                break;

            case "Electric":
                if (targetType.equals("Electric") || targetType.equals("Grass") || targetType.equals("Dragon")) {
                    return 0.5f;
                } else if (targetType.equals("Water") || targetType.equals("Flying")) {
                    return 2f;
                } else if (targetType.equals("Ground")) {
                    return 0f;
                }
                break;

            case "Grass":
                if (targetType.equals("Fire") || targetType.equals("Grass") || targetType.equals("Poison") || targetType.equals("Flying") || targetType.equals("Bug") || targetType.equals("Dragon") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Water") || targetType.equals("Ground") || targetType.equals("Rock")) {
                    return 2f;
                }
                break;

            case "Ice":
                if (targetType.equals("Fire") || targetType.equals("Water") || targetType.equals("Ice") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Grass") || targetType.equals("Ground") || targetType.equals("Flying") || targetType.equals("Dragon")) {
                    return 2f;
                }
                break;

            case "Fighting":
                if (targetType.equals("Normal") || targetType.equals("Ice") || targetType.equals("Rock") || targetType.equals("Dark") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Poison") || targetType.equals("Flying") || targetType.equals("Psychic") || targetType.equals("Bug") || targetType.equals("Fairy")) {
                    return 2f;
                }
                break;

            case "Poison":
                if (targetType.equals("Poison") || targetType.equals("Ground") || targetType.equals("Rock") || targetType.equals("Ghost")) {
                    return 0.5f;
                } else if (targetType.equals("Grass") || targetType.equals("Fairy")) {
                    return 2f;
                } else if (targetType.equals("Steel")) {
                    return 0f;
                }
                break;

            case "Ground":
                if (targetType.equals("Grass") || targetType.equals("Bug")) {
                    return 0.5f;
                } else if (targetType.equals("Fire") || targetType.equals("Electric") || targetType.equals("Poison") || targetType.equals("Rock") || targetType.equals("Steel")) {
                    return 2f;
                } else if (targetType.equals("Flying")) {
                    return 0f;
                }
                break;

            case "Flying":
                if (targetType.equals("Electric") || targetType.equals("Rock") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Grass") || targetType.equals("Fighting") || targetType.equals("Bug")) {
                    return 2f;
                }
                break;

            case "Psychic":
                if (targetType.equals("Psychic") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Fighting") || targetType.equals("Poison")) {
                    return 2f;
                } else if (targetType.equals("Dark")) {
                    return 0f;
                }
                break;

            case "Bug":
                if (targetType.equals("Fire") || targetType.equals("Fighting") || targetType.equals("Poison") || targetType.equals("Flying") || targetType.equals("Ghost") || targetType.equals("Steel") || targetType.equals("Fairy")) {
                    return 0.5f;
                } else if (targetType.equals("Grass") || targetType.equals("Psychic") || targetType.equals("Dark")) {
                    return 2f;
                }
                break;

            case "Rock":
                if (targetType.equals("Fighting") || targetType.equals("Ground") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Fire") || targetType.equals("Ice") || targetType.equals("Flying") || targetType.equals("Bug")) {
                    return 2f;
                }
                break;

            case "Ghost":
                if (targetType.equals("Dark")) {
                    return 0.5f;
                } else if (targetType.equals("Psychic") || targetType.equals("Ghost")) {
                    return 2f;
                } else if (targetType.equals("Normal")) {
                    return 0f;
                }
                break;

            case "Dragon":
                if (targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Dragon")) {
                    return 2f;
                } else if (targetType.equals("Fairy")) {
                    return 0f;
                }
                break;

            case "Dark":
                if (targetType.equals("Fighting") || targetType.equals("Dark") || targetType.equals("Fairy")) {
                    return 0.5f;
                } else if (targetType.equals("Psychic") || targetType.equals("Ghost")) {
                    return 2f;
                }
                break;

            case "Steel":
                if (targetType.equals("Fire") || targetType.equals("Water") || targetType.equals("Electric") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Ice") || targetType.equals("Rock") || targetType.equals("Fairy")) {
                    return 2f;
                }
                break;

            case "Fairy":
                if (targetType.equals("Fire") || targetType.equals("Poison") || targetType.equals("Steel")) {
                    return 0.5f;
                } else if (targetType.equals("Fighting") || targetType.equals("Dragon") || targetType.equals("Dark")) {
                    return 2f;
                }
                break;
        }
        return 1f;
    }
}