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

import entities.Pedido;
import entities.Pedido_;

@Stateful
public class PedidoDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
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
	
	public void delete(Pedido pedido) {

		em.remove(pedido);
		
	}
	
}
