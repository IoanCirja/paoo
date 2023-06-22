package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Input.KeyManager;
import PaooGame.Input.MouseManager;
import PaooGame.Items.Camera;
import PaooGame.States.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable
{
    private GameWindow wnd;
    private boolean runState;
    private Thread gameThread;
    private BufferStrategy bs;

    public Graphics getG() {
        return g;
    }
    private Camera camera;
    private Graphics g;


    public Level1 getPlayState() {
        return (Level1) lvl1;
    }

    private State lvl1;
    private State scores;
    private State load;
    private State lvl2;
    private State lvl3;
    private State end;

    private State menuState;

    public State getLvl2() {
        return lvl2;
    }
    public Level2 getLvl22() {
        return (Level2)lvl2;
    }



    private KeyManager keyManager;
    private MouseManager mouseManager;
    private RefLinks refLink;


    public Game(String title, int width, int height)
    {

        wnd = new GameWindow(title, width, height);
        runState = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }


    public State getLvl3() {
        return lvl3;
    }

    public State getEnd() {
        return end;
    }

    public State getLoad() {
        return load;
    }

    public void setLvl1(State lvl1) {
        this.lvl1 = lvl1;
    }

    public void setLvl2(State lvl2) {
        this.lvl2 = lvl2;
    }

    public void setLvl3(State lvl3) {
        this.lvl3 = lvl3;
    }

    private void InitGame()
    {
            /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
            ///Sa ataseaza ferestrei managerul de tastatura pentru a primi evenimentele furnizate de fereastra.
        wnd.GetWndFrame().addKeyListener(keyManager);
        wnd.GetWndFrame().addMouseListener(mouseManager);
        wnd.GetWndFrame().addMouseMotionListener(mouseManager);
            ///Se incarca toate elementele grafice (dale)
        Assets.Init();
            ///Se construieste obiectul de tip shortcut ce va retine o serie de referinte catre elementele importante din program.
        refLink = new RefLinks(this);
            ///Definirea starilor programului
        lvl1       = new Level1(refLink);
        lvl2 = new Level2(refLink);
        lvl3 = new Level3(refLink);
        menuState       = new MenuState(refLink);

        scores = new Scores(refLink);
        load = new Load(refLink);
        end = new EndState(refLink);
            ///Seteaza starea implicita cu care va fi lansat programul in executie
        State.SetState(menuState);
        camera = new Camera(refLink, 0, 0);
    }

    public GameWindow getWnd() {
        return wnd;
    }

    public State getScores() {
        return scores;
    }

    public Camera getCamera()
    {
        return camera;
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run()
    {
            /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

            /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
            /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

            /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true)
        {
                /// Se obtine timpul curent
            curentTime = System.nanoTime();
                /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame()
    {
        if(runState == false)
        {
                /// Se actualizeaza flagul de stare a threadului
            runState = true;
                /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
                /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
                /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else
        {
                /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame()
    {
        if(runState == true)
        {
                /// Actualizare stare thread
            runState = false;
                /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                    /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                    /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                    /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
                /// Thread-ul este oprit deja.
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update()
    {
            ///Determina starea tastelor
        keyManager.Update();
        mouseManager.Update();
        ///Trebuie obtinuta starea curenta pentru care urmeaza a se actualiza starea, atentie trebuie sa fie diferita de null.
        if(State.GetState() != null)
        {
                ///Actualizez starea curenta a jocului daca exista.
            State.GetState().Update();
        }
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Draw()
    {
            /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
            /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
                /// Se executa doar la primul apel al metodei Draw()
            try
            {
                    /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                    /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
            /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();
            /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        /// operatie de desenare
            ///Trebuie obtinuta starea curenta pentru care urmeaza a se actualiza starea, atentie trebuie sa fie diferita de null.
            if(State.GetState() != null)
            {
                ///Actualizez starea curenta a jocului daca exista.
                State.GetState().Draw(g);
            }
        /// end operatie de desenare

            /// Se afiseaza pe ecran
        bs.show();

            /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
            /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }

    /*! \fn public int GetWidth()
        \brief Returneaza latimea ferestrei
     */
    public int GetWidth()
    {
        return wnd.GetWndWidth();
    }

    /*! \fn public int GetHeight()
        \brief Returneaza inaltimea ferestrei
     */
    public int GetHeight()
    {
        return wnd.GetWndHeight();
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza obiectul care gestioneaza tastatura.
     */

    public State getMenuState() {
        return menuState;
    }

    public GameWindow getGameWindow() {
        return wnd;
    }

    public KeyManager GetKeyManager()
    {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public State getLvl1() {
        return lvl1;
    }
    public Level1 getLvl11() {
        return (Level1) lvl1;
    }

    public void resetLevels(RefLinks refLinks)
    {
        lvl1 = new Level1(refLinks);
        lvl2 = new Level2(refLinks);
        lvl3 = new Level3(refLinks);
        System.out.println("fafaf");
    }

    public Level3 getLvl33() {
        return (Level3) lvl3;
    }
}

