# ToDoAPI
First commit of the project contain inside working project for all test tasks except the last one. Second commit of project contains reworked project for all test tasks, including jwt authentication, authorization.  
Application uses PostgreSQL. You need to extract all info from db-sructure.sql into your postgres query to get fully-working db for the application.
Application works on port 8080 by default, db must have a name "test-work" and works on port 5432.  
All application's endpoints:  
/api/auth/register (POST) - registration of a new user and getting from the server a JWT token, which works for an hour;  
/api/auth/login (POST) - login of existing user and getting from the server a JWT token, which works for an hour;  
/api/tasks/** - for all endpoints of this type you need to provide a JWT token as an authorization type;  
/api/tasks (GET) - endpoint gives you all tasks of the users, whos token was given;  
/api/tasks/{task_id} (GET) - endpoint gives you an information about the tasks leaning under "task_id". If the task is not created by the user, whos token we've got, the info will not be provided;  
/api/tasks (POST) - endpoint for creating a new task;  
/api/tasks/{id} (PUT) - endpoint for updaiting existing task. If the task is not created by the user, whos token we've got, the info will not be provided;  
/api/tasks/{id} (DELETE) - endpoint for deleting existing task. If the task is not created by the user, whos token we've got, the info will not be provided;  
