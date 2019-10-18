package in.solve.problems.companies.conference.scheduling.intereter;

import in.solve.problems.companies.conference.scheduling.model.Talk;

import java.time.Duration;

public class TalkInterpreterWithMinutesTime implements TalkInterpreter {

    private final String talkDescription;

    private TalkInterpreterWithMinutesTime(final String talkDescription) {
        this.talkDescription = talkDescription;
    }

    public static TalkInterpreterWithMinutesTime createFor(final String talkDescription) {
        return new TalkInterpreterWithMinutesTime(talkDescription);
    }

    @Override
    public Talk interpret(final TalkInterpreterContext context) {
        return context.splitWithNumber(talkDescription, "min", (min) -> Duration.ofMinutes(min));
    }
}
