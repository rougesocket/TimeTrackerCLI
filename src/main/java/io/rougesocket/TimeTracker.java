package io.rougesocket;

import io.rougesocket.data.Category;
import io.rougesocket.data.CurrentTask;
import io.rougesocket.data.Task;
import io.rougesocket.util.ArgUtil;
import io.rougesocket.util.Args;
import io.rougesocket.util.FileUtil;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class TimeTracker {
    public static void main(String[] args) throws IOException {

        ArgUtil argUtil = new ArgUtil();
        Args arguments = argUtil.parseArgs(args);

        FileUtil fileUtil = new FileUtil();
        CurrentTask currentTask = fileUtil.getSavedTask();

        switch (arguments.getCommand()){
            case TASK_START -> {
                Task task = new Task(arguments.getTaskName(),new Category(arguments.getCategoryName()));
                currentTask.startTask(task);
            }
            case TASK_STOP -> currentTask.completeTask(arguments.getTaskName());
            case REPORT_TASKS -> {
                Map<String, Duration> taskReport = currentTask.getTaskReport();
                for(Map.Entry<String,Duration> entry : taskReport.entrySet()){
                    System.out.println("Task: "+entry.getKey());
                    System.out.println("Duration in minutes: "+entry.getValue().toMinutes());
                }
            }
            case REPORT_CATEGORIES -> {
                Map<String, Duration> categoryReport = currentTask.getCategoryReport();
                for(Map.Entry<String,Duration> entry : categoryReport.entrySet()){
                    System.out.println("Category Name: "+entry.getKey());
                    System.out.println("Duration in minutes: "+entry.getValue().toMinutes());
                }
            }
            case EXPORT -> {
                fileUtil.exportToCSV(arguments.getReportPath());
            }
        };

        System.out.println(currentTask);
        fileUtil.saveTaskToFile(currentTask);
    }
}