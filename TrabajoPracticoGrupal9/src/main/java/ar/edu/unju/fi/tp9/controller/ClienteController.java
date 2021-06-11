package ar.edu.unju.fi.tp9.controller;


import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp9.model.Beneficio;
import ar.edu.unju.fi.tp9.service.IBeneficioService;
import ar.edu.unju.fi.tp9.controller.ClienteController;
import ar.edu.unju.fi.tp9.model.Cliente;
import ar.edu.unju.fi.tp9.service.IClienteService;

@Controller
public class ClienteController {
	
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
		
	@Autowired
	@Qualifier("clienteServiceMysql")
	private IClienteService clienteService;
	
	@Autowired
	@Qualifier("beneficioServiceSimple")
	private IBeneficioService beneficioService;
	
	@Autowired
	@Qualifier("beneficioServiceMysql")
	private IBeneficioService beneficioServiceMysql;
	
	@Autowired
	private Beneficio beneficio;
	
	
	@GetMapping("/cliente/nuevo")
	public ModelAndView getNuevoClientePage() {
		
		LOGGER.info("CONTROLLER: ClienteController");
		LOGGER.info("METHOD: getNuevoClientePage()");
		LOGGER.info("RESULT: visualiza la pagina nuevocliente.html");
		
		ModelAndView mav = new ModelAndView("nuevocliente");
		mav.addObject("cliente",clienteService.generarCliente());
		mav.addObject("Beneficio",beneficio );
		mav.addObject("beneficiosEncontrados", beneficioService.obtenerListaBeneficios());
		
		return mav;
	}
	
	@PostMapping("/cliente/guardar")
	public ModelAndView getGuardarClientePage(@Valid @ModelAttribute("cliente") Cliente unCliente , BindingResult resultadoValidacion) {
		
		LOGGER.info("CONTROLLER: ClienteController");
		LOGGER.info("METHOD: getGuardarClientePage() con parametros");
		LOGGER.info("RESULT: guarda los datos registrados en el formulario de la nuevocliente.html, y muestra la pagina clientes.html");
		
		ModelAndView mav; 
		if(resultadoValidacion.hasErrors()) {
			mav = new ModelAndView("nuevocliente");
			mav.addObject("cliente", unCliente);
			mav.addObject("Beneficio",beneficio );
			mav.addObject("beneficiosEncontrados", beneficioService.obtenerListaBeneficios());
		
		}else {
			mav = new ModelAndView("clientes");
			unCliente.setBeneficios(beneficioService.obtenerListaBeneficios());
			clienteService.agregarCliente(unCliente);
			mav.addObject("clientes", clienteService.obtenerListaClientes());
		}
		return mav;
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getListadoClientesPage() {
		
		LOGGER.info("CONTROLLER: ClienteController");
		LOGGER.info("METHOD: getListadoClientePage()");
		LOGGER.info("RESULT: muestra el listado de clientes en la pagina clientes.html");
		
		ModelAndView mav = new ModelAndView("clientes");			
		mav.addObject("clientes", clienteService.obtenerListaClientes());
		return mav;
	}
	
	@GetMapping("/cliente/editar/{id}")
	public ModelAndView getEditarClientePage(@PathVariable(value = "id") Long id) {
		
		LOGGER.info("CONTROLLER: ClienteController");
		LOGGER.info("METHOD: getEditarClientePage()");
		LOGGER.info("RESULT: muestra la pagina clientes.html con los datos del cliente a editar. ");
		
		ModelAndView modelView = new ModelAndView("nuevoCliente");
		Cliente cliente = clienteService.obtenerClientePorId(id).get();
		modelView.addObject("cliente",cliente);
		modelView.addObject("Beneficio",beneficio );
		modelView.addObject("beneficiosEncontrados", beneficioService.obtenerListaBeneficios());
		
		return modelView;
	}
	
	@GetMapping("/cliente/eliminar/{id}")
	public ModelAndView getEliminarClientePage(@PathVariable(value = "id") Long id) {
		
		LOGGER.info("CONTROLLER: ClienteController");
		LOGGER.info("METHOD: getEliminarClientePage()");
		LOGGER.info("RESULT: elimina un cliente de la pagina clientes.html");
		
		clienteService.eliminarCliente(id);
		ModelAndView modelView = new ModelAndView("clientes");
		modelView.addObject("clientes", clienteService.obtenerListaClientes());
		return modelView ;
	}
	
	@GetMapping("/beneficio/buscar")
	public ModelAndView getBuscarBeneficiosPorDescripcion(@ModelAttribute("Beneficio") Beneficio unBeneficio) {
	
		ModelAndView mav =new ModelAndView("nuevocliente");
		Beneficio beneficioEncontrado;
		try {
			beneficioEncontrado = beneficioServiceMysql.buscarPorId(unBeneficio.getId()).get();
		}catch(Exception e) {
			beneficioEncontrado=null;
		}
		String mensajeError = "";
		if (beneficioEncontrado != null)
			beneficioService.guardarBeneficio(beneficioEncontrado);
		else {
			mensajeError = "El beneficio no existe";
			mav.addObject("mensajeError", mensajeError);
		}
		mav.addObject("cliente",clienteService.generarCliente());
		mav.addObject("Beneficio",beneficio );
		mav.addObject("beneficiosEncontrados", beneficioService.obtenerListaBeneficios());
		return mav;
		
	}

	@GetMapping("/beneficio/quitar/{id}")
	public ModelAndView quitarBeneficios(@PathVariable(name="id")Long id) {
		ModelAndView mav= new ModelAndView("nuevocliente");
		beneficioService.quitarBeneficioLista(id);
		mav.addObject("cliente",clienteService.generarCliente());
		mav.addObject("Beneficio",beneficio );
		mav.addObject("beneficiosEncontrados", beneficioService.obtenerListaBeneficios());
		
		return mav;
		
	}
	
}
