/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
//import static jumper.Jumper.scoreFile;

public class Result implements Serializable
{
    private String nome;
    private int pointScore;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPointScore() {
        return pointScore;
    }

    public void setPointScore(int pointScore) {
        this.pointScore = pointScore;
    }

    public float getTimeScore() {
        return timeScore;
    }

    public void setTimeScore(float timeScore) {
        this.timeScore = timeScore;
    }
    private float timeScore;
    
    public Result(String nome, int point, float time)
    {
        this.nome= nome;
        pointScore= point;
        timeScore= time;
    }
    /*public void wrteEmptyFile()
    {
        try
        {
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(scoreFile));
            for(int i=0;i<10;i++)
            {
                out.writeObject(new Result("-",0,0));
            }
            out.close();
        }
        catch (IOException ex) 
        {
            Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
}
