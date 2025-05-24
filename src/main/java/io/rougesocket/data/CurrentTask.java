package io.rougesocket.data;

import io.rougesocket.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, Duration> getTaskReport(){

        return currentTasks.values().stream().filter(task -> task.getEndTime()!=null)
                .collect(Collectors.toMap(Task::getTaskName,Task::getTaskDuration));
    }

    public Map<String,Duration> getCategoryReport(){

        Map<String,Duration> categoryReport = new HashMap<>();
        currentTasks.values().stream().filter(task -> task.getEndTime()!=null)
                .forEach(task -> {
                    String categoryName = task.getCategory().getName();
                    Duration categoryDuration = categoryReport.getOrDefault(categoryName, Duration.ZERO);
                    categoryReport.put(categoryName,categoryDuration.plus(task.getTaskDuration()));
                });
        return categoryReport;
    }

}
