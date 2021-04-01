package Pokemon;

public class Move {
    String name;
    String type;
    int power;
    float accuracy;
    int currPP;
    int maxPP;
    

    //Constructor
    public Move(String name, String type, int power, float accuracy, int PP) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.currPP = PP;
        this.maxPP = PP;
    }

    //Getter
    public String getMoveName() { return this.name; }
    public int getPower() { return this.power; }
    public int getCurrPP() { return this.currPP; }
    public int getMaxPP() { return this.maxPP; }
}