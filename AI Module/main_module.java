import java.util.*;  
class Greet{
    public Greet(){
        System.out.println("AI: Hello My Name Is Ruby, And I am AI Robot");
        System.out.println("AI: How Can I Help You Please tell me some question:");
        }
}
class AI{
    public void Answer(String Text){
        System.out.println(Text);
    }
}
class Calling extends Greet{
    AI Ans = new AI();    
    public void Question(){
        Scanner Question = new Scanner(System.in);
        String Ques = " ";
        while(Ques != "Exit"){
            System.out.print("Me:");
            Ques = Question.nextLine();        
            if(Ques == " "){
                Ans.Answer("Nothing I Am Exit");
                Ques = "Exit";
            }
            else if(Ques!= " "){
                String[] arr =  Ques.split(" ", 5);
                // str.split("s", 0);
                for(String a : arr){
                    System.out.println("AI:"+a);
                }
            }
        }
    }
}
public class main_module {
    public static void main(String[] args) {
        Calling obj = new Calling();
        obj.Question();
    }
}
