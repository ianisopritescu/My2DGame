package io_handler;

import main.GamePanel;
import object.ObjectDesk;
import object.ObjectToilet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean searchPlayer = false;

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

		ObjectDesk desk = (ObjectDesk)gp.objMap.get(gp.player.objPointColliding);

		if (code == KeyEvent.VK_W) {
			if (desk.slotRow != 0)
				desk.slotRow --;
		}
		if (code == KeyEvent.VK_S) {
			if (desk.slotRow != desk.maxSlotRow)
				desk.slotRow ++;
		}
		if (code == KeyEvent.VK_D) {
			if (desk.slotCol != desk.maxSlotCol)
				desk.slotCol ++;
		}
		if (code == KeyEvent.VK_A) {
			if (desk.slotCol != 0)
				desk.slotCol --;
		}
		if (code >= KeyEvent.VK_1 && code <= KeyEvent.VK_5) {
			if (gp.ui.hb.slotSelected == code - KeyEvent.VK_1 + 1) {
				gp.ui.hb.slotSelected = 0;
			} else {
				gp.ui.hb.slotSelected = code - KeyEvent.VK_1 + 1;
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			desk.getItems();
		}
	}
	void toiletState (int code) {
		if (code == KeyEvent.VK_E || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_W) {
			if (((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).slotRow != 0)
				((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).slotRow --;
		}
		if (code == KeyEvent.VK_S) {
			if (((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).slotRow != ((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).maxSlotRow)
				((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).slotRow ++;
		}
		if (code == KeyEvent.VK_D) {
			if (((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).slotCol != ((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).maxSlotCol)
				((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).slotCol ++;
		}
		if (code == KeyEvent.VK_A) {
			if (((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).slotCol != 0)
				((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).slotCol --;
		}
		if (code >= KeyEvent.VK_1 && code <= KeyEvent.VK_5) {
			if (gp.ui.hb.slotSelected == code - KeyEvent.VK_1 + 1) {
				gp.ui.hb.slotSelected = 0;
			} else {
				gp.ui.hb.slotSelected = code - KeyEvent.VK_1 + 1;
			}
		}
//		if (code == KeyEvent.VK_ENTER) {
//			((ObjectToilet)gp.objMap.get(gp.player.objPointColliding)).getItemFromHotbar();
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
		if (code == KeyEvent.VK_Z) {
			searchPlayer = true;
		}
		if (code >= KeyEvent.VK_1 && code <= KeyEvent.VK_5) {
			if (gp.ui.hb.slotSelected == code - KeyEvent.VK_1 + 1) {
				gp.ui.hb.slotSelected = 0;
			} else {
				gp.ui.hb.slotSelected = code - KeyEvent.VK_1 + 1;
			}
		}
		if (code == KeyEvent.VK_E) {
			if (gp.player.objPointColliding != null) {
				if (gp.objMap.get(gp.player.objPointColliding).name.equals("Desk"))
					gp.gameState = gp.deskState;
				if (gp.objMap.get(gp.player.objPointColliding).name.equals("toilet"))
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
		if (code == KeyEvent.VK_Z) {
			searchPlayer = false;
		}
	}
}
