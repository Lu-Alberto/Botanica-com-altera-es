package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Planta;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

public class Usuarios extends Controller {
	
	public static void form() {
		Usuario usuario = (Usuario) Cache.get("usuario");
		Cache.clear();	
		render(usuario);
	}
	
	public static void salvar(@Valid Usuario usuario, String senha){
		if (senha.equals("") == false)
			usuario.senha = senha;
		
		if(validation.hasErrors()){
			validation.keep();
			
			flash.error("Cadastro Inválido!");
			Cache.set("usuario", usuario);
			
			form();
		}
			
	usuario.save();
	flash.success("Cadastro Concluido!");
	site();
}
	
	
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		
		List<Planta> plantas = Planta.findAll();
		
		renderTemplate("Usuarios/form.html", usuario, plantas);
	}
	
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		listar();
		
	}
	
	
	public static void listar() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
	
	public static void site() {
	
		render();
	}
		
	
}

