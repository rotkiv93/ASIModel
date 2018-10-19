package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Genre;

@Repository
public class GenreDAOImplementation implements GenreDAO {

	@Override
	public List<Genre> findAll() {
		 return InMemoryDB.genres.values().stream().collect(Collectors.toList());
			
	}

	@Override
	public Genre findById(Long id) {
		return InMemoryDB.genres.get(id);
	}

	@Override
	public Genre save(Genre genre) {
		InMemoryDB.genres.put(genre.getId(), genre);
        return genre;
	}

	@Override
	public void deleteById(Long id) {
		InMemoryDB.genres.remove(id);
		
	}

}
