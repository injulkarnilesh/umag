package in.solve.problems.companies.conference.scheduling.interpreter;

import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterContext;
import in.solve.problems.companies.conference.scheduling.model.Talk;
import org.junit.Test;

import java.time.Duration;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TalkInterpreterContextTest {

    @Test
    public void shouldConvertToTalkWithoutUnit() {
        final String desc = "Topic lightning";

        final Talk talk = TalkInterpreterContext.createNew()
                .splitWithoutNumber(desc, "lightning", () -> Duration.ofMinutes(5));

        assertThat(talk.getTopic(), is("Topic"));
        assertThat(talk.getDuration(), is(Duration.ofMinutes(5)));
    }

    @Test
    public void shouldConvertToTalkWithUnit() {
        final String desc = "Topic 50min";

        final Talk talk = TalkInterpreterContext.createNew()
                .splitWithNumber(desc, "min", (min) -> Duration.ofMinutes(min));

        assertThat(talk.getTopic(), is("Topic"));
        assertThat(talk.getDuration(), is(Duration.ofMinutes(50)));
    }
}
