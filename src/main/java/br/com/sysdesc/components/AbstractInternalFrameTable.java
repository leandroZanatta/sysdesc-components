package br.com.sysdesc.components;

import javax.swing.table.AbstractTableModel;

public abstract class AbstractInternalFrameTable extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	public abstract void clear();

	public void setEnabled(Boolean enabled) {

	};
}
