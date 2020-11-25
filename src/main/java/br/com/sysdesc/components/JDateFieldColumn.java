package br.com.sysdesc.components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

public class JDateFieldColumn extends AbstractCellEditor
		implements TableCellRenderer, TableCellEditor, ActionListener {

	private static final long serialVersionUID = 1L;

	private JFormattedTextField renderButton;
	private JFormattedTextField editButton;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public JDateFieldColumn(JTable table, int column) {
		super();

		try {
			MaskFormatter mascaraRender = new MaskFormatter("##/##/####");
			mascaraRender.setPlaceholderCharacter('_');
			MaskFormatter mascaraEdit = new MaskFormatter("##/##/####");
			mascaraEdit.setPlaceholderCharacter('_');

			renderButton = new JFormattedTextField(mascaraRender);

			editButton = new JFormattedTextField(mascaraEdit);

			editButton.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {

					if (e.getKeyCode() == KeyEvent.VK_ENTER) {

						fireEditingStopped();
					}
				}
			});

			editButton.addFocusListener(new FocusAdapter() {

				@Override
				public void focusLost(FocusEvent e) {

					fireEditingStopped();
				}
			});
		} catch (ParseException e) {

		}

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(table.getSelectionBackground());
			renderButton.setBorder(table.getBorder());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("TextField.background"));
			renderButton.setBorder(table.getBorder());
		}

		renderButton.setText(simpleDateFormat.format((Date) value));

		return renderButton;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		editButton.setValue(simpleDateFormat.format((Date) value));

		return editButton;
	}

	public Object getCellEditorValue() {

		try {
			return simpleDateFormat.parseObject(editButton.getText());
		} catch (ParseException e) {
			return null;
		}
	}

	public void actionPerformed(ActionEvent e) {

		fireEditingStopped();
	}

}
