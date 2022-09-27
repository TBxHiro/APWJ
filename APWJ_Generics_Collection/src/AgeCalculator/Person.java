package AgeCalculator;

import java.time.LocalDate;

public class Person {
    private String name;
    private LocalDate dob;
    private String id;

    public String getName() {
        return this.name;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Person() {
    }
}


