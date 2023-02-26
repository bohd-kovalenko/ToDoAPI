package com.testproject.todoapi.repositories;

import com.testproject.todoapi.models.Task;
import com.testproject.todoapi.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.isDone = :booleanFilter and t.user.id=:user_id")
    List<Task> findAllSortedAndFilteredByUserId(@Param("booleanFilter") boolean booleanFilterType, Sort sort, @Param("user_id") String user_id);

    @Query("select t from Task t where t.user.id=:user_id")
    List<Task> findAllSortedByUserId(Sort sort,@Param("user_id") String userId);
}
