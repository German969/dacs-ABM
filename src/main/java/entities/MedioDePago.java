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
public class MedioDePago {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_mediodepago")
	private int id;
	
	@Column(name="nombre",nullable=false)
	@Size(min=1,max=50)
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "mediodepago")
	List<Pedido> pedidos = new ArrayList<Pedido>();
	
	public MedioDePago(){
		
	}

	public MedioDePago(String nombre) {
		super();
		this.nombre = nombre;
	}

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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	@Override
	public String toString(){
		return this.getNombre();
	}
	
}
