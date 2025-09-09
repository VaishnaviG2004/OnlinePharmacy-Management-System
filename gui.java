import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



class Medicine {
    int id;
    String name;
    double price;
    int stock;

    Medicine(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return id + ") " + name + " - ₹" + price + " (Stock: " + stock + ")";
    }
}

public class gui {
    static java.util.List<Medicine> inventory = new ArrayList<>();
    static Map<Medicine, Integer> cart = new HashMap<>();

    public static void main(String[] args) {
        seedData();
        SwingUtilities.invokeLater(gui::loginScreen);
    }

    // LOGIN SCREEN
    static void loginScreen() {
        JFrame frame = new JFrame("Pharmacy Login");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        frame.add(emailLabel); frame.add(emailField);
        frame.add(passLabel); frame.add(passField);
        frame.add(new JLabel("")); frame.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String pass = new String(passField.getPassword());

            if (email.equals("user@gmail.com") && pass.equals("12345")) {
                JOptionPane.showMessageDialog(frame, "Login Successful ✅");
                frame.dispose();
                mainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Login Failed ❌");
            }
        });

        frame.setVisible(true);
    }

    // MAIN MENU
    static void mainMenu() {
        JFrame frame = new JFrame("Online Pharmacy 🏥");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1, 5, 5));

        JButton viewBtn = new JButton("View Medicines");
        JButton addBtn = new JButton("Add to Cart");
        JButton cartBtn = new JButton("View Cart");
        JButton checkoutBtn = new JButton("Checkout");
        JButton exitBtn = new JButton("Exit");

        frame.add(viewBtn);
        frame.add(addBtn);
        frame.add(cartBtn);
        frame.add(checkoutBtn);
        frame.add(exitBtn);

        viewBtn.addActionListener(e -> viewMedicines());
        addBtn.addActionListener(e -> addToCart());
        cartBtn.addActionListener(e -> viewCart());
        checkoutBtn.addActionListener(e -> checkout());
        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // SEED DATA
    static void seedData() {
        inventory.add(new Medicine(1, "Paracetamol 500mg", 20, 50));
        inventory.add(new Medicine(2, "Amoxicillin 500mg", 45, 30));
        inventory.add(new Medicine(3, "Vitamin C 500mg", 15, 40));
        inventory.add(new Medicine(4, "Cetirizine 10mg", 13, 45));
        inventory.add(new Medicine(5, "Azithromycin 500mg", 40, 50));
        inventory.add(new Medicine(6, "Montek LC 50mg", 30, 35));
        inventory.add(new Medicine(7, "Dolo-650 650mg",10 , 50));
        inventory.add(new Medicine(8, "ibuprofen 400mg", 30, 60));
        inventory.add(new Medicine(9, "Benadryl 50ml", 233, 45));
        inventory.add(new Medicine(10, "CIPLOX 10ML", 35, 20));
    }

    // VIEW MEDICINES
    static void viewMedicines() {
        StringBuilder sb = new StringBuilder("Available Medicines:\n");
        for (Medicine m : inventory) sb.append(m).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // ADD TO CART
    static void addToCart() {
        String idStr = JOptionPane.showInputDialog("Enter Medicine ID:");
        if (idStr == null) return;
        int id = Integer.parseInt(idStr);

        Medicine m = null;
        for (Medicine x : inventory) {
            if (x.id == id) {
                m = x;
                break;
            }
        }

        if (m == null) {
            JOptionPane.showMessageDialog(null, "Not found.");
            return;
        }

        String qtyStr = JOptionPane.showInputDialog("Enter Quantity:");
        int qty = Integer.parseInt(qtyStr);

        if (qty <= 0 || qty > m.stock) {
            JOptionPane.showMessageDialog(null, "Invalid quantity.");
            return;
        }

        cart.put(m, cart.getOrDefault(m, 0) + qty);
        JOptionPane.showMessageDialog(null, qty + " x " + m.name + " added to cart.");
    }

    // VIEW CART
    static void viewCart() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- Cart ---\n");
        double total = 0;
        for (var entry : cart.entrySet()) {
            Medicine m = entry.getKey();
            int qty = entry.getValue();
            double cost = qty * m.price;
            total += cost;
            sb.append(m.name).append(" x ").append(qty).append(" = ₹").append(cost).append("\n");
        }
        sb.append("Total = ₹").append(total);
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // CHECKOUT
    static void checkout() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Confirm checkout?", "Checkout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            for (var entry : cart.entrySet()) {
                Medicine m = entry.getKey();
                int qty = entry.getValue();
                m.stock -= qty;
            }
            cart.clear();
            JOptionPane.showMessageDialog(null, "Order placed successfully ✅");
        }
    }
}

    


