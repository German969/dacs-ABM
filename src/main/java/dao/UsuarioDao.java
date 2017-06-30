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

import abm.UsuarioFilter;
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
		
		UsuarioFilter uf = new UsuarioFilter();
		
		uf.setCod(filterBy);

		switch (filterBy) {

		case "1":

			int foo = Integer.parseInt(name);

//			p = builder.equal(root.get(Usuario_.id), foo);
			
			p = builder.equal(root.get(uf.getAttr(Integer.parseInt(filterBy))), foo);

			break;

		case "2":
			
			p = builder.like(root.get(uf.getAttr(filterBy)), "%" + name + "%");

//			p = builder.like(root.get(Usuario_.nombre), "%" + name + "%");

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
	
}
