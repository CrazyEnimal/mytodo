package org.druzhinin.mytodo.controller;

import org.druzhinin.mytodo.model.Task;
import org.druzhinin.mytodo.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskPerository;

    @GetMapping
    public String getTasksPage(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model){
        List<Task> tasks = taskPerository.findAll();
        model.put("tasks", tasks);
        return "tasks";
    }
}
