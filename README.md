# Soda-Fountain-Machine-Android-App
I worked with a classmate to make a little system that can serve 2 drinks using an Arduino UNO, a bluetooth module hc-05, 2 tiny electric water pumps, a SQLite database and an android app. The arduino was used to receive the data from the bluetooth module and switch the relays for the water pumps, on the arduino loop only checks if the HC-05 receives a "1" or a "2" from the cellphone (depending on which it will serve one drink or the other). We also used a buffer on the arduino output because the relays drain too much current. Finally, we used the SQLite database to storage the info of administrators,workers and products (CRUD, login, money/points, etc).<br />
Here are 3 videos to show its operation: <br />
Hardware only : https://www.youtube.com/watch?v=DBnkQEXR4pQ <br /> 
Software only (not explained) : https://www.youtube.com/watch?v=LXLzt-yf-ro <br />
Software and Hardware explained in Spanish (I Edited it with my cellphone): https://www.youtube.com/watch?v=UGGGN9v5IvI<br /><br />
The "Software-Only.rar" file has the files to test the Android app only. <br/>
The "Full_Project.rar" file contains the code to connect with the bluetooth module HC-05, so you can't test this files without connecting to the bluetooth module. <br/>

(Inside each rar file) "Activities.rar" file contains all the activities for the android project, the "res.rar" file contains all the images and layouts for the android project.There is the Manifest.xml file too.
<br /><br/>
This project started on November 10th 2018 and finished on December 3rd 2018.
Made by Rafael Leon Estrada (SQLite database code, layouts, activities and Hardware) and Miguel Angel Luis Espinoza (Hardware, code for Arduino and code for Android App to connect with module HC-05).
