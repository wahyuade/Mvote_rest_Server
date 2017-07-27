package mvote.rest.database_service;

import mvote.rest.model.SuaraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by wahyuade on 16/07/17.
 */
public interface SuaraService extends JpaRepository<SuaraModel, Long>{
    int countByIdCalon(String idCalon);
    ArrayList<SuaraModel> findAll();
}
