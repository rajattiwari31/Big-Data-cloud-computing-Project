import java.util.*;
import java.net.*;
import java.io.*;
import org.json.simple.JSONObject;

public class serverProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;
    private int cur=5;
    JSONObject obj = new JSONObject();
    Map<String,String> hm = new HashMap<>();
    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who","hi" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?",
                                 "hi" };

    public String processInput(String theInput)throws Exception {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Server Strated";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            String[] ch = theInput.split(" ");
            //System.out.println(ch[0]); 	
            if (ch[0].equalsIgnoreCase("put")) {
               // theOutput = clues[currentJoke];
                String[] data = ch[1].split(",");
                obj.put(data[0],data[1]);
               // hm.put(data[0],data[1]);
                //System.out.println(hm.get(data[0]));
                theOutput = "OK";
               // state = SENTCLUE;
                }
                else if(ch[0].equalsIgnoreCase("get"))
                {
                	//theOutput = clues[cur];
                	//System.out.println("hello " + ch[1]);	
                	//theOutput = ch[1] + "  " + hm.get(ch[1]);
                	StringWriter out = new StringWriter();
      			obj.writeJSONString(out);
      
      			String jsonText = out.toString();
      			theOutput=jsonText;
                	
                	//state = SENTCLUE;
                }
            
            
            else if(ch[0].equalsIgnoreCase("exit")) {
            
            	//state=SENTCLUE;
                theOutput = "exit";
           //System.exit(0);
                
            }
         /*else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + 
			    clues[currentJoke] + 
			    " who?\"" + 
			    "! Try again. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } */}else {
                theOutput = "Bye.";
                state = WAITING;
            }
        
        return theOutput;
    }
}
