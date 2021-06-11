package ar.edu.unju.fi.tp9.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp9.model.Beneficio;
import ar.edu.unju.fi.tp9.repository.IBeneficioRepository;
import ar.edu.unju.fi.tp9.service.IBeneficioService;

@Service("beneficioServiceMysql")
public class BeneficioServiceMysqlImp implements IBeneficioService{

	@Autowired
	private IBeneficioRepository beneficioRepository;
	
	@Override
	public Optional<Beneficio> buscarPorId(Long id) {
		Optional<Beneficio> beneficio= beneficioRepository.findById(id);
		return beneficio;
	}

	@Override
	public void guardarBeneficio(Beneficio beneficio) {
		beneficioRepository.save(beneficio);
		
	}

	@Override
	public List<Beneficio> obtenerListaBeneficios() {
		List<Beneficio> beneficios = (List<Beneficio>) beneficioRepository.findAll();
		return beneficios;
	}

	@Override
	public void quitarBeneficioLista(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
