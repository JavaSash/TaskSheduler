package ru.stepenko.taskSheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.stepenko.taskSheduler.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query("UPDATE Task t SET t.done = true WHERE t.id = :id")
    void markAsDone(@Param("id") Long id);
}
