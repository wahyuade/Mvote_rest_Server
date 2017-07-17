package mvote.rest.database_service;

/**
 * Created by wahyuade on 16/07/17.
 */

import mvote.rest.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<UserModel, Long> {
    UserModel findUserModelByNrp(String nrp);
    UserModel findUserModelByLocal(String local);

    int countByStatusToken(String statusToken);

    int countByStatusPilih(String statusPilih);
}
