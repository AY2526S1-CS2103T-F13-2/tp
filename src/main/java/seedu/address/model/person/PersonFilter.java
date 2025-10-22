package seedu.address.model.person;

import java.util.Collections;
import java.util.List;

import seedu.address.model.skill.Skill;

/**
 * A filter with the parameters to check each {@code Person} for
 */
public class PersonFilter {

    private static final PersonFilter EMPTY_FILTER = new PersonFilter(Collections.emptyList(), Collections.emptyList(),
            Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
            Collections.emptyList());

    // Identity fields
    private final List<Name> names;
    private final List<String> phones;
    private final List<String> emails;

    // Data fields
    private final List<String> telegrams;
    private final List<Role> roles;
    private final List<Skill> skills;
    private final List<Event> events;

    /**
     * Creates a new PersonFilter object
     * @param names Name to be filtered in List, Empty List if not filtering by name
     * @param phones Phone to be filtered in List, Empty List if not filtering by phone
     * @param emails Email to be filtered in List, Empty List if not filtering by email
     * @param telegrams Telegram to be filtered in List, Empty List if not filtering by telegram handle
     * @param roles Role to be filtered in List, Empty List if not filtering by role
     * @param events Event to be filtered in List, Empty List if not filtering by event
     * @param skills Skill to be filtered in List, Empty List if not filtering by skill
     */
    public PersonFilter(List<Name> names, List<String> phones, List<String> emails, List<String> telegrams,
                        List<Role> roles, List<Event> events, List<Skill> skills) {
        this.names = names;
        this.phones = phones;
        this.emails = emails;
        this.telegrams = telegrams;
        this.roles = roles;
        this.events = events;
        this.skills = skills;
    }

    /**
     * Alternate constructor taking in raw values instead of Lists
     */
    public PersonFilter(Name name, Phone phone, Email email, Telegram telegram, Role role, Event event, Skill skill) {
        this.names = name != null ? List.of(name) : Collections.emptyList();
        this.phones = phone != null ? List.of(phone.toString()) : Collections.emptyList();
        this.emails = email != null ? List.of(email.toString()) : Collections.emptyList();
        this.telegrams = telegram != null ? List.of(telegram.toString()) : Collections.emptyList();
        this.roles = role != null ? List.of(role) : Collections.emptyList();
        this.events = event != null ? List.of(event) : Collections.emptyList();
        this.skills = skill != null ? List.of(skill) : Collections.emptyList();
    }

    public List<Name> getNames() {
        return names;
    }

    public List<String> getPhones() {
        return phones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public List<String> getTelegrams() {
        return telegrams;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public static PersonFilter getEmptyFilter() {
        return EMPTY_FILTER;
    }

    /**
     * Returns true if both PersonFilters have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonFilter)) {
            return false;
        }

        PersonFilter otherPersonFilter = (PersonFilter) other;
        return names.equals(otherPersonFilter.names)
                && phones.equals(otherPersonFilter.phones)
                && emails.equals(otherPersonFilter.emails)
                && telegrams.equals(otherPersonFilter.telegrams)
                && skills.equals(otherPersonFilter.skills)
                && roles.equals(otherPersonFilter.roles)
                && events.equals(otherPersonFilter.events);

    }
}
