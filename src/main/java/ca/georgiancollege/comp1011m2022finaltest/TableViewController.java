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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    private ArrayList<Customer> saved5Dollars = new ArrayList<>();

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
        purchaseListView.getItems().clear();
        tableView.getItems().clear();
        tableView.getSelectionModel().select(0);

        try {
//            idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
//            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
//            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
//            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
//            totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("Purchases"));

            Customer[] customer5dollars = new Customer[this.saved5Dollars.size()];
            for (int i = 0; i < customer5dollars.length; i++) {
                customer5dollars[i] = this.saved5Dollars.get(i);
            }

            tableView.getItems().addAll(customer5dollars);
            rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            if (this.saved5Dollars.isEmpty()) {
                for (var customer : customerList.getCustomers()) {
                    if(Double.parseDouble(customer.getDiscounts().substring(1)) > 5.00d) {
//                        System.out.println(customer.getFirstName());
                        saved5Dollars.add(customer);
                    }
                }
            }

            rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());

            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldCustomer, newCustomer) -> {
                purchaseListView.getItems().clear();
                if (newCustomer.getProducts() != null) {
                    purchaseListView.getItems().addAll(newCustomer.getProducts());
                    purchaseListView.getSelectionModel().select(0);

                    msrpLabel.setText(String.format("Total Regular Price: $%.2f", Double.parseDouble(newCustomer.getOriginalPurchases().substring(1))));
                    saleLabel.setText(String.format("Total Sale Price: $%.2f", Double.parseDouble(newCustomer.getPurchases().substring(1))));
                    savingsLabel.setText(String.format("Total Savings: $%.2f", Double.parseDouble(newCustomer.getDiscounts().substring(1))));
                }
            });
        } catch (IOException e) {
            System.out.println("Customer does not belong in the selected category!");
            e.printStackTrace();
        }

    }
}
