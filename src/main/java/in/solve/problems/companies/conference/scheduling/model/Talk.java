package in.solve.problems.companies.conference.scheduling.model;

import java.time.Duration;
import java.util.Objects;

public class Talk {

    private final String topic;
    private final Duration duration;

    private Talk(final String topic, final Duration duration) {
        this.topic = topic;
        this.duration = duration;
    }

    public static Talk about(final String topic, final Duration duration) {
        return new Talk(topic, duration);
    }

    public String getTopic() {
        return topic;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Talk talk = (Talk) o;
        return Objects.equals(topic, talk.topic) &&
                Objects.equals(duration, talk.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, duration);
    }

    @Override
    public String toString() {
        return "Talk{ " +
                "topic='" + topic + '\'' +
                ", duration=" + duration +
                '}';
    }
}
