package br.com.sysdesc.components.renders;

import static javax.swing.SwingConstants.CENTER;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class CheckBoxRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

	private static final long serialVersionUID = 1L;

	private JCheckBox render;

	private JCheckBox edit;

	public CheckBoxRenderer(JTable table, int column) {

		render = new JCheckBox();
		edit = new JCheckBox();

		render.setHorizontalAlignment(CENTER);
		edit.setHorizontalAlignment(CENTER);

		edit.addActionListener(e -> selectPermissao());

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);

		columnModel.getColumn(column).setCellEditor(this);
	}

	@Override
	public Object getCellEditorValue() {

		return edit.isSelected();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		edit.setSelected((Boolean) value);

		return edit;
	}

	private void selectPermissao() {

		fireEditingStopped();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		render.setSelected((Boolean) value);

		return render;
	}

}