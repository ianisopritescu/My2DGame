package main;

import object.ObjectDesk;
import object.ObjectToilet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;

	public boolean showDebugText = false;
	public boolean spacePressed;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_T) {
			showDebugText = !showDebugText;
		}

		if (gp.gameState == gp.titleState) {
			titleState(code);
		} else if (gp.gameState == gp.playState) {
			playState(code);
		} else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		} else if (gp.gameState == gp.deskState) {
			deskState(code);
		} else if (gp.gameState == gp.toiletState) {
			toiletState(code);
		}
	}

	void titleState(int code) {
		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum --;
			if (gp.ui.commandNum < 0)
				gp.ui.commandNum = 2;
		}
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum ++;
			if (gp.ui.commandNum > 2)
				gp.ui.commandNum = 0;
		}

		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
			}
			if (gp.ui.commandNum == 1) {
				gp.gameState = gp.playState;
			}
			if (gp.ui.commandNum == 2) {
				System.exit(0);
			}
		}
	}
	void pauseState(int code) {
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}
	void deskState(int code) {
		if (code == KeyEvent.VK_E || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_W) {
			if (((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).slotRow != 0)
				((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).slotRow --;
		}
		if (code == KeyEvent.VK_S) {
			if (((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).slotRow != ((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).maxSlotRow)
				((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).slotRow ++;
		}
		if (code == KeyEvent.VK_D) {
			if (((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).slotCol != ((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).maxSlotCol)
				((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).slotCol ++;
		}
		if (code == KeyEvent.VK_A) {
			if (((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).slotCol != 0)
				((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).slotCol --;
		}
		if (code >= KeyEvent.VK_1 && code <= KeyEvent.VK_5) {
			if (gp.ui.hb.slotSelected == code - KeyEvent.VK_1 + 1) {
				gp.ui.hb.slotSelected = 0;
			} else {
				gp.ui.hb.slotSelected = code - KeyEvent.VK_1 + 1;
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			((ObjectDesk)gp.obj.get(gp.player.objIndexColliding)).getItems();
		}
	}
	void toiletState (int code) {
		if (code == KeyEvent.VK_E || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_W) {
			if (((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).slotRow != 0)
				((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).slotRow --;
		}
		if (code == KeyEvent.VK_S) {
			if (((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).slotRow != ((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).maxSlotRow)
				((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).slotRow ++;
		}
		if (code == KeyEvent.VK_D) {
			if (((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).slotCol != ((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).maxSlotCol)
				((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).slotCol ++;
		}
		if (code == KeyEvent.VK_A) {
			if (((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).slotCol != 0)
				((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).slotCol --;
		}
		if (code >= KeyEvent.VK_1 && code <= KeyEvent.VK_5) {
			if (gp.ui.hb.slotSelected == code - KeyEvent.VK_1 + 1) {
				gp.ui.hb.slotSelected = 0;
			} else {
				gp.ui.hb.slotSelected = code - KeyEvent.VK_1 + 1;
			}
		}
//		if (code == KeyEvent.VK_ENTER) {
//			((ObjectToilet)gp.obj.get(gp.player.objIndexColliding)).getItemFromHotbar();
//		}
	}
	void playState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.titleState;
		}
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if (code >= KeyEvent.VK_1 && code <= KeyEvent.VK_5) {
			if (gp.ui.hb.slotSelected == code - KeyEvent.VK_1 + 1) {
				gp.ui.hb.slotSelected = 0;
			} else {
				gp.ui.hb.slotSelected = code - KeyEvent.VK_1 + 1;
			}
		}
		if (code == KeyEvent.VK_E) {
			if (gp.player.objIndexColliding != 999) {
				if (gp.obj.get(gp.player.objIndexColliding).name.equals("Desk"))
					gp.gameState = gp.deskState;
				if (gp.obj.get(gp.player.objIndexColliding).name.equals("toilet"))
					gp.gameState = gp.toiletState;
			}
		}
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if (code == KeyEvent.VK_R) {
			gp.tileM.loadMap("resources/maps/map2.txt");
		}
		if (code == KeyEvent.VK_Q) {
			if (gp.ui.hb.slotSelected != 0 && gp.ui.hb.inventory[gp.ui.hb.slotSelected - 1] != null) {
				gp.ui.hb.dropItem(gp.ui.hb.slotSelected - 1);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
}
