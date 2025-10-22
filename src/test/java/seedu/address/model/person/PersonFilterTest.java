package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.skill.Skill;
import seedu.address.testutil.PersonFilterBuilder;

public class PersonFilterTest {

    @Test
    public void equals() {
        // same values -> returns true
        PersonFilter amy = new PersonFilterBuilder().build();
        PersonFilter amyCopy = new PersonFilterBuilder().build();
        assertTrue(amy.equals(amyCopy));

        // same object -> returns true
        assertTrue(amy.equals(amy));

        // null -> returns false
        assertFalse(amy.equals(null));

        // different type -> returns false
        assertFalse(amy.equals(5));

        // different name -> returns false
        PersonFilter dimothy = new PersonFilterBuilder().withName("Dimothy").build();
        assertFalse(amy.equals(dimothy));

        // different phone -> returns false
        PersonFilter editedAmy = new PersonFilterBuilder().withPhone(VALID_PHONE_BOB).build();
        assertFalse(amy.equals(editedAmy));

        // different email -> returns false
        editedAmy = new PersonFilterBuilder().withEmail(VALID_EMAIL_BOB).build();
        assertFalse(amy.equals(editedAmy));

        // different event -> returns false
        editedAmy = new PersonFilterBuilder().withEvent("Tennis").build();
        assertFalse(amy.equals(editedAmy));

        // different role -> returns false
        editedAmy = new PersonFilterBuilder().withRole("Coach").build();
        assertFalse(amy.equals(editedAmy));

        // different telegram -> returns false
        editedAmy = new PersonFilterBuilder().withTelegram("Bishan").build();
        assertFalse(amy.equals(editedAmy));

        // different skills -> returns false
        editedAmy = new PersonFilterBuilder().withSkill("hunting").build();
        assertFalse(amy.equals(editedAmy));
    }

    @Test
    public void getEmptyFilter_returnsEmptyFilter() {
        PersonFilter emptyFilter = PersonFilter.getEmptyFilter();
        assertNotNull(emptyFilter);
        assertTrue(emptyFilter.getNames().isEmpty());
        assertTrue(emptyFilter.getPhones().isEmpty());
        assertTrue(emptyFilter.getEmails().isEmpty());
        assertTrue(emptyFilter.getAddresses().isEmpty());
        assertTrue(emptyFilter.getRoles().isEmpty());
        assertTrue(emptyFilter.getEvents().isEmpty());
        assertTrue(emptyFilter.getSkills().isEmpty());
    }

    @Test
    public void getters_returnCorrectValues() {
        Name name = new Name("Alice");
        Role role = new Role("Coach");
        Event event = new Event("Tennis");
        Skill skill = new Skill("Hunting");
        String phone = "98765432";
        String email = "alice@example.com";
        String address = "Bishan";

        PersonFilter filter = new PersonFilter(
                List.of(name),
                List.of(phone),
                List.of(email),
                List.of(address),
                List.of(role),
                List.of(event),
                List.of(skill)
        );

        assertEquals(List.of(name), filter.getNames());
        assertEquals(List.of(phone), filter.getPhones());
        assertEquals(List.of(email), filter.getEmails());
        assertEquals(List.of(address), filter.getAddresses());
        assertEquals(List.of(role), filter.getRoles());
        assertEquals(List.of(event), filter.getEvents());
        assertEquals(List.of(skill), filter.getSkills());
    }

    @Test
    public void alternateConstructor_createsCorrectFilter() {
        Name name = new Name("Alice");
        Phone phone = new Phone("98765432");
        Email email = new Email("alice@example.com");
        Address address = new Address("Bishan");
        Role role = new Role("Coach");
        Event event = new Event("Tennis");
        Skill skill = new Skill("Hunting");

        PersonFilter filter = new PersonFilter(name, phone, email, address, role, event, skill);

        assertEquals(List.of(name), filter.getNames());
        assertEquals(List.of(phone.toString()), filter.getPhones());
        assertEquals(List.of(email.toString()), filter.getEmails());
        assertEquals(List.of(address.toString()), filter.getAddresses());
        assertEquals(List.of(role), filter.getRoles());
        assertEquals(List.of(event), filter.getEvents());
        assertEquals(List.of(skill), filter.getSkills());
    }

}
