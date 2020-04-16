package br.com.sysdesc.components;

import javax.swing.JTextField;

import br.com.sysdesc.components.documents.LongDocumento;

public class JTextFieldId extends JTextField {

	private static final long serialVersionUID = 1L;

	public JTextFieldId() {
		super();

		setEditable(Boolean.FALSE);

		setDocument(new LongDocumento(0));
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
