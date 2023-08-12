# TODO Application

This repository contains a TODO application. They are basic rest APIs.

The Todo List REST API allows you to manage a collection of todo items. You can add new items, retrieve the list of items, update their status, change their status, and delete items.

## Base URL

```
http://localhost:8080
```

Replace `localhost:8080` with the appropriate host and port where your application is deployed.

## Endpoints

### Add Todo

Add a new todo item.

- **URL:** `/todos`
- **Method:** POST
- **Request Body:** String (description of the todo item)
- **Response:** JSON representation of the added todo item

#### Example

Request:

```
POST /todos
Content-Type: application/json

"Buy groceries"
```

Response:

```json
{
  "id": "unique-id-1",
  "description": "Buy groceries",
  "done": false
}
```

### Get Todos

Retrieve the list of all todo items.

- **URL:** `/todos`
- **Method:** GET
- **Response:** List of JSON representations of todo items

#### Example

Request:

```
GET /todos
```

Response:

```json
[
  {
    "id": "unique-id-1",
    "description": "Buy groceries",
    "done": false
  },
  {
    "id": "unique-id-2",
    "description": "Finish project",
    "done": true
  }
]
```

### Update Todo Status

Update the status of a specific todo item.

- **URL:** `/todos/{id}`
- **Method:** PUT
- **URL Parameters:** `id` (string, the unique ID of the todo item)
- **Request Body:** boolean (status: `true` for completed, `false` for pending)
- **Response:** Success message or error message

#### Example

Request:

```
PUT /todos/unique-id-1
Content-Type: application/json

true
```

Response:

```
Status of todo item with ID unique-id-1 updated successfully.
```

### Delete Todo

Delete a specific todo item.

- **URL:** `/todos/{id}`
- **Method:** DELETE
- **URL Parameters:** `id` (string, the unique ID of the todo item)
- **Response:** Success message or error message

#### Example

Request:

```
DELETE /todos/unique-id-1
```

Response:

```
Todo item with ID unique-id-1 deleted successfully.
```

### Change Todo Status

Change the status of a specific todo item.

- **URL:** `/todos/{id}/status`
- **Method:** PUT
- **URL Parameters:** `id` (string, the unique ID of the todo item)
- **Request Body:** boolean (status: `true` for completed, `false` for pending)
- **Response:** Success message or error message

#### Example

Request:

```
PUT /todos/unique-id-1/status
Content-Type: application/json

true
```

Response:

```
Status of todo item with ID unique-id-1 changed to completed.
```

## Error Handling

If an error occurs, the API will return an appropriate HTTP status code along with an error message in the response body.

### Example

Response (HTTP 404 - Not Found):

```json
{
  "message": "Todo item with ID unique-id-1 not found."
}
```

# Unit tests
 You can run unit tests using below command:
 
```shell
mvn test
```
This will generate JaCoCo reports containing coverage data.
