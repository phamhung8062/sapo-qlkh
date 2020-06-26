package warehouseManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import warehouseManagement.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
	UserEntity findOneByUserNameAndStatus(String name, int status);
}
