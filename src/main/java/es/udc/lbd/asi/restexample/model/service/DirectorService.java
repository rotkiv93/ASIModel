package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;


@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DirectorService {

	@Autowired
	private DirectorDAO directorDAO;
	
	 public List<DirectorDTO> findAll() {
	        return directorDAO.findAll().stream().map(director -> new DirectorDTO(director)).collect(Collectors.toList());
	    }

	    public DirectorDTO findById(Long id) {
	        return new DirectorDTO(directorDAO.findById(id));
	    }

	    @Transactional(readOnly = false)
	    public DirectorDTO save(Director director) {
	    	 Director bdDirector = new Director(director.getNombre(), director.getApellido1(), director.getApellido2());
	         directorDAO.save(bdDirector);
	         return new DirectorDTO(bdDirector);
	    }
	    
	    @Transactional(readOnly = false)
	    public DirectorDTO update (Director director){
	    	Director bdDirector = directorDAO.findById(director.getId());
	    	bdDirector.setNombre(director.getNombre());
	    	bdDirector.setApellido1(director.getApellido1());
	    	bdDirector.setApellido2(director.getApellido2());
	    
	    	directorDAO.save(bdDirector);
	    	return new DirectorDTO(bdDirector);
	    }
	    
	    
	    @Transactional(readOnly = false)
	    public void deleteById(Long id) {
	        directorDAO.deleteById(id);
	    }
	
}
