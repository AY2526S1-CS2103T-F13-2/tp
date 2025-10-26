package seedu.address.ui;


import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label telegram;
    @FXML
    private Label email;
    @FXML
    private FlowPane skills;
    @FXML
    private Label role;
    @FXML
    private Label event;
    @FXML
    private ImageView favouriteIcon;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        telegram.setText("@" + person.getTelegram().value);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        role.setText(person.getRole().value);
        event.setText(person.getEvent().value);
        person.getSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> skills.getChildren().add(new Label(skill.skillName)));
        updateFavouriteIcon(person);
    }

    public ImageView getFavouriteIcon() {
        return favouriteIcon;
    }

    private void updateFavouriteIcon(Person person) {
        if (person.isFavourite()) {
            favouriteIcon.setImage((new Image(this.getClass().getResourceAsStream("/images/fav.png"))));
            favouriteIcon.setVisible(true);
        } else {
            favouriteIcon.setVisible(false);
        }
    }
}
