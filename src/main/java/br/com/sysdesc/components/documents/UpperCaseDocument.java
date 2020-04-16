package br.com.sysdesc.components.documents;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class UpperCaseDocument extends PlainDocument {

	private static final long serialVersionUID = 1L;
	private final int limiteCampo;

	public UpperCaseDocument(int limiteCampo) {
		this.limiteCampo = limiteCampo;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {

		if (str == null) {
			return;
		}

		if (limiteCampo == 0 || getLength() < this.limiteCampo) {

			super.insertString(offset, str.toUpperCase(), attr);
		}
	}
}