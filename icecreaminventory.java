Import utility classes like List, ArrayList, HashMap
import java.util.*;

// IceCream class represents one ice cream item
class IceCream {

    String flavor;     // Name of the ice cream flavor
    String category;   // Category (Classic, Premium, Seasonal)
    double price;      // Price of the ice cream
    int stock;         // Quantity available in inventory

    // Constructor - initializes ice cream object when created
    IceCream(String flavor, String category, double price, int stock) {
        this.flavor = flavor;       // Assign flavor name
        this.category = category;   // Assign category
        this.price = price;         // Assign price
        this.stock = stock;         // Assign stock quantity
    }

    // toString() method defines how object prints when displayed
    public String toString() {
        return flavor + " | " + category + " | ₱" + price + " | Stock: " + stock;
    }
}

// Node class for Binary Search Tree
class Node {

    IceCream data;   // Each node stores one IceCream object
    Node left;       // Reference to left child
    Node right;      // Reference to right child

    // Constructor to create a new node
    Node(IceCream data) {
        this.data = data;  // Store ice cream inside the node
        left = null;       // Initially no left child
        right = null;      // Initially no right child
    }
}

// Inventory system class
class IceCreamInventory {

    Node root;   // Root of the Binary Search Tree

    // HashMap for fast lookup (flavor → IceCream)
    HashMap<String, IceCream> inventoryMap = new HashMap<>();


    // ===============================
    // INSERT INTO BST (Recursive)
    // ===============================
    Node insert(Node node, IceCream iceCream) {

        // If current position is empty, create new node
        if (node == null) {
            return new Node(iceCream);
        }

        // Compare flavor alphabetically
        // If new flavor comes BEFORE current node
        if (iceCream.flavor.compareTo(node.data.flavor) < 0) {

            // Insert into left subtree (recursive call)
            node.left = insert(node.left, iceCream);

        } else {

            // Otherwise insert into right subtree
            node.right = insert(node.right, iceCream);
        }

        // Return unchanged node pointer
        return node;
    }


    // ===============================
    // SEARCH IN BST (Recursive)
    // ===============================
    IceCream search(Node node, String flavor) {

        // If node is null, flavor not found
        if (node == null)
            return null;

        // If flavor matches current node
        if (flavor.equals(node.data.flavor))
            return node.data;

        // If searched flavor is alphabetically smaller
        if (flavor.compareTo(node.data.flavor) < 0)

            // Search left subtree
            return search(node.left, flavor);
        else

            // Search right subtree
            return search(node.right, flavor);
    }


    // ===============================
    // INORDER TRAVERSAL (Sorted Print)
    // ===============================
    void inorder(Node node) {

        // Base condition
        if (node != null) {

            // Visit left subtree first
            inorder(node.left);

            // Print current node
            System.out.println(node.data);

            // Visit right subtree
            inorder(node.right);
        }
    }


    // ===============================
    // MERGE SORT (Sort by Price)
    // ===============================
    List<IceCream> mergeSort(List<IceCream> list) {

        // Base case: if list has 1 or 0 elements, it's already sorted
        if (list.size() <= 1)
            return list;

        // Find middle index
        int mid = list.size() / 2;

        // Recursively sort left half
        List<IceCream> left = mergeSort(list.subList(0, mid));

        // Recursively sort right half
        List<IceCream> right = mergeSort(list.subList(mid, list.size()));

        // Merge sorted halves
        return merge(left, right);
    }


    // ===============================
    // MERGE TWO SORTED LISTS
    // ===============================
    List<IceCream> merge(List<IceCream> left, List<IceCream> right) {

        // Create result list
        List<IceCream> result = new ArrayList<>();

        int i = 0;  // Pointer for left list
        int j = 0;  // Pointer for right list

        // Compare elements from both lists
        while (i < left.size() && j < right.size()) {

            // Compare prices
            if (left.get(i).price < right.get(j).price) {

                // Add smaller price to result
                result.add(left.get(i));
                i++;  // Move left pointer

            } else {

                // Add right element
                result.add(right.get(j));
                j++;  // Move right pointer
            }
        }

        // Add remaining elements from left list
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }

        // Add remaining elements from right list
        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        // Return fully merged sorted list
        return result;
    }
}


// ===============================
// MAIN CLASS
// ===============================
public class Main {

    public static void main(String[] args) {

        // Create inventory system object
        IceCreamInventory inventory = new IceCreamInventory();

        // Create sample ice cream objects
        IceCream i1 = new IceCream("Vanilla", "Classic", 50, 20);
        IceCream i2 = new IceCream("Chocolate", "Classic", 55, 15);
        IceCream i3 = new IceCream("Matcha", "Premium", 75, 10);
        IceCream i4 = new IceCream("Mango", "Seasonal", 60, 12);

        // Insert into BST (sorted by flavor)
        inventory.root = inventory.insert(inventory.root, i1);
        inventory.root = inventory.insert(inventory.root, i2);
        inventory.root = inventory.insert(inventory.root, i3);
        inventory.root = inventory.insert(inventory.root, i4);

        // Store also in HashMap for fast lookup
        inventory.inventoryMap.put(i1.flavor, i1);
        inventory.inventoryMap.put(i2.flavor, i2);
        inventory.inventoryMap.put(i3.flavor, i3);
        inventory.inventoryMap.put(i4.flavor, i4);

        // Display inventory alphabetically
        System.out.println("=== Alphabetical Inventory (BST Inorder) ===");
        inventory.inorder(inventory.root);

        // Search using BST (recursive search)
        System.out.println("\n=== Search using BST ===");
        System.out.println(inventory.search(inventory.root, "Matcha"));

        // Search using HashMap (O(1) lookup)
        System.out.println("\n=== Search using HashMap ===");
        System.out.println(inventory.inventoryMap.get("Matcha"));

        // Convert HashMap values into list for sorting
        List<IceCream> list = new ArrayList<>(inventory.inventoryMap.values());

        // Sort by price using Merge Sort
        List<IceCream> sortedByPrice = inventory.mergeSort(list);

        // Display sorted result
        System.out.println("\n=== Sorted by Price (Merge Sort) ===");
        for (IceCream ice : sortedByPrice) {
            System.out.println(ice);
        }
    }
}
