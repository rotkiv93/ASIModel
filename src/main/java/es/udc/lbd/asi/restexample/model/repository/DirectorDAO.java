package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Director;

public interface DirectorDAO {
 	List<Director> findAll();

    Director findById(Long id);

    void save(Director director);

    void deleteById(Long id);
}
