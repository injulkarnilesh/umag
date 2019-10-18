package in.solve.problems.companies.conference.scheduling.model;

public abstract class Event {

    protected final String name;
    protected final EventTime time;

    protected Event(final String name, EventTime time) {
        this.name = name;
        this.time = time;
    }

    public EventTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public abstract boolean hasParts();
}
