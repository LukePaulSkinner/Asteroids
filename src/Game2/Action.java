package Game2;

//Used to send info to ship class
public class Action {
    public int thrust; // 0 = off, 1 = on
    public int turn; // -1 = left turn, 0 = no turn, 1 = right turn
    public boolean shoot;
    public boolean land;
}