package seedu.address.testutil;

import java.util.List;

import seedu.address.model.person.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonFilter;
import seedu.address.model.person.Role;
import seedu.address.model.skill.Skill;

/**
 * A utility class to help with building PersonFilter objects
 */
public class PersonFilterBuilder {

    public static final String DEFAULT_NAME = "Amy";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "Jurong";
    public static final String DEFAULT_ROLE = "Volunteer";
    public static final String DEFAULT_EVENT = "Angbao";
    public static final String DEFAULT_SKILL = "Java";

    private List<Name> names;
    private List<String> phones;
    private List<String> emails;
    private List<String> telegrams;
    private List<Role> roles;
    private List<Event> events;
    private List<Skill> skills;


    /**
     * Creates a {@code PersonFilterBuilder} with the default details.
     */
    public PersonFilterBuilder() {
        names = List.of(new Name(DEFAULT_NAME));
        phones = List.of(DEFAULT_PHONE);
        emails = List.of(DEFAULT_EMAIL);
        telegrams = List.of(DEFAULT_TELEGRAM);
        roles = List.of(new Role(DEFAULT_ROLE));
        events = List.of(new Event(DEFAULT_EVENT));
        skills = List.of(new Skill(DEFAULT_SKILL));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personFilterToCopy}.
     */
    public PersonFilterBuilder(PersonFilter personFilterToCopy) {
        names = personFilterToCopy.getNames();
        phones = personFilterToCopy.getPhones();
        emails = personFilterToCopy.getEmails();
        telegrams = personFilterToCopy.getTelegrams();
        roles = personFilterToCopy.getRoles();
        events = personFilterToCopy.getEvents();
        skills = personFilterToCopy.getSkills();
    }

    /**
     * Sets the {@code Name} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withName(String name) {
        this.names = List.of(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withRole(String role) {
        this.roles = List.of(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Skill} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withSkill(String skill) {
        this.skills = List.of(new Skill(skill));
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withTelegram(String telegram) {
        this.telegrams = List.of(telegram);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withPhone(String phone) {
        this.phones = List.of(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withEmail(String email) {
        this.emails = List.of(email);
        return this;
    }

    /**
     * Sets the {@code Event} of the {@code PersonFilter} that we are building.
     */
    public PersonFilterBuilder withEvent(String event) {
        this.events = List.of(new Event(event));
        return this;
    }

    public PersonFilter build() {
        return new PersonFilter(names, phones, emails, telegrams, roles, events, skills);
    }
}
