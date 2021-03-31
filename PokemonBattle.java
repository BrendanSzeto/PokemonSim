import Pokemon.*;
import Trainer.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PokemonBattle {
    public static void main(String[] args) throws InterruptedException {
        //Create Monster 'Bulbasaur'
        Monster bulbasaur = new Monster("Bulbasaur", "Grass", "Poison", 150);
        bulbasaur.addMove(new Move("Tackle", "Normal", 40, 35));
        bulbasaur.addMove(new Move("Vine Whip", "Grass", 45, 25));

        //Create Monster 'Charmander'
        Monster charmander = new Monster("Charmander", "Fire", 125);
        charmander.addMove(new Move("Scratch", "Normal", 40, 35));
        charmander.addMove(new Move("Ember", "Fire", 40, 25));

        //Create Monster 'Squirtle'
        Monster squirtle = new Monster("Squirtle", "Water", 200);
        squirtle.addMove(new Move("Tackle", "Normal", 40, 35));
        squirtle.addMove(new Move("Water Gun", "Water", 40, 25));

        //Create player and opponent
        Player player = new Player();
        Opponent opponent = new Opponent();

        //User input and output
        System.out.println("Welcome to the world of Pokémon! Tell me about yourself: what is your name?");                                                              //Player enters their name
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        player.setTrainerName(input);
        System.out.println("\n" + player.getTrainerName() + ", it's time to begin your journey with your closest friend! What was their name again?");                  //Player enters their opponent's name
        input = scanner.nextLine();
        opponent.setTrainerName(input);
        System.out.println("\nAh yes, " + opponent.getTrainerName() + ". With that all sorted out, it's time to pick your Pokémon companion. Who will you pick?");      //Player chooses their starter
        System.out.println("[1] Bulbasaur, the Seed Pokémon");
        System.out.println("[2] Charmander, the Lizard Pokémon");
        System.out.println("[3] Squirtle, the Tiny Turtle Pokémon");
        input = scanner.nextLine();
        if (Integer.parseInt(input) == 1) {
            player.addMonster(bulbasaur);
            opponent.addMonster(charmander);
        } else if (Integer.parseInt(input) == 2) {
            player.addMonster(charmander);
            opponent.addMonster(squirtle);
        } else {
            player.addMonster(squirtle);
            opponent.addMonster(bulbasaur);
        }
        System.out.println("\n" + player.getTrainerName() + " recieved " + player.getMonster(0).getMonsterName() + "!");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("What's this? Rival " + opponent.getTrainerName() + " would like to battle!\n");
        
        //Run battle() until a Monster is fainted
        do {
            battle(player, opponent);
        } while ((!(player.getMonster(0).isFainted())) && (!(opponent.getMonster(0).isFainted())));

        //Print fainted Pokemon and the winnner
        if (player.getMonster(0).isFainted()) {
            System.out.println(player.getMonster(0).getMonsterName() + " fainted!\nRival " + opponent.getTrainerName() + " wins...");
        } else {
            System.out.println(opponent.getMonster(0).getMonsterName() + " fainted!\nYou won!");
        }

        scanner.close();
    }

    //Function simulates 1 turn of the battle between player's Pokemon and Opponent's Pokemon
    private static void battle(Player player, Opponent opponent) {
        //Get move from player and use against opponent
        Move move = player.chooseMove(player.getMonster(0));
        player.useMove(move, player.getMonster(0), opponent.getMonster(0));
        move = opponent.chooseMove(opponent.getMonster(0));
        opponent.useMove(move, opponent.getMonster(0), player.getMonster(0));

        //Print current status of Pokemon on the field
        System.out.println("\n" + player.getMonster(0).getMonsterName() + " is at " + player.getMonster(0).getHP() + ".");
        System.out.println("Enemy " + opponent.getMonster(0).getMonsterName() + " is at " + opponent.getMonster(0).getHP() + ".\n");
    }
}
