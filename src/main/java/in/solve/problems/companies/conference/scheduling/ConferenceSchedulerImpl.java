package in.solve.problems.companies.conference.scheduling;

import in.solve.problems.companies.conference.scheduling.model.ConferenceSchedule;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTiming;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTrack;
import in.solve.problems.companies.conference.scheduling.model.Event;
import in.solve.problems.companies.conference.scheduling.model.ScheduledSession;
import in.solve.problems.companies.conference.scheduling.model.ScheduledTalk;
import in.solve.problems.companies.conference.scheduling.model.ScheduledTrack;
import in.solve.problems.companies.conference.scheduling.model.Talk;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

final class ConferenceSchedulerImpl implements ConferenceScheduler {

    private ConferenceSchedulerImpl() {
    }

    public static ConferenceSchedulerImpl getInstance() {
        return new ConferenceSchedulerImpl();
    }

    @Override
    public ConferenceSchedule schedule(final ConferenceTiming conferenceTiming, final List<Talk> talks) {
        final List<Talk> orderedTalks = new ArrayList<>(talks);
        orderedTalks.sort(Comparator.comparing(Talk::getDuration).reversed());

        final ConferenceSchedule schedule = ConferenceSchedule.createNew();

        for (ConferenceTrack track: conferenceTiming.getTracks()) {
            final ScheduledTrack scheduledTrack = ScheduledTrack.createNew();

            for (Event session: track.getEvents()) {
                final ScheduledSession scheduledSession = ScheduledSession.between(session.getName(), session.getTime().getFrom(), session.getTime().getTo());
                LocalTime startTime = scheduledSession.getFrom();
                final Iterator<Talk> talkIterator = orderedTalks.iterator();

                if (session.hasParts()) {
                    while (talkIterator.hasNext()) {
                        final Talk talk = talkIterator.next();
                        final LocalTime talkEndTime = startTime.plus(talk.getDuration());
                        if (!talkEndTime.isAfter(session.getTime().getTo())) {
                            final ScheduledTalk scheduledTalk = ScheduledTalk.about(talk.getTopic(), startTime, talkEndTime);
                            scheduledSession.add(scheduledTalk);
                            startTime = scheduledTalk.getTo();
                            talkIterator.remove();
                        }
                    }
                }

                scheduledTrack.add(scheduledSession);
            }

            schedule.addTrack(scheduledTrack);
        }

        return schedule;
    }
}
