package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class MainWindow {
    private JFrame frame;
    Vector<AddressEntry> addressEntryList = new Vector<AddressEntry>();
    JList<AddressEntry> addressEntryJList;
    DefaultListModel<AddressEntry> myaddressEntryListModel = new DefaultListModel<AddressEntry>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
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
        // make a dummy addressEntryList with 2 AddressEntry objects--Project 2 will read from Database instead,etc.
        addressEntryList.add(new AddressEntry(1,"Lynne", "Grewe", "33 A street", "Hayward", "CA", 9399,"l@csueastbay.edu","555-1212"));
        addressEntryList.add(new AddressEntry(2,"Jane", "Doe", "22 Cobble street", "Hayward", "CA", 9399,"jane@csueastbay.edu","555-9999"));

        // because we want to REMOVE or ADD to our JList we have to create it

        // from a DefaultListModel (see https://docs.oracle.com/javase/tutorial/uiswing/components/list.html)
        // to which we add the elements of our collection of AddressEntry objects
        for(int i = 0; i<addressEntryList.size(); i++) {
            this.myaddressEntryListModel.add(i, this.addressEntryList.elementAt(i));
        }

        //Now when we create our JList do it from our ListModel rather than our vector of AddressEntry
        addressEntryJList = new JList<AddressEntry>(this.myaddressEntryListModel);

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
                    AddressEntry newEntry = new AddressEntry(firstName.getText(), lastName.getText(), street.getText(), city.getText(), state.getText(), Integer.parseInt(zip.getText()), email.getText(), phone.getText());
                    addressEntryList.add(newEntry);
                    myaddressEntryListModel.addElement(newEntry);
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            // Remove item from our JList's ListModel
            public void actionPerformed(ActionEvent arg0) {
                int index = addressEntryJList.getSelectedIndex();
                if(index != -1)//something is selected otherwise do nothing
                {   //retrieve the DefaultListModel associated
                    // with our JList and remove from it the AddressEntry at this index
                    ((DefaultListModel<AddressEntry>) (addressEntryJList.getModel())).remove(index);
                    addressEntryList.remove(index);
                    // NOTE in your project 2 you will also remove it from your AddressBook.addressEntryList
                    // AND ALSO remove it from the associated database table
                }
            }
        });

        btnDisplay.addActionListener(new ActionListener() {
            // Display contents of JList's ListModel
            public void actionPerformed(ActionEvent arg0) {
                scrollPane.getViewport().remove(addressEntryJList);
                scrollPane.getViewport().add(addressEntryJList);
            }
        });

        // addressEntryJList.setCellRenderer(new AddressEntryRenderer());
        scrollPane.add(buttonPanel);
        scrollPane.setColumnHeaderView(buttonPanel);
    }
}

//class AddressEntryRenderer extends JLabel implements ListCellRenderer<AddressEntry> {
//    @Override
//    public Component getListCellRendererComponent(JList<? extends AddressEntry> list, AddressEntry value, int index, boolean isSelected, boolean cellHasFocus) {
//        StringBuffer entry = new StringBuffer();
//        entry.append(value.firstName).append(" ").append(value.lastName).append("\n");
//        setText(entry.toString());
//        return this;
//    }
//
//
//}