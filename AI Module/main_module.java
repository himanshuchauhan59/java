import java.util.*;  
class Greet{
    public Greet(){
        System.out.println("AI: Hello My Name Is Ruby, And I am AI Robot");
        System.out.println("AI: How Can I Help You Please tell me some question:");
        }
}
class Calling extends Greet{
    public void Answer(String Text){
        System.out.println(Text);
    }
    public void Question(){
        Scanner Question = new Scanner(System.in);
        String Ques = "";
        while(!Ques.equals("Exit")){
            System.out.print("Me:");
            Ques = Question.nextLine();        
            if(Ques.equals("")){
                Answer("Nothing I Am Exit");
                Ques = "Exit";
            }
            else if(!Ques.equals("")){
                String[] arr =  Ques.split(" "); 
                    for(String s : arr){
                        if(s.equals("name")){
                            System.out.println("hello "+arr[arr.length-1]);
                        }
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
