package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Telegram;
import seedu.address.model.skill.Skill;



/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */

public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Telegram("Blk 30 Geylang Street 29, #06-40"), new Role("Organiser"),
                    new Event("Orientation Camp"), getSkillSet("JavaScript")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Telegram("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Role("Organiser"),
                    new Event("Charity Gala"), getSkillSet("JavaScript", "Piano")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Telegram("Blk 11 Ang Mo Kio Street 74, #11-04"), new Role("Organiser"),
                    new Event("Charity Gala"), getSkillSet("JavaScript")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Telegram("Blk 436 Serangoon Gardens Street 26, #16-43"), new Role("Organiser"),
                    new Event("Night Cycling"), getSkillSet("Drumming")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Telegram("Blk 47 Tampines Street 20, #17-35"), new Role("Organiser"),
                    new Event("NUS Marathon"), getSkillSet("JavaScript")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Telegram("Blk 45 Aljunied Street 85, #11-31"), new Role("Organiser"), new Event("Thopz"),
                    getSkillSet("JavaScript"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

}
