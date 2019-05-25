package com.security.authorization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.security.authorization.model.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, String>{

	List<UserRoles> findByUserNameAndDeletedFlagFalse(String userName);
	
	@Query("select b.roleName, b.priorityOrder from UserRoles b where b.userName = :userName AND b.deletedFlag = false")
	List<Object[]> getByUserNameAndDeletedFlagFalse(@Param("userName") String userName);
}
