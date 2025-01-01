package YarnTenantPortal.AutomationTestCases;

import java.util.Random;

public class randomGenerator {

	private static final String[] FIRST_NAMES = {"Liam", "Emma", "Noah", "Olivia", "Ava", "Isabella", "Sophia", "Mason"};
	private static final String[] LAST_NAMES = {"Smithson", "Johnsonson", "Williamsen", "Joneson", "Browner", "Davisley", "Milleridge", "Wilsonette"};
	private static final String[] EMAIL_DOMAINS = {"example.com", "test.com", "demo.com", "sample.com", "mail.com", "webmail.com", "contact.com"};
	private static final String[] NUMBERS = {"123456", "654321", "789012", "345678", "901234", "567890", "234567", "890123", "456789", "012345", "678901", "234890", "345012", "456123", "567234", "678345", "789456", "890567"};

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
