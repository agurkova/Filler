public class Hex {

    private Player team;
    private int colour;

    public Hex(int colour) {
        this.colour = colour;
    }

    public Player getTeam() {
        return team;
    }

    public void setTeam(Player team) {
        this.team = team;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }


}
