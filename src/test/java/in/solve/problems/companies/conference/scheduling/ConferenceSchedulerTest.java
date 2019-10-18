package in.solve.problems.companies.conference.scheduling;

import in.solve.problems.companies.conference.scheduling.model.Break;
import in.solve.problems.companies.conference.scheduling.model.ConferenceSchedule;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTiming;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTimingBuilder;
import in.solve.problems.companies.conference.scheduling.model.Event;
import in.solve.problems.companies.conference.scheduling.model.ScheduledSession;
import in.solve.problems.companies.conference.scheduling.model.ScheduledTalk;
import in.solve.problems.companies.conference.scheduling.model.ScheduledTrack;
import in.solve.problems.companies.conference.scheduling.model.Session;
import in.solve.problems.companies.conference.scheduling.model.Talk;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConferenceSchedulerTest {

    private ConferenceScheduler scheduler = ConferenceSchedulerImpl.getInstance();

    @Test
    public void shouldScheduleEmptyConference() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew().build();
        final List<Talk> talks = new ArrayList<>();

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();

        assertThat(scheduledTracks, is(empty()));
    }

    @Test
    public void shouldScheduleSingleTalkInSingleSessionInSingleTrackWithoutAnyBreak() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(time("10:00"))
                .withSessionFor("Morning Kata", Duration.ofMinutes(60))
                .then().build();

        final List<Talk> talks = talksOfDurations(60);

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();
        assertThat(scheduledTracks, hasSize(1));
        final ScheduledTrack track1 = scheduledTracks.get(0);
        verifySessionInTrack(track1, Session.of("Morning Kata", time("10:00"), time("11:00")));
        verifyTalksInSession(track1.getSessions().get(0), talks.get(0));
    }

    @Test
    public void shouldScheduleTwoTalksInSingleSessionInSingleTrackWithoutAnyBreak() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(time("10:00"))
                .withSessionFor("Morning News", Duration.ofMinutes(120))
                .then().build();

        final List<Talk> talks = talksOfDurations(60, 20);

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();
        assertThat(scheduledTracks, hasSize(1));
        final ScheduledTrack track1 = scheduledTracks.get(0);
        verifySessionInTrack(track1, Session.of("Morning News", time("10:00"), time("12:00")));
        verifyTalksInSession(track1.getSessions().get(0), talks.get(0), talks.get(1));
    }

    @Test
    public void shouldScheduleTalksAcrossMoreThan1SessionInSingleTrackWithoutBreak() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(time("10:00"))
                .withSessionFor("Morning", Duration.ofMinutes(120))
                .withSessionFor("Noon", Duration.ofMinutes(120))
                .then().build();

        final List<Talk> talks = talksOfDurations(100, 100);

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();
        assertThat(scheduledTracks, hasSize(1));
        final ScheduledTrack track1 = scheduledTracks.get(0);
        verifySessionInTrack(track1, Session.of("Morning", time("10:00"), time("12:00")),
                Session.of("Noon", time("12:00"), time("14:00")));
        verifyTalksInSession(track1.getSessions().get(0), talks.get(0));
        verifyTalksInSession(track1.getSessions().get(1), talks.get(1));
    }

    @Test
    public void shouldScheduleTalksOfFittingLengthInFirstSessionAcrossMoreThan1SessionInSingleTrackWithoutBreak() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(time("10:00"))
                .withSessionFor("Morning", Duration.ofHours(2))
                .withSessionFor("Noon", Duration.ofHours(2))
                .then().build();
        final List<Talk> talks = talksOfDurations(100, 100, 20);

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();
        assertThat(scheduledTracks, hasSize(1));
        final ScheduledTrack track1 = scheduledTracks.get(0);
        verifySessionInTrack(track1, Session.of("Morning", time("10:00"), time("12:00")),
                Session.of("Noon", time("12:00"), time("14:00")));
        verifyTalksInSession(track1.getSessions().get(0), talks.get(0), talks.get(2));
        verifyTalksInSession(track1.getSessions().get(1), talks.get(1));
    }

    @Test
    public void shouldScheduleTalksOfBiggerLengthInMatchingSession() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(time("10:00"))
                .withSessionFor("Morning", Duration.ofHours(2))
                .withSessionFor("Noon", Duration.ofHours(4))
                .then().build();

        final List<Talk> talks = talksOfDurations(140, 100, 20);

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();
        assertThat(scheduledTracks, hasSize(1));
        final ScheduledTrack track1 = scheduledTracks.get(0);
        verifySessionInTrack(track1, Session.of("Morning", time("10:00"), time("12:00")),
                Session.of("Noon", time("12:00"), time("16:00")));
        verifyTalksInSession(track1.getSessions().get(0), talks.get(1), talks.get(2));
        verifyTalksInSession(track1.getSessions().get(1), talks.get(0));
    }

    @Test
    public void shouldScheduleTalksInDescendingOrderOrTimeToSaveOnTime() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(time("10:00"))
                .withSessionFor("Morning", Duration.ofMinutes(100))
                .withSessionFor("Noon", Duration.ofMinutes(90))
                .then().build();

        final List<Talk> talks = talksOfDurations(50, 10, 30, 40, 60);

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();
        assertThat(scheduledTracks, hasSize(1));
        final ScheduledTrack track1 = scheduledTracks.get(0);
        verifySessionInTrack(track1, Session.of("Morning", time("10:00"), time("11:40")),
                Session.of("Noon", time("11:40"), time("13:10")));
        verifyTalksInSession(track1.getSessions().get(0), talks.get(4), talks.get(3));
        verifyTalksInSession(track1.getSessions().get(1), talks.get(0), talks.get(2), talks.get(1));
    }


    @Test
    public void shouldScheduleThreeTalksInTwoSessionsInOneTrackWithOneBreak() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(time("10:00"))
                .withSessionFor("Morning", Duration.ofHours(2))
                .withBreaksFor("Lunch", Duration.ofHours(1))
                .withSessionFor("Noon", Duration.ofHours(2))
                .then().build();
        final List<Talk> talks = talksOfDurations(60, 60, 60);

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();
        assertThat(scheduledTracks, hasSize(1));
        final ScheduledTrack track1 = scheduledTracks.get(0);
        verifySessionInTrack(track1, Session.of("Morning", time("10:00"), time("12:00")), Break.of("Lunch", time("12:00"), time("13:00")), Session.of("Noon", time("13:00"), time("15:00")));
        verifyTalksInSession(track1.getSessions().get(0), talks.get(0), talks.get(1));
        verifyTalksInSession(track1.getSessions().get(2), talks.get(2));
    }


    @Test
    public void shouldTalksForMoreTracksWithMoreSessionAndBreaks() {
        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(time("10:00"))
                    .withSessionFor("Morning", Duration.ofHours(2))
                    .withBreaksFor("Lunch", Duration.ofHours(1))
                    .withSessionFor("Afternoon", Duration.ofHours(4))
                .then()
                .addNewTrackStartingFrom(time("09:00"))
                    .withSessionFor("Morning", Duration.ofHours(3))
                    .withBreaksFor("Lunch", Duration.ofHours(2))
                    .withSessionFor("Afternoon", Duration.ofHours(3))
                    .withBreaksFor("Networking", Duration.ofHours(2))
                .then().build();

        final List<Talk> talks = talksOfDurations(100, 60, 60, 60, 90, 30, 20, 60, 60, 60, 60, 30, 30);

        final ConferenceSchedule schedule  = scheduler.schedule(conferenceTiming, talks);

        final List<ScheduledTrack> scheduledTracks = schedule.getTracks();
        assertThat(scheduledTracks, hasSize(2));
        final ScheduledTrack track1 = scheduledTracks.get(0);
        verifySessionInTrack(track1, Session.of("Morning", time("10:00"), time("12:00")),
                Break.of("Lunch", time("12:00"), time("13:00")),
                Session.of("Afternoon", time("13:00"), time("17:00")));
        verifyTalksInSession(track1.getSessions().get(0), talks.get(0), talks.get(6));
        verifyTalksInSession(track1.getSessions().get(2), talks.get(4), talks.get(1), talks.get(2), talks.get(5));

        final ScheduledTrack track2 = scheduledTracks.get(1);
        verifySessionInTrack(track2, Session.of("Morning", time("09:00"), time("12:00")),
                Break.of("Lunch", time("12:00"), time("14:00")),
                Session.of("Afternoon", time("14:00"), time("17:00")),
                Break.of("Networking", time("17:00"), time("19:00")));
        verifyTalksInSession(track2.getSessions().get(0), talks.get(3), talks.get(7), talks.get(8));
        verifyTalksInSession(track2.getSessions().get(2), talks.get(9), talks.get(10), talks.get(11), talks.get(12));
    }

    private List<Talk> talksOfDurations(final int ...durations) {
        final List<Talk> talks = new ArrayList<>();
        for (int i = 0; i < durations.length; i++) {
            talks.add(Talk.about("Talk" + i, Duration.ofMinutes(durations[i])));
        }
        return talks;
    }

    private void verifySessionInTrack(final ScheduledTrack track, final Event...events) {
        final List<ScheduledSession> scheduledSessions = track.getSessions();
        assertThat(scheduledSessions, hasSize(events.length));
        for (int i = 0; i < scheduledSessions.size(); i++) {
            final ScheduledSession scheduledSession = scheduledSessions.get(i);
            final Event event = events[i];
            assertThat("Event name mismatch " + i, scheduledSession.getName(), is(event.getName()));
            assertThat("Start time mismatch for event " + i, scheduledSession.getFrom(), is(event.getTime().getFrom()));
            assertThat("End time mismatch for event " + i, scheduledSession.getTo(), is(event.getTime().getTo()));
        }
    }

    private void verifyTalksInSession(final ScheduledSession session, final Talk...talks) {
        final List<ScheduledTalk> scheduledTalks =  session.getTalks();
        assertThat("Session has wrong number of talks", scheduledTalks, hasSize(talks.length));
        LocalTime startTime = session.getFrom();
        for (int i = 0; i < scheduledTalks.size(); i++) {
            final Talk talk = talks[i];
            final ScheduledTalk scheduledTalk = scheduledTalks.get(i);
            assertThat("Talk topic mismatch", scheduledTalk.getTopic(), is(talk.getTopic()));
            assertThat("Talk start time mismatch", scheduledTalk.getFrom(), is(startTime));
            final LocalTime endTime = startTime.plus(talk.getDuration());
            assertThat("Talk end time mismatch", scheduledTalk.getTo(), is(endTime));
            startTime = endTime;
        }
    }

    private LocalTime time(final String text) {
        return LocalTime.parse(text);
    }
}
