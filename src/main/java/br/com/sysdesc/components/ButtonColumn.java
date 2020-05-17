package br.com.sysdesc.components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.com.sysdesc.components.listeners.CollumnActionListener;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private JButton renderButton;
    private JButton editButton;
    private String text;
    protected EventListenerList buttonListener = new EventListenerList();

    public ButtonColumn(JTable table, int column) {
        this(table, column, "...");
    }

    public ButtonColumn(JTable table, int column, String text) {
        this(table, column, text, null);
    }

    public ButtonColumn(JTable table, int column, String text, Icon icon) {
        super();

        this.text = text;

        this.table = table;

        initComponents(column, icon);
    }

    private void initComponents(int column, Icon icon) {
        renderButton = new JButton(this.text);
        renderButton.setIcon(icon);

        editButton = new JButton(this.text);
        editButton.setIcon(icon);
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(table.getBackground());
        }

        renderButton.setText(text);

        return renderButton;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        editButton.setText(text);

        return editButton;
    }

    public Object getCellEditorValue() {

        return text;
    }

    public void actionPerformed(ActionEvent e) {

        fireEditingStopped();

        fireButtonListener(table.getSelectedRow());

        ((AbstractTableModel) table.getModel()).fireTableDataChanged();
    }

    public void addButtonListener(CollumnActionListener actionListener) {

        buttonListener.add(CollumnActionListener.class, actionListener);
    }

    private void fireButtonListener(int row) {

        Object[] listeners = buttonListener.getListenerList();

        for (int i = 0; i < listeners.length; i = i + 2) {

            if (listeners[i] == CollumnActionListener.class) {

                ((CollumnActionListener) listeners[i + 1]).buttonClicked(row);
            }
        }
    }
}
