package PaooGame.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
// mouse manager not really used too much
public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean leftPressed = false;
    private int mouseX, mouseY;
    private boolean aux = false;

    public MouseManager() {}

    // MouseListener
    public void Update()
    {
        leftPressed = aux;
    }




    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("sadas");
        aux = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("sadas");
            aux = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            aux = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // MouseMotionListener

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    // Getters

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
