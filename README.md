This repository is a simple Spring based Api for making Api requests for managing posts for a blog

Project Url: https://github.com/Gabriel-Ax/Blog-Api/tree/main

It has 6 types of requests:

Get [/api/posts]: Returns all posts in the Database
Get [/api/posts/{id}]: Returns the post with the specified id from the Database
Get [/api/posts/term]: Return all posts with the tag specified in the url: /api/posts/term?Household

Post [/api/posts]: Creates a new post based on the request body
Post [/api/posts/{id}]: Updates the post with the specified id with the request body

Delete [/api/posts/{id]}: Deletes the post with the specified id from the database

Request body: 
{
    "title": (string),
    "content": (String),
    "category": (String),
    "tags": (List<String>)
}

Post you get from responses:
{
    "title": (string),
    "content": (String),
    "category": (String),
    "tags": (List<String>),
    "createdAtDate": "LocalDate",
    "updatedAtDate": "LocalDate"
}
