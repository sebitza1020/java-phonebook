<h1>Java Phone Book Appliation</h1>

This is a Phone Book Console Application made in Java.

We started with a simple class called Contact where we define the phone number as an identification key, name, first name, email and contact address, 
plus the known get and set methods.

Then, I created an interface with the name of the exercise itself (PhoneBook), where I added methods for adding contacts, editing contacts, displaying all contacts, 
displaying contact by no. by phone, display by name and surname. 

In the PhoneBookImpl class we implemented the actual methods in the interface using the required assignment, 
plus uses of for, while, do-while, if, to solve and display the expected results.

Well, in order to validate if a telephone number corresponds to the Romanian pattern in the assignment, we used Matcher to do this, and if the entry did not correspond, 
then the entry of the telephone number was resumed until it was entered accordingly.

To make the famous Main have little code, we used a single class that brings the Contact skeleton class and the PhoneBook interface to the same place, namely PhoneBookImpl. 
A select entry was required to use the switch statement, and from 1 to 4, I had to choose between: adding / editing, displaying all contacts, searching by phone number, 
and searching by first name, last name or first and last name together. If it was anything other than 1-4, then I was out of the program.

Initially, it was supposed to take 8 hours/2 days(4h/1d for 2 options), but after I have done 2 options in less than 2 hours, I had enough time to finish other 2 options in
3 hours and now I have tested the program, everything works great according to requirements.

As a plus, I have integrated MySQL to save, retrieve or update data.

You can take a look at the program above now.

<h3>Mentions: </h3>
  Romanian phone pattern (w/o international preffix +40): ^07[1-9][0-9]\s\d{3}\s\d{3}$
