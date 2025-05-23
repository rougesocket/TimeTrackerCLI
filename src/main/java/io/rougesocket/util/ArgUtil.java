package io.rougesocket.util;

import io.rougesocket.Logger;
import io.rougesocket.data.Category;

public class ArgUtil {


    public Args parseArgs(String[] args){
        if(!validate(args)){
            throw new RuntimeException("Invalid Arguments!!!");
        }
        Args argsObj = new Args();
        String cmd = args[0];
        Commands command = switch (cmd){
            case "start"->Commands.TASK_START;
            case "stop" ->Commands.TASK_STOP;
            case "report"-> args[1].equals("task") ? Commands.REPORT_TASKS:"category".equals(args[1])?Commands.REPORT_CATEGORIES:null;
            default -> throw new RuntimeException("Unexpected value: " + cmd+"Please refer the manual");
        };
        argsObj.setCommand(command);
        if(Commands.TASK_START.equals(command) || Commands.TASK_STOP.equals(command)){
            argsObj.setTaskName(args[1]);
            argsObj.setCategoryName(args.length==3? args[2]: Category.NONE);
        }
        return argsObj;
    }

    public boolean validate(String[] args){
        if(args.length<2){
            Logger.log("Error! Not enough Arguments");
            return false;
        }
        return true;
    }
}
