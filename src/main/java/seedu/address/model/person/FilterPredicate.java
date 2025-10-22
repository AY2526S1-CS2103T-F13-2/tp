package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s fields matches all the given keywords
 */
public class FilterPredicate implements Predicate<Person> {

    private final PersonFilter filterParams;

    public FilterPredicate(PersonFilter filterParams) {
        this.filterParams = filterParams;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterPredicate)) {
            return false;
        }

        FilterPredicate otherFilterPredicate = (FilterPredicate) other;
        return filterParams.equals(otherFilterPredicate.filterParams);
    }

    @Override
    public boolean test(Person person) {

        if (filterParams.equals(PersonFilter.getEmptyFilter())) {
            return false;
        }


        boolean checkName = filterParams.getNames().isEmpty()
                || filterParams.getNames().stream().anyMatch(
                    keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword.fullName));

        boolean checkPhone = filterParams.getPhones().isEmpty()
                || filterParams.getPhones().stream().anyMatch(
                    phone -> person.getPhone().value.toLowerCase().contains(phone.toLowerCase()));

        boolean checkEmail = filterParams.getEmails().isEmpty()
                || filterParams.getEmails().stream().anyMatch(
                    email -> person.getEmail().value.toLowerCase().contains(email.toLowerCase()));

        boolean checkTelegram = filterParams.getTelegrams().isEmpty()
                || filterParams.getTelegrams().stream().anyMatch(
                    telegram -> person.getTelegram().value.toLowerCase().contains(telegram.toLowerCase()));

        boolean checkRole = filterParams.getRoles().isEmpty()
                || filterParams.getRoles().stream().anyMatch(
                    role -> StringUtil.containsWordIgnoreCase(person.getRole().toString(), role.toString()));

        boolean checkEvent = filterParams.getEvents().isEmpty()
                || filterParams.getEvents().stream().anyMatch(
                    event -> StringUtil.containsWordIgnoreCase(person.getEvent().toString(), event.toString()));

        boolean checkSkill = filterParams.getSkills().isEmpty()
                || filterParams.getSkills().stream().anyMatch(
                        skill -> person.getSkills().stream().anyMatch(personSkill ->
                        personSkill.skillName.equalsIgnoreCase(skill.skillName)));

        boolean matchFilters = checkName && checkPhone && checkEmail && checkTelegram
                && checkRole && checkEvent && checkSkill;

        return matchFilters;
    }
}
