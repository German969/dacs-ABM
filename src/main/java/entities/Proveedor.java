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
import javax.validation.constraints.Size;

@Entity
public class Proveedor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_proveedor")
	private long id;
	
	@Column(name="nombre",nullable=false)
	@Size(min=1,max=50)
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "proveedor")
	private List<Producto> productos = new ArrayList<Producto>();
	
	public Proveedor(){
		
	}
	
	public Proveedor(String nombre) {
		super();
		this.nombre = nombre;
	}

	@Override
	public String toString(){
		
		return this.getNombre();
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	
}
