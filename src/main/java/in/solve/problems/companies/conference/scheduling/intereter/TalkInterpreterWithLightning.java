package in.solve.problems.companies.conference.scheduling.intereter;

import in.solve.problems.companies.conference.scheduling.model.Talk;

import java.time.Duration;

public class TalkInterpreterWithLightning implements TalkInterpreter {

    private final String talkDescription;

    private TalkInterpreterWithLightning(final String talkDescription) {
        this.talkDescription = talkDescription;
    }

    public static TalkInterpreterWithLightning createFor(final String talkDescription) {
        return new TalkInterpreterWithLightning(talkDescription);
    }

    @Override
    public Talk interpret(final TalkInterpreterContext context) {
        return context.splitWithoutNumber(talkDescription, "lightning", () -> Duration.ofMinutes(5));
    }
}
