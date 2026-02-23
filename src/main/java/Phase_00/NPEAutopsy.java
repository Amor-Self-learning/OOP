/*
 * NPE AUTOPSY REPORT
 *
 * Scenario 1:
 *   Line that crashed: [71]
 *   at Phase_00.NPEAutopsy$Person.getCity(NPEAutopsy.java:58)
 *   What was null: [adress]
 *   Root cause: [address was null. we can't get city of a null]
 *   Fix applied: [check for adress != null before accessing it's properties i.e city]
 *
 * Scenario 2:
 *   Line that crashed: [66]
 *   What was null: [name field]
 *   Root cause: [Cannot invoke "String.length()" because "this.name" is null]
 *   Fix applied: [check for name != null]
 *
 * Scenario 3:
 *   Line that crashed: [99]
 *   What was null: [Cannot read field "name" because "found" is null]
 *   Root cause: [found was null but we tried to get it's name field]
 *   Fix applied: [check for found != null before normal operation if found == null print person not found]
 *
 * Pattern I noticed across all three:
 *   [NPE spreads like fire, one NPE can cause a lot of things to become undefined and a casue of programs' failure.
 *   We should safely check for null before accessing fields or interacting with them.]
 */
package Phase_00;

public class NPEAutopsy {

    static class Address {
        String street;
        String city;

        Address(String street, String city) {
            this.street = street;
            this.city = city;
        }

        String getFullAddress() {
            return street + ", " + city;
        }
    }

    static class Person {
        String name;
        Address address; // intentionally nullable for this drill

        Person(String name) {
            if (name == null) throw new IllegalArgumentException("name must not be null");
            this.name = name;
            // address is NOT initialized — it is null
        }

        Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }

        String getCity() {
            if (address == null) {
                throw new IllegalStateException("Cannot get city: this person has no address");
            }
            return address.city; // NPE if address is null
        }

        int getNameLength() {
            return name.length(); // NPE if name is null
        }
    }

    // ===== SCENARIO 1: Uninitialized field =====
    static void scenario1_uninitializedField() {
        System.out.println("\n=== Scenario 1: Uninitialized Field ===");
        Person person = new Person("Alice"); // address is null
        String city = person.getCity();      // NPE here
        System.out.println("City: " + city);
    }

    // ===== SCENARIO 2: Null passed as argument =====
    static void scenario2_nullArgument() {
        System.out.println("\n=== Scenario 2: Null Argument ===");
        Person person = new Person(null);    // name is null
        int length = person.getNameLength(); // NPE here
        System.out.println("Name length: " + length);
    }

    // ===== SCENARIO 3: Method return value not checked =====
    static Person findPersonByName(String name) {
        // Simulating a lookup that may return null
        if (name.equals("Bob")) {
            return new Person("Bob", new Address("123 Main St", "Springfield"));
        }
        return null; // not found
    }

    static void scenario3_uncheckedReturnValue() {
        System.out.println("\n=== Scenario 3: Unchecked Return Value ===");
        Person found = findPersonByName("Alice"); // returns null
        if (found == null) {
            System.out.println("Person not found");
        } else {
            System.out.println("Found: " + found.name);
        }
    }

    public static void main(String[] args) {
        // Run each scenario ONE AT A TIME.
        // After each NPE: read the stack trace, use the debugger, fix it, then move to the next.
        // Start with scenario 1 commented out the rest:

        // scenario1_uninitializedField();
        // scenario2_nullArgument();
        scenario3_uncheckedReturnValue();
    }
}
