package io.rougesocket;

import io.rougesocket.data.Category;
import io.rougesocket.data.CurrentTask;
import io.rougesocket.data.Task;
import io.rougesocket.util.ArgUtil;
import io.rougesocket.util.Args;
import io.rougesocket.util.FileUtil;

import java.io.IOException;

public class TimeTracker {
    public static void main(String[] args) throws IOException {

        ArgUtil argUtil = new ArgUtil();
        Args arguments = argUtil.parseArgs(args);

        FileUtil fileUtil = new FileUtil();
        CurrentTask currentTask = fileUtil.getSavedTask();

        switch (arguments.getCommand().name()){
            case "TASK_START" -> {
                Task task = new Task(arguments.getTaskName(),new Category(arguments.getCategoryName()));
                currentTask.startTask(task);
            }
            case "TASK_STOP" -> currentTask.completeTask(arguments.getTaskName());
        };

        System.out.println(currentTask);
        fileUtil.saveTaskToFile(currentTask);
    }
}