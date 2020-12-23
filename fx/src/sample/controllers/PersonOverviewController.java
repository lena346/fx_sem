package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.models.Person;
import javafx.scene.control.TableColumn;
import sample.util.DateUtil;


public class PersonOverviewController {
    @FXML
    private TableView<Person> personTableView;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private Main main;

    public PersonOverviewController(){

    }
    @FXML
    private void initialize(){
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

        //showPersonDetail(null);

        personTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showPersonDetail(newValue))
        );
    }
    @FXML
    private void handleDeletePerson(){
        int selectedIndex = personTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
            personTableView.getItems().remove(selectedIndex);
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Выберите пользователя из таблицы");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleNewPerson(){
        Person tempPerson = new Person();
        boolean okClicked = main.showPersonEditDialog(tempPerson);
        if (okClicked) {
            main.getPersonData().add(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetail(selectedPerson);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }

    private void showPersonDetail(Person person){
        if (person != null){
            firstNameLabel.setText(person.getFirstName());
            streetLabel.setText(person.getStreet());
            lastNameLabel.setText(person.getLastName());
            cityLabel.setText((person.getCity()));
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            birthdayLabel.setText(DateUtil.format(person.getBirthdate()));
        }else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            birthdayLabel.setText("");

        }
    }

    public void setMainApp(Main main){
        this.main = main;
        personTableView.setItems(this.main.getPersonData());
    }






}
