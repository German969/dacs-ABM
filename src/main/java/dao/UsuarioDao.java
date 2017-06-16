package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entities.Categoria;
import entities.Categoria_;
import entities.MedioDePago;
import entities.MedioDePago_;
import entities.Pedido;
import entities.Pedido_;
import entities.Producto;
import entities.Producto_;
import entities.Proveedor;
import entities.Proveedor_;
import entities.Rol;
import entities.Rol_;
import entities.Usuario;
import entities.Usuario_;

@Stateful
public class UsuarioDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	// ---------------- USUARIO -------------------- //

	public void create(Usuario u) {

		try {
			em.persist(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);

		query.from(Usuario.class);

		TypedQuery<Usuario> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();

	}

	public List<Usuario> getByFilter(String name, String filterBy) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);

		Root<Usuario> root = query.from(Usuario.class);

		Predicate p;

		switch (filterBy) {

		case "1":

			int foo = Integer.parseInt(name);

			p = builder.equal(root.get(Usuario_.id), foo);

			break;

		case "2":

			p = builder.like(root.get(Usuario_.nombre), "%" + name + "%");

			break;

		case "3":

			p = builder.like(root.get(Usuario_.apellido), "%" + name + "%");

			break;

		case "4":

			p = builder.like(root.get(Usuario_.cuit), "%" + name + "%");

			break;

		case "5":

			p = builder.like(root.get(Usuario_.user), "%" + name + "%");

			break;

		case "6":

			p = builder.like(root.get(Usuario_.password), "%" + name + "%");

			break;

		case "7":

			p = builder.like(root.get(Usuario_.telefono), "%" + name + "%");

			break;

		case "8":

			p = builder.like(root.get(Usuario_.pais), "%" + name + "%");

			break;

		case "9":

			p = builder.like(root.get(Usuario_.provincia), "%" + name + "%");

			break;

		case "10":

			p = builder.like(root.get(Usuario_.localidad), "%" + name + "%");

			break;

		case "11":

			p = builder.like(root.get(Usuario_.codigoPostal), "%" + name + "%");

			break;

		case "12":

			p = builder.like(root.get(Usuario_.direccion), "%" + name + "%");

			break;

		case "13":

			p = builder.like(root.get(Usuario_.rol.getName()), "%" + name + "%");

			break;

		default:

			p = builder.like(root.get(Usuario_.nombre), "%" + name + "%");

			break;
		}

		query.where(p);

		TypedQuery<Usuario> typedQuery = em.createQuery(query);

		List<Usuario> rl = typedQuery.getResultList();

		return rl;

	}

	public void update(Usuario u) {

		em.merge(u);

	}

	public void delete(Usuario u) {

		em.remove(u);

	}

	public void createRol(Rol r) {

		try {
			em.persist(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Rol getRol(String name) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Rol> query = builder.createQuery(Rol.class);

		Root<Rol> root = query.from(Rol.class);

		Predicate p = builder.like(root.get(Rol_.nombre), "%" + name + "%");

		query.where(p);

		TypedQuery<Rol> typedQuery = em.createQuery(query);

		Rol r = typedQuery.getSingleResult();

		return r;
	}

	public List<Rol> getRoles() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Rol> query = builder.createQuery(Rol.class);

		query.from(Rol.class);

		TypedQuery<Rol> typedQuery = em.createQuery(query);

		List<Rol> r = typedQuery.getResultList();

		return r;

	}

	// ---------------- PRODUCTO -------------------- //

	public void create(Producto p) {

		try {
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Producto> getProductos() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);

		query.from(Producto.class);

		TypedQuery<Producto> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();

	}

	public List<Producto> getProductoByFilter(String name, String filterBy) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);

		Root<Producto> root = query.from(Producto.class);

		Predicate p;

		switch (filterBy) {

		case "1":

			int foo = Integer.parseInt(name);

			p = builder.equal(root.get(Producto_.id), foo);

			break;

		case "2":

			p = builder.like(root.get(Producto_.nombre), "%" + name + "%");

			break;

		case "3":

			p = builder.like(root.get(Producto_.marca), "%" + name + "%");

			break;
			
		case "4":
			
			p = builder.like(root.get(Producto_.categoria.getName()), "%" + name + "%");

			break;
			
		case "5":
			
			p = builder.like(root.get(Producto_.proveedor.getName()), "%" + name + "%");

			break;

		default:

			p = builder.like(root.get(Producto_.nombre), "%" + name + "%");

			break;
		}

		query.where(p);

		TypedQuery<Producto> typedQuery = em.createQuery(query);

		List<Producto> rl = typedQuery.getResultList();

		return rl;

	}
	
	public void update(Producto producto){
		em.merge(producto);
	}
	
	public Categoria getCategoria(String name) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);

		Root<Categoria> root = query.from(Categoria.class);

		Predicate p = builder.like(root.get(Categoria_.nombre), "%" + name + "%");

		query.where(p);

		TypedQuery<Categoria> typedQuery = em.createQuery(query);

		Categoria C = typedQuery.getSingleResult();

		return C;
	}
	
	public Proveedor getProveedor(String name) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Proveedor> query = builder.createQuery(Proveedor.class);

		Root<Proveedor> root = query.from(Proveedor.class);

		Predicate p = builder.like(root.get(Proveedor_.nombre), "%" + name + "%");

		query.where(p);

		TypedQuery<Proveedor> typedQuery = em.createQuery(query);

		Proveedor sr = typedQuery.getSingleResult();

		return sr;
	}

	public List<Categoria> getCategorias() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);

		query.from(Categoria.class);

		TypedQuery<Categoria> typedQuery = em.createQuery(query);

		List<Categoria> c = typedQuery.getResultList();

		return c;
	}

	public List<Proveedor> getProveedores() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Proveedor> query = builder.createQuery(Proveedor.class);

		query.from(Proveedor.class);

		TypedQuery<Proveedor> typedQuery = em.createQuery(query);

		List<Proveedor> rl = typedQuery.getResultList();

		return rl;
	}

	public void delete(Producto producto) {

		em.remove(producto);
		
	}
	
	// ---------------- PEDIDO -------------------- //
	
	public void create(Pedido p) {

		try {
			em.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Pedido> getPedidos() {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Pedido> query = builder.createQuery(Pedido.class);

		query.from(Pedido.class);

		TypedQuery<Pedido> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();

	}

	public List<Pedido> getPedidoByFilter(String name, String filterBy) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Pedido> query = builder.createQuery(Pedido.class);

		Root<Pedido> root = query.from(Pedido.class);

		Predicate p;

		switch (filterBy) {

		case "1":

			int foo = Integer.parseInt(name);

			p = builder.equal(root.get(Pedido_.id), foo);

			break;

		case "2":
			
			Date date = formatter.parse(name);

			p = builder.equal(root.get(Pedido_.fecha), date);

			break;

		case "3":

			p = builder.like(root.get(Pedido_.estado), "%" + name + "%");

			break;
			
		case "4":
			
			p = builder.like(root.get(Pedido_.usuario.getName()), "%" + name + "%");

			break;

		default:

			int foo2 = Integer.parseInt(name);

			p = builder.equal(root.get(Pedido_.id), foo2);

			break;
		}

		query.where(p);

		TypedQuery<Pedido> typedQuery = em.createQuery(query);

		List<Pedido> rl = typedQuery.getResultList();

		return rl;

	}
	
	public void update(Pedido pedido){
		em.merge(pedido);
	}
	
	public Usuario getUsuario(String name) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);

		Root<Usuario> root = query.from(Usuario.class);

		Predicate p = builder.like(root.get(Usuario_.nombre), "%" + name + "%");

		query.where(p);

		TypedQuery<Usuario> typedQuery = em.createQuery(query);

		Usuario u = typedQuery.getSingleResult();

		return u;
	}
	
	public MedioDePago getMediodepago(String name) {

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<MedioDePago> query = builder.createQuery(MedioDePago.class);

		Root<MedioDePago> root = query.from(MedioDePago.class);

		Predicate p = builder.like(root.get(MedioDePago_.nombre), "%" + name + "%");

		query.where(p);

		TypedQuery<MedioDePago> typedQuery = em.createQuery(query);

		MedioDePago mp = typedQuery.getSingleResult();

		return mp;
	}

	public List<MedioDePago> getMediosDePago() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<MedioDePago> query = builder.createQuery(MedioDePago.class);

		query.from(MedioDePago.class);

		TypedQuery<MedioDePago> typedQuery = em.createQuery(query);

		List<MedioDePago> rl = typedQuery.getResultList();

		return rl;
	}

	public void delete(Pedido pedido) {

		em.remove(pedido);
		
	}
	
}
