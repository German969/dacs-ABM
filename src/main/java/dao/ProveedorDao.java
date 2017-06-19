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

import entities.Proveedor;
import entities.Proveedor_;

@Stateful
public class ProveedorDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
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

	public List<Proveedor> getProveedores() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Proveedor> query = builder.createQuery(Proveedor.class);

		query.from(Proveedor.class);

		TypedQuery<Proveedor> typedQuery = em.createQuery(query);

		List<Proveedor> rl = typedQuery.getResultList();

		return rl;
	}
	
}
