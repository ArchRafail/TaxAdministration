package org.example;

import java.util.*;

public class TaxBase {
    private final Set<Person> personSet;
    private final Scanner scanner;

    private TaxBase() {
        this.personSet = new HashSet<>();
         scanner = new Scanner(System.in);
    }

    public static TaxBase taxBaseInstance() {
        return new TaxBase();
    }

    public void setPersonSet(Person person) {
        this.personSet.add(person);
    }

    public void baseShow() {
        for (Person person: personSet) {
            System.out.println(person.showPersonWithFines());
        }
    }

    public void addNewFine(long identityNumber, Fine fine) {
        if (identityNumberValidation(identityNumber)) {
            validationFineAdding(identityNumber, fine);
        }
    }

    public void addNewFine(long identityNumber) {
        System.out.println();
        if (identityNumberValidation(identityNumber) && validationFineAdding(identityNumber, setFine())) {
            System.out.println("\nNew fine was added!\n");
        }
    }

    private boolean identityNumberValidation(long identityNumber) {
        for (Person person: personSet) {
            if (person.getIdentityNumber() == identityNumber) {
                return true;
            }
        }
        System.out.println("No any record with identity number " + identityNumber + " found.\n");
        return false;
    }

    private boolean validationFineAdding(long identityNumber, Fine fine) {
        if (fine == null) {
            System.out.println("New fine was not added. Fine is empty.\n");
            return false;
        }
        for (Person person: personSet) {
            if (person.getIdentityNumber() == identityNumber) {
                person.addFine(fine);
                return true;
            }
        }
        return false;
    }

    private Fine setFine() {
        System.out.print("Enter the protocol number of the fine: ");
        String protocolNumber = scanner.nextLine();
        if (fineProtocolValidation(protocolNumber)) {
            System.out.println("\nThe protocol number is already in the base!\n");
            return null;
        }
        return createFine(protocolNumber);
    }

    private Fine createFine(String protocolNumber) {
        System.out.print("Sum of the fine (in format digits.digits): ");
        float sum = Float.parseFloat(scanner.nextLine());
        System.out.print("Type of the fine: ");
        String type = scanner.nextLine();
        System.out.print("The city where violation happened: ");
        String city = scanner.nextLine();
        System.out.print("Enter the description: ");
        String description = scanner.nextLine();
        return new Fine(protocolNumber, sum, type, city, description);
    }

    private boolean fineProtocolValidation(String protocolNumber) {
        for (Person person: personSet) {
            for (Fine fine : person.getFines()) {
                if (fine.getProtocol().equals(protocolNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean showByIdentityNumber(long identityNumber) {
        System.out.println();
        for (Person person: personSet) {
            if (person.getIdentityNumber() == identityNumber) {
                System.out.println(person.showPersonWithFines());
                return true;
            }
        }
        System.out.println("No any record with identity number " + identityNumber + " found.\n");
        return false;
    }

    public void showFinesByType(String type) {
        System.out.println();
        int totalFinesCounter = 0;
        StringBuilder personFineByType = new StringBuilder();
        for (Person person: personSet) {
            personFineByType.append(person.showPersonWithoutFines()).append("\n");
            int finesByPerson = 1;
            if (person.getFines().toArray().length != 0) {
                for (Fine fine: person.getFines()) {
                    if (fine.getType().equals(type)) {
                        personFineByType.append(finesByPerson++).append(". ").append(fine.fineShow()).append("\n");
                        totalFinesCounter++;
                    }
                }
            }
            if (finesByPerson > 1) {
                System.out.println(personFineByType);
            }
            personFineByType.setLength(0);
        }
        if (totalFinesCounter == 0) {
            System.out.println("No any record with fine type \"" + type + "\" found.\n");
        }
    }

    public void showFinesByCity(String city) {
        System.out.println();
        int totalFinesCounter = 0;
        StringBuilder personFineByCity = new StringBuilder();
        for (Person person: personSet) {
            personFineByCity.append(person.showPersonWithoutFines()).append("\n");
            int finesByPerson = 1;
            if (person.getFines().toArray().length != 0) {
                for (Fine fine: person.getFines()) {
                    if (fine.getCity().equals(city)) {
                        personFineByCity.append(finesByPerson).append(". ").append(fine.fineShow()).append("\n");
                        finesByPerson++;
                        totalFinesCounter++;
                    }
                }
            }
            if (finesByPerson > 1) {
                System.out.println(personFineByCity);
            }
            personFineByCity.setLength(0);
        }
        if (totalFinesCounter == 0) {
            System.out.println("No any record in the city \"" + city + "\" found.\n");
        }
    }

    public void addNewPerson(long identityNumber) {
        for (Person person: personSet) {
            if (identityNumber == person.getIdentityNumber()) {
                System.out.println("\nThe person with identity number " + identityNumber + " is already in base.\n");
                return;
            }
        }
        Person person = createNewPerson(identityNumber);
        if (person != null) {
            personSet.add(person);
            System.out.println("\nPerson already added!\n");
        }
    }

    private Person createNewPerson(long identityNumber) {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Birthday (in format day.month.year): ");
        String birthday = scanner.nextLine();
        String[] dateByUser = birthday.split("\\.");
        if (dateByUser.length != 3) {
            System.out.println("Incorrect birthday date!");
            return null;
        }
        int[] convertedDateByUser = new int[3];
        for (int i = 0; i < dateByUser.length; i++) {
            convertedDateByUser[i] = Integer.parseInt(dateByUser[i]);
        }
        if (!validateBirthday(convertedDateByUser)) {
            System.out.println("Incorrect birthday date!");
            return null;
        }
        return new Person(identityNumber, firstName, lastName, convertedDateByUser[0],
                convertedDateByUser[1], convertedDateByUser[2]);
    }

    private boolean validateBirthday(int[] convertedDateByUser) {
        switch (convertedDateByUser[1]) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (convertedDateByUser[0] < 1 || convertedDateByUser[0] > 31)
                    return false;
                break;
            case 2:
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, convertedDateByUser[2]);
                if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
                    if (convertedDateByUser[0] < 1 || convertedDateByUser[0] > 29)
                        return false;
                } else {
                    if (convertedDateByUser[0] < 1 || convertedDateByUser[0] > 28)
                        return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (convertedDateByUser[0] < 1 || convertedDateByUser[0] > 30)
                    return false;
                break;
            default:
                return false;
        }
        return true;
    }

    public void removeFine(long identityNumber) {
        if (!showByIdentityNumber(identityNumber)) {
            return;
        }
        System.out.print("Enter the protocol number that has to be removed: ");
        String protocolNumber = scanner.nextLine();
        for (Person person : personSet) {
            if (person.getIdentityNumber() == identityNumber) {
                for (Fine fine : person.getFines()) {
                    if (fine.getProtocol().equals(protocolNumber)) {
                        person.getFines().remove(fine);
                        System.out.println("\nFine with protocol number " + protocolNumber + " removed from base.\n");
                        return;
                    }
                }
            }
        }
        System.out.println("\nNothing to delete! The fine with protocol number " + protocolNumber + " was not found.\n");
    }

    public void changeInfo(long identityNumber) {
        if (!showByIdentityNumber(identityNumber)) {
            return;
        }
        Person personToChange = null;
        for (Person person : personSet) {
            if(person.getIdentityNumber() == identityNumber) {
                personToChange = person;
                break;
            }
        }
        System.out.print("Do you want to change the identity number of person? yes/(other key or keys) : ");
        String answer = scanner.nextLine();
        long newIdentityNumber = identityNumber;
        if (answer.equals("yes")) {
            System.out.println();
            newIdentityNumber = Main.identityNumber();
            for (Person person: personSet) {
                if (person.getIdentityNumber() == newIdentityNumber) {
                    System.out.println("\nNew identity number is already in the base. Process terminated!\n");
                    return;
                }
            }
            assert personToChange != null;
            personToChange.setIdentityNumber(newIdentityNumber);
        }
        System.out.print("\nDo you want to change other personal information? yes/(other key or keys) : ");
        Person temporaryPerson;
        answer = scanner.nextLine();
        assert personToChange != null;
        if (answer.equals("yes")) {
            System.out.println("\n" + personToChange.showPersonWithoutFines() + "\n");
            temporaryPerson = createNewPerson(newIdentityNumber);
            if (personToChange.getFines().toArray().length != 0) {
                assert temporaryPerson != null;
                temporaryPerson.setFines(new ArrayList<>(personToChange.getFines()));
            }
        } else {
            temporaryPerson = new Person(personToChange);
        }
        removingPerson(personToChange);
        personSet.add(temporaryPerson);
        System.out.println("\nA new information saved!");
        for (Person person : personSet) {
            assert temporaryPerson != null;
            if (person.getIdentityNumber() == temporaryPerson.getIdentityNumber()) {
                personToChange = person;
            }
        }
        assert temporaryPerson != null;
        System.out.println("\n" + temporaryPerson.showPersonWithFines());
        if (temporaryPerson.getFines().toArray().length != 0) {
            System.out.print("Do you want to change an information about fines? yes/(other key or keys) : ");
            answer = scanner.nextLine();
            if (answer.equals("yes")) {
                do {
                    System.out.print("\nEnter the protocol number that you want to change: ");
                    String protocolNumber = scanner.nextLine();
                    Fine fineToChange = null;
                    for (Fine fine : temporaryPerson.getFines()) {
                        if (fine.getProtocol().equals(protocolNumber)) {
                            fineToChange = fine;
                            System.out.println("\n" + fineToChange.fineShow());
                            break;
                        }
                    }
                    if (fineToChange == null) {
                        System.out.println("\nFine with your protocol number was not found!\n");
                        return;
                    }
                    System.out.print("\nEnter the protocol number of the fine: ");
                    String newProtocolNumber = scanner.nextLine();
                    if (fineProtocolValidation(newProtocolNumber) && !fineToChange.getProtocol().equals(newProtocolNumber)) {
                        System.out.println("\nThe protocol number is already in the base!\n");
                        return;
                    }
                    Fine newFine = createFine(newProtocolNumber);
                    temporaryPerson.getFines().remove(fineToChange);
                    temporaryPerson.addFine(newFine);
                    System.out.print("\nDo you want to do more change with fines? yes/(other key or keys): ");
                    answer = scanner.nextLine();
                } while (answer.equals("yes"));
            }
            System.out.println();
        }
        removingPerson(personToChange);
        personSet.add(temporaryPerson);
        System.out.println("A new information about person with IN " + temporaryPerson.getIdentityNumber() + " saved!\n");
    }

    private void removingPerson(Person personToChange) {
        for (Iterator<Person> iterator = personSet.iterator(); iterator.hasNext();) {
            Person person = iterator.next();
            if (person.getIdentityNumber() == personToChange.getIdentityNumber()) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "TaxBase{" +
                "personSet=" + personSet +
                '}';
    }
}