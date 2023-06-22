package PaooGame.GameWindow;

import javax.swing.*;
import java.awt.*;
//implementeaza notiunea de game window, cu setari aferente
// Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
//         ferestre grafice si totodata si cea a unui container (toate elementele
//         grafice vor fi continute de fereastra).
public class GameWindow
{
    private JFrame  wndFrame;
    private String  wndTitle;
    private int     wndWidth;
    private int     wndHeight;
    private Canvas  canvas;

    public GameWindow(String title, int width, int height)
    {
        wndTitle    = title;
        wndWidth    = width;
        wndHeight   = height;
        wndFrame    = null;
    }

    public void BuildGameWindow()
    {
        /// Daca fereastra a mai fost construita intr-un apel anterior
        /// se renunta la apel

        if(wndFrame != null)
        {
            return;
        }
        /// Aloca memorie pentru obiectul de tip fereastra si seteaza denumirea
        /// ce apare in bara de titlu

        wndFrame = new JFrame(wndTitle);

        wndFrame.setSize(wndWidth, wndHeight);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);
        wndFrame.setVisible(true); // ca sa o vad

        canvas = new Canvas();// mapa e cat canvasul
        /// Avand in vedere ca elementele unei ferestre pot fi scalate atunci cand
        /// fereastra este redimensionata

        canvas.setPreferredSize(new Dimension(6400, 1920));
        canvas.setMaximumSize(new Dimension(6400, 1920));
        canvas.setMinimumSize(new Dimension(6400, 1920));
        /// Avand in vedere ca obiectul de tip canvas, proaspat creat, nu este automat
        /// adaugat in fereastra trebuie apelata metoda add a obiectul wndFrame
        wndFrame.add(canvas);


    }

    public int GetWndWidth()
    {
        return wndWidth;
    }
    public int GetWndHeight()
    {
        return wndHeight;
    }
    public Canvas GetCanvas()
    {
        return canvas;
    }
    public JFrame GetWndFrame()
    {
        return wndFrame;
    }

    public int getX() {
        return wndFrame.getX();
    }

    public int getY() {
        return wndFrame.getY();
    }

}
