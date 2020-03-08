// adding manny's comment

package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class MainWindow {
    private JFrame frame;
    JList<AddressEntry> addressEntryJList;  // for displaying local data
    static AddressBook addressBook; // for containing local data
    static int SIZE = 0;    // for holding size of data set
    /**
     * Launch the application.
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        addressBook = new AddressBook(); // for holding address entries
        // dummy address entry fields
        int id;
        String firstName;
        String lastName;
        String street;
        String city;
        String state;
        int zip;
        String telephone;
        String email;

        // Load the Oracle JDBC driver
        Class.forName("oracle.jdbc.OracleDriver");

        // Connect to the database
        // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
        // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
        //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
        Connection conn =
                DriverManager.getConnection("jdbc:oracle:thin:mcs1014/CIwblJjO@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

        // Create a Statement
        Statement stmt = conn.createStatement ();

        // Select the all (*) from the table JAVATEST
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");
        System.out.println(rset);

        // Iterate through the result and add employees to addressBook
        while ( rset.next() ) //get next row of table returned
        {
            id = rset.getInt(0);
            firstName = rset.getString(1);
            lastName = rset.getString(2);
            street = rset.getString(3);
            city = rset.getString(4);
            state = rset.getString(5);
            zip = rset.getInt(6);
            email = rset.getString(7);
            telephone = rset.getString(8);
            addressBook.add(id, firstName, lastName, street, city, state, zip, email, telephone);
            SIZE++; // for counting number of entries read
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainWindow() {

        //Now when we create our JList do it from our ListModel rather than our vector of AddressEntry
        addressEntryJList = new JList<AddressEntry>(addressBook.addressEntryList);

        //setting up the look of the JList
        this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.addressEntryJList.setVisibleRowCount(-1);

        //setup GUI and use the JList we created
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel containing all buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,5));

        // create scrollPane associated with JList
        JScrollPane scrollPane = new JScrollPane();
        // JScrollPane scrollPane = new JScrollPane(this.addressEntryJList);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Creating buttons to remove, add a new entry, and display entries
        JButton btnRemove = new JButton("Remove");
        JButton btnNew = new JButton("New");
        JButton btnDisplay = new JButton("Display");

        // Adding buttons to the button panel
        buttonPanel.add(btnNew);
        buttonPanel.add(btnRemove);
        buttonPanel.add(btnDisplay);

        btnNew.addActionListener(new ActionListener() {
            // Add item to JList's ListModel
            public void actionPerformed(ActionEvent arg0) {
                JPanel newEntryPanel = new JPanel();
                newEntryPanel.setLayout(new GridLayout(8,10));

                // Create input text boxes
                JTextField firstName = new JTextField(15);
                JTextField lastName = new JTextField(15);
                JTextField street = new JTextField(15);
                JTextField city = new JTextField(15);
                JTextField state = new JTextField(15);
                JTextField zip = new JTextField(15);
                JTextField email = new JTextField(15);
                JTextField phone = new JTextField(15);

                // Add to JPanel
                newEntryPanel.add(new JLabel("First Name:"));
                newEntryPanel.add(firstName);
                newEntryPanel.add(new JLabel("Last Name:"));
                newEntryPanel.add(lastName);
                newEntryPanel.add(new JLabel("Street:"));
                newEntryPanel.add(street);
                newEntryPanel.add(new JLabel("City:"));
                newEntryPanel.add(city);
                newEntryPanel.add(new JLabel("State:"));
                newEntryPanel.add(state);
                newEntryPanel.add(new JLabel("Zip:"));
                newEntryPanel.add(zip);
                newEntryPanel.add(new JLabel("Email:"));
                newEntryPanel.add(email);
                newEntryPanel.add(new JLabel("Phone Number:"));
                newEntryPanel.add(phone);

                String[] buttons = {"Confirm" , "Cancel"};

                int c = JOptionPane.showOptionDialog(btnNew, newEntryPanel, "Add new entry",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);

                if(c == 0) {
                    SIZE++; // add 1 to size
                    AddressEntry newEntry = new AddressEntry(SIZE, firstName.getText(), lastName.getText(), street.getText(), city.getText(), state.getText(), Integer.parseInt(zip.getText()), email.getText(), phone.getText());
                    addressBook.add(newEntry); // add it to our address book

                    // PUSH TO THE DATABASE
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            // Remove item from our JList's ListModel
            public void actionPerformed(ActionEvent arg0) {
                int index = addressEntryJList.getSelectedIndex();
                if(index != -1)//something is selected otherwise do nothing
                {
                    //retrieve the DefaultListModel associated
                    // with our JList and remove from it the AddressEntry at this index
                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);
                    // REMOVE FROM DATABASE
                }
            }
        });

        btnDisplay.addActionListener(new ActionListener() {
            // Display contents of JList's ListModel
            public void actionPerformed(ActionEvent arg0) {
                scrollPane.getViewport().add(addressEntryJList);
            }
        });

        // adds the buttons to the scroll pane
        scrollPane.add(buttonPanel);
        scrollPane.setColumnHeaderView(buttonPanel);
    }
}