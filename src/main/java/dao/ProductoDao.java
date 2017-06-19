package dao;

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

import entities.Producto;
import entities.Producto_;

@Stateful
public class ProductoDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager pem;
	
	public void create(Producto p) {

		try {
			pem.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Producto> getProductos() {

		CriteriaBuilder builder = pem.getCriteriaBuilder();

		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);

		query.from(Producto.class);

		TypedQuery<Producto> typedQuery = pem.createQuery(query);

		return typedQuery.getResultList();

	}

	public List<Producto> getProductoByFilter(String name, String filterBy) {

		CriteriaBuilder builder = pem.getCriteriaBuilder();

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

		TypedQuery<Producto> typedQuery = pem.createQuery(query);

		List<Producto> rl = typedQuery.getResultList();

		return rl;

	}
	
	public void update(Producto producto){
		pem.merge(producto);
	}
	
	public void delete(Producto producto) {

		pem.remove(producto);
		
	}
	
}
