package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.models.Person;
import sample.util.DateUtil;

import java.awt.*;
import java.security.PrivateKey;

public class PersonEditController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameTextField.getText());
            person.setLastName(lastNameTextField.getText());
            person.setStreet(streetTextField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeTextField.getText()));
            person.setCity(cityTextField.getText());
            person.setBirthday(DateUtil.parse(birthdayTextField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameTextField.setText(person.getFirstName());
        lastNameTextField.setText(person.getLastName());
        streetTextField.setText(person.getStreet());
        postalCodeTextField.setText(Integer.toString(person.getPostalCode()));
        cityTextField.setText(person.getCity());
        birthdayTextField.setText(DateUtil.format(person.getBirthdate()));


    }

    public boolean isOkClicked() {
        return okClicked;
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameTextField.getText() == null || firstNameTextField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameTextField.getText() == null || lastNameTextField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetTextField.getText() == null || streetTextField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeTextField.getText() == null || postalCodeTextField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // пытаемся преобразовать почтовый код в int.
            try {
                Integer.parseInt(postalCodeTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code Only numeric!\n";
            }
        }

        if (cityTextField.getText() == null || cityTextField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayTextField.getText() == null || birthdayTextField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayTextField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
