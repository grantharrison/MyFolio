# MyFolio
Personal Stock Market Notebook with Real Time Data Checking

# Grant Harrison  CS3330 FINAL PROJECT DOCUMENTATION 
UI Development: This project was created using JavaFX. There are three .fxml documents in the project including Home.fxml, Watchlist.fxml and Research.fxml 
Architecture: 
Model { Stock.java, Switchable.java }
View { Home.fxml, Watchlist.fxml, Research.fxml }
Controller { HomeController.java, WatchlistController.java, ResearchController.java } 
Required Elements 
1.	Object Oriented Elements that you write the code for:
 a. Classes 
Stock.java, Switchable.java, HomeController.java, WatchlistController.java, ResearchController.java, etc.
	b. Subclasses 
WatchlistController.java is a subclass of Switchable.java 
(visible on line 38 of WatchlistController.java) 
2.	At least one Abstract Class 
Switchable.java is abstract 
(visible on line 20 of Switchable.java) 
3.	At least one Interface 
Welcome.java is an interface 
2.	Code elements that you utilize: 
a. One or more collection classes
   	I utilize an HashMap of Controllers in Switchable.java (visible on lines 22 )
b. Exception Handling
 	There is exception handling on nearly every java file in the project (visible  in try catch blocks on lines 166 to 182 of WatchlistController.java) 
3.	The application must have a clearly defined model
The ‘Models’ of this is all located in the Stock.java file and the Switchable.java file
It holds all the stock information in Stock.java and switchable info in Switchable.java 
4.	The UI must utilize multiple scenes and at least one of the scenes will have the contents of the scene graph changed based on the application state 
This project has a main “Home” scene that welcomes the user to the app and gives them the choice of going to research or watchlist. It also has the Watchlist and Research pages. In the Research page the scene changes when the help button is pushed, it displays how to use the search ( in ResearchController.java lines 168 – 170 ). All the scene switching is handled in each function by the handleGoTo functions (ex. In ResearchController.java lines 146 – 155 )
5.	There must be a way to access “About” information that includes information about you and the application 
On each page in the menu bar you can access the help dropdown and select the about tab which will display a pop-up about me and my app.
6.	The application must save and load data. The target for saving/loading data can be files, a network service, or a database 
The application allows the user to both save and upload .json files from the Watchlist page. This allows the user to watch a stock take notes and save it to their computer in which they can re-open in the app later on to see how there analysis worked. The code for save and open is in Stock.java and WatchlistController.java
