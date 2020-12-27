package br.com.sysdesc.components;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.event.EventListenerList;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

import br.com.sysdesc.components.listeners.ChangeListener;

public class JMoneyField extends JFormattedTextField {

	private static final long serialVersionUID = -5712106034509737967L;
	private static final SimpleAttributeSet nullAttribute = new SimpleAttributeSet();
	private NumberFormat numberFormat = NumberFormat.getNumberInstance();
	protected EventListenerList buttonListener = new EventListenerList();
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
		this.addFocusListener(new FocusAdapter() {

			private BigDecimal value;

			@Override
			public void focusGained(FocusEvent e) {

				value = getValue();
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (getValue().compareTo(value) != 0) {
					fireChangeListener();
				}
			}
		});
		this.setText("0");

		this.addCaretListener(e -> {
			if (e.getDot() != getText().length()) {
				getCaret().setDot(getText().length());
			}
		});

	}

	protected void fireChangeListener() {

		Object[] listeners = buttonListener.getListenerList();

		for (int i = 0; i < listeners.length; i = i + 2) {

			if (listeners[i] == ChangeListener.class) {

				((ChangeListener) listeners[i + 1]).valueChanged(getValue());
			}
		}
	}

	public void setCasasDecimais(int casasDecimais) {
		this.casasDecimais = casasDecimais;

		numberFormat.setMaximumFractionDigits(casasDecimais);
		numberFormat.setMinimumFractionDigits(casasDecimais);
	}

	public void addChangeValue(ChangeListener<BigDecimal> changeListener) {

		buttonListener.add(ChangeListener.class, changeListener);
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

				String contatenado = original + str;

				String sinal = "";

				if (contatenado.contains("-") && !contatenado.contains("+") && !contatenado.equals("-0,0") && !contatenado.equals("0,00-")) {
					sinal = "-";
				}

				super.insertString(0, (sinal + formatar(contatenado.replaceAll("[^0-9]", ""))), null);
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
