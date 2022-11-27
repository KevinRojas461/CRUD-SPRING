package com.appspring.app.controller;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.appspring.app.dao.IClienteDao;
import com.appspring.app.entity.Cliente;



@Controller
@SessionAttributes("cliente")
public class ClienteController {


	@Autowired
	private IClienteDao clienteDao;

	

	@RequestMapping(value = { "/index", "/resultado","/", "/juansebas","/inicio"}, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("titulo", "inicio");
		model.addAttribute("clientes", clienteDao.findAll());
		
		return "index";
	}
	
	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de jugadores");
		model.addAttribute("clientes", clienteDao.findAll());
		
		return "listar";
	}
	
	@RequestMapping(value = {"/listarExcel"}, method = RequestMethod.GET)
	public String listarExcel(Model model) {
		model.addAttribute("clientes", clienteDao.findAll());
		return "listarExcel";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de jugador");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteDao.findone(id);
		} else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		clienteDao.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			clienteDao.delete(id);
		}
		return "redirect:/listar";
	}
	
	

}