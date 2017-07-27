package mvote.rest.database_service;

import mvote.rest.model.CalonModel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by wahyuade on 16/07/17.
 */
public interface CalonService extends CrudRepository<CalonModel, Long> {
    ArrayList<CalonModel> findAll();
}
