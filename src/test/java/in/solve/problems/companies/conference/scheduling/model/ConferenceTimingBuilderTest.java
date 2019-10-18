package in.solve.problems.companies.conference.scheduling.model;

import in.solve.problems.companies.conference.scheduling.model.Break;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTiming;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTimingBuilder;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTrack;
import in.solve.problems.companies.conference.scheduling.model.Event;
import in.solve.problems.companies.conference.scheduling.model.Session;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ConferenceTimingBuilderTest {

    @Test
    public void shouldBuildWithEmptyTracks() {
        final ConferenceTiming timing = ConferenceTimingBuilder.createNew()
                .build();

        assertNotNull(timing);
        assertThat(timing.getTracks(), is(empty()));
    }

    @Test
    public void shouldBuildConferenceTimingWithOneTrackWithOneSession() {
        final ConferenceTimingBuilder conferenceTimingBuilder = ConferenceTimingBuilder.createNew();
        conferenceTimingBuilder
                .addNewTrackStartingFrom(LocalTime.parse("10:00"))
                .withSessionFor("Name", Duration.ofMinutes(60));

        final ConferenceTiming conferenceTiming = conferenceTimingBuilder.build();

        assertNotNull(conferenceTiming);
        final List<ConferenceTrack> tracks = conferenceTiming.getTracks();
        assertThat(tracks, hasSize(1));

        final Session session = Session.of("Name", LocalTime.parse("10:00"), LocalTime.parse("11:00"));
        verifySessionInTrack(tracks.get(0), session);
    }

    @Test
    public void shouldBuildConferenceTimingWithOneTrackWithMultipleSessions() {
        final ConferenceTimingBuilder conferenceTimingBuilder = ConferenceTimingBuilder.createNew();
        final ConferenceTiming conferenceTiming = conferenceTimingBuilder
                .addNewTrackStartingFrom(LocalTime.parse("10:00"))
                .withSessionFor("Name", Duration.ofMinutes(60))
                .withSessionFor("SomethingElse", Duration.ofMinutes(30))
                .then().build();

        assertNotNull(conferenceTiming);
        final List<ConferenceTrack> tracks = conferenceTiming.getTracks();
        assertThat(tracks, hasSize(1));
        verifySessionInTrack(tracks.get(0),
            Session.of("Name", LocalTime.parse("10:00"), LocalTime.parse("11:00")),
            Session.of("SomethingElse", LocalTime.parse("11:00"), LocalTime.parse("11:30")));
    }

    @Test
    public void shouldBuildConferenceTimingWithOneTrackWithMultipleSessionsAndBreaks() {
        final ConferenceTimingBuilder conferenceTimingBuilder = ConferenceTimingBuilder.createNew();
        final ConferenceTiming conferenceTiming = conferenceTimingBuilder
                .addNewTrackStartingFrom(LocalTime.parse("10:00"))
                .withSessionFor("Name", Duration.ofMinutes(60))
                .withSessionFor("SomethingElse", Duration.ofMinutes(30))
                .withBreaksFor("Lunch", Duration.ofMinutes(90))
                .then().build();

        assertNotNull(conferenceTiming);
        final List<ConferenceTrack> tracks = conferenceTiming.getTracks();
        assertThat(tracks, hasSize(1));
        verifySessionInTrack(tracks.get(0),
            Session.of("Name", LocalTime.parse("10:00"), LocalTime.parse("11:00")),
            Session.of("SomethingElse", LocalTime.parse("11:00"), LocalTime.parse("11:30")),
            Break.of("Lunch", LocalTime.parse("11:30"), LocalTime.parse("13:00")));
    }

    @Test
    public void shouldBuildConferenceTimingWithMultipleTrackWithMultipleSessionsAndBreaks() {
        final ConferenceTimingBuilder conferenceTimingBuilder = ConferenceTimingBuilder.createNew();
        final ConferenceTiming conferenceTiming =  conferenceTimingBuilder
                .addNewTrackStartingFrom(LocalTime.parse("10:00"))
                .withSessionFor("Name", Duration.ofMinutes(60))
                .withBreaksFor("Lunch", Duration.ofMinutes(90))
                .then()
                .addNewTrackStartingFrom(LocalTime.parse("11:00"))
                .withSessionFor("My Intro", Duration.ofMinutes(120))
                .withBreaksFor("Games", Duration.ofMinutes(120))
                .then().build();

        assertNotNull(conferenceTiming);
        final List<ConferenceTrack> tracks = conferenceTiming.getTracks();
        assertThat(tracks, hasSize(2));

        verifySessionInTrack(tracks.get(0),
            Session.of("Name", LocalTime.parse("10:00"), LocalTime.parse("11:00")),
            Session.of("Lunch", LocalTime.parse("11:00"), LocalTime.parse("12:30")));

        verifySessionInTrack(tracks.get(1),
                Session.of("My Intro", LocalTime.parse("11:00"), LocalTime.parse("13:00")),
                Break.of("Games", LocalTime.parse("13:00"), LocalTime.parse("15:00")));
    }

    private void verifySessionInTrack(final ConferenceTrack track, final Event...events) {
        final List<Event> scheduledSessions = track.getEvents();
        assertThat(scheduledSessions, hasSize(events.length));
        for (int i = 0; i < scheduledSessions.size(); i++) {
            final Event event1 = scheduledSessions.get(i);
            final Event event = events[i];
            assertThat("Event name mismatch " + i, event1.getName(), is(event.getName()));
            assertThat("Start time mismatch for event " + i, event1.getTime().getFrom(), is(event.getTime().getFrom()));
            assertThat("End time mismatch for event " + i, event1.getTime().getTo(), is(event.getTime().getTo()));
        }
    }
}
