import java.util.*;

/*
 * ===============================
 * TRANSACTION CLASS
 * ===============================
 */
class Transaction {

    String transactionID;
    String flavor;
    String size;
    double price;
    int quantitySold;
    int startingInventory;
    int inventoryLeft;

    public Transaction(String id, String flavor, String size,
                       double price, int qtySold, int startInv) {

        this.transactionID = id;
        this.flavor = flavor;
        this.size = size;
        this.price = price;
        this.quantitySold = qtySold;
        this.startingInventory = startInv;
        this.inventoryLeft = startInv - qtySold;
    }

    public void display() {
        System.out.println(
            transactionID + " | " +
            flavor + " | " +
            size + " | Start: " + startingInventory +
            " | Sold: " + quantitySold +
            " | Left: " + inventoryLeft
        );
    }
}

/*
 * ===============================
 * INVENTORY SYSTEM WITH SEARCH MENU
 * ===============================
 */
public class InventorySystem {

    // Shared inventory list (newest transactions at top)
    static LinkedList<Transaction> inventory = new LinkedList<>();

    // Scanner for user input
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Preload sample data (simulates dataset)
        preloadData();

        int choice;

        do {
            // Display menu options
            System.out.println("\n=== INVENTORY SEARCH MENU ===");
            System.out.println("1. View All Transactions");
            System.out.println("2. Search by Transaction ID");
            System.out.println("3. Search by Flavor");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAll();
                    break;

                case 2:
                    searchByID();
                    break;

                case 3:
                    searchByFlavor();
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);
    }

    /* ===============================
       PRELOAD DATA
       =============================== */
    static void preloadData() {
        inventory.addFirst(new Transaction("T10009", "Strawberry", "Medium", 109.68, 15, 137));
        inventory.addFirst(new Transaction("T10008", "Chocolate", "Medium", 117.40, 3, 176));
        inventory.addFirst(new Transaction("T10007", "Strawberry", "Small", 119.62, 9, 130));
        inventory.addFirst(new Transaction("T10006", "Cookies", "Medium", 115.85, 2, 85));
    }

    /* ===============================
       VIEW ALL TRANSACTIONS
       =============================== */
    static void viewAll() {
        System.out.println("\n--- ALL TRANSACTIONS ---");

        for (Transaction t : inventory) {
            t.display();
        }
    }

    /* ===============================
       SEARCH BY TRANSACTION ID
       =============================== */
    static void searchByID() {
        System.out.print("\nEnter Transaction ID: ");
        String id = scanner.nextLine();

        boolean found = false;

        for (Transaction t : inventory) {
            if (t.transactionID.equalsIgnoreCase(id)) {
                t.display();
                found = true;
                break; // ID is unique
            }
        }

        if (!found) {
            System.out.println("Transaction not found.");
        }
    }

    /* ===============================
       SEARCH BY FLAVOR
       =============================== */
    static void searchByFlavor() {
        System.out.print("\nEnter Flavor: ");
        String flavor = scanner.nextLine();

        boolean found = false;

        for (Transaction t : inventory) {
            if (t.flavor.equalsIgnoreCase(flavor)) {
                t.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for this flavor.");
        }
    }
}
