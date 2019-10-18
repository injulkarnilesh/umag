package in.solve.problems.companies.conference.scheduling.intereter;

public interface TalkInterpreterFactory {

    TalkInterpreter get(String description);

    class InterpreterNotFoundException extends RuntimeException {
        public InterpreterNotFoundException(final String message) {
            super(message);
        }
    }

}
