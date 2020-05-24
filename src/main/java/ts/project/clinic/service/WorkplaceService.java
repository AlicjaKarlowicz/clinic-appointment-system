package ts.project.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ts.project.clinic.exception.ResourceNotFound;
import ts.project.clinic.model.entity.Workplace;
import ts.project.clinic.repository.WorkplaceRepository;

import java.util.List;

/**
 * service class for Workplace class
 * @author alicj
 */
@Service
public class WorkplaceService {

    @Autowired
    private WorkplaceRepository workplaceRepository;

    /**
     * get all workplaces in database
     * @return List<Workplace>
     */
    public List<Workplace> getAll() {
        return workplaceRepository.findAll();
    }

    /**
     * find workplace by id
     * @param id
     * @return Workplace
     */
    public Workplace findById(Integer id){
        return workplaceRepository.findById(id).orElseThrow(ResourceNotFound::new);
    }
}
