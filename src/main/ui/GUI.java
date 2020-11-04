package ui;

import model.Collection;
import model.Sneaker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import static java.lang.Double.parseDouble;

public class GUI {

    private static final String JSON_STORE = "./data/collection.json";
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 400;

    String[] columns = {"Brand", "Model", "ColourWay", "Size", "Condition [X/10]", "Retail Value", "Resell Value"};

    JFrame frame;
    JTable table;
    JTextField brand;
    JTextField model;
    JTextField colour;
    JTextField size;
    JTextField condition;
    JTextField retail;
    JTextField resell;
    JButton buttonAdd;
    JButton buttonRemove;
    JButton buttonLoad;
    JButton buttonSave;
    DefaultTableModel tableModel;

    JsonWriter jsonWriter;
    JsonReader jsonReader;
    Collection collection;

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
        initializeJTextFields();
        initializeButtonRemove();
        initializeButtonAdd();
        initializeButtonSave();
        initializeButtonLoad();

        frame.setTitle("Sneaks");

        // create a table model and set a Column Identifiers to this model
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(tableModel);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 20, FRAME_WIDTH - 40, 200);

        frame.setLayout(null);
        frame.add(pane);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes text fields
    private void initializeJTextFields() {
        brand = new JTextField();
        model = new JTextField();
        colour = new JTextField();
        size = new JTextField();
        condition = new JTextField();
        retail = new JTextField();
        resell = new JTextField();

        brand.setBounds(20, 220, 100, 30);
        model.setBounds(130, 220, 100, 30);
        colour.setBounds(240, 220, 100, 30);
        size.setBounds(350, 220, 100, 30);
        condition.setBounds(460, 220, 100, 30);
        retail.setBounds(570, 220, 100, 30);
        resell.setBounds(680, 220, 100, 30);

        frame.add(brand);
        frame.add(model);
        frame.add(colour);
        frame.add(size);
        frame.add(condition);
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
                    tableModel.removeRow(table.getSelectedRow());
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
        row[0] = brand.getText().toUpperCase();
        row[1] = model.getText().toUpperCase();
        row[2] = colour.getText().toUpperCase();
        row[3] = size.getText();
        row[4] = condition.getText();
        row[5] = retail.getText();
        row[6] = resell.getText();
        String b = brand.getText().toUpperCase();;
        String m = brand.getText().toUpperCase();;
        String col = brand.getText().toUpperCase();;
        Double s = parseDouble(size.getText());
        Double c = parseDouble(condition.getText());
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
        brand.setText("");
        model.setText("");
        colour.setText("");
        size.setText("");
        condition.setText("");
        retail.setText("");
        resell.setText("");
    }

}
