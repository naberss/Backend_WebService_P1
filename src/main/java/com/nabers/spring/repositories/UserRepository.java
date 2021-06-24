package com.nabers.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nabers.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public void deleteByName(String name);

	@Query(value = "select a from User a where a.name like %:name%")
	public Iterable<User> ContainName(@Param(value = "name") String name);

}
