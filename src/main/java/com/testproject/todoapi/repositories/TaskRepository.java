package com.testproject.todoapi.repositories;

import com.testproject.todoapi.models.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.isDone = :booleanFilter")
    List<Task> findAllSortedAndFiltered(@Param("booleanFilter") boolean booleanFilterType, Sort sort);

    @Query("select t from Task t")
    List<Task> findAllSorted(Sort sort);
}
