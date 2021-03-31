package Pokemon;

public class Move {
    String name;
    String type;
    int pp;
    int power;

    //Constructor
    public Move(String name, String type, int power, int pp) {
        this.name = name;
        this.power = power;
        this.pp = pp;
    }

    //Getter
    public String getMoveName(){ return this.name; }
}