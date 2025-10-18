import java.util.*;


class InventoryItem {
    private String name;

    public InventoryItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof InventoryItem)) return false;
        InventoryItem other = (InventoryItem) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}


public class InventoryManagementSystem {
    private List<InventoryItem> arrayListInventory = new ArrayList<>();
    private List<InventoryItem> linkedListInventory = new LinkedList<>();
    private Set<InventoryItem> hashSetInventory = new HashSet<>();
    private Set<InventoryItem> treeSetInventory = new TreeSet<>(Comparator.comparing(InventoryItem::getName));

    // Add item to all collections
    public void addItem(String itemName) {
        InventoryItem item = new InventoryItem(itemName);
        arrayListInventory.add(item);
        linkedListInventory.add(item);

        if (!hashSetInventory.add(item)) {
            System.out.println(itemName + " already exists in HashSet.");
        }
        if (!treeSetInventory.add(item)) {
            System.out.println(itemName + " already exists in TreeSet.");
        }
    }

    // Remove item from all collections
    public void removeItem(String itemName) {
        InventoryItem item = new InventoryItem(itemName);
        boolean removed = arrayListInventory.remove(item) |
                          linkedListInventory.remove(item) |
                          hashSetInventory.remove(item) |
                          treeSetInventory.remove(item);

        if (removed) {
            System.out.println(itemName + " removed from inventory.");
        } else {
            System.out.println(itemName + " not found in inventory.");
        }
    }

    // Print all items in ArrayList
    public void printArrayListInventory() {
        System.out.println("ArrayList Inventory:");
        for (InventoryItem item : arrayListInventory) {
            {
                System.out.println(item);
            }
        }
    }

    // Print all items in LinkedList
    public void printLinkedListInventory() {
        System.out.println("LinkedList Inventory:");
        for (InventoryItem item : linkedListInventory) {
            {
                System.out.println(item);
            }
        }
    }

    // Print all items in HashSet
    public void printHashSetInventory() {
        System.out.println("HashSet Inventory:");
        for (InventoryItem item : hashSetInventory) {
            {
                System.out.println(item);
            }
        }
    }

    // Print all items in TreeSet
    public void printTreeSetInventory() {
        System.out.println("TreeSet Inventory:");
        for (InventoryItem item : treeSetInventory) {
            {
                System.out.println(item);
            }
        }
    }

    // Check if item exists in HashSet
    public boolean isDuplicateInHashSet(String itemName) {
        return hashSetInventory.contains(new InventoryItem(itemName));
    }

    // Check if item exists in TreeSet
    public boolean isDuplicateInTreeSet(String itemName) {
        return treeSetInventory.contains(new InventoryItem(itemName));
    }

    
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nInventory Management System Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Print ArrayList Inventory");
            System.out.println("4. Print LinkedList Inventory");
            System.out.println("5. Print HashSet Inventory");
            System.out.println("6. Print TreeSet Inventory");
            System.out.println("7. Check if Item Exists in HashSet");
            System.out.println("8. Check if Item Exists in TreeSet");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: {
                    System.out.print("Enter item name to add: ");
                    String addName = scanner.nextLine();
                    ims.addItem(addName);
                    break;
                }
                case 2: {
                    System.out.print("Enter item name to remove: ");
                    String removeName = scanner.nextLine();
                    ims.removeItem(removeName);
                    break;
                }
                case 3: {
                    ims.printArrayListInventory();
                    break;
                }
                case 4: {
                    ims.printLinkedListInventory();
                    break;
                }
                case 5: {
                    ims.printHashSetInventory();
                    break;
                }
                case 6: {
                    ims.printTreeSetInventory();
                    break;
                }
                case 7: {
                    System.out.print("Enter item name to check in HashSet: ");
                    String checkHashSet = scanner.nextLine();
                    if (ims.isDuplicateInHashSet(checkHashSet)) {
                        System.out.println("Item exists in HashSet.");
                    } else {
                        System.out.println("Item does not exist in HashSet.");
                    }
                    break;
                }
                case 8: {
                    System.out.print("Enter item name to check in TreeSet: ");
                    String checkTreeSet = scanner.nextLine();
                    if (ims.isDuplicateInTreeSet(checkTreeSet)) {
                        System.out.println("Item exists in TreeSet.");
                    } else {
                        System.out.println("Item does not exist in TreeSet.");
                    }
                    break;
                }
                case 9: {
                    System.out.println("Exiting...");
                    break;
                }
                default: {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } while (choice != 9);
        scanner.close();
    }
}