/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package addrbook;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField addressField;
    @FXML private TextField cityField;
    @FXML private TextField stateField;
    @FXML private TextField zipField;
    @FXML private TableView<Person> contactsTable;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, String> addressColumn;
    @FXML private TableColumn<Person, String> cityColumn;
    @FXML private TableColumn<Person, String> stateColumn;
    @FXML private TableColumn<Person, String> zipColumn;
    @FXML private ImageView myImageView;
    
    private ObservableList<Person> contactData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zip"));

        contactsTable.setItems(contactData);
    }
 
    @FXML
    private void handleClearAction() {
        clearInputFields();
    }

    @FXML
    private void handleFindAction() {
        String searchLastName = lastNameField.getText().toLowerCase();
        if (!searchLastName.isEmpty()) {
            ObservableList<Person> foundContacts = contactData.filtered(contact ->
                    contact.getLastName().toLowerCase().contains(searchLastName)
            );
            contactsTable.setItems(foundContacts);
        } else {
            contactsTable.setItems(contactData); 
        }
    }

    @FXML
    private void handleAddAction() {
        try {
            validateInput();
            Person newContact = new Person(
                firstNameField.getText(),
                lastNameField.getText(),
                addressField.getText(),
                cityField.getText(),
                stateField.getText(),
                zipField.getText()
            );
            contactData.add(newContact);
            clearInputFields();
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
        }
    }

    @FXML
    private void handleUpdateAction() {
        try {
            validateInput();
            Person selected = contactsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                updateContact(selected);
                contactsTable.refresh();
                clearInputFields();
            } else {
                showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a contact to update.");
            }
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
        }
    }

    @FXML
    private void handleDeleteAction() {
        Person selected = contactsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            contactData.remove(selected);
        } else {
            showAlert(Alert.AlertType.ERROR, "No Selection", "No Contact Selected");
        }
    }

    private void validateInput() throws IllegalArgumentException {
        if (firstNameField.getText().isEmpty() || !firstNameField.getText().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("First name must be alphabetic and cannot be empty.");
        }
        if (lastNameField.getText().isEmpty() || !lastNameField.getText().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Last name must be alphabetic and cannot be empty.");
        }
        if (!stateField.getText().matches("[A-Z]{2}")) {
            throw new IllegalArgumentException("State must be a two-letter abbreviation.");
        }
        if (!zipField.getText().matches("\\d{5}(-\\d{4})?")) {
            throw new IllegalArgumentException("Zip must be five digits or five digits followed by a hyphen and four digits.");
        }
    }

    private void updateContact(Person contact) {
        contact.setFirstName(firstNameField.getText());
        contact.setLastName(lastNameField.getText());
        contact.setAddress(addressField.getText());
        contact.setCity(cityField.getText());
        contact.setState(stateField.getText());
        contact.setZip(zipField.getText());
    }

    private void clearInputFields() {
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        cityField.clear();
        stateField.clear();
        zipField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
