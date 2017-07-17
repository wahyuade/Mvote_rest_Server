package mvote.rest.database_service;

import mvote.rest.model.AdminModel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by wahyuade on 16/07/17.
 */
public interface AdminService extends CrudRepository<AdminModel, Long>{
    ArrayList<AdminModel> findByStatusLogin(String status);

    AdminModel findByUsername(String username);
}
