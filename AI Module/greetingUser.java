class greetingUser {
    public void Answer(String Text){
        System.out.println(Text);
    }
    public String greet(String txt){
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
                String[] greetings_words = {"Hi" , "Hello" , "hi" , "hello"};
                for(int start = 0; start <= greetings_words.length; start++)
                {
                    if(txt.equals(greetings_words[start])){
                    System.out.println("AI:"+txt+" How are You! I Am Fine");    
                    break;
                }
                }
            }
            return txt;
    }
}
