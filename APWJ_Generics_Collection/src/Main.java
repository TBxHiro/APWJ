import AgeCalculator.Calculator;
import AgeCalculator.Employee;
import AgeCalculator.Person;
import AgeCalculator.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(createStudent("ZAMAN", 1, 1, 2000));
        list.add(createEmployee("Employee", 2, 2, 1990));
        for (Person person : list) {
            Calculator cal = new Calculator<>(person);
            Period diff = cal.Diff();
            String age = diff.getDays() + " Day(s) " + diff.getMonths() + " Month(s) " + diff.getYears() + " Year(s)";
            printAge(person, age);
        }
    }

    private static Person createStudent(String name, Integer day, Integer month, Integer year) {
        Student s1 = new Student();
        s1.setDob(LocalDate.of(year, month, day));
        s1.setName(name);
        return s1;
    }

    private static Person createEmployee(String name, Integer day, Integer month, Integer year) {
        Employee e1 = new Employee();
        e1.setDob(LocalDate.of(year, month, day));
        e1.setName(name);
        return e1;
    }

    private static void printAge(Person person, String age) {
        System.out.println("Full Name     : " + person.getName());
        System.out.println("Date of Birth : " + person.getDob());
        System.out.println("Age           : " + age);
        System.out.println("");
    }
}