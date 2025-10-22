package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicateEmailException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.DuplicatePhoneException;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).withSkills(VALID_SKILL_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasName(null));
    }

    @Test
    public void hasPerson_nameNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasName(ALICE));
    }

    @Test
    public void hasPerson_nameInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasName(ALICE));
    }

    @Test
    public void hasPerson_nameWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_BOB).withSkills(VALID_SKILL_HUSBAND)
                .build();
        assertTrue(addressBook.hasName(editedAlice));
    }

    @Test
    public void hasPerson_nameWithSamePhoneInAddressBook_throwsIllegalValueException() {
        addressBook.addPerson(AMY);
        Person dupplicateNoPerson = new PersonBuilder(AMY).withTelegram(VALID_TELEGRAM_BOB)
                .withSkills(VALID_SKILL_HUSBAND).withPhone(VALID_PHONE_AMY).build();

        assertThrows(DuplicatePersonException.class, ()->addressBook.addPerson(dupplicateNoPerson));
    }

    @Test
    public void hasPerson_nameWithSameInAddressBook_throwsIllegalValueException() {
        addressBook.addPerson(AMY);
        Person dupplicateNoPerson = new PersonBuilder(AMY).withTelegram(VALID_TELEGRAM_BOB)
                .withSkills(VALID_SKILL_HUSBAND).withPhone(VALID_PHONE_AMY).build();

        assertThrows(DuplicatePersonException.class, ()->addressBook.addPerson(dupplicateNoPerson));
    }

    @Test
    public void hasPersonWithSameNoInAddressBook_throwsIllegalValueException() {
        addressBook.addPerson(AMY);
        Person dupplicateNoPerson = new PersonBuilder(BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withSkills(VALID_SKILL_HUSBAND).withPhone(VALID_PHONE_AMY).build();

        assertThrows(DuplicatePhoneException.class, ()->addressBook.addPerson(dupplicateNoPerson));
    }

    @Test
    public void hasPersonWithSameEmailInAddressBook_throwsIllegalValueException() {
        addressBook.addPerson(AMY);
        Person dupplicateNoPerson = new PersonBuilder(BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withSkills(VALID_SKILL_HUSBAND)
                .withEmail(VALID_EMAIL_AMY).build();

        assertThrows(DuplicateEmailException.class, ()->addressBook.addPerson(dupplicateNoPerson));
    }



    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
