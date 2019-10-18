package in.solve.problems.companies.conference.scheduling.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConferenceTimingBuilder {

    private List<ConferenceTrackBuilder> trackBuilders = new ArrayList<>();

    public static ConferenceTimingBuilder createNew() {
        return new ConferenceTimingBuilder();
    }

    public ConferenceTiming build() {
        final List<ConferenceTrack> tracks = trackBuilders.stream().map(b -> ConferenceTrack.with(b.sessions))
                .collect(Collectors.toList());
        return ConferenceTiming.withTracks(tracks);
    }

    public ConferenceTrackBuilder addNewTrackStartingFrom(final LocalTime startingTime) {
        final ConferenceTrackBuilder trackBuilder = ConferenceTrackBuilder.addNewTrackStartingFrom(this, startingTime);
        trackBuilders.add(trackBuilder);
        return trackBuilder;
    }


    public static class ConferenceTrackBuilder {

        private LocalTime lastTime;
        private final ConferenceTimingBuilder timingBuilder;
        private final List<Event> sessions = new ArrayList<>();

        private ConferenceTrackBuilder(final ConferenceTimingBuilder timingBuilder, final LocalTime startTime) {
            this.timingBuilder = timingBuilder;
            this.lastTime = startTime;
        }

        static ConferenceTrackBuilder addNewTrackStartingFrom(final ConferenceTimingBuilder timingBuilder, final LocalTime startingTime) {
            return new ConferenceTrackBuilder(timingBuilder, startingTime);
        }

        public ConferenceTrackBuilder withSessionFor(final String sessionName, final Duration duration) {
            final LocalTime endTime = lastTime.plus(duration);
            final Session session = Session.of(sessionName, lastTime, endTime);
            sessions.add(session);
            lastTime = endTime;
            return this;
        }

        public ConferenceTrackBuilder withBreaksFor(final String breakName, final Duration duration) {
            final LocalTime endTime = lastTime.plus(duration);
            final Break aBreak = Break.of(breakName, lastTime, endTime);
            sessions.add(aBreak);
            lastTime = endTime;
            return this;
        }

        public ConferenceTimingBuilder then() {
            return timingBuilder;
        }
    }
}
