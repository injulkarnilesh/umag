package in.solve.problems.companies.conference.scheduling.interpreter;

import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterContext;
import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterWithLightning;
import in.solve.problems.companies.conference.scheduling.model.Talk;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.function.Supplier;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TalkInterpreterWithLightningTest {

    @Mock private TalkInterpreterContext talkInterpreterContext;
    @Mock private Talk expectedTalk;
    @Captor private ArgumentCaptor<Supplier<Duration>> durationSuppliedCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInterpretWithoutTime() {
        final String desc = "Something lightning";
        when(talkInterpreterContext.splitWithoutNumber(eq(desc), eq("lightning"), any()))
            .thenReturn(expectedTalk);

        final Talk talk = TalkInterpreterWithLightning.createFor(desc)
                .interpret(talkInterpreterContext);

        verify(talkInterpreterContext).splitWithoutNumber(eq(desc), eq("lightning"), durationSuppliedCaptor.capture());
        final Supplier<Duration> durationSupplier = durationSuppliedCaptor.getValue();
        final Duration duration = durationSupplier.get();
        assertThat(duration, is(Duration.ofMinutes(5)));
        assertThat(talk, is(expectedTalk));
    }
}
