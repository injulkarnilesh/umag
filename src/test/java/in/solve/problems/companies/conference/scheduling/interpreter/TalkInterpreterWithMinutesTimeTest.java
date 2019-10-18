package in.solve.problems.companies.conference.scheduling.interpreter;

import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterContext;
import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterWithMinutesTime;
import in.solve.problems.companies.conference.scheduling.model.Talk;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.function.Function;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TalkInterpreterWithMinutesTimeTest {

    @Mock private TalkInterpreterContext talkInterpreterContext;
    @Mock private Talk expectedTalk;
    @Captor private ArgumentCaptor<Function<Integer, Duration>> durationFunctionCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInterpretWithoutTime() {
        final String desc = "Something 10min";
        when(talkInterpreterContext.splitWithNumber(eq(desc), eq("min"), any()))
            .thenReturn(expectedTalk);

        final Talk talk = TalkInterpreterWithMinutesTime.createFor(desc)
                .interpret(talkInterpreterContext);

        verify(talkInterpreterContext).splitWithNumber(eq(desc), eq("min"), durationFunctionCaptor.capture());
        final Function<Integer, Duration> function = durationFunctionCaptor.getValue();
        final Duration duration = function.apply(10);
        assertThat(duration, is(Duration.ofMinutes(10)));
        assertThat(talk, is(expectedTalk));
    }
}
