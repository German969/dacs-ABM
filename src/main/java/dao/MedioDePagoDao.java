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

import entities.MedioDePago;
import entities.MedioDePago_;

@Stateful
public class MedioDePagoDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
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
	
}
