package warehouseManagement.util.SpecificationExecution;


import org.springframework.data.jpa.domain.Specification;
import warehouseManagement.entity.SupplierEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierSpecificationBuilder {
             
    private final List<SearchCriteria> params;
 
    public SupplierSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }
 
    public SupplierSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
 
    public Specification<SupplierEntity> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification> specs = params.stream()
          .map(SupplierSpecification::new)
          .collect(Collectors.toList());
         
        Specification result = specs.get(0);
 
        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}