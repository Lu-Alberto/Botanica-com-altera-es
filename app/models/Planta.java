package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Planta extends Model {

	@Required
	public String Nome;
	public String Nome_Cientifico;
	public String Caracteristicas;
	public String nomeFoto;
	
}
