package ca.georgiancollege.comp1011m2022finaltest;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TableViewController implements Initializable {

    private ArrayList<Customer> saved5Dollars = new ArrayList<>();
    private List<Customer> topSpenders = new ArrayList<>();

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
        purchaseListView.getItems().clear();
        tableView.getItems().clear();

        try {
            Customer[] topSpenders = new Customer[10];
            for (int i = 0; i < 10; i++) {
                topSpenders[i] = this.topSpenders.get(i);
            }

            tableView.getItems().addAll(topSpenders);
            rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        tableView.getSelectionModel().select(0);
    }

    @FXML
    private void customersSavedOver5()
    {
        purchaseListView.getItems().clear();
        tableView.getItems().clear();

        try {
            Customer[] customer5dollars = new Customer[this.saved5Dollars.size()];
            for (int i = 0; i < customer5dollars.length; i++) {
                customer5dollars[i] = this.saved5Dollars.get(i);
            }

            tableView.getItems().addAll(customer5dollars);
            rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableView.getSelectionModel().select(0);
    }

    @FXML
    private void loadAllCustomers()
    {
        purchaseListView.getItems().clear();
        tableView.getItems().clear();
        tableView.getSelectionModel().select(0);

        try {
            var customerList = APIManager.Instance().getCustomerList();
            tableView.getItems().addAll(customerList.getCustomers());
        } catch (Exception e) {
            e.printStackTrace();
        }

        msrpLabel.setText("Total Regular Price:");
        saleLabel.setText("Total Sale Price:");
        savingsLabel.setText("Total Savings:");
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
                    topSpenders.add(customer);
                    if(Double.parseDouble(customer.getDiscounts().substring(1)) > 5.00d) {
                        saved5Dollars.add(customer);
                    }
                }
                Collections.sort(topSpenders);
            }

            rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());

            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldCustomer, newCustomer) -> {
                purchaseListView.getItems().clear();
                if (newCustomer != null && newCustomer.getProducts() != null) {

                    purchaseListView.getItems().addAll(newCustomer.getProducts());
                    purchaseListView.getSelectionModel().select(0);
                    // Performance needs to be improved
                    purchaseListView.getSelectionModel().selectedItemProperty().addListener((obse, oldPurchase, newPurchase) -> {
                        if (newPurchase != null) {
                            try {
                                imageView.setImage(new Image(newPurchase.getPoster()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            imageView.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/b/b1/Grey_background.jpg"));
                        }
                    });

                    msrpLabel.setText(String.format("Total Regular Price: $%.2f", Double.parseDouble(newCustomer.getOriginalPurchases().substring(1))));
                    saleLabel.setText(String.format("Total Sale Price: $%.2f", Double.parseDouble(newCustomer.getPurchases().substring(1))));
                    savingsLabel.setText(String.format("Total Savings: $%.2f", Double.parseDouble(newCustomer.getDiscounts().substring(1))));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
