package in.solve.problems.companies.conference.scheduling.intereter;

public final class TalkInterpreterFactoryImpl implements TalkInterpreterFactory {

    public static TalkInterpreterFactoryImpl INSTANCE = new TalkInterpreterFactoryImpl();

    private TalkInterpreterFactoryImpl() {
    }

    @Override
    public TalkInterpreter get(final String description) {
        final String lowerCaseDesc = description.toLowerCase();
        if (lowerCaseDesc.endsWith("lightning")) {
            return TalkInterpreterWithLightning.createFor(description);
        }

        if (lowerCaseDesc.endsWith("min")) {
            return TalkInterpreterWithMinutesTime.createFor(description);
        }

        throw new InterpreterNotFoundException("Interpreter not found for " + description);
    }
}
