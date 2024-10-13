package YarnTenantPortal.AutomationTestCases;

import java.util.Random;

public class randomGenerator {

	private static final String[] FIRST_NAMES = {"John", "Jane", "Bob", "Alice", "Mike", "Sarah", "Emily", "David"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
    private static final String[] NUMBERS = {"202", "212", "415", "650", "818"};

    public static class Visitor {
        public String firstName;
        public String lastName;
        public String email;
        public String numbers;

        public Visitor(String firstName, String lastName, String email, String numbers) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.numbers = numbers;
        }
    }

    public static Visitor generateRandomContact() {
        Random random = new Random();

        // Generate random first name
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];

        // Generate random last name
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        // Generate random email domain
        String emailDomain = EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];

        // Generate random email username (with numbers)
        String emailUsername = firstName.toLowerCase() + lastName.toLowerCase() + random.nextInt(100);

        // Generate random email
        String email = emailUsername + "@" + emailDomain;

        // Generate random phone number
        String numbers = NUMBERS[random.nextInt(NUMBERS.length)];
        String phoneNumberSuffix = String.valueOf(random.nextInt(1000000));
        String phoneNumber = numbers + "-" + phoneNumberSuffix;

        // Return the generated contact information
        return new Visitor(firstName, lastName, email, numbers);
    }
}
