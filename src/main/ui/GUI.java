package ui;

import model.Collection;
import model.Sneaker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import static java.lang.Double.parseDouble;

// Represents the sneaks app GUI
public class GUI {

    private static final String JSON_STORE = "./data/collection.json";
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private static final String[] COLUMNS =
            {"Brand", "Model", "ColourWay", "Size", "Condition", "Retail Value", "Resell Value"};
    private static final String[] BRAND_OPTIONS =
            {"---", "ADIDAS", "ASICS", "CONVERSE", "DESIGNER", "FILA", "JORDAN", "NEW BALANCE", "NIKE", "REEBOK",
                    "SKECHERS", "UNDER ARMOUR", "VANS"};
    private static final String[] SIZE_OPTIONS =
            {"---", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12",
                    "12.5", "13"};
    private static final String[] CONDITION_OPTIONS =
            {"---", "0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7",
                    "7.5", "8", "8.5", "9", "9.5", "10"};

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> brandChoice;
    private JTextField model;
    private JTextField colour;
    private JComboBox<String> sizeChoice;
    private JComboBox<String> conditionChoice;
    private JTextField retail;
    private JTextField resell;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Collection collection;

    public GUI() {
        runSneaksGUI();
    }

    // EFFECTS: runs the sneaks app gui
    private void runSneaksGUI() {
        frame = new JFrame();
        collection = new Collection("User's Collection");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeSneakerInputs();
        initializeJLabels();
        initializeButtonRemove();
        initializeButtonAdd();
        initializeButtonSave();
        initializeButtonLoad();
        initializeTable();

        frame.setLayout(null);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Sneaks");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: initializes JTable, DefaultTableModel, and JScrollPane
    private void initializeTable() {
        table = new JTable();
        tableModel = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        tableModel.setColumnIdentifiers(COLUMNS);
        table.setModel(tableModel);
        table.setRowHeight(100);
        customizeTable();

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(275, 25, 700, 525);
        frame.add(pane);
    }

    // MODIFIES: this
    // EFFECTS: customizes the table
    private void customizeTable() {
        table.setRowHeight(75);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("", Font.PLAIN, 13));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableHeader.setDefaultRenderer(centerRenderer);
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(Double.class, centerRenderer);

        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(75);
        table.getColumnModel().getColumn(3).setPreferredWidth(75);
        table.getColumnModel().getColumn(4).setPreferredWidth(125);
        table.getColumnModel().getColumn(6).setPreferredWidth(125);

    }

    // MODIFIES: this
    // EFFECTS: initializes input boxes and selection boxes
    private void initializeSneakerInputs() {
        brandChoice = new JComboBox<>(BRAND_OPTIONS);
        model = new JTextField();
        colour = new JTextField();
        sizeChoice = new JComboBox<>(SIZE_OPTIONS);
        conditionChoice = new JComboBox<>(CONDITION_OPTIONS);
        retail = new JTextField();
        resell = new JTextField();

        brandChoice.setBounds(50, 50, 175, 30);
        model.setBounds(50, 100, 175, 30);
        colour.setBounds(50, 150, 175, 30);
        sizeChoice.setBounds(50, 200, 175, 30);
        conditionChoice.setBounds(50, 250, 175, 30);
        retail.setBounds(50, 300, 175, 30);
        resell.setBounds(50, 350, 175, 30);

        frame.add(brandChoice);
        frame.add(model);
        frame.add(colour);
        frame.add(sizeChoice);
        frame.add(conditionChoice);
        frame.add(retail);
        frame.add(resell);
    }

    // MODIFIES: this
    // EFFECTS: initialize JLabels
    private void initializeJLabels() {
        JLabel brandText = new JLabel("Brand:");
        JLabel modelText = new JLabel("Model:");
        JLabel colourwayText = new JLabel("Colourway:");
        JLabel sizeText = new JLabel("Size:");
        JLabel conditionText = new JLabel("Condition:");
        JLabel retailText = new JLabel("Retail Value:");
        JLabel resellText = new JLabel("Resell Value:");

        brandText.setBounds(55, 30, 150, 30);
        modelText.setBounds(55, 80, 150, 30);
        colourwayText.setBounds(55, 130, 150, 30);
        sizeText.setBounds(55, 180, 150, 30);
        conditionText.setBounds(55, 230, 150, 30);
        retailText.setBounds(55, 280, 150, 30);
        resellText.setBounds(55, 330, 150, 30);

        frame.add(brandText);
        frame.add(modelText);
        frame.add(colourwayText);
        frame.add(sizeText);
        frame.add(conditionText);
        frame.add(retailText);
        frame.add(resellText);
    }

    // MODIFIES: this
    // EFFECTS: initializes add button
    private void initializeButtonAdd() {
        JButton buttonAdd = new JButton("Add");
        buttonAdd.setBounds(25, 400, 100, 30);
        frame.add(buttonAdd);

        buttonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addSneakerFromFields();
                clearInputFields();

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes remove button
    private void initializeButtonRemove() {
        JButton buttonRemove = new JButton("Remove");
        buttonRemove.setBounds(150, 400, 100, 30);
        frame.add(buttonRemove);

        buttonRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() >= 0) {
                    int i = table.getSelectedRow();
                    Sneaker s = collection.getSneaker(i);
                    tableModel.removeRow(i);
                    collection.removeSneaker(i);
                    JOptionPane.showMessageDialog(frame,
                            s.getBrand() + " " + s.getModel() + " " + s.getColourway() + " has been removed.",
                            "Sneaks - Sneaker Removed",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes save button
    private void initializeButtonSave() {
        JButton buttonSave = new JButton("Save");
        buttonSave.setBounds(25, 450, 100, 30);
        frame.add(buttonSave);

        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveCollection();
                JOptionPane.showMessageDialog(frame,
                        "The collection has been saved.",
                        "Sneaks - Collection Saved",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes add button
    private void initializeButtonLoad() {
        JButton buttonLoad = new JButton("Load");
        buttonLoad.setBounds(150, 450, 100, 30);
        frame.add(buttonLoad);

        buttonLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadCollection();
                JOptionPane.showMessageDialog(frame,
                        "The collection has been loaded.",
                        "Sneaks - Collection Loaded",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: gets text from fields and add it to the collection
    private void addSneakerFromFields() {
        Icon brandIcon = new ImageIcon("./data/brandPhotos/" + brandChoice.getSelectedItem() + ".jpg");

        Object[] row = new Object[7];
        row[0] = brandIcon;
        row[1] = model.getText().toUpperCase();
        row[2] = colour.getText().toUpperCase();
        row[3] = sizeChoice.getSelectedItem();
        row[4] = conditionChoice.getSelectedItem();
        row[5] = retail.getText();
        row[6] = resell.getText();
        Sneaker sneakerToAdd = new Sneaker(
                brandChoice.getSelectedItem().toString(),
                row[1].toString(),
                row[2].toString(),
                parseDouble(row[3].toString()),
                parseDouble(row[4].toString()),
                parseDouble(row[5].toString()),
                parseDouble(row[6].toString()));
        collection.addSneaker(sneakerToAdd);
        tableModel.addRow(row);
        JOptionPane.showMessageDialog(frame,
                brandChoice.getSelectedItem() + " " + row[1] + " " + row[2] + " has been added.",
                "Sneaks - Sneaker Added",
                JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: saves collection to file
    private void saveCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(collection);
            jsonWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads collection from file
    private void loadCollection() {
        try {
            collection = jsonReader.read();
            tableModel.setRowCount(0);
            for (int i = 0; i < collection.collectionSize(); i++) {
                Sneaker s = collection.getSneaker(i);
                Icon brandIcon = new ImageIcon("./data/brandPhotos/"
                        + s.getBrand() + ".jpg");
                Object[] row = new Object[7];
                row[0] = brandIcon;
                row[1] = s.getModel();
                row[2] = s.getColourway();
                row[3] = s.getShoeSize();
                row[4] = s.getCondition();
                row[5] = s.getRetailValue();
                row[6] = s.getResellValue();
                tableModel.addRow(row);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: clears text field boxes
    private void clearInputFields() {
        brandChoice.setSelectedIndex(0);
        model.setText("");
        colour.setText("");
        sizeChoice.setSelectedIndex(0);
        conditionChoice.setSelectedIndex(0);
        retail.setText("");
        resell.setText("");
    }

}
