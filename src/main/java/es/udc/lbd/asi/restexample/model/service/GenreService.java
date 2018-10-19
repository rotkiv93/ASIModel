package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GenreService {

	@Autowired
    private GenreDAO genreDAO;

    public List<Genre> findAll() {
        return genreDAO.findAll();
    }

    public Genre findById(Long id) {
        return genreDAO.findById(id);
    }

    public Genre save(Genre genre) {
        return genreDAO.save(genre);
    }

    public void deleteById(Long id) {
        genreDAO.deleteById(id);
    }

}
