package ui;

import model.Collection;
import model.Sneaker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static java.lang.Double.parseDouble;

// Represents the sneaks app GUI
public class GUI {

    private static final String JSON_STORE = "./data/collection.json";
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 400;
    private static final String[] COLUMNS =
            {"Brand", "Model", "ColourWay", "Size", "Condition", "Retail Value", "Resell Value"};
    private static final String[] BRAND_OPTIONS = {"ASICS", "CONVERSE", "DESIGNER", "FILA", "JORDAN", "NEW BALANCE",
            "NIKE", "REEBOK", "SKETCHERS", "UNDER ARMOUR", "VANS"};
    private static final String[] SIZE_OPTIONS =
            {"5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12", "12.5",
                    "13"};
    private static final String[] CONDITION_OPTIONS =
            {"0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9",
                    "9.5", "10"};

    private JFrame frame;
    private JTable table;
    private JComboBox<String> brandChoice;
    private JTextField model;
    private JTextField colour;
    private JComboBox<String> sizeChoice;
    private JComboBox<String> conditionChoice;
    private JTextField retail;
    private JTextField resell;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton buttonLoad;
    private JButton buttonSave;
    private DefaultTableModel tableModel;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Collection collection;

    public GUI() {
        runSneaksGUI();
    }

    // EFFECTS: runs the sneaks app gui
    private void runSneaksGUI() {
        frame = new JFrame();
        table = new JTable();
        collection = new Collection("User's Collection");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeSneakerInputs();
        initializeButtonRemove();
        initializeButtonAdd();
        initializeButtonSave();
        initializeButtonLoad();

        frame.setTitle("Sneaks");

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(COLUMNS);
        table.setModel(tableModel);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, FRAME_WIDTH - 40, 200);
        frame.add(pane);

        frame.setLayout(null);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: initializes input boxes and selection boxes
    private void initializeSneakerInputs() {
        brandChoice = new JComboBox<String>(BRAND_OPTIONS);
        model = new JTextField();
        colour = new JTextField();
        sizeChoice = new JComboBox<String>(SIZE_OPTIONS);
        conditionChoice = new JComboBox<String>(CONDITION_OPTIONS);
        retail = new JTextField();
        resell = new JTextField();

        brandChoice.setBounds(20, 220, 100, 30);
        model.setBounds(130, 220, 100, 30);
        colour.setBounds(240, 220, 100, 30);
        sizeChoice.setBounds(350, 220, 100, 30);
        conditionChoice.setBounds(460, 220, 100, 30);
        retail.setBounds(570, 220, 100, 30);
        resell.setBounds(680, 220, 100, 30);

        frame.add(brandChoice);
        frame.add(model);
        frame.add(colour);
        frame.add(sizeChoice);
        frame.add(conditionChoice);
        frame.add(retail);
        frame.add(resell);
    }

    // MODIFIES: this
    // EFFECTS: initializes remove button
    private void initializeButtonRemove() {
        buttonRemove = new JButton("Remove");
        buttonRemove.setBounds(500, 300, 100, 30);
        frame.add(buttonRemove);

        buttonRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() >= 0) {
                    int i = table.getSelectedRow();
                    tableModel.removeRow(i);
                    collection.removeSneaker(i);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes add button
    private void initializeButtonAdd() {
        buttonAdd = new JButton("Add");
        buttonAdd.setBounds(200, 300, 100, 30);
        frame.add(buttonAdd);

        buttonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addSneakerFromFields();
                clearTextFields();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: gets text from fields and add it to the collection
    private void addSneakerFromFields() {
        Object[] row = new Object[7];
        row[0] = brandChoice.getSelectedItem();
        row[1] = model.getText().toUpperCase();
        row[2] = colour.getText().toUpperCase();
        row[3] = sizeChoice.getSelectedItem();
        row[4] = conditionChoice.getSelectedItem();
        row[5] = retail.getText();
        row[6] = resell.getText();
        String b = row[0].toString();
        String m = model.getText().toUpperCase();
        String col = colour.getText().toUpperCase();
        Double s = parseDouble(row[3].toString());
        Double c = parseDouble(row[4].toString());
        Double ret = parseDouble(retail.getText());
        Double res = parseDouble(resell.getText());
        collection.addSneaker(new Sneaker(b, m, col, s, c, ret, res));
        tableModel.addRow(row);
    }

    // MODIFIES: this
    // EFFECTS: initializes save button
    private void initializeButtonSave() {
        buttonSave = new JButton("Save");
        buttonSave.setBounds(500, 330, 100, 30);
        frame.add(buttonSave);

        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveCollection();
            }
        });
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
    // EFFECTS: initializes add button
    private void initializeButtonLoad() {
        buttonLoad = new JButton("Load");
        buttonLoad.setBounds(200, 330, 100, 30);
        frame.add(buttonLoad);

        buttonLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadCollection();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: loads collection from file
    private void loadCollection() {
        try {
            collection = jsonReader.read();
            tableModel.setRowCount(0); // wipes the current table
            for (int i = 0; i < collection.collectionSize(); i++) {
                Object[] row = new Object[7];
                Sneaker s = collection.getSneaker(i);
                row[0] = s.getBrand();
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
    private void clearTextFields() {
        model.setText("");
        colour.setText("");
        retail.setText("");
        resell.setText("");
    }

}
