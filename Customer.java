
package hotelmanagementsystem;
import java.util.*;

public class Customer extends Person {
    public Customer(String name, String contact) {
        super(name, contact);
    }

    @Override
    public void displayInfo() {
        System.out.println("Customer Name: " + name);
        System.out.println("Contact: " + contact);
    }

    public void displayCustomer() {
        displayInfo(); // call the overridden method
    }
}

