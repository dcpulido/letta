package es.uvigo.esei.lettaG4.entities;

import java.sql.Timestamp;
import java.lang.IllegalArgumentException;
import java.util.List;

/**
 * An entity that represents a event.
 * 
 */
public class Event {

	public static String[] categorias = { "Deportes", "Cine", "Musica", "Teatro", "Literatura", "Television", "Otros" };

	private int id;
	private User creador;
	private List<User> asistentes;
	private String name;
	private String description;
	private String place;
	private String categoria;
	private Timestamp date;
	private String foto;

	// Constructor needed for the JSON conversion
	Event() {
	}

	/**
	 *
	 * @param id
	 *            identifier of the Event.
	 * @param name
	 *            name of the Event.
	 * @param description
	 *            description of the Event.
	 */
	public Event(int id, String name, String description) {
		this.id = id;
		this.setName(name);
		this.setDescription(description);
	}

	public Event(int id, String name, String description, String place, Timestamp date) {
		this(id, null, null, name, description, "Otros", place, date, null);
	}

	public Event(int id, User creador, List<User> asistentes, String name, String description, String place,
			Timestamp date) {

		this(id, creador, asistentes, name, description, "Otros", place, date, null);

	}

	public Event(int id, User creador, List<User> asistentes, String name, String description, String categoria,
			String place, Timestamp date, String foto) {
		this.id = id;
		this.setName(name);
		this.setDescription(description);
		this.setPlace(place);
		this.setCategoria(categoria);
		this.setTimestamp(date);
		this.setCreador(creador);
		this.setAsistentes(asistentes);
		this.setFoto(foto);

	}

	public static String[] getCategorias() {
		return categorias;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {

		boolean flag = false;
		for (int i = 0; i < categorias.length; i++) {

			if (categoria.equals(categorias[i])) {
				this.categoria = categoria;
				flag = true;

			}
		}
		if (flag == false)
			throw new IllegalArgumentException("Categoría incorrecta");
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * Returns the identifier of the Event.
	 * 
	 * @return the identifier of the Event.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the
	 * 
	 * @return the name of the Event.
	 */
	public String getName() {
		return name;
	}

	public void setCreador(User c) {
		creador = c;
	}

	public User getCreador() {
		return creador;
	}

	public void setAsistentes(List<User> asistentes) {
		this.asistentes = asistentes;
	}

	public List<User> getAsistentes() {
		return asistentes;
	}

	/**
	 * Set the name of this Event.
	 * 
	 * @param name
	 *            the new name of the Event.
	 * @throws IllegalArgumentException
	 *             if the {@code name} length is not between 0 and 140.
	 */
	public void setName(String name) {
		if (name.length() < 140 && name.length() > 0) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Illegal length");
		}
	}

	/**
	 * Returns the description of the Event.
	 * 
	 * @return the description of the Event.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of this Event.
	 * 
	 * @param description
	 *            the new description of the Event.
	 * @throws IllegalArgumentException
	 *             if the {@code description} length is not between 0 and 500.
	 */
	public void setDescription(String description) {
		if (description.length() < 500 && description.length() > 0) {
			this.description = description;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getPlace() {
		return place;
	}

	/**
	 * Set the place of this Event.
	 * 
	 * @param place
	 *            the new place of the Event.
	 * @throws IllegalArgumentException
	 *             if the {@code place} length is longer than 140.
	 */
	public void setPlace(String place) {
		if (place.length() < 140) {
			this.place = place;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Timestamp getTimestamp() {
		return date;
	}

	public void setTimestamp(Timestamp date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	} 
}