package ca.georgiancollege.comp1011m2022finaltest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    @FXML
    private Label saleLabel;

    @FXML
    private Label msrpLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Label rowsInTableLabel;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> totalPurchaseColumn;

    @FXML
    private ListView<Product> purchaseListView;

    @FXML
    private ImageView imageView;

    @FXML
    private void top10Customers()
    {
        System.out.println("called method top10Customers()");
    }

    @FXML
    private void customersSavedOver5()
    {
        System.out.println("called method customersSavedOver5()");
    }

    @FXML
    private void loadAllCustomers()
    {
        System.out.println("called method loadAllCustomers");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        purchaseListView.getItems().clear();

        try {
            var customerList = APIManager.Instance().getCustomerList();
            idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("Purchases"));

            tableView.getItems().addAll(customerList.getCustomers());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
