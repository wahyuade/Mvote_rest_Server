package mvote.rest.database_service;

import mvote.rest.model.CalonModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wahyuade on 16/07/17.
 */
public interface CalonService extends CrudRepository<CalonModel, Long> {

}
