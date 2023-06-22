package PaooGame.States;


import java.awt.*;

import static PaooGame.Items.Coin.score;

//buttoanele mele cu hover
public class Button extends Rectangle {

    private String text;
    private boolean hovered;

    public Button(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        this.text = text;
        this.hovered = false;
    }

    public String getText() {
        return text;
    }


    public void draw(Graphics g) {


        Font cartoonFont = new Font("Comic Sans MS", Font.BOLD, 30); // increase font size
        g.setFont(cartoonFont);

        if(hovered)
        {
            g.setColor(new Color(105, 105, 105)); // yellowish color

        }
        else {
            g.setColor(new Color(105, 65, 23)); // yellowish color
        }


        g.drawString(text, x + width / 2 - g.getFontMetrics().stringWidth(text) / 2, y + height / 2 + g.getFontMetrics().getAscent() / 2);
    }

    public void update(int mouseX, int mouseY) {
        hovered = contains(mouseX, mouseY);
    }


}
