package in.solve.problems.companies.conference.scheduling;

import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreter;
import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterContext;
import in.solve.problems.companies.conference.scheduling.intereter.TalkInterpreterFactoryImpl;
import in.solve.problems.companies.conference.scheduling.model.ConferenceSchedule;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTiming;
import in.solve.problems.companies.conference.scheduling.model.ConferenceTimingBuilder;
import in.solve.problems.companies.conference.scheduling.model.Talk;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConferenceApp {

    @Test
    public void app() {
        final List<String> input = new ArrayList<>();
        input.add("Writing Fast Tests Against Enterprise Rails 60min");
        input.add("Overdoing it in Python 45min");
        input.add("Lua for the Masses 30min");
        input.add("Ruby Errors from Mismatched Gem Versions 45min");
        input.add("Common Ruby Errors 45min");
        input.add("Rails for Python Developers lightning");
        input.add("Communicating Over Distance 60min");
        input.add("Accounting-Driven Development 45min");
        input.add("Woah 30min");
        input.add("Sit Down and Write 30min");
        input.add("Pair Programming vs Noise 45min");
        input.add("Rails Magic 60min");
        input.add("Ruby on Rails: Why We Should Move On 60min");
        input.add("Clojure Ate Scala (on my project) 45min");
        input.add("Programming in the Boondocks of Seattle 30min");
        input.add("Ruby vs. Clojure for Back-End Development 30min");
        input.add("Ruby on Rails Legacy App Maintenance 60min");
        input.add("A World Without HackerNews 30min");
        input.add("User Interface CSS in Rails Apps 30min");

        final List<Talk> talks =
                input.stream()
                .map(desc -> {
                    final TalkInterpreter interpreter = TalkInterpreterFactoryImpl.INSTANCE.get(desc);
                    return interpreter.interpret(TalkInterpreterContext.createNew());
                })
                .collect(Collectors.toList());

        final ConferenceTiming conferenceTiming = ConferenceTimingBuilder.createNew()
                .addNewTrackStartingFrom(LocalTime.parse("09:00"))
                .withSessionFor("Morning Events", Duration.ofHours(3))
                .withBreaksFor("Lunch", Duration.ofHours(1))
                .withSessionFor("Evening Events", Duration.ofHours(4))
                .withBreaksFor("Networking Event", Duration.ofHours(2))
                .then()
                .addNewTrackStartingFrom(LocalTime.parse("09:00"))
                .withSessionFor("Morning Events", Duration.ofHours(3))
                .withBreaksFor("Lunch", Duration.ofHours(1))
                .withSessionFor("Evening Events", Duration.ofHours(4))
                .withBreaksFor("Networking Event", Duration.ofHours(2))
                .then()
                .build();

        final ConferenceSchedule schedule = ConferenceSchedulerImpl.getInstance()
                    .schedule(conferenceTiming, talks);

        System.out.println(schedule);
    }
}
