package model;

import java.io.Serializable;
import java.util.Objects;

public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String numero;
	
	private Usuarios usuario_id;
	private Usuarios usuario_cad_id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Usuarios getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Usuarios usuario_id) {
		this.usuario_id = usuario_id;
	}
	public Usuarios getUsuario_cad_id() {
		return usuario_cad_id;
	}
	public void setUsuario_cad_id(Usuarios usuario_cad_id) {
		this.usuario_cad_id = usuario_cad_id;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	

}
