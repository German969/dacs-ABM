package abm;

import javax.persistence.metamodel.SingularAttribute;

import entities.Usuario;
import entities.Usuario_;

public class UsuarioFilter {
	
	private String f1;
	private int f2;
	
	public UsuarioFilter(){
		
	}
	
	private String cod;
	
	public String getCod(){
		
		return cod;
		
	}
	
	public void setCod(String cod){
		
		this.cod = cod;
		
		if(cod.equals("1")){
			f2 = Integer.parseInt(cod);
		}
		
	}

	public SingularAttribute<Usuario,Integer> getAttr(int f2){
		
		if(f2 == 1){
			
			return Usuario_.id;
		
		}
		
		return null;
		
	}
	
	public SingularAttribute<Usuario,String> getAttr(String f1){
		
		if(f1.equals("2")){
			
			return Usuario_.nombre;
		
		}
		
		return null;
		
	}
	
}
