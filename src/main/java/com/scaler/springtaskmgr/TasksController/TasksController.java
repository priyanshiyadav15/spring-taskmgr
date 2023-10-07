package com.scaler.springtaskmgr.TasksController;

import com.scaler.springtaskmgr.Task.Task;
import com.scaler.springtaskmgr.TasksService.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @GetMapping("/tasks")
    List<Task> getTasks(){
        return tasksService.getTasks();
    }
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        var newTask = tasksService.createTask(task.getTitle(), task.getDescription(), task.getDueDate().toString());
        return newTask;
    }
    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable("id") Integer id){
        return tasksService.getTask(id);
    }
    @DeleteMapping("/tasks/{id}")
    public Task deleteTask(@PathVariable("id") Integer id){
        return tasksService.deleteTask(id);
    }

    @PatchMapping("/tasks/{id}")
    public Task updateTask(@PathVariable("id") Integer id, @RequestBody Task task){
        Task updateTask = tasksService.updateTask(id,task.getTitle(), task.getDescription(), task.getDueDate().toString());
        return updateTask;
    }
}
