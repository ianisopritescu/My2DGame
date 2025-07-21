package ui;

import constants.General;

import java.awt.*;

public class TextBox {
    public static void draw(final Graphics2D g2d, final String text, final int x, final int y) {
//        Font font = new Font("Arial", Font.PLAIN, 23);
        Font font = General.mainFont().deriveFont(Font.PLAIN, 23);

        g2d.setFont(font);

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        int padding = 7;
        int boxHeight = textHeight + 2 * padding;
        int boxWidth = textWidth + 2 * padding;

        g2d.setColor(new Color(205, 127, 50));
        g2d.fillRect(x, y, boxWidth, boxHeight);

        int textX = x + padding;
        int textY = y + padding + fm.getAscent();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, textX, textY);
    }
}
