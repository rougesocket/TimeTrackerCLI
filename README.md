# TimeTrackerCLI
A Time Tracking CLI app build in java

## Usage

### Start Logging Time
`timetracker start <task-name>`
`timetracker start <task-name> <category-name>`

Note: If the task name is already in progress, this will result in no-op 
### Stop Logging Time
`timetracker stop <task-name>`

### Show Time
`timetracker report task`
shows the tasks and time spent on each task

`timetracker report category`
shows the categories and time spend on each category

### Export Report
`timetracker export <path-to-file>`

Note: Throws a Runtime exception if file path is not valid