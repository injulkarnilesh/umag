package in.solve.problems.companies.conference.scheduling.model;

import java.util.ArrayList;
import java.util.List;

final public class ConferenceSchedule {

    final List<ScheduledTrack> tracks = new ArrayList<>();

    private ConferenceSchedule() {
    }

    public static ConferenceSchedule createNew() {
        return new ConferenceSchedule();
    }

    public void addTrack(final ScheduledTrack track) {
        this.tracks.add(track);
    }

    public List<ScheduledTrack> getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder(512);
        for (ScheduledTrack track: tracks) {
            str.append(track.toString())
                    .append("\n");
        }
        return str.toString();
    }

}
