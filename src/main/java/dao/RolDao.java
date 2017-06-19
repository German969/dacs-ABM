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

import entities.Rol;
import entities.Rol_;

@Stateful
public class RolDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
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
	
}
