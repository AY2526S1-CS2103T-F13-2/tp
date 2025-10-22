package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonFilter;
import seedu.address.model.skill.Skill;


/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_TELEGRAM + person.getTelegram().value + " ");
        sb.append(PREFIX_ROLE + person.getRole().value + " ");
        sb.append(PREFIX_EVENT + person.getEvent().value + " ");
        person.getSkills().stream().forEach(
                s -> sb.append(PREFIX_SKILL + s.skillName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getTelegram().ifPresent(telegram -> sb.append(PREFIX_TELEGRAM).append(telegram.value).append(" "));
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.value).append(" "));
        descriptor.getEvent().ifPresent(event -> sb.append(PREFIX_EVENT).append(event.value).append(" "));
        if (descriptor.getSkills().isPresent()) {
            Set<Skill> skills = descriptor.getSkills().get();
            if (skills.isEmpty()) {
                sb.append(PREFIX_SKILL);
            } else {
                skills.forEach(s -> sb.append(PREFIX_SKILL).append(s.skillName).append(" "));
            }
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code PersonFilter}'s details.
     */
    public static String getPersonFilterDetails(PersonFilter personFilter) {
        StringBuilder sb = new StringBuilder();
        personFilter.getNames().forEach(name ->
                sb.append(PREFIX_NAME + name.fullName + " "));
        personFilter.getPhones().forEach(phone ->
                sb.append(PREFIX_PHONE + phone + " "));
        personFilter.getEmails().forEach(email ->
                sb.append(PREFIX_EMAIL + email + " "));
        personFilter.getTelegrams().forEach(telegram ->
                sb.append(PREFIX_TELEGRAM + telegram + " "));
        personFilter.getRoles().forEach(role ->
                sb.append(PREFIX_ROLE + role.value + " "));
        personFilter.getEvents().forEach(event ->
                sb.append(PREFIX_EVENT + event.value + " "));
        personFilter.getSkills().forEach(skill ->
                sb.append(PREFIX_SKILL + skill.skillName + " "));

        return sb.toString();
    }
    /**
     * Returns a filter command for filtering the {@code Person}
     */
    public static String getFilterCommand(PersonFilter personFilter) {
        return FilterCommand.COMMAND_WORD + " " + getPersonFilterDetails(personFilter);
    }
}
