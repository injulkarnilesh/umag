package in.solve.problems.companies.conference.scheduling.model;

import java.time.LocalTime;

public class EventTime {

    private final LocalTime from;
    private final LocalTime to;

    private EventTime(final LocalTime from, final LocalTime to) {
        this.from = from;
        this.to = to;
    }

    public static EventTime between(final LocalTime from, final LocalTime to) {
        return new EventTime(from, to);
    }

    public LocalTime getFrom() {
        return from;
    }

    public LocalTime getTo() {
        return to;
    }
}
