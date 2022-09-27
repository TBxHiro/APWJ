package AgeCalculator;

import java.time.LocalDate;
import java.time.Period;

public class Calculator<T extends Person> {
    private final Person person;

    public Period Diff() {
        LocalDate curDate = LocalDate.now();
        Period period = Period.between(person.getDob(), curDate);
        return period;
    }

    public Calculator(Person person) {
        this.person = person;
    }
}
