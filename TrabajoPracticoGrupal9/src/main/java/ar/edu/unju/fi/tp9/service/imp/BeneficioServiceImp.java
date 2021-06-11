package ar.edu.unju.fi.tp9.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp9.model.Beneficio;
import ar.edu.unju.fi.tp9.service.IBeneficioService;

@Service("beneficioServiceSimple")
public class BeneficioServiceImp implements IBeneficioService {

	private List<Beneficio> beneficios = new ArrayList<Beneficio>();
	
	@Override
	public Optional<Beneficio> buscarPorId(Long id) {
		
		return null;
	}

	@Override
	public void guardarBeneficio(Beneficio beneficio) {
		
		beneficios.add(beneficio);
	}

	@Override
	public List<Beneficio> obtenerListaBeneficios() {
		
		return beneficios;
	}

	@Override
	public void quitarBeneficioLista(Long id) {
		for(int i=0; i<beneficios.size(); i++) 
		{
			if(beneficios.get(i).getId()==id)
				beneficios.remove(i);
		}
		
	}
	
	

}
