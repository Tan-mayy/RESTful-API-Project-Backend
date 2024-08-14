################################ STEPS TO RUN THE PROJECT #########################################
1. Clone the Project 
2. Build the project 
3. Start the server 

API EndPoints Listing 
# GET ALL USERS
localhost:8080/courses

# DELETE EXISTING USER
localhost:8080/course/19

# CREATE BOOK USER
localhost:8080/course

Body : (raw : json)
{
    "id":253,
    "name": "Alice in Wonderland",
    "price": 2300.00
}

# UPDATE COURSE USER
localhost:8080/course/21

Body : (form-data)
price : 6000
name : Alice in hell

#GET COURSE USER
localhost:8080/course/21
