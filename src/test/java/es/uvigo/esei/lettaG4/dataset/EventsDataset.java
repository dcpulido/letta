package es.uvigo.esei.lettaG4.dataset;


import static es.uvigo.esei.lettaG4.dataset.UsersDataset.*;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.stream;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.function.Predicate;

import es.uvigo.esei.lettaG4.entities.Event;

public final class EventsDataset {
	private EventsDataset() {}
	
	
	public static Event[] event() {
		return new Event[] {
				new Event(1, "Deportes", "Evento de deportes", "Bar Deportes", new Timestamp(1459852342)),
				new Event(2, "Cine", "Evento de cine", "Bar Cine", new Timestamp(1459862342)),
				new Event(3, "Música", "Evento de música", "Bar Música", new Timestamp(1459872342)),
				new Event(4, "Teatro", "Evento de teatro", "Bar Teatro", new Timestamp(1459883442)),
				new Event(5, "Literatura", "Evento de literatura", "Bar Literatura", new Timestamp(1459892342)),
				new Event(6, "Television", "Evento de television", "Bar Television", new Timestamp(1459902342)),
				new Event(7, "Otros", "Evento de otros", "Bar Otros", new Timestamp(1459203342)),				

		};
	}
	
	public static Event[] events() {
		
		return new Event[] {
				new Event(1, Arrays.asList(user()).get(0),Arrays.asList(user()), "Deportes", "Evento de Deportes", "Deportes", "Bar Deportes", new Timestamp(1459852342), "imgur.com/deportes"),
				new Event(2, Arrays.asList(user()).get(1),Arrays.asList(user()), "Cine", "Evento de cine", "Cine", "Bar cine", new Timestamp(1459862342), "imgur.com/Cine"),
				new Event(3, Arrays.asList(user()).get(2),Arrays.asList(user()), "Música", "Evento de Música", "Musica", "Bar Músical	", new Timestamp(1459872342), "imgur.com/Musica"),
				new Event(4, Arrays.asList(user()).get(3),Arrays.asList(user()),"Teatro", "Evento de teatro", "Teatro","Bar teatro", new Timestamp(1459883442), "imgur.com/Teatro"),
				new Event(5, Arrays.asList(user()).get(4),Arrays.asList(user()),"Literatura", "Evento de literatura", "Literatura", "Bar Literatura", new Timestamp(1459892342), "imgur.com/Literatura"),
				new Event(6, Arrays.asList(user()).get(5),Arrays.asList(user()),"Television", "Evento de Television", "Television","Bar Television", new Timestamp(1459902342), "imgur.com/Television"),				
				new Event(7, Arrays.asList(user()).get(6),Arrays.asList(user()),"Otros", "Evento de otros", "Otros", "Bar Otros", new Timestamp(1459902342), "imgur.com/Otros")				
		};
	}
	
	public static Event[] eventsWithout(int ... ids) {
		Arrays.sort(ids);
		
		final Predicate<Event> hasValidId = event ->
			binarySearch(ids, event.getId()) < 0;
		
		return stream(event())
			.filter(hasValidId)
		.toArray(Event[]::new);
	}
	
	public static Event event(int id) {
		return stream(event())
			.filter(event -> event.getId() == id)
			.findAny()
		.orElseThrow(IllegalArgumentException::new);
	}
	
	public static int existentId() {
		return 5;
	}
	
	public static int nonExistentId() {
		return 1234;
	}

	public static Event existentEvent() {
		return event(existentId());
	}
	
	public static Event nonExistentEvent() {
		return new Event(nonExistentId(), "Coches", "Evento de coches","Bar coches", new Timestamp(1359902342));
	}
	
	public static String newName() {
		return "Barcos";
	}
	
	public static String newDescription() {
		return "Evento de barcos";
	}
	
	public static String newPlace() {
		return "Bar de barcos";
	}
	
	public static Timestamp newDate() {
		return new Timestamp(1459851342);
	}
	
	public static Event newEvent() {
		return new Event(event().length + 1, newName(), newDescription(), newPlace(), newDate());
	}
}
