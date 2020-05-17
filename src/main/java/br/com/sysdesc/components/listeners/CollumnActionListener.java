package br.com.sysdesc.components.listeners;

import java.util.EventListener;

@FunctionalInterface
public interface CollumnActionListener extends EventListener {

	public void buttonClicked(int collumn);
}
