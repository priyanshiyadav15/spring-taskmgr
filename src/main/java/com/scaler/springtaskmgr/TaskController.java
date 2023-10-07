package com.scaler.springtaskmgr;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TaskController {
    private final List<Task> taskList;
    private AtomicInteger taskId = new AtomicInteger(0);

        public TaskController() {
                taskList=new ArrayList<>();
                taskList.add(new Task(taskId.incrementAndGet(),"Task 1","Description 1",new Date()));
                taskList.add(new Task(taskId.incrementAndGet(),"Task 2","Description 2",new Date()));
                taskList.add(new Task(taskId.incrementAndGet(),"Task 3","Description 3",new Date()));
        }
    @GetMapping("/tasks")
    List<Task> getTasks(){
        return taskList;
    }

    @PostMapping("/tasks")
    Task createTask(@RequestBody Task task){
            var newTask = new Task(taskId.incrementAndGet(), task.getTitle(), task.getDescription(), task.getDueDate());
            taskList.add(newTask);
            return newTask;
    }

    @GetMapping("/tasks/{id}")
    Task getTask(@PathVariable("id") Integer id){
            for(Task task:taskList){
                if(task.getId().equals(id)){
                    return task;
                }
            }
            return null;
    }

    @PatchMapping("/tasks/{id}")
    void updateTask(@PathVariable("id") Integer id, @RequestBody Task task){
        for(int i=0;i<taskList.size();i++){
            Task t = taskList.get(i);
            if(t.getId().equals(id)){
                taskList.set(i,task);
                return;
            }
        }

    }

    @DeleteMapping("/tasks/{id}")
    Task deleteTask(@PathVariable("id") Integer id) {
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.getId().equals(id)) {
                taskList.remove(i);
                i--;
            }
        }
        return null;

    }
}
