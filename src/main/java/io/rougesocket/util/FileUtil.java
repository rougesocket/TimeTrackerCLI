package io.rougesocket.util;

import io.rougesocket.Logger;
import io.rougesocket.data.Category;
import io.rougesocket.data.CurrentTask;
import io.rougesocket.data.Task;
import io.rougesocket.data.TaskStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtil {

    private static final String PATH = "task-info.csv";

    public CurrentTask getSavedTask() throws  IOException {
        Path path = Paths.get(PATH);
        if(Files.notExists(path))Files.createFile(path);
        Map<String,Task> taskMap = Files.lines(path)
                .map(line -> line.split(","))
                .filter(tokenArray -> tokenArray.length==5)
                .map( tokenArray -> new Task(tokenArray[0],new Category(tokenArray[1]),
                        tokenArray[2]==null || tokenArray[2].equals("null") || tokenArray[2].isBlank() ? null:LocalDateTime.parse(tokenArray[2]),
                        tokenArray[3]==null || tokenArray[3].equals("null") || tokenArray[3].isBlank() ? null:LocalDateTime.parse(tokenArray[3]),
                        TaskStatus.valueOf(tokenArray[4])))
                .collect(Collectors.toMap(Task::getTaskName, Function.identity()));
        return new CurrentTask(taskMap);
    }
    public void saveTaskToFile(CurrentTask tasks) throws IOException {
        Path path = Paths.get(PATH);
        if(Files.notExists(path))Files.createFile(path);

        List<String> data = tasks.getCurrentTasks()
                .values()
                .stream()
                .map(Task::getCsvFormat)
                .toList();

        Files.write(path,data);
    }

    public void exportToCSV(String path) throws IOException {
        try{
            Path src = Paths.get(PATH);
            Path des = Paths.get(path);
            Files.copy(src,des,StandardCopyOption.REPLACE_EXISTING);
            Logger.log("Report is available at: "+path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
