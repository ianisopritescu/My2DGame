package io_handler;

import entity.Entity;
import main.GamePanel;
import object.SuperObject;

import java.awt.*;
import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
    private final GamePanel gp;
    public boolean hovering = false;
    public SuperObject objectHovered = null;
    public Entity entityHovered = null;
    public int x = 0;
    public int y = 0;
    public int xWindow = 0;
    public int yWindow = 0;

    public MouseHandler(final GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        xWindow = e.getX();
        yWindow = e.getY();
        x = xWindow + gp.player.worldX - gp.player.screenX;
        y = yWindow + gp.player.worldY - gp.player.screenY;
        Point mousePoint = new Point(x, y);

//        if (objectHovered != null) {
//            Rectangle rect = new Rectangle(objectHovered.solidArea);
//            rect.x += objectHovered.worldX;
//            rect.y += objectHovered.worldY;
//
//            if (rect.contains(mousePoint)) {
//                return;
//            }
//
//            hovering = false;
//        }
//
//        gp.objMap.values().forEach(obj -> {
//            Rectangle rect1 = new Rectangle(obj.solidArea);
//            rect1.x += obj.worldX;
//            rect1.y += obj.worldY;
//            if (rect1.contains(mousePoint)) {
//                System.out.println(obj.name);
//                hovering = true;
//                objectHovered = obj;
//            }
//        });

        if (entityHovered != null) {
            Rectangle rect = new Rectangle(entityHovered.worldX, entityHovered.worldY, gp.tileSize, gp.tileSize);

            if (rect.contains(mousePoint)) {
                return;
            }

            hovering = false;
            entityHovered = null;
        }

        gp.entities.values().forEach(entity -> {
            Rectangle rect1 = new Rectangle(entity.worldX, entity.worldY, gp.tileSize, gp.tileSize);
            if (rect1.contains(mousePoint)) {
                entityHovered = entity;
                hovering = true;
            }
        });

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
