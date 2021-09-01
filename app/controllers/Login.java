package controllers;

import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller{
	
	public static void form(){
		
		if (Usuario.count() == 0){
			Usuario u = new Usuario();
			u.email = "admin@admin.com";
			u.senha = "123456";
			u.nivel = 1;
			u.save();
		}
		
		if  (Usuario.count() == 1) {
			Usuario u1 = new Usuario();
			u1.email = "albertosoiza13@gmail.com";
			u1.senha = "123456";
			u1.nivel = 0;
			u1.save();
			
		}
				render();
	}
	

	
	public static void logar(String email, String senha){
		
		Usuario usu = Usuario.find("email = ?1 and senha = ?2",
									email, Crypto.passwordHash(senha) ).first();
		
		if(usu == null) {
			form();
		}else{
			session.put("usuario.email", usu.email);
			session.put("usuario.nivel", usu.nivel);
			
			Usuarios.site();
		}
		
	
	}
	
	public static void sair() {
		session.clear();
		Login.form();
	}
}


