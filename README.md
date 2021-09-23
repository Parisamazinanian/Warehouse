# Steps of creating a src file in Intellij IDE
1- Installing intelligence IDE
`..`
2- Open intelliJ IDE
3- Openning a new project from
`Projects >> New Project >> Java`
4- wtite the following commands in the terminal of the new project which we open:

- `git init`
- `git remote add origin <SSH link of the GitHub>`
- `git checkout main`
- Change the directory to the src folder which is automatically create in the new Java project. `cd src`
- Creating a nested folder with the folders java and resources in them: `mkdir -p src/{main,test}/{java,resources}`
- Create a java file in the java folder which is located in the main directory with:`File>>new>>Java file`
- write the Hello java command in it : `package src.main.java;

`public class Main {  
public static void main(String[] args) {  
System.out.println("Hello Java !");  
}}`

- adding the changes to the local repository : `git add .`
  and commit the changes `git commit -m "init commit"`
- Pushing it to the remote repository `git push -u origin main`