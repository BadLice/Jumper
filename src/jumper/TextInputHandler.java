
package jumper;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;

public class TextInputHandler 
{
    private static Input in;
    private String name;
    private AppGameContainer app;
    private int delay=120;
    private boolean enter = false;
    
    public TextInputHandler(String name, Input in, AppGameContainer app)
    {
        this.in = in;
        this.name = name+"_";
        this.app = app;
    }
    
    public String update()
    {
        enter = false;
        String ch=inputCheck();
                switch(ch)
                {
                    case "/":
                        break;
                    case "ENTER":
                        enter=true;
                        app.sleep(delay);
                        break;
                    case "SPACE":
                        app.sleep(delay);  
                        name=name.substring(0,name.length()-1);
                        name+=" _";
                        break;
                    case "DEL":
                        app.sleep(delay);
                        if(name.length()>1)
                        {
                            name=name.substring(0,name.length()-2);
                            name+="_";
                        }
                        
                        break;
                        
                    default: 
                        app.sleep(delay);
                        name=name.substring(0,name.length()-1);
                        name+=ch;
                        name+="_";
                        break;
                }
                return name;
    }
    
    public static String inputCheck()
        {
            if(in.isKeyDown(Input.KEY_BACK))
                return "DEL";
            if(in.isKeyDown(Input.KEY_ENTER))
                return "ENTER";
            if(in.isKeyDown(Input.KEY_SPACE))
                return "SPACE";
            if(in.isKeyDown(Input.KEY_0))
                return "0";
            if(in.isKeyDown(Input.KEY_1))
                            return "1";
            if(in.isKeyDown(Input.KEY_2))
                            return "2";
            if(in.isKeyDown(Input.KEY_3))
                            return "3";
            if(in.isKeyDown(Input.KEY_4))
                            return "4";
            if(in.isKeyDown(Input.KEY_5))
                            return "5";
            if(in.isKeyDown(Input.KEY_6))
                            return "6";
            if(in.isKeyDown(Input.KEY_7))
                            return "7";
            if(in.isKeyDown(Input.KEY_8))
                            return "8";
            if(in.isKeyDown(Input.KEY_9))
                            return "9";
            if(in.isKeyDown(Input.KEY_A))
                return "A";
            if(in.isKeyDown(Input.KEY_B))
                            return "B";
            if(in.isKeyDown(Input.KEY_C))
                            return "C";
            if(in.isKeyDown(Input.KEY_D))
                            return "D";
            if(in.isKeyDown(Input.KEY_E))
                            return "E";
            if(in.isKeyDown(Input.KEY_F))
                            return "F";
            if(in.isKeyDown(Input.KEY_G))
                            return "G";
            if(in.isKeyDown(Input.KEY_H))
                            return "H";
            if(in.isKeyDown(Input.KEY_I))
                            return "I";
            if(in.isKeyDown(Input.KEY_J))
                            return "J";
            if(in.isKeyDown(Input.KEY_K))
                            return "K";
            if(in.isKeyDown(Input.KEY_L))
                            return "L";
            if(in.isKeyDown(Input.KEY_M))
                            return "M";
            if(in.isKeyDown(Input.KEY_N))
                            return "N";
            if(in.isKeyDown(Input.KEY_O))
                            return "O";
            if(in.isKeyDown(Input.KEY_P))
                            return "P";
            if(in.isKeyDown(Input.KEY_Q))
                            return "Q";
            if(in.isKeyDown(Input.KEY_R))
                            return "R";
            if(in.isKeyDown(Input.KEY_S))
                            return "S";
            if(in.isKeyDown(Input.KEY_T))
                            return "T";
            if(in.isKeyDown(Input.KEY_U))
                            return "U";
            if(in.isKeyDown(Input.KEY_V))
                            return "V";
            if(in.isKeyDown(Input.KEY_W))
                            return "W";
            if(in.isKeyDown(Input.KEY_X))
                            return "X";
            if(in.isKeyDown(Input.KEY_Y))
                            return "Y";
            if(in.isKeyDown(Input.KEY_Z))
                            return "Z";
            return "/";
        }
    
    public String getString()
    {
           return name.substring(0,name.length()-1);
 
    }
    
    public boolean notifyEnter()
    {
        if(enter)
        {
            enter = false;
            return true;
        }
        else return false;
    }
    
}
