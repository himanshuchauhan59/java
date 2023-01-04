import java.util.*;  
class Greet{
    public Greet(){
        System.out.println("AI: Hello My Name Is Ruby, And I am AI Robot");
        System.out.println("AI: How Can I Help You Please tell me some question:");
        }
}
class Calling extends Greet{
    
    
    public void Question(){
        greetingUser greets = new greetingUser();
        Scanner Question = new Scanner(System.in);
        String Ques = "";
        while(!Ques.equals("Exit")){
            System.out.print("Me:");
            Ques = Question.nextLine();        
            greets.greet(Ques);
        }
    }
}
public class main_module {
    public static void main(String[] args) {
        Calling obj = new Calling();
        obj.Question();
    }
}
