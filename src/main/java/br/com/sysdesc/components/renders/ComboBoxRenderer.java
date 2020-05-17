package br.com.sysdesc.components.renders;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ComboBoxRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private static final long serialVersionUID = 1L;

    private JComboBox<Object> render;

    private JComboBox<Object> edit;

    public ComboBoxRenderer(JTable table, int column) {

        render = new JComboBox<>();
        edit = new JComboBox<>();

        edit.addActionListener(e -> selectPermissao());

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);

        columnModel.getColumn(column).setCellEditor(this);
    }

    @Override
    public Object getCellEditorValue() {

        return edit.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        edit.setSelectedItem(value);

        return edit;
    }

    public void addItem(Object item) {
        edit.addItem(item);
        render.addItem(item);
    }

    private void selectPermissao() {

        fireEditingStopped();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (isSelected) {
            render.setForeground(table.getSelectionForeground());
            render.setBackground(table.getSelectionBackground());
        } else {
            render.setForeground(table.getForeground());
            render.setBackground(table.getBackground());
        }

        render.setSelectedItem(value);

        return render;
    }

}