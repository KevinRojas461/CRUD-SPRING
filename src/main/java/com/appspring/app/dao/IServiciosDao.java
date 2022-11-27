package com.appspring.app.dao;

	import java.util.List;

	import com.appspring.app.entity.Servicios;

	public interface IServiciosDao {

		public List<Servicios> findAll();

		public void save(Servicios servicios);

		public void delete(Long id);

		public Servicios findone(Long id);

	}