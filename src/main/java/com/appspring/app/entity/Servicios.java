package com.appspring.app.entity;



	import java.io.Serializable;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;
	
	import javax.validation.constraints.NotEmpty;


	@Entity
	@Table(name = "servicios")
	public class Servicios  implements Serializable {

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotEmpty
		private String nombre;
		
		@NotEmpty
		private String encargado;
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getEncargado() {
			return encargado;
		}

		public void setEncargado(String encargado) {
			this.encargado = encargado;
		}

		
	}