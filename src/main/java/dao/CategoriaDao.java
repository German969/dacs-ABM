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

import entities.Categoria;
import entities.Categoria_;

@Stateful
public class CategoriaDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
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
	
public List<Categoria> getCategorias() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);

		query.from(Categoria.class);

		TypedQuery<Categoria> typedQuery = em.createQuery(query);

		List<Categoria> c = typedQuery.getResultList();

		return c;
	}
	
}
