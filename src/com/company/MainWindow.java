package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class MainWindow {
    /**
     * main frame for window
     */
    private JFrame frame;
    /**
     * for displaying our addressbook's data to the scroll pane
     */
    JList<AddressEntry> addressEntryJList;
    /**
     * for modifying local address book data
     */
    static AddressBook addressBook;
    /**
     * for holding SIZE of data set
     */
    static int SIZE = 0;
    /**
     * Launch the application
     * @param args command line args
     * @throws SQLException for database operations
     * @throws ClassNotFoundException for driver connection
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
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:mcs1014/CIwblJjO@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");

        // Create a Statement
        Statement stmt = conn.createStatement ();

        // Select the all (*) from the table JAVATEST
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");
        System.out.println(rset);

        // Iterate through the result and add employees to addressBook
        while ( rset.next() ) //get next row of table returned
        {
            id = rset.getInt(1);
            firstName = rset.getString(2);
            lastName = rset.getString(3);
            street = rset.getString(4);
            city = rset.getString(5);
            state = rset.getString(6);
            zip = rset.getInt(7);
            email = rset.getString(8);
            telephone = rset.getString(9);
            addressBook.add(new AddressEntry(id, firstName, lastName, street, city, state, zip, email, telephone));
            if( id > SIZE ) { SIZE = id; }; // for finding next key to use
        }
        EventQueue.invokeLater(new Runnable() {
            /**
             * create main window
             */
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //Close access to everything...will otherwise happen when disconnect from database.
        rset.close();
        stmt.close();
        conn.close();
    }

    /**
     * Create the main window and initialize the local data from db
     */
    public MainWindow() {

        // create our JList from our ListModel
        addressEntryJList = new JList<AddressEntry>(addressBook.addressEntryList);

        //setting up the look of the JList
        this.addressEntryJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.addressEntryJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.addressEntryJList.setVisibleRowCount(-1);

        //setup GUI and use the JList we created
        initialize();
    }

    /**
     * Initialize the contents of the frame, open database connection, populate initial list from db
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel containing all buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create JPanel for searching entries
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        // Create Panel to hold button and search panel
        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new GridLayout(2, 3));
        bigPanel.add(buttonPanel);
        bigPanel.add(searchPanel);

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

        // Add Textfield and search button to search panel
        JTextField searchCriteria = new JTextField(15);
        JButton btnSearch = new JButton("Search");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchCriteria);
        searchPanel.add(btnSearch);

        btnNew.addActionListener(new ActionListener() {
            /**
             * add an item to jlists's listmodel and to the database
             * @param arg0
             */
            public void actionPerformed(ActionEvent arg0) {
                // attempt to open connection to database
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:oracle:thin:mcs1014/CIwblJjO@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

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

                // Assign input verifiers to each text field
                firstName.setInputVerifier(new AlphaVerifier());
                lastName.setInputVerifier(new AlphaVerifier());
                street.setInputVerifier(new StreetVerifier());
                city.setInputVerifier(new AlphaVerifier());
                state.setInputVerifier(new AlphaVerifier());
                zip.setInputVerifier(new ZipCodeVerifier());
                email.setInputVerifier(new EmailVerifier());
                phone.setInputVerifier(new PhoneNumberVerifier());

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
                    SIZE++; // add 1 to SIZE
                    AddressEntry newEntry = new AddressEntry(SIZE, firstName.getText(), lastName.getText(), street.getText(), city.getText(), state.getText(), Integer.parseInt(zip.getText()), email.getText(), phone.getText());
                    addressBook.add(newEntry); // add it to our address book

                    // PUSH TO THE DATABASE
                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = null;
                    try {
                        // the mysql insert statement
                        String query = " insert into ADDRESSENTRYTABLE (ID, FIRST_NAME, LAST_NAME, STREET, CITY, STATE, ZIP, EMAIL, PHONE)"
                                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        // create prepared statement for insertion
                        preparedStmt = conn.prepareStatement(query);
                        preparedStmt.setInt   (1, SIZE);
                        preparedStmt.setString(2, firstName.getText());
                        preparedStmt.setString(3, lastName.getText());
                        preparedStmt.setString(4, street.getText());
                        preparedStmt.setString(5, city.getText());
                        preparedStmt.setString(6, state.getText());
                        preparedStmt.setInt   (7, Integer.parseInt(zip.getText()));
                        preparedStmt.setString(8, email.getText());
                        preparedStmt.setString(9, phone.getText());
                        // execute the preparedstatement
                        preparedStmt.execute();
                        // close the connection
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        btnRemove.addActionListener(new ActionListener() {
            /**
             * adds an action listener for removing data from lists (local and remote)
             * @param arg0
             */
            public void actionPerformed(ActionEvent arg0) {
                int index = addressEntryJList.getSelectedIndex();
                // open connection to database
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:oracle:thin:mcs1014/CIwblJjO@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu");
                    if(index != -1)//something is selected otherwise do nothing
                    {
                        // get ID of object to delete from database
                        int toDelete = addressEntryJList.getModel().getElementAt(index).getID();
                        //retrieve the DefaultListModel associated
                        // with our JList and remove from it the AddressEntry at this index
                        ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);
                        // REMOVE FROM DATABASE
                        // create query
                        String query = "delete from ADDRESSENTRYTABLE where id = ?";
                        // create statement from query
                        PreparedStatement preparedStmt = conn.prepareStatement(query);
                        // set up statement
                        preparedStmt.setInt(1, toDelete);
                        // execute the prepared statement
                        preparedStmt.execute();
                        // close connection
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });


        btnDisplay.addActionListener(new ActionListener() {
            /**
             * adds an action listener for displaying contents from local list to scroll pane
             * @param arg0
             */
            public void actionPerformed(ActionEvent arg0) {
                scrollPane.getViewport().add(addressEntryJList);
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            /**
             * adds an action listener for displaying the search results to the scroll pane
             * @param arg0
             */
            public void actionPerformed(ActionEvent arg0) {
                JList<AddressEntry> searchResult = new JList<AddressEntry>(addressBook.find(searchCriteria.getText()));
                scrollPane.getViewport().add(searchResult);
            }
        });

        // adds the buttons to the scroll pane
        scrollPane.add(bigPanel);
        scrollPane.setColumnHeaderView(bigPanel);
    }

    /**
     * Custom input verifier that tests if the input given to a JTextField is alphabetical or the empty string
     */
    class AlphaVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            return text.matches("[a-zA-Z]+|^$");
        }
    }

    /**
     * Custom input verifier that tests if the input given to a JTextField contains numbers and alphbetical characters or the empty string
     */
    class StreetVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            return text.matches("[a-zA-Z0-9 ]+|^$");
        }
    }

    /**
     * Custom input verifier that tests if the input given to a JTextField is a 5-digit string
     */
    class ZipCodeVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            return text.matches("\\d\\d\\d\\d\\d|^$");
        }
    }

    /**
     * Custom input verifier that tests if the input given to a JTextField follows the format of an email address
     */
    class EmailVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            return text.matches("\\w+@[a-zA-Z]+\\.[a-zA-Z]+|^$");
        }
    }

    /**
     * Custom input verifier that tests if the input given to a JTextField is a 10-digit phone number separated or not separated by dashes
     */
    class PhoneNumberVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            return text.matches("\\d\\d\\d[-]?\\d\\d\\d[-]?\\d\\d\\d\\d|^$");
        }
    }
}