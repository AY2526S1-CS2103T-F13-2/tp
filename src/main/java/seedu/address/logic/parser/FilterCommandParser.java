package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Event;
import seedu.address.model.person.FilterPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonFilter;
import seedu.address.model.person.Role;
import seedu.address.model.skill.Skill;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_EVENT, PREFIX_TELEGRAM,
                        PREFIX_ROLE, PREFIX_SKILL);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TELEGRAM, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE,
                PREFIX_EVENT, PREFIX_SKILL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }


        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_ROLE,
                PREFIX_EVENT, PREFIX_SKILL);


        List<Name> names = argMultimap.getValue(PREFIX_NAME)
                .map(words -> Arrays.stream(words.split("\\s+"))
                        .map(nameInput -> {
                            try {
                                return ParserUtil.parseName(nameInput);
                            } catch (ParseException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(Collections.emptyList());

        List<String> phones = argMultimap.getValue(PREFIX_PHONE)
                .map(words -> Arrays.stream(words.split("\\s+"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(Collections.emptyList());

        List<String> emails = argMultimap.getValue(PREFIX_EMAIL)
                .map(words -> Arrays.stream(words.split("\\s+"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(Collections.emptyList());

        List<String> telegrams = argMultimap.getValue(PREFIX_TELEGRAM)
                .map(words -> Arrays.stream(words.split("\\s+"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(Collections.emptyList());

        List<Role> roles = argMultimap.getValue(PREFIX_ROLE)
                .map(words -> Arrays.stream(words.split("\\s+"))
                        .map(roleInput -> {
                            try {
                                return ParserUtil.parseRole(roleInput);
                            } catch (ParseException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(Collections.emptyList());

        List<Event> events = argMultimap.getValue(PREFIX_EVENT)
                .map(words -> Arrays.stream(words.split("\\s+"))
                        .map(eventInput -> {
                            try {
                                return ParserUtil.parseEvent(eventInput);
                            } catch (ParseException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(Collections.emptyList());

        List<Skill> skills = argMultimap.getValue(PREFIX_SKILL)
                .map(words -> Arrays.stream(words.split("\\s+"))
                        .map(skillInput -> {
                            try {
                                return ParserUtil.parseSkill(skillInput);
                            } catch (ParseException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList())
                .orElse(Collections.emptyList());

        PersonFilter filterParams = new PersonFilter(names, phones, emails, telegrams, roles, events, skills);

        FilterPredicate filterPredicate = new FilterPredicate(filterParams);

        return new FilterCommand(filterPredicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
