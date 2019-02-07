# Softwares Required  
1. Visual Studio Code
2. Eclipse
3. Tomcat Server
4. MySql Workbench
5. Git 
6. WinSCP 5.9.4
7. Putty
8. MySql Server

     
# Get the project using Git
1. Create a folder in D: drive in which you want to download the project.
2. Open Windows Explorer.
3. Go to the folder you created.
4. Right click on the right hand side blank area.
5. Select "Git Bash Here".
6. Run following commands one by one:  
`git config --global user.name "[FIRST NAME] [LAST NAME]"`     
`git config --global user.email "[COGNIZANT EMAIL ID]"`     
`git clone https://code.cognizant.com/125546/tShell.git`    
`git checkout release1`.  
For Example,  
`git config --global user.name "Ankita Singh5"`   
`git config --global user.email "ankita.singh4@cognizant.com"`    
`git clone https://code.cognizant.com/125546/tShell.git`    
`git checkout release1`

  
# To start the Mysql Database
1. Inside your created folder for tShell project, goto "db" folder.  
2. Inside db folder there will be script file with the name "tShell_script.sql".  
3. Execute the above given script file.
4. You will get the database.

# To start writing the services
1. Go to the C: drive ->your user Id -> paste the .m2 repository folder from Sir's PC.
2. You will get all the required dependency which is inside pom.xml in your eclipse project.
3. Open your eclipse .Inside this goto file -> open project from file system.
4. Select in the Directory your folder where you have saved your tShell project Service part.
5. then Right click on the project, select Maven  then update Project.
6. This will remove all the errors.

# To work in visual studio code
1. Go to File then select open folder.
2. Select your tshell project UI part.
3. For opening the terminal ,click on view -> integrated terminal.
4. In the terminal run command `npm install`.
5. For running on local host server run this command `ng serve --open`.

# To Deploy your application on Server
1.Open WinSCP ->
1. Open putty.
2. Give your Host Name as 10.223.99.44 and port as 22.
3. Login as your UserID and your Password