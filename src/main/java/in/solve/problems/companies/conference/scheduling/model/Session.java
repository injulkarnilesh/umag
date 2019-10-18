package in.solve.problems.companies.conference.scheduling.model;

import java.time.LocalTime;

public class Session extends Event {

    private Session(final String name, final LocalTime from, final LocalTime to) {
        super(name, EventTime.between(from, to));
    }

    public static Session of(final String name, final LocalTime from, final LocalTime to) {
        return new Session(name, from, to);
    }

    @Override
    public boolean hasParts() {
        return true;
    }

    public LocalTime getFrom() {
        return time.getFrom();
    }

    public LocalTime getTo() {
        return time.getTo();
    }
}
