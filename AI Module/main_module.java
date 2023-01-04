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
        greetingUser greets = new greetingUser();
        Scanner Question = new Scanner(System.in);
        String Ques = "";
        while(!Ques.equals("Exit")){
            System.out.print("Me:");
            String txt = Question.nextLine();     
            if(txt.equals("")){
                Answer("Nothing I Am Exit");
                txt = "Exit";
            }
            else if(!txt.equals("")){
                String[] arr =  txt.split(" "); 
                    for(String s : arr){
                        if(s.equals("name") || s.equals("Name") || s.equals("NAME")){
                            System.out.println("AI:hello "+arr[arr.length-1]);
                        }
                    } 
                    String[] greetings_words = {"Hi" , "Hello" , "hi" , "hello" , "hey" , "Hey"};
                    for(int start = 0; start <= greetings_words.length-1; start++)
                    {
                        if(arr[0].equals(greetings_words[start])){
                        System.out.println("AI:"+arr[0]+" ");    
                        break;
                        }
                        else if(!arr[0].equals(greetings_words[start]))
                        {
                                    
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
