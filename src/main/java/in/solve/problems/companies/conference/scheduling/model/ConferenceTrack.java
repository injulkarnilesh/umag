package in.solve.problems.companies.conference.scheduling.model;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.List;

public class ConferenceTrack {

    private List<Event> events;

    private ConferenceTrack(final List<Event> events) {
        this.events = events;
    }

    static ConferenceTrack with(final List<Event> sessions) {
        final int size = sessions.size();
        return new ConferenceTrack(new UnmodifiableArrayList<>(sessions.toArray(new Event[size]), size));
    }

    public List<Event> getEvents() {
        return events;
    }
}
