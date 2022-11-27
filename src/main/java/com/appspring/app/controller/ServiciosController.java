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

import com.appspring.app.dao.IServiciosDao;
import com.appspring.app.entity.Servicios;

@Controller
@SessionAttributes("servicios")

public class ServiciosController {

	@Autowired
	private IServiciosDao serviciosDao;


	@RequestMapping(value = { "/listarServicios" }, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de entrenadores");
		model.addAttribute("servicios", serviciosDao.findAll());

		return "listarServicios";
	}

	@RequestMapping(value = { "/listarServiciosExcel" }, method = RequestMethod.GET)
	public String listarServiciosExcel(Model model) {
		model.addAttribute("servicios", serviciosDao.findAll());
		return "listarServiciosExcel";
	}

	@RequestMapping(value = "/formServicios")
	public String crear(Map<String, Object> model) {

		Servicios servicios = new Servicios();
		model.put("servicios", servicios);
		model.put("titulo", "Formulario de entrenadores");
		return "formServicios";
	}

	@RequestMapping(value = "/formServicios/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Servicios servicios = null;

		if (id > 0) {
			servicios = serviciosDao.findone(id);
		} else {
			return "redirect:/listarServicios";
		}
		model.put("servicios", servicios);
		model.put("titulo", "Editar Servicio");
		return "formServicios";
	}

	@RequestMapping(value = "/formServicios", method = RequestMethod.POST)
	public String guardar(@Valid Servicios servicios, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Servicios");
			return "formServicios";
		}

		serviciosDao.save(servicios);
		status.setComplete();
		return "redirect:listarServicios";
	}

	@RequestMapping(value = "/eliminarServicios/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			serviciosDao.delete(id);
		}
		return "redirect:/listarServicios";
	}

}
