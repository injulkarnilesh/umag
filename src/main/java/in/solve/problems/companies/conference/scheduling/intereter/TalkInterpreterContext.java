package in.solve.problems.companies.conference.scheduling.intereter;

import in.solve.problems.companies.conference.scheduling.model.Talk;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

public class TalkInterpreterContext {

    private TalkInterpreterContext() {

    }

    public static TalkInterpreterContext createNew() {
        return new TalkInterpreterContext();
    }

    public Talk splitWithoutNumber(final String description, final String timeUnit, final Supplier<Duration> durationSupplier) {
        final int lastIndex = description.lastIndexOf(timeUnit);
        final String topic = description.substring(0, lastIndex).trim();
        final Duration duration = durationSupplier.get();
        return Talk.about(topic, duration);
    }

    public Talk splitWithNumber(final String description, final String timeUnit, final Function<Integer, Duration> durationFunction) {
        final int lastIndex = description.lastIndexOf(timeUnit);
        final String topicWithTime = description.substring(0, lastIndex);
        final String[] words = topicWithTime.split(" ");
        final StringBuilder topic = new StringBuilder(128);
        for (int i = 0; i < words.length - 1; i++) {
            topic.append(words[i]);
            if (i != words.length - 2) {
                topic.append(" ");
            }
        }
        final String minutes = words[words.length - 1];
        final int time = Integer.parseInt(minutes);
        return Talk.about(topic.toString(), durationFunction.apply(time));
    }

}
