package in.solve.problems.companies.conference.scheduling.model;

import java.time.LocalTime;

public final class Break extends Event {

    private Break(final String name, final LocalTime from, final LocalTime to) {
        super(name, EventTime.between(from, to));
    }

    public static Break of(final String name, final LocalTime from, final LocalTime to) {
        return new Break(name, from, to);
    }

    @Override
    public boolean hasParts() {
        return false;
    }

}
