package com.nabers.spring.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nabers.spring.entities.Category;

@Repository
@Profile(value = {"dev","test","prod"})
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query(value = "select A from Category A where A.name like %:name%")
	public Iterable<Category> findByName(@Param(value = "name") String name);

}
