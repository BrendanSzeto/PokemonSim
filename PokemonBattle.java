import Pokemon.*;
import Trainer.*;
import java.util.Scanner;

public class PokemonBattle {
    public static void main(String[] args) throws InterruptedException {
        //Create player and cpu
        Player player = new Player();
        CPU cpu = new CPU();

        tutorial(player, cpu);
    }

    //Function runs tutorial of a Pokemon battle
    private static void tutorial(Player player, CPU cpu) {
        //Create Monster 'Bulbasaur'
        Monster bulbasaur = new Monster("Bulbasaur", "Grass", "Poison", 5, 21, 12, 13, 11);
        bulbasaur.addMove(new Move("Tackle", "Normal", 40, 1f, 35));
        bulbasaur.addMove(new Move("Vine Whip", "Grass", 45, 1f, 25));

        //Create Monster 'Charmander'
        Monster charmander = new Monster("Charmander", "Fire", 5, 20, 12, 11, 13);
        charmander.addMove(new Move("Scratch", "Normal", 40, 1f, 35));
        charmander.addMove(new Move("Ember", "Fire", 40, 1f, 25));

        //Create Monster 'Squirtle'
        Monster squirtle = new Monster("Squirtle", "Water", 5, 20, 12, 13, 10);
        squirtle.addMove(new Move("Tackle", "Normal", 40, 1f, 35));
        squirtle.addMove(new Move("Water Gun", "Water", 40, 1f, 25));

        //User input and output
        System.out.println("Welcome to the world of Pokémon! Tell me about yourself: what is your name?");                                              //Player enters their name
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        player.setTrainerName(input);
        System.out.println("\n" + player.getTrainerName() + ", it's time to begin your journey with your closest friend! What was their name again?");  //Player enters their cpu's name
        input = scanner.nextLine();
        cpu.setTrainerName(input);
        System.out.println("\nAh yes, " + cpu.getTrainerName()
                            + ". With that all sorted out, it's time to pick your Pokémon companion. Who will you pick?");                              //Player chooses their starter                        
        System.out.println("[1] Bulbasaur, the Seed Pokémon");
        System.out.println("[2] Charmander, the Lizard Pokémon");
        System.out.println("[3] Squirtle, the Tiny Turtle Pokémon");

        //Ensure user input is valid
        input = scanner.nextLine();
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("Invalid option. Please enter the number of the Pokemon you'd like to select.");
            input = scanner.nextLine();
        }
        
        //Add chosen Pokemon to Player's "team", and opposing Pokemon to CPU's "team"
        if (Integer.parseInt(input) == 1) {
            player.addMonster(bulbasaur);
            cpu.addMonster(charmander);
        } else if (Integer.parseInt(input) == 2) {
            player.addMonster(charmander);
            cpu.addMonster(squirtle);
        } else {
            player.addMonster(squirtle);
            cpu.addMonster(bulbasaur);
        }
        System.out.println("\n" + player.getTrainerName() + " recieved " + player.getMonster(0).getMonsterName() + "!");
        System.out.println("What's this? Rival " + cpu.getTrainerName() + " would like to battle!\n");
        
        //Run battle() until a Monster is fainted
        Boolean isBattling;
        do {
            isBattling = battle(player, cpu);
        } while (isBattling == true);

        //Print fainted Pokemon and the winnner
        if (player.getMonster(0).isFainted()) {
            System.out.println(player.getMonster(0).getMonsterName() + " fainted!\nRival " + cpu.getTrainerName() + " wins...");
        } else {
            System.out.println(cpu.getMonster(0).getMonsterName() + " fainted!\nYou won!");
        }
    }

    //Function simulates 1 turn of the battle between player's Pokemon and CPU's Pokemon
    private static Boolean battle(Player player, CPU cpu) {
        if (player.getMonster(0).getSpeed() > cpu.getMonster(0).getSpeed()) {
            //Player goes first
            player.turn(player, player.getMonster(0), cpu.getMonster(0));
            if (cpu.getMonster(0).isFainted()) { return false; }
            cpu.turn(cpu, cpu.getMonster(0), player.getMonster(0));
            if (player.getMonster(0).isFainted()) { return false; }

        } else {
            //CPU goes first
            cpu.turn(cpu, cpu.getMonster(0), player.getMonster(0));
            if (player.getMonster(0).isFainted()) { return false; }
            player.turn(player, player.getMonster(0), cpu.getMonster(0));
            if (cpu.getMonster(0).isFainted()) { return false; }
        }
        
        //If neither Pokemon is fainted, battle is still ongoing
        return true;
    }
}