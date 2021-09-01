package controllers;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Planta;
import models.Usuario;
import play.cache.Cache;
import play.data.Upload;
import play.data.validation.Valid;
import play.modules.paginate.ValuePaginator;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;


public class Plantas extends Controller {
	
	@Before(unless={"Plantas.listar" })
	static void permissoes(){
		if (session.get("usuario.nivel").equals("1") == false){
			renderText("Acesso negado, volte!");
			
		}
	}
	public static void form() {
		Planta planta = (Planta)Cache.get("planta");
		Cache.clear();
		render(planta);
	}
	
	public static void salvar(@Valid Planta planta, File foto) {
		
		foto.renameTo(new File("./uploads/" + foto.getName() ));
		
		
		if (validation.hasErrors()){
			validation.keep();
			
			flash.error("Falha ao salvar planta.");
			Cache.set("planta", planta);
			
			form();
		}
		
		planta.nomeFoto = foto.getName();
		planta.save();
		flash.success("Planta cadastrado.");
		listar(null);
	}
	
	public static void editar(Long id) {
		Planta planta = Planta.findById(id);
		renderTemplate("Plantas/form.html", planta);
		
	}
	
	public static void remover(Long id) {
		Planta planta = Planta.findById(id);
		planta.delete();
		flash.success("Planta removido.");
		listar(null);
	}
	
	
	public static void listar(String busca) {
		
		List<Planta> plantas;
		if(busca == null) {
			 plantas = Planta.findAll();
		} else {
			 plantas = Planta.find("byNomeLike", "%"+busca+"%").fetch();
			 
		}
		
		ValuePaginator Listapaginacao = new ValuePaginator(plantas);
		Listapaginacao.setPageSize(3);
		render(Listapaginacao);
	}
}
