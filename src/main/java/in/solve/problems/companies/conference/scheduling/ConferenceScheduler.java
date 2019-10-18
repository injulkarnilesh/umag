package in.solve.problems.companies.conference.scheduling;

import in.solve.problems.companies.conference.scheduling.model.ConferenceSchedule;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTiming;
import in.solve.problems.companies.conference.scheduling.model.Talk;

import java.util.List;

public interface ConferenceScheduler {
    ConferenceSchedule schedule(ConferenceTiming conferenceTiming, List<Talk> talks);
}
