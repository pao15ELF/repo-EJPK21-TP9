package ar.edu.unju.fi.tp9.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "BENEFICIOS")
public class Beneficio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ben_id")
	private Long id;
	@Column(name = "ben_descripcion")
	private String descripcion;


	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "beneficios")
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public Beneficio() {
		super();
	}
	
	/**
	 * @param id
	 * @param descripcion
	 * @param clientes
	 */
	public Beneficio(Long id, String descripcion, List<Cliente> clientes) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.clientes = clientes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return "Beneficio [id=" + id + ", descripcion=" + descripcion + ", clientes=" + clientes + "]";
	}

	
	
}

	
