package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.service.dto.GenreDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GenreService {

	@Autowired
    private GenreDAO genreDAO;

    public List<GenreDTO> findAll() {
        return genreDAO.findAll().stream().map(genre -> new GenreDTO(genre)).collect(Collectors.toList());
    }

    public GenreDTO findById(Long id) {
        return new GenreDTO(genreDAO.findById(id));
    }

    @Transactional(readOnly = false)
    public GenreDTO save(Genre genre) {
    	 Genre bdGenre = new Genre(genre.getNombre());
         genreDAO.save(bdGenre);
         return new GenreDTO(bdGenre);
    }
    
    @Transactional(readOnly = false)
    public GenreDTO update (GenreDTO genre){
    	Genre bdGenre = genreDAO.findById(genre.getId());
    	bdGenre.setNombre(genre.getNombre());
    	
    	genreDAO.save(bdGenre);
    	return new GenreDTO(bdGenre);
    }
    
    
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        genreDAO.deleteById(id);
    }

}
