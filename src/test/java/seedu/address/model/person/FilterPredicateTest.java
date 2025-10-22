package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.skill.Skill;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonFilterBuilder;

public class FilterPredicateTest {
    @Test
    public void equals() {
        PersonFilter firstPredicateFilter = new PersonFilterBuilder().build();
        PersonFilter secondPredicateFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), new Email("alice@gmail.com"), new Telegram("Tampines"),
                new Role("Farmer"), new Event("Secret"), new Skill("Java"));


        FilterPredicate firstPredicate = new FilterPredicate(firstPredicateFilter);
        FilterPredicate secondPredicate = new FilterPredicate(secondPredicateFilter);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FilterPredicate firstPredicateCopy = new FilterPredicate(firstPredicateFilter);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different personFilter -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_filter_returnsTrue() {

        // One keyword
        PersonFilter oneKeywordFilter = new PersonFilter(
                new Name("Alice"), null, null, null, null, null, null);
        FilterPredicate predicate = new FilterPredicate(oneKeywordFilter);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Two keywords
        PersonFilter twoKeywordFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), null, null, null, null, null);
        predicate = new FilterPredicate(twoKeywordFilter);
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withPhone("98765432").build()));

        // Three keywords
        PersonFilter threeKeywordFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), new Email("alice@gmail.com"), null, null,
                null, null);
        predicate = new FilterPredicate(threeKeywordFilter);
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withPhone("98765432").withEmail("alice@gmail.com").build()));

        // Four keywords
        PersonFilter fourKeywordFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), new Email("alice@gmail.com"), new Telegram("Tampines"),
                null, null, null);
        predicate = new FilterPredicate(fourKeywordFilter);
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withPhone("98765432").withEmail("alice@gmail.com")
                .withTelegram("Tampines Street 74").build()));

        // Five keywords
        PersonFilter fiveKeywordFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), new Email("alice@gmail.com"), new Telegram("Tampines"),
                new Role("Farmer"), null, null);
        predicate = new FilterPredicate(fiveKeywordFilter);
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withPhone("98765432").withEmail("alice@gmail.com")
                .withTelegram("Tampines Street 74").withRole("Farmer").build()));

        // Six keywords
        PersonFilter sixKeywordFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), new Email("alice@gmail.com"), new Telegram("Tampines"),
                new Role("Farmer"), new Event("Secret"), null);
        predicate = new FilterPredicate(sixKeywordFilter);
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withPhone("98765432").withEmail("alice@gmail.com")
                .withTelegram("Tampines Street 74").withRole("Farmer").withEvent("Secret Santa").build()));

        // All keywords
        PersonFilter allKeywordFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), new Email("alice@gmail.com"), new Telegram("Tampines"),
                new Role("Farmer"), new Event("Secret"), new Skill("Java"));
        predicate = new FilterPredicate(allKeywordFilter);
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withPhone("98765432").withEmail("alice@gmail.com")
                .withTelegram("Tampines Street 74").withRole("Farmer").withEvent("Secret Santa")
                .withSkills("Java").build()));

        // Mixed-case keywords
        PersonFilter mixedCaseFilter = new PersonFilter(
                new Name("aLiCe"), null, new Email("alIcE@gMAil.coM"), new Telegram("tamPiNes"),
                new Role("faRMer"), new Event("secREt"), new Skill("jAva"));
        predicate = new FilterPredicate(mixedCaseFilter);
        assertTrue(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withEmail("alice@gmail.com")
                .withTelegram("Tampines Street 74").withRole("Farmer").withEvent("Secret Santa")
                .withSkills("Java").build()));

        // Multiple name
        PersonFilter multipleNameFilter = new PersonFilter(List.of(new Name("Amy"), new Name("Bob")),
                Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                        Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        predicate = new FilterPredicate(multipleNameFilter);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keyword
        PersonFilter zeroKeywordFilter = PersonFilter.getEmptyFilter();
        FilterPredicate predicate = new FilterPredicate(zeroKeywordFilter);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Non-matching keyword
        PersonFilter nonMatchFilter = new PersonFilter(
                new Name("Alice"), null, null, null, null, null, null);
        predicate = new FilterPredicate(nonMatchFilter);
        assertFalse(predicate.test(new PersonBuilder().withName("Chen Dong").build()));

        // Keywords match all except role
        PersonFilter wrongRoleFilter = new PersonFilter(
                new Name("Alice"), new Phone("98765432"), new Email("alice@gmail.com"), new Telegram("Tampines"),
                new Role("Director"), new Event("Secret"), new Skill("Java"));
        predicate = new FilterPredicate(wrongRoleFilter);
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob").withPhone("98765432").withEmail("alice@gmail.com")
                .withTelegram("Tampines Street 74").withRole("Farmer").withEvent("Secret Santa")
                .withSkills("Java").build()));
    }
}
