package scores;

public class Score {

    private int home;
    private int away;

    public Score() {
        this.home = 0;
        this.away = 0;
    }

    public int getTotal(){
        return home + away;
    }

    public Score(int home, int away) {
        this.home = home;
        this.away = away;
    }

    public Score incrementHome() {
        this.home = this.home + 1;
        return this;
    }

    public Score incrementAway() {
        this.away = this.away + 1;
        return this;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", home, away);
    }
}
