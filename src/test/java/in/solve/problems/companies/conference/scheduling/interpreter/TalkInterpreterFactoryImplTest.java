package in.solve.problems.companies.conference.scheduling.interpreter;

import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreter;
import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterFactory;
import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterFactoryImpl;
import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterWithLightning;
import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterWithMinutesTime;
import org.junit.Assert;
import org.junit.Test;

public class TalkInterpreterFactoryImplTest {

    @Test
    public void shouldFindInterpreterForMinutes() {
        final String desc = "Some 40min";
        final TalkInterpreter interpreter = TalkInterpreterFactoryImpl.INSTANCE.get(desc);

        Assert.assertTrue(interpreter instanceof TalkInterpreterWithMinutesTime);
    }

    @Test
    public void shouldFindInterpreterForLightning() {
        final String desc = "Some lightning";
        final TalkInterpreter interpreter = TalkInterpreterFactoryImpl.INSTANCE.get(desc);

        Assert.assertTrue(interpreter instanceof TalkInterpreterWithLightning);
    }

    @Test(expected = TalkInterpreterFactory.InterpreterNotFoundException.class)
    public void shouldNotFindInterpreterForNonMatchingDescription() {
        final String desc = "Unknown";

        TalkInterpreterFactoryImpl.INSTANCE.get(desc);
    }
}
