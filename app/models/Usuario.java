package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import net.sf.oval.constraint.MinSize;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model {

	

	
	@Required
	public String nome;
	
	
	@Required
	@Email (message="Por favor, verique seu endereço de email!")
	public String email;
		
	
	@Required
	public String cpf;
	
	
	public String senha;
	

	public int nivel;
	
	


	
	public void setSenha(String s) {
		
		senha = Crypto.passwordHash(s);
		
	}
}
	




