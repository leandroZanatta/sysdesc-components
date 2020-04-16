package br.com.sysdesc.components;

import javax.swing.JTextField;

import br.com.sysdesc.components.documents.UpperCaseDocument;

public class JTextFieldMaiusculo extends JTextField {

	private static final long serialVersionUID = 1L;

	public JTextFieldMaiusculo() {
		this(0);
	}

	public JTextFieldMaiusculo(int limiteCampo) {
		super();

		setDocument(new UpperCaseDocument(limiteCampo));
	}

}
