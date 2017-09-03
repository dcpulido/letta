package es.uvigo.esei.lettaG4.entities;

import static java.util.Objects.requireNonNull;
import java.sql.Timestamp;
import java.lang.IllegalArgumentException;
import java.util.List;


/**
 * An entity that represents a event.
 * 
*/
public class Relationship {
	private int idEvent;
	private int idUser;
	public Relationship(int idEvent,int idUser){
		this.idUser=idUser;
		this.idEvent=idEvent;
	}
	public void setIdUser(int idUser){
		this.idUser=idUser;
	}
	public int getIdUser(){
		return idUser;
	}
	public void setidEvent(int idEvent){
		this.idEvent=idEvent;
	}
	public int getidEvent(){
		return idEvent;
	}
}