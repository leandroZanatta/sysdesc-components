package br.com.sysdesc.components;

import javax.swing.JTextField;

import br.com.sysdesc.components.documents.LongDocument;

public class JTextFieldId extends JTextField {

	private static final long serialVersionUID = 1L;

	public JTextFieldId() {
		super();

		setEditable(Boolean.FALSE);

		setDocument(new LongDocument(0));
	}

	public Long getValue() {
		try {
			return Long.valueOf(getText());
		} catch (Exception e) {
			return null;
		}
	}

	public void setValue(Long value) {
		if (value != null) {
			setText(value.toString());
		}
	}

}
