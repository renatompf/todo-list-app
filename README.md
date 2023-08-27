# todo-list-app

This is basic todo-list [JAVA](https://java.com/en) project using [Spring](https://spring.io) and [PostgreSQL](https://www.postgresql.org).

This project has a couple of endpoints for tasks and user management.

## Endpoints:

### Users:

* Get all users:

```
GET localhost:8080/users
```

* Get user by id:

```
GET localhost:8080/users/{userId}
```

* Create a new user:

```
POST localhost:8080/users
```
Body example: 

```json
{
    "name": "John",
    "email": "johnnyjohn@john.com"
}
```

* Update user:

```
PUT localhost:8080/users/{userId}
```
Body example:

```json
{
    "name": "John",
    "email": "johnnyjohn@john.com"
}
```

* Delete user:

```
DELETE localhost:8080/users/{userId}
```

### Tasks:

* Get all tasks:

```
GET localhost:8080/tasks
```

* Get task by id:

```
GET localhost:8080/tasks/{taskId}
```

* Get all tasks for user:

```
GET localhost:8080/tasks/by-user/{userId}
```

* Create a new task:

```
POST localhost:8080/tasks
```
Body example:

```json
{
  "description": "Walk the dog",
  "user": 1
}
```

* Update task status:

```
PUT localhost:8080/tasks/{taskId}
```
Body example:

```json
{
  "newStatus" : "IN_PROGRESS"
}
```

* Delete task:

```
DELETE localhost:8080/tasks/{taskId}
```

## How to run:

 To run this app you simply have to go to the root of the project and simply do: 
 ```shell 
 make run
 ```
And that should run the both containers.

### Problems:

In the final stage of this project I wasn't able, for same reason, connecting the app container to the postgres container.
So I was forced to deploy the database on docker and run the application locally, instead of doing it via docker.

