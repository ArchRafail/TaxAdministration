# TaxAdministration</br>

</br>
</br>
The task was the next:</br>
To develop the data base of tax administration for fines store.</br>
To identify the person base will use the personal identification number.</br>
One person may have a lot of fines.</br>
Print an information to the console will be taken into account as printing.</br>
</br>
To realize:</br>
1. Full print of data base.</br>
2. Print of data according entered identification number.</br>
3. Print of data according entered type of fine.</br>
4. Print of data according entered city.</br>
5. Adding a new person with their information.</br>
6. Adding new fines for an existing record.</br>
7. Deleting the fine.</br>
8. Changing an information about person and their fines.</br>
</br>
</br>
Program operating conditions.</br>
1. An aplication is totally console's cycled program with menu.</br>
+ User can pick up point of menu with digit.</br>
+ An applicator each time provide you what is it expected from you - or it is number with 10 deigits, or the entered number is alredy in the base.</br>
2. Personal identification number - number with long type and consists from 10 digits (like in Ukraine).</br>
3. In birthday of person take into account day of month and month of year according callendar (with leap year).</br>
4. First name, last name, fine's protocol number and description can be any word/words of string type.</br>
5. Personal identification number is a unique number, that can't be repeated in the base.</br>
6. Fine's protocol number is a unique string, that can't be repeated in the base.</br>
7. The program doesn't read or write any information into file/files. It was not be a task.</br>
</br>
</br>
Test of program.</br>
1. Input incorrect point of menu path. Example, type a.</br>
Program will show you an error. Input mismatch exception. You have to enter number with integer type.</br>
2. Input incorrect point of menu path. Example, type 9.</br>
The application will provide you enter your choice again. Maximum points of menu is 8.</br>
3. Input correct point of menu. For example, type 2.</br>
Program catch it, show you the next text and waits for your next input.</br>
4. At this point of menu (2), an application waiys for an identification number. Enter incorrect number, for example 111111111 (nine's one).</br>
The application will provide you enter your identification number again. The length of identification number is 10 digits. </br>
5. Enter incorrect number, for example 1111111111 (ten's one).</br>
Program will show you the result, that "No any record with identity number 1111111111 found." and return you to the main menu.</br>
6. Enter correct number, for example 3456789126 (it is already predefined in the base)</br>
Program will show you the result: all information about person and their fines.</br>
After that, an application will return you to the main menu.
