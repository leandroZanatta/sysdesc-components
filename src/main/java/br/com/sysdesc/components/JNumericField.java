package br.com.sysdesc.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import br.com.sysdesc.components.documents.LongDocument;

public class JNumericField extends JTextField {

    private static final long serialVersionUID = 1L;

    public JNumericField() {
        this(0);
    }

    public JNumericField(int limiteCampo) {
        super();
        setDocument(new LongDocument(limiteCampo));
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    transferFocus();
                }
            }
        });
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
