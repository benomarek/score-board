package teams;

import exceptions.TeamException;

import java.util.Objects;

public abstract class Team {

    private final String name;

    protected Team(String name) {

        if (name == null || "".equals(name)) {
            throw new TeamException("Team name is required.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
