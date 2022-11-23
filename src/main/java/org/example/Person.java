package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Person {
    private long identityNumber;
    private final String firstName;
    private final String lastName;
    private final Date birthday;
    private ArrayList<Fine> fines;

    public Person(long identityNumber, String firstName, String lastName, int day, int month, int year) {
        this.identityNumber = identityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        this.birthday = calendar.getTime();
        this.fines = new ArrayList<>();
    }

    protected Person(Person person) {
        this.identityNumber = person.identityNumber;
        this.firstName = person.firstName;
        this.lastName = person.lastName;
        this.birthday = person.birthday;
        this.fines = new ArrayList<>(person.fines);
    }

    protected void setIdentityNumber(long identityNumber) {
        this.identityNumber = identityNumber;
    }

    protected void addFine(Fine fine) {
        this.fines.add(fine);
    }

    public void setFines(ArrayList<Fine> fines) {
        this.fines = fines;
    }

    protected ArrayList<Fine> getFines() {
        return fines;
    }

    protected long getIdentityNumber() {
        return this.identityNumber;
    }

    protected String showPersonWithoutFines() {
        String personInfo = "IdentityNumber: " + identityNumber + ", firstName: " +
                firstName + ", lastName: " + lastName;
        SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yyyy");
        String birthday = dt.format(this.birthday);
        return personInfo + ", birthday: " + birthday + ".";
    }

    protected String showPersonWithFines() {
        StringBuilder fineInfo = new StringBuilder();
        if (fines.toArray().length != 0) {
            fineInfo.append("\n");
            int counter = 1;
            for (Fine fine: fines) {
                fineInfo.append(counter).append(".");
                fineInfo.append(fine.fineShow());
                fineInfo.append("\n");
                counter++;
            }
        }
        else {
            fineInfo.append("none.\n");
        }
        return showPersonWithoutFines() + "\nFines: " + fineInfo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "identityNumber=" + identityNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", fines=" + fines +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (identityNumber != person.identityNumber) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (!birthday.equals(person.birthday)) return false;
        return Objects.equals(fines, person.fines);
    }

    @Override
    public int hashCode() {
        int result = (int) (identityNumber ^ (identityNumber >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + (fines != null ? fines.hashCode() : 0);
        return result;
    }
}
