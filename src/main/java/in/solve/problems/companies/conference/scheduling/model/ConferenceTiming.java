package in.solve.problems.companies.conference.scheduling.model;


import java.util.ArrayList;
import java.util.List;

public final class ConferenceTiming {

    final private List<ConferenceTrack> tracks;

    private ConferenceTiming(final List<ConferenceTrack> tracks) {
        this.tracks = tracks;
    }

    static ConferenceTiming withTracks(final List<ConferenceTrack> tracks) {
        return new ConferenceTiming(new ArrayList(tracks));
    }

    public List<ConferenceTrack> getTracks() {
        return tracks;
    }
}
