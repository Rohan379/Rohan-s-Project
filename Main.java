import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Item class
class Item {
    protected String name;
    protected int quantity;
    protected double price;

    // Constructor
    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Display item details
    public void displayDetails() {
        System.out.println("Name: " + name + ", Quantity: " + quantity + ", Price: " + price);
    }
}

// Inventory class
class Inventory {
    private ArrayList<Item> items;

    // Constructor
    public Inventory() {
        items = new ArrayList<>();
    }

    // Add a new item
    public void addItem(Item item) {
        items.add(item);
    }

    // Update an existing item
    public void updateItem(String itemName, int quantity, double price) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                item.setQuantity(quantity);
                item.setPrice(price);
                return;
            }
        }
        System.out.println("Item not found: " + itemName);
    }

    // Display all items
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Item item : items) {
                item.displayDetails();
            }
        }
    }
}

// ExceptionHandler class
class ExceptionHandler {

    public static void handleException(Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    }

    public static void handleInputMismatchException(InputMismatchException e) {
        System.out.println("Invalid input. Please enter the correct data type.");
    }

    public static void handleNoSuchElementException(NoSuchElementException e) {
        System.out.println("Input was expected but not provided.");
    }

    public static void handleIllegalArgumentException(IllegalArgumentException e) {
        System.out.println("Illegal argument provided: " + e.getMessage());
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("1. Add Item");
                System.out.println("2. Update Item");
                System.out.println("3. Display Inventory");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Add new item
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        inventory.addItem(new Item(name, quantity, price));
                        break;
                    
                    case 2:
                        // Update existing item
                        System.out.print("Enter item name to update: ");
                        String itemName = scanner.nextLine();
                        System.out.print("Enter new quantity: ");
                        quantity = scanner.nextInt();
                        System.out.print("Enter new price: ");
                        price = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        inventory.updateItem(itemName, quantity, price);
                        break;

                    case 3:
                        // Display inventory
                        inventory.displayInventory();
                        break;
                    
                    case 4:
                        // Exit
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                ExceptionHandler.handleInputMismatchException(e);
                scanner.next(); // Clear invalid input
            } catch (NoSuchElementException e) {
                ExceptionHandler.handleNoSuchElementException(e);
            } catch (IllegalArgumentException e) {
                ExceptionHandler.handleIllegalArgumentException(e);
            } catch (Exception e) {
                ExceptionHandler.handleException(e);
            }
        }
    }
}
