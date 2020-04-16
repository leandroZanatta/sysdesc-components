package br.com.sysdesc.components;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

public class JMoneyField extends JFormattedTextField {

	private static final long serialVersionUID = -5712106034509737967L;
	private static final SimpleAttributeSet nullAttribute = new SimpleAttributeSet();
	private NumberFormat numberFormat = NumberFormat.getNumberInstance();
	private int casasDecimais;

	public JMoneyField() {
		this(2);
	}

	public JMoneyField(int casasDecimais) {
		this.casasDecimais = casasDecimais;

		numberFormat.setMaximumFractionDigits(casasDecimais);
		numberFormat.setMinimumFractionDigits(casasDecimais);

		this.setHorizontalAlignment(LEFT);

		this.setDocument(new MoneyFieldDocument());
		this.addFocusListener(new MoneyFieldFocusListener());
		this.setText("0");

		this.addCaretListener(e -> {
			if (e.getDot() != getText().length()) {
				getCaret().setDot(getText().length());
			}
		});

	}

	public void setCasasDecimais(int casasDecimais) {
		this.casasDecimais = casasDecimais;

		numberFormat.setMaximumFractionDigits(casasDecimais);
		numberFormat.setMinimumFractionDigits(casasDecimais);
	}

	@Override
	public BigDecimal getValue() {

		return BigDecimal.valueOf(Double.valueOf(super.getText().replace(".", "").replace(",", ".")));
	}

	public void setValue(BigDecimal value) {

		if (value == null) {
			setText("");

			return;
		}

		setText(numberFormat.format(value.doubleValue()));
	}

	private final class MoneyFieldFocusListener extends FocusAdapter {

		@Override
		public void focusGained(FocusEvent e) {
			selectAll();
		}
	}

	private final class MoneyFieldDocument extends PlainDocument {

		private static final long serialVersionUID = -3802846632709128803L;

		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

			String original = getText(0, getLength());

			if (original.length() < 16) {

				super.remove(0, getLength());

				super.insertString(0, formatar((original + str).replaceAll("[^0-9]", "")), null);
			}

		}

		@Override
		public void remove(int offs, int len) throws BadLocationException {
			if (len == getLength()) {
				super.remove(0, len);
				if (offs != -1) {
					insertString(0, "", nullAttribute);
				}
			} else {
				String original = getText(0, getLength()).substring(0, offs)
						+ getText(0, getLength()).substring(offs + len);
				super.remove(0, getLength());
				insertString(0, original, null);
			}
		}

		private String formatar(String str) {

			StringBuilder valor = new StringBuilder(str);

			while (valor.length() <= casasDecimais) {
				valor.insert(0, "0");
			}

			if (casasDecimais > 0) {

				valor.insert(valor.length() - casasDecimais, ".");

			}

			return numberFormat.format(Double.valueOf(valor.toString()));
		}
	}

}
