package com.appspring.app.dao;



	import java.util.List;
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	import org.springframework.stereotype.Repository;
	import org.springframework.transaction.annotation.Transactional;

	import com.appspring.app.entity.Servicios;



	@Repository
	public class ServiciosDaoImpl implements IServiciosDao {

		@PersistenceContext
		private EntityManager em;

		@SuppressWarnings("unchecked")
		@Transactional(readOnly = true)
		@Override
		public List<Servicios> findAll() {

			return em.createQuery("from Servicios").getResultList();
		}

		@Override
		@Transactional
		public void save(Servicios servicios) {

			if (servicios.getId() != null && servicios.getId() > 0) {
				em.merge(servicios);
			} else {
				em.persist(servicios);
			}

		}

		@Override
		@Transactional
		public void delete(Long id) {
			em.remove(findone(id));

		}

		@Override
		@Transactional(readOnly = true)
		public Servicios findone(Long id) {

			return em.find(Servicios.class, id);
		}

	}
