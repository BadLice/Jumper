/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Jumper extends BasicGame
{
    public static AppGameContainer app;
    static Image background;
   //static Image base;
    static boolean err=false; 
    static ArrayList<Block> blocks;
    static boolean gameOver=false;
    static Bullet bullet;
    static Random rnd = new Random();
    static Player pg;
    static Input in;
    static int countdown=3;
    static long start;
    static long startAtomic;
    static boolean verso;
    static double time=0; 
    static long initTime=0;
    static int punteggio=0;
    static File scoreFileEasy;
    static File scoreFileMedium;
    static File scoreFileHard;
    static ArrayList<Result> scoreEasy;  
    static ArrayList<Result> scoreMedium;
    static ArrayList<Result> scoreHard;
    static boolean salvato = false;
    static String name = "";
    static TextInputHandler nameInput;
    static TextInputHandler FPSInput;
    static boolean scoreboard=false;
    static boolean menu = true;
    static boolean inizio=false;
    static boolean timer = false;
    static boolean options = false;
    static Menu menuClass;
    static Menu menuOptions;
    static long timerTime;
    static String[] voices = {"PLAY","LEADERBOARDS","OPTIONS","CREDITS","EXIT"};
    static String[] voicesMenu = {"SET FPS","SET DIFFICULTY","RESET SCORE","--> BACK"};
    static boolean direzione=false;
    static int i=0;
    static long instant;
    static double coinTime;
    static boolean coinDetected=false, coinSpawned=false, coinTaken=false;
    static Image coin;
    static int coinCount;
    static String FPSString="";
    static File coinFile = new File("coin.dat");
    static boolean FPSIn = false;
    static boolean difficulty=false;
    static boolean ch=false;
    static Menu difficultyMenu;
    static String[] voiceDifficulty={"EASY","MEDIUM","HARD"};
    static int blocksNumber=4;
    static int bulletVel=4;
    static int difficultyID=1;
    static boolean scoreboardMenu = true;
    static Menu menuScoreboard;
    static int k=-1;
    static Atomic nuclear;
    static Image crosshair;
    static boolean mouse = false;
    static Sound coinSound,soundGameOver;
    public static int po=0;
    public static boolean soundb=false,gone = false,menuSoundb=false;
    public static Sound soundtrack = new Sound("soundtrack.wav"),menuSound = new Sound("menu.wav");
    
	public Jumper(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
        {
            crosshair = new Image("crosshairW.png");
            menuScoreboard = new Menu(voiceDifficulty,Color.red,app);
            menuOptions = new Menu(voicesMenu,Color.red,app);
            difficultyMenu=new Menu(voiceDifficulty,Color.red,app);
            verso = rnd.nextBoolean();
           //base = new Image("base.png");
            background = new Image("base1.png"/*background.png"*/);
            blocks = new ArrayList(); blocks.clear();
            for(int i=0;i<blocksNumber;i++)
            {
                blocks.add(new Block(app.getWidth(),app.getHeight()));
            }
            coin = new Image("coin.png");
            bullet = new Bullet(bulletVel);
            in = new Input(app.getHeight());
            nameInput = new TextInputHandler(name,in,app);
            menuClass=new Menu(voices,Color.red,app);
            pg = new Player("pgL.png","pgR.png", in);
            FPSInput = new TextInputHandler(FPSString,in,app);
            nuclear = new Atomic();
            coinSound = new Sound("coins.wav");
            soundGameOver = new Sound("gameOver.wav");
            app.setMouseCursor(crosshair,crosshair.getWidth()/2,crosshair.getHeight()/2);
            soundtrack.setLoop(-1);
            readSave();
            menuSound.setLoop(-1);
            menuSound.play();
        }

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
        {
           
           if(!menu)
           {
               if(!timer)
               {
                   
                   for (Block block : blocks)
                    {
                       block.move();
                    }
                   if(!coinDetected)
                    {
                        Block.coinX=(rnd.nextInt(app.getWidth()-100)+50);
                        instant= System.nanoTime();
                        coinTime= (rnd.nextInt(6)+10)*Math.pow(10, 9);
                        coinDetected=true;
                        coinTaken=false;
                    }
                    else
                        if(System.nanoTime()/Math.pow(10,9)>=(instant+coinTime)/Math.pow(10,9))
                        {
                            coinSpawned=true;
                        }
                   
                    checkCollision();
                   
                
                    if(System.nanoTime()-start > 3*Math.pow(10,9))
                    {

                        bullet.bulletMove();
                    }
                
                
                if(difficultyID==2 && blocksNumber<10)
                {
                    
                    if(Math.floor(System.nanoTime()*10000000/10000000)%10==0)
                    {
                        blocksNumber++;
                        blocks.add(new Block(app.getWidth(),app.getHeight()));
                        
                    }
                }
                
                checkCollision();
                if(gameOver)
                {
                    nuclear.bombSound.stop();
                    name = nameInput.update();
                }
               }
               
           }
                
                
        }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
            
            if(menu)
            {
                app.setMouseGrabbed(false);
                menuClass.drawMenu(g, app);
                updateMenu(g);
            }
            else if(scoreboard)
            {
                if(scoreboardMenu)
                {
                    menuScoreboard.drawMenu(g,app);
                    k=menuScoreboard.getSelected();
                    if(k!=-1)
                        scoreboardMenu=false;
                }
                else
                    drawScoreBoard(g,k);
            }
            else if(options)
            {
              drawOptions(g);  
            }
            else
            {
                drawGame(g);
            }
                            
           
                
	}
        
        
        public static void drawGame(Graphics g) throws SlickException
        {
            app.setMouseGrabbed(false);
             if(!gameOver)
            {
                if(!inizio)
                {
                    if(!timer)
                    {
                        i=0;
                        timerTime=System.nanoTime();
                        coinTime= (rnd.nextInt(6)+10)*Math.pow(10, 9);
                        timer = true;
                        countdown=4;
                    }
                    salvato=false;
                    punteggio=0;
                }
                if(timer)
                {
                    if(System.nanoTime()-timerTime>=i*Math.pow(10,9))
                    {
                        i++;
                        countdown--;
                    }
                    if(countdown<=0)
                    {
                        timer = false;
                        inizio = true;
                        initTime=System.nanoTime();
                    }
                    g.drawString(""+countdown,app.getWidth()/2,app.getHeight()/2);
                }
                else
                {
                    if(inizio)
                    {
                        if(!gone)
                        {
                            gone = true;
                            soundtrack=new Sound("soundtrack.wav");
                            soundtrack.setLoop(-1);
                            soundtrack.play();
                        }
                        
                        if(!mouse)
                        {
                            crosshair = new Image("crosshair.png");
                            app.setMouseCursor(crosshair,crosshair.getWidth()/2,crosshair.getHeight()/2);
                            mouse = true;
                        }
                        time=tronc(((System.nanoTime()-initTime)/Math.pow(10,9)));
                        background.draw(0,0,app.getWidth(),app.getHeight());
                       // base.draw(0, app.getHeight()-50, app.getWidth(),50);
                        pg.draw();
                        for(int i=0;i<blocks.size();i++)
                        {
                           blocks.get(i).getImg().draw(blocks.get(i).getX(),blocks.get(i).getY(),blocks.get(i).getWidth(),blocks.get(i).getHeight());
                        }
                        nuclear.draw();
                        if(System.nanoTime()-nuclear.k<=0.8*Math.pow(10,9))
                        {
                            nuclear.boom.draw(nuclear.dx, nuclear.dy,30,30);
                        }
                        bullet.getImg().draw(bullet.getX(),bullet.getY(),bullet.getWidth(),bullet.getHeight());
                        g.drawString(""+time, 20,50);
                        g.drawString(""+punteggio,20,80);
                        g.drawString(""+coinCount, 20,110);
                        if(coinSpawned && !coinTaken)
                        {
                                coin.draw(Block.coinX, Block.coinY, 15, 15);
                                coinSpawned=false;
                        }
                    }
                } 
            }
            else
            {
                drawGameOver(g);  
  
            }
               
        }
        
        public static void drawOptions(Graphics g) throws SlickException//anche update
        {
            
            if(FPSIn)
            {
                int FPS=60;
                FPSString = FPSInput.update();
                g.drawString("FPS:",100,100);
                g.drawString(FPSString,150,100);
                if (err)
                {
                    g.setColor(Color.red);
                    g.drawString("VALORE NON VALIDO",100,120);
                    g.setColor(Color.white);
                }
                
                if(FPSInput.notifyEnter())
                {
                    try
                    {
                        err=false;
                        FPS = Integer.parseInt(FPSInput.getString());
                    }
                    catch(Exception e)
                    {
                       err=true; 
                    }
                    if(!err)
                    {
                        app.setTargetFrameRate(FPS);
                        menu=true;
                        FPSIn=false;
                        options=false;
                        FPSInput = new TextInputHandler("",in,app);
                    }
                    
                }
            }
            else if(difficulty)
            {
                difficultyMenu.drawMenu(g, app);
                switch(difficultyMenu.getSelected())
                {
                    case 0:blocksNumber=3;
                        bulletVel=3;
                        resetAll();
                        app.sleep(120);
                        difficulty=false;
                        difficultyID=0;
                        options=false;
                        menu = true;
                        break;
                    case 1:blocksNumber=4;
                        bulletVel=4;
                        resetAll();
                        app.sleep(120);
                        difficultyID=1;
                        difficulty=false;
                        options=false;
                        menu = true;
                        break;
                    case 2:blocksNumber=6;
                        bulletVel=8;
                        resetAll();
                        app.sleep(120);
                        difficultyID=2;
                        difficulty=false;
                        options=false;
                        menu = true;
                        break;
                }
            }
            else
                menuOptions.drawMenu(g, app);
            
            switch(menuOptions.getSelected())
            {
                case 0:FPSIn=true;
                    menuOptions.resetSelected();
                    break;
                case 1: difficulty = true;
                        app.sleep(120);
                        menuOptions.resetSelected();
                    break;    
                case 2: resetScore(); 
                        readSave();
                        options=false;
                        menu=true;
                    break;
                case 3: options=false;
                        menu=true;
                    break;
            }
        }
        
        public void checkCollision()
        {
            for (Block block : blocks) 
            {
                if (block.getX() >= pg.getPgX() && block.getX() <= pg.getPgX()+pg.getPgWidth() && block.getY() >= pg.getPgY() && block.getY() <= pg.getPgY()+pg.getPgHeight()) {
                    gameOver=true;
                }
            }
            
             if(bullet.getX()>=pg.getPgX()
                    &&bullet.getX()<=pg.getPgX()+pg.getPgWidth()
                    &&bullet.getY()>=pg.getPgY()
                    &&bullet.getY()<=pg.getPgY()+pg.getPgHeight())
                    {
                        gameOver=true;
                    }
             
             if(coinSpawned && pg.getPgX()+pg.getPgWidth()>=Block.coinX && pg.getPgX()<=Block.coinX+15
                     && pg.getPgY()+pg.getPgHeight()>=Block.coinY && pg.getPgY()<=Block.coinY+15)
                    {
                        coinTaken=true;
                        coinSound = new Sound("coins.wav");
                        coinSound.play();
                        coinSpawned=false;
                        coinDetected=false;
                        coinCount++;
                    }
            }
        
        public static void updateMenu(Graphics g) throws SlickException
        {
            switch(menuClass.getSelected())
            {
                case 0: 
                   resetAll();
                   menuSound.stop();
                    break;
                    
                case 1:
                     scoreboard=true;
                     menu = false;
                    break;
                    
                case 2:
                        resetAll();
                        menu=false;
                        options=true;
                    break;
                    
                case 4:
                    System.exit(0);
                    break;
            }
           
        }
        public static void drawGameOver(Graphics g) throws SlickException
        {
            soundtrack.stop();

            if(!scoreboard)
            {
                g.drawString("YOU LOST!", app.getWidth()/2-30, 70);
                g.drawString("TIME: "+time+" S", app.getWidth()/2-48, 100);
                g.drawString("SCORE: "+punteggio+" PTS", app.getWidth()/2-51, 130);
                g.drawString("NAME:\n\t"+name,app.getWidth()/2-80,160);
                soundGameOver.play();
                if(ch)
                {
                    g.setColor(Color.red);
                    g.drawString("MIN 1 CHAR, MAX 8 CHAR",app.getWidth()/2-80,205); 
                    g.setColor(Color.white);
                }
                if(nameInput.notifyEnter())
                {
                    if(nameInput.getString().length()<=8&&nameInput.getString().length()>0)
                    {
                        if(!salvato)
                        {
                            saveScore();
                            salvato = true;
                            mouse = false;
                            crosshair=new Image("crosshairW.png");
                            app.setMouseCursor(crosshair,crosshair.getWidth()/2,crosshair.getHeight()/2);
                        }   
                    menu=true;
                    gameOver=false;
                    initTime=System.nanoTime();
                    salvato = false;
                    ch=false;
                    punteggio =0;
                     if(!menuSoundb)
                    {
                        menuSound=new Sound("menu.wav");
                        menuSound.setLoop(-1);
                        menuSound.play();
                        menuSoundb=true;
                    }
                    }
                    else
                        ch=true;
                }
            }    
        }
        
        public static void drawScoreBoard(Graphics g,int k)
        {
            g.drawString("NAME          TIME          SCORE",40,40);
            String[] vet = new String[10];
            ArrayList<Result> score = new ArrayList<Result>();
            switch(k)
            {
                case 0:
                    score = scoreEasy;
                    break;
                case 1: score = scoreMedium;
                    break;
                case 2: score = scoreHard;
                    break;
            }
            for(int i=0;i<10;i++)
            {
                vet[i]=score.get(i).getNome();
                for(int j=0;j<14-(""+score.get(i).getNome()).length();j++)
                {
                    vet[i]+=" ";
                }
                
                vet[i]+=score.get(i).getTimeScore();
                for(int j=0;j<16-(""+score.get(i).getTimeScore()).length();j++)
                {
                    vet[i]+=" ";
                }
                vet[i]+=score.get(i).getPointScore();
                
            }
            for(int i=0;i<10;i++)
            {
                g.drawString(vet[i],40,80+(20*i));
            }
            Voice backBtn= new Voice("--> BACK",70,350);
            backBtn.drawVoice(app, g, Color.white);
            backBtn.updateVoice(app);
            if(backBtn.isSelected())
            {
                menu=true;
                scoreboard=false;
                scoreboardMenu=true;
                k=-1;
            }
            if(backBtn.isHighlight())
                backBtn.drawVoice(app, g, Color.red);
        }
        
        public static double tronc(double x)
        {
            return Math.floor(x*100)/100;
        }
        
        public static void readSave()
        {
            scoreMedium.clear();
            scoreEasy.clear();
            scoreHard.clear();
            try{
             ObjectInputStream in = new ObjectInputStream(new FileInputStream(scoreFileMedium));
                //coinCount =(int) in.readObject();
               for(int i=0;i<10;i++)
               {
                  scoreMedium.add((Result)in.readObject());
                  
               }
               in.close();
              DataInputStream in2 = new DataInputStream(new FileInputStream(coinFile));
              coinCount = in2.readInt();
              in2.close();
            }
            catch(Exception e){}
            try{
             ObjectInputStream in = new ObjectInputStream(new FileInputStream(scoreFileEasy));
                //coinCount =(int) in.readObject();
               for(int i=0;i<10;i++)
               {
                  scoreEasy.add((Result)in.readObject());
                  
               }
               in.close();
            }
            catch(Exception e){}
            try{
             ObjectInputStream in = new ObjectInputStream(new FileInputStream(scoreFileHard));
                //coinCount =(int) in.readObject();
               for(int i=0;i<10;i++)
               {
                  scoreHard.add((Result)in.readObject());
                  
               }
               in.close();
            }
            catch(Exception e){}
        }
        
        public static void saveScore()
        {
            try 
            { 
                File scoreFile = new File("");
                ArrayList<Result> score = new ArrayList<Result>();
               switch(difficultyID)
               {
                   case 0 : scoreFile = scoreFileEasy;
                            score = scoreEasy;
                       break;
                   case 1:scoreFile = scoreFileMedium;
                            score = scoreMedium;
                       break;
                   case 2:scoreFile = scoreFileHard;
                            score = scoreHard;
                       break;
               }
               Result r = new Result(nameInput.getString(),punteggio,(float) time);
                    boolean stop = false;
                    for(int i=0;(i<10)&&!stop;i++)
                    {
                        if(score.get(i).getTimeScore()<r.getTimeScore())
                        {
                            score.add(i,r);
                            stop = true;
                        }
                    }

                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(scoreFile));
                    for(int i=0;i<10;i++)
                    {
                       out.writeObject(score.get(i));
                    }
                    out.close();
               
                
                
                DataOutputStream out2 = new DataOutputStream(new FileOutputStream(coinFile));
                out2.writeInt(coinCount);
                out2.close();
            }
            catch (IOException ex) 
            {
                Logger.getLogger(Jumper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        public static void print(String msg)
        {
            Logger.getLogger(Jumper.class.getName()).log(Level.INFO,msg);
        }
        
        public static void resetScore()
        {
            Result r = new Result("-",0,0);
             try 
                {
                 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(scoreFileEasy));
                 for(int i=0;i<10;i++)
                   {
                      out.writeObject(r);
                   }
                } catch (Exception ex) {}
             
             try 
                {
                 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(scoreFileMedium));
                 for(int i=0;i<10;i++)
                   {
                      out.writeObject(r);
                   }
                } catch (Exception ex) {}
             try
                {
                 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(scoreFileHard));
                 for(int i=0;i<10;i++)
                   {
                      out.writeObject(r);
                   }
                } catch (Exception ex) {}
        }
         
        public static void main(String[] args)
	{
		try
		{
                        
                        scoreEasy = new ArrayList<Result>();
                        scoreMedium = new ArrayList<Result>();
                        scoreHard = new ArrayList<Result>();
			scoreFileEasy = new File("scoreEasy.dat");
                        scoreFileMedium = new File("scoreMedium.dat");
                        scoreFileHard = new File("scoreHard.dat");
			app = new AppGameContainer(new Jumper("Simple Slick Game"));
			app.setDisplayMode(640, 480, false);
                        app.setTargetFrameRate(60);
                        app.setFullscreen(false);
                        
                        //app.setMouseGrabbed(true);
			app.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Jumper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
        
        public static void resetAll() throws SlickException
        {
                    soundb=false;
                    menu= false;
                    timer = false;
                    gameOver=false;
                    inizio = false;
                    coinDetected=false; coinSpawned=false; coinTaken=false;
                    verso = rnd.nextBoolean();
                    //base = new Image("base.png");
                    background = new Image("base1.png");
                    blocks.clear();
                    for(int i=0;i<blocksNumber;i++)
                    {
                        blocks.add(new Block(app.getWidth(),app.getHeight()));
                    }
                    bullet = new Bullet(bulletVel);
                    in = new Input(app.getHeight());
                    name="";
                    nameInput = new TextInputHandler(name,in,app);
                    menuClass=new Menu(voices,Color.red,app);
                    pg = new Player("pgL.png","pgR.png", in);
                    crosshair = new Image("crosshairW.png");
                    app.setMouseCursor(crosshair,crosshair.getWidth()/2,crosshair.getHeight()/2);
                    //mouse = false;
                    nuclear= new Atomic();
                    soundGameOver = new Sound("gameOver.wav");
                    countdown = 4;
                    gone = false;
                    menuSoundb=false;
                    readSave();
                    
            
        }
        
}

