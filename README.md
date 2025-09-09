This is a simple Java Swing GUI application for managing an online pharmacy system. 
It provides a basic interface where users can log in, browse available medicines, add items to a shopping cart, and proceed to checkout. 
The application includes user authentication (hardcoded credentials), an inventory system that lists medicines with their price and available stock, and a checkout process that deducts purchased items from inventory. 
Upon launching the application, users are presented with a login screen.
Once authenticated (using email user@gmail.com and password 12345), the main menu allows users to view medicines, add items to their cart, view the current cart, and place an order.
The medicines are preloaded using seed data, including common medications like Paracetamol, Amoxicillin, Vitamin C, and more. Each medicine has a unique ID, name, price, and stock count.
The user interacts with the app via dialog boxes for inputs and outputs, using Java's built-in JOptionPane. 
The cart is implemented using a HashMap, and the inventory is stored in an ArrayList of custom Medicine objects. 
The code demonstrates basic object-oriented programming in Java, along with the use of Swing for creating graphical user interfaces.
To run the application, ensure you have Java JDK 8 or above installed. 
You can compile the code using javac gui.java and run it with java gui. 
