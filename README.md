# My Personal Project

## A subtitle

A *bulleted* list:
- item 1
- item 2
- item 3

An example of text with **bold** and *italic* fonts.  

# Project Descript
I plan to design a plug-in software for ubc's workday website for personal time management. Since I find that in the workday system, it can only make a timetable that contains all courses in a year, instead of giving separate timetable for each term in a year. Thus, students in the school could use it do a better course management at the start of the semester.

## The main functionality for my software:
- It would support importing pdf timetables downloaded from workday timetable and then separate courses from different terms and then make timetable for each term.
- It will be also avaible for user to type in their courses by inserting information for courses' names, courses' description (*optional, description include location, section type, professor, etc*)
## other extended function:
- Users can also insert their comtemporary planning into daily todo list.
- Users can delete a course section permanently on timetable due to the change of course selection at the start of the term, they can also delet a course section only for one day due the change of course arrangement.
## User Stories
- I want to add a forum for my tomorrow daily arrangement
- I want to add a course to the timetable for my next term
- I want to remove a lecture this week since professor is sick this week and i do not need to come to the lecture
- I want to check my daily arrangement to see when is the office hour start
- I want to be notified when it is time to go to the office hour
- I want to be able to check the lecture's classroom at the start of the term
- As a user, I want to save the list of event in this week
- As a user, I want to save the list of course in this week
- As a user, I want to save this week's schedule

# Instructions for End User
- input "GUI" in the terminal to access the graphical user interface
- input basic information of the course you want to input into the input part, and then put the "AddCourse" button
- click button that correspond to each of the term to show separate time tables for different terms
- You can find my visual component at the top of the panel, right above the time table
- To save the input courses you can click "SaveCourse" button
- To load the courses you can click "LoadCourse" button

# Phase 4: Task 2
- Thu Nov 28 14:39:58 PST 2024
Add new case Office Hour into the caseList
Thu Nov 28 14:40:16 PST 2024
Add new case Test into the caseList
Thu Nov 28 14:40:21 PST 2024
add cases into the week's schedule
Thu Nov 28 14:40:21 PST 2024
add cases into the week's schedule
Thu Nov 28 14:40:50 PST 2024
remove one case Office Hour from Mon's  caseList
Thu Nov 28 14:40:50 PST 2024
remove one case Office Hour from the caseList

# Phase 4: Task 3
- In my project, there are many problems about the code structure which will affect the readability.
First, there is too many redundant class in my code, in ListOfCase class, it basically represent a list of cases (courses and events), which can be represented by ArrayList, but I separately create classes to represent this because I want to make by code able to calculate the total credit for all courses. In the future, I may create a new method separately in ui package to calculate the total credit instead of creating new class.
- Second, the input type in my classes in ui packages is messy. In my design, all elements are of type "course" or "event" and there is no other types. However, I store them in the type of CaseToDo which is the super type of the Course and Event which makes it hard to design distinct method on "Course" and "Event". In the future, I will try to store them as separate types.