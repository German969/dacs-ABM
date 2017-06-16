package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rol")
	int id;
	
	@Column(name="nombre",unique=true)
	String nombre;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "rol")
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Rol(){
		
	}

	public Rol(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	@Override
	public String toString(){
		return nombre;
	}
	
}
