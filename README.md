# Sneaks - CPSC 210 Project

## An Overview
**Sneaks** is an app designed to make keeping track of your sneakers easy and simple. The app will be able to keep track of 
your collection of sneakers complete with each sneaker's Brand, Model, Colourway, Size, Condition, Retail Value, and 
Resell Value. Currently, there are four main features including: adding sneakers, removing sneakers, a breakdown of your
collection statistics, and a viewing of the sneakers in your collection.

**Why I Chose  This Project:**
- I've been an avid sneaker collector for years, and I wanted my project to reflect some of my interests which include 
streetwear and computer science!
- This sneaker collection tracker app is a perfect, simple-enough project which fits within my desired scope of difficulty
- I also wanted to choose a project that had the potential to actually be used in my daily life

## User Stories
My main tasks for **Phase  1** of the project:
- As a user, I want to be able to add a sneaker to my collection
- As a user, I want to be able to remove a sneaker from my collection
- As a user, I want to be able to view the sneaker(s) in my collection
- As a user, I want to be able to view my collection statistics (how many shoes in my collection, etc.)

My main tasks for **Phase  2** of the project:
- As a user, I want to be able to save my collection to file
- As a user, I want to be able to load my collection from file

**Phase 4: Task 2**
- Test and design a class in your model package that is robust.  You must have at least one method that throws a checked 
exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.
- Classes Involved: Collection
- Methods Involved: removeSneaker, totalRetailValue, totalResellValue
- These methods throw an EmptyCollectionException if the collection is empty.

**Phase 4: Task 3**

Ultimately, I am pretty satisfied with the project I made, overall the classes in the model package including
Collection and Sneaker were quite straight-forward to design and implement, as a result, I would not refactor those
particular classes. However, I think with more time to work on the project I could improve the GUI class in
the ui package. These are the changes I would make:
- The implementation of the buttons (add, remove, save, load, etc.) could have used more helper methods to reduce duplication
and as well as improve readability
- There were a lot of parameters used for button positions, I think I could have used a better approach to doing this rather
than meticulously enter in numbers and run the gui to see if it looked right. I would use some sort of static final variable
to fix this, improving single-point control.