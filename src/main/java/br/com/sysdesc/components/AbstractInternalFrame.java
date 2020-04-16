package br.com.sysdesc.components;

import javax.swing.JInternalFrame;

public class AbstractInternalFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private final Long codigoUsuario;

	private final Long codigoPrograma;

	public AbstractInternalFrame(Long codigoPrograma, Long codigoUsuario) {
		this.codigoPrograma = codigoPrograma;
		this.codigoUsuario = codigoUsuario;

	}

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public Long getCodigoPrograma() {
		return codigoPrograma;
	}

}
