package br.com.sysdesc.components;

import javax.swing.JTextField;

import br.com.sysdesc.components.documents.LongDocumento;

public class JNumericField extends JTextField {

	private static final long serialVersionUID = 1L;

	public JNumericField() {
		this(0);
	}

	public JNumericField(int limiteCampo) {
		super();
		setDocument(new LongDocumento(limiteCampo));
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
