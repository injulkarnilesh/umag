package in.solve.problems.companies.conference.scheduling.intereter;

import in.solve.problems.companies.conference.scheduling.model.Talk;

public interface TalkInterpreter {
    Talk interpret(TalkInterpreterContext context);
}
