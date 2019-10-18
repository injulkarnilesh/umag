package in.solve.problems.companies.conference.scheduling.model;

import java.util.ArrayList;
import java.util.List;

public class ScheduledTrack {

    private List<ScheduledSession> sessions = new ArrayList<>();

    public static ScheduledTrack createNew() {
        return new ScheduledTrack();
    }

    public List<ScheduledSession> getSessions() {
        return sessions;
    }

    public void add(final ScheduledSession scheduledSession) {
        this.sessions.add(scheduledSession);
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder(512);
        for (ScheduledSession session: sessions) {
            str.append(session.toString())
            .append("\n");
        }
        return str.toString();
    }
}
