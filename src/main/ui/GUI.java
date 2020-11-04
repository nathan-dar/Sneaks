package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI {

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
    DefaultTableModel tableModel;

    public GUI() {
        runSneaksGUI();
    }

    // EFFECTS: runs the sneaks app gui
    private void runSneaksGUI() {
        frame = new JFrame();
        table = new JTable();
        initializeJTextFields();
        initializeButtonRemove();
        initializeButtonAdd();

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

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if (i >= 0) {
                    // remove a row from JTable
                    tableModel.removeRow(i);
                } else {
                    System.out.println("Delete Error");
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
                Object[] row = new Object[7];
                row[0] = brand.getText().toUpperCase();
                row[1] = model.getText().toUpperCase();
                row[2] = colour.getText().toUpperCase();
                row[3] = size.getText().toUpperCase();
                row[4] = condition.getText().toUpperCase();
                row[5] = retail.getText().toUpperCase();
                row[6] = resell.getText().toUpperCase();

                tableModel.addRow(row);
                clearTextFields();
            }
        });
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
