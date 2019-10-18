package in.solve.problems.companies.conference.scheduling.model;

import java.time.LocalTime;

public class ScheduledTalk {

    private final String topic;
    private final EventTime time;

    private ScheduledTalk(final String topic, final LocalTime from, final LocalTime to) {
        this.topic = topic;
        this.time = EventTime.between(from, to);
    }

    public static ScheduledTalk about(final String topic, final LocalTime from, final LocalTime to) {
        return new ScheduledTalk(topic, from, to);
    }

    public String getTopic() {
        return topic;
    }

    public LocalTime getFrom() {
        return time.getFrom();
    }

    public LocalTime getTo() {
        return time.getTo();
    }

    @Override
    public String toString() {
        return time.getFrom() + " " + topic;
    }
}
