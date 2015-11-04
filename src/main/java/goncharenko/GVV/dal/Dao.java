package goncharenko.GVV.dal;

import goncharenko.GVV.model.BaseModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vitaliy on 31.10.2015.
 */
public interface Dao<T extends BaseModel>{
    T findById(String id);

    void delete(T model);

    void save(T model);

    void saveList(List<T> modelList);

}
