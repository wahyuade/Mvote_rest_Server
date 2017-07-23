package mvote.rest.database_service;

/**
 * Created by wahyuade on 16/07/17.
 */

import mvote.rest.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<UserModel, Long> {
    UserModel findUserModelByNrp(String nrp);
    int countByWaktuLoginNotNull();
    UserModel findUserModelByN(String n);
}
