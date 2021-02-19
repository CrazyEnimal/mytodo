package org.druzhinin.mytodo.repo;


import org.druzhinin.mytodo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);

    void deleteById(Long id);
}
