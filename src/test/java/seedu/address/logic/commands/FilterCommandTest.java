package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Event;
import seedu.address.model.person.FilterPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonFilter;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Telegram;
import seedu.address.model.skill.Skill;
import seedu.address.testutil.PersonFilterBuilder;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonFilter firstPredicateFilter = new PersonFilterBuilder().build();
        PersonFilter secondPredicateFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), new Email("alice@gmail.com"), new Telegram("Tampines"),
                new Role("Farmer"), new Event("Secret"), new Skill("Java"));


        FilterPredicate firstPredicate = new FilterPredicate(firstPredicateFilter);
        FilterPredicate secondPredicate = new FilterPredicate(secondPredicateFilter);

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonFilter personFilter = PersonFilter.getEmptyFilter();
        FilterPredicate predicate = new FilterPredicate(personFilter);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_twoPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonFilter personFilter = new PersonFilter(null, null, null, null,
                new Role("Organiser"), null, null);
        FilterPredicate predicate = new FilterPredicate(personFilter);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonFilter personFilter = new PersonFilter(new Name("George"), null, null, null,
                new Role("Logistics"), new Event("Ucl"), null);
        FilterPredicate predicate = new FilterPredicate(personFilter);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonFilter personFilter = new PersonFilter(List.of(new Name("George"), new Name("Bob")),
                Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                        List.of(new Role("Logistics")), List.of(new Event("Ucl")), Collections.emptyList());
        FilterPredicate predicate = new FilterPredicate(personFilter);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_emailSubstringFilter_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonFilter personFilter = new PersonFilter(List.of(new Name("Alice")), Collections.emptyList(),
                List.of("alice"), Collections.emptyList(), Collections.emptyList(),
                        Collections.emptyList(), List.of(new Skill("Rust")));
        FilterPredicate predicate = new FilterPredicate(personFilter);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        PersonFilter personFilter = new PersonFilterBuilder().build();
        FilterPredicate predicate = new FilterPredicate(personFilter);
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }
}
