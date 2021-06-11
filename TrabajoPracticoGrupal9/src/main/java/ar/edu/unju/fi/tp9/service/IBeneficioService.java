package ar.edu.unju.fi.tp9.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp9.model.Beneficio;

public interface IBeneficioService {
	
	public Optional<Beneficio> buscarPorId(Long id);
	
	public void guardarBeneficio(Beneficio beneficio);
	
	public List<Beneficio> obtenerListaBeneficios();
	
	public void quitarBeneficioLista(Long id);

}
