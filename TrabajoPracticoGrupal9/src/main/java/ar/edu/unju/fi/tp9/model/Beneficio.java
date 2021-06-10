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
	@Column(name = "ben_desc")
	private int dctoJubilados;


	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "beneficios")
	private List<Cliente> clientes = new ArrayList<Cliente>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public int getDctoJubilados() {
		return dctoJubilados;
	}


	public void setDctoJubilados(int dctoJubilados) {
		this.dctoJubilados = dctoJubilados;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public Beneficio(Long id, int dctoJubilados, List<Cliente> clientes) {
		super();
		this.id = id;
		this.dctoJubilados = dctoJubilados;
		this.clientes = clientes;
	}


	public Beneficio() {
		super();
	}


	@Override
	public String toString() {
		return "Beneficio [id=" + id + ", dctoJubilados=" + dctoJubilados + ", clientes=" + clientes + "]";
	}
}

	
