package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import entities.Usuario;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int id;
	
	@Column(name = "fecha",nullable=false)
	private Date fecha;
	
	@Column(name = "estado",nullable=false)
	private String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario",nullable=false,updatable=false)
	private Usuario usuario;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pedidos")
	private List<Producto> productos;
	
	@Column(name = "fechaPago")
	private Date fechaPago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mediodepago")
	private MedioDePago mediodepago;
	
	@Transient
	private boolean canEdit;
	
	@Transient
	private boolean newPedido;
	
	@Transient
	private String stringFecha;
	
	@Transient
	private String stringFechaPago;
	
	@Transient
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getStringFecha() {
		return stringFecha;
	}

	public String getStringFechaPago() {
		return stringFechaPago;
	}

	public void setStringFecha(String stringFecha) {
		this.stringFecha = stringFecha;
	}

	public void setStringFechaPago(String stringFechaPago) {
		this.stringFechaPago = stringFechaPago;
	}

	public Pedido(){
		
	}
	
	public Pedido(Date fecha, String estado, Usuario usuario,Date fechapago,MedioDePago mediodepago) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.usuario = usuario;
		this.fechaPago = fechapago;
		this.mediodepago = mediodepago;
		this.stringFecha = formatter.format(fecha);
		this.stringFechaPago = formatter.format(fechapago);
	}



	public Usuario getUsuario(){
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public MedioDePago getMediodepago() {
		return mediodepago;
	}

	public void setMediodepago(MedioDePago mediodepago) {
		this.mediodepago = mediodepago;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public boolean isNewPedido() {
		return newPedido;
	}

	public void setNewPedido(boolean newPedido) {
		this.newPedido = newPedido;
	}
		
}

