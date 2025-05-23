package io.rougesocket.data;

import io.rougesocket.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CurrentTask {
    private Map<String, Task> currentTasks = new HashMap<>();

    public CurrentTask(Map<String,Task> currentTasks){
        this.currentTasks=currentTasks;
    }
   public void startTask(Task task){
       if(currentTasks.putIfAbsent(task.getTaskName(),task)!=null){
            Logger.log("Task Already exists, Skipping");
       }
   }

    public void completeTask(String taskName){
        Task currTask =  currentTasks.get(taskName);
        if(currTask==null){
            Logger.log("No Task Found");
        }
        else{
            currTask.setEndTime(LocalDateTime.now());
            currTask.setStatus(TaskStatus.COMPLETE);
        }
    }

    public Map<String, Task> getCurrentTasks() {
        return currentTasks;
    }

    public void setCurrentTasks(Map<String, Task> currentTasks) {
        this.currentTasks = currentTasks;
    }

    @Override
    public String toString() {
        return "Current Task: " + currentTasks;
    }
}
