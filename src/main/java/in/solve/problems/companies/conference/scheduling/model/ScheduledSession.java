package in.solve.problems.companies.conference.scheduling.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduledSession extends Event {

    private final List<ScheduledTalk> talks = new ArrayList<>();

    public ScheduledSession(final String name, final LocalTime from, final LocalTime to) {
        super(name, EventTime.between(from, to));
    }

    public static ScheduledSession between(final String name, final LocalTime from, final LocalTime to) {
        return new ScheduledSession(name, from, to);
    }

    public LocalTime getFrom() {
        return time.getFrom();
    }

    public LocalTime getTo() {
        return time.getTo();
    }

    public List<ScheduledTalk> getTalks() {
        return talks;
    }

    public void add(final ScheduledTalk scheduledTalk) {
        talks.add(scheduledTalk);
    }

    @Override
    public boolean hasParts() {
        return this.talks.size() > 0;
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder(512);
        str.append(this.time.getFrom() + " " + this.name).append("\n");
        for (ScheduledTalk talk: talks) {
            str.append(talk.toString())
                    .append("\n");
        }
        return str.toString();
    }

}
