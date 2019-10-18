package in.solve.problems.companies.conference.scheduling.model;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.List;

public final class ConferenceTiming {

    final private List<ConferenceTrack> tracks;

    private ConferenceTiming(final List<ConferenceTrack> tracks) {
        this.tracks = tracks;
    }

    static ConferenceTiming withTracks(final List<ConferenceTrack> tracks) {
        final int size = tracks.size();
        return new ConferenceTiming(new UnmodifiableArrayList<>(
                    tracks.toArray(new ConferenceTrack[size]), size));
    }

    public List<ConferenceTrack> getTracks() {
        return tracks;
    }
}
