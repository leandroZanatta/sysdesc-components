package br.com.sysdesc.components.listeners;

import java.util.EventListener;

@FunctionalInterface
public interface ComboBoxActionListener extends EventListener {

    public void actionPerformed(int row, Object value);
}
