package warehouseManagement.service;

import java.awt.print.Pageable;
import java.text.ParseException;
import java.util.List;

public interface ICommonService<T> {
    T save(T object);

    List<T> findAll() throws Exception;

    List<T> findAll(Integer pageNo, Integer pageSize);

    T findById(Long id);
    // T findOneByReceiptId(Long id);


    //Boolean existByName(String name);

    T update(T object, Long id);

    void delete(Long id);
}