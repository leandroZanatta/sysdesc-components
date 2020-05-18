package br.com.sysdesc.components.listeners;

import java.util.EventListener;

@FunctionalInterface
public interface NewListener<T> extends EventListener {

    public void newEvent(T object);

}
