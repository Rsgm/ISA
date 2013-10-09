package isa.gui;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import isa.Gameplay;
import isa.Person;
import isa.gui.GameScreen.Tab;

public class GameInput implements InputProcessor {

    public static int scroll;
    public GameScreen screen;

    public GameInput(GameScreen screen) {
        this.screen = screen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screen.peopleTab.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
            screen.tab = Tab.PEOPLE;
            screen.peopleTab.setColor(.7f, .7f, .7f, 1f);
            screen.logTab.setColor(Color.WHITE);
            screen.storageTab.setColor(Color.WHITE);
            scroll = 0;
            return true;
        } else if (screen.logTab.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
            screen.tab = Tab.LOG;
            screen.peopleTab.setColor(Color.WHITE);
            screen.logTab.setColor(.7f, .7f, .7f, 1f);
            screen.storageTab.setColor(Color.WHITE);
            scroll = 0;
            return true;
        } else if (screen.storageTab.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
            screen.tab = Tab.STORAGE;
            screen.peopleTab.setColor(Color.WHITE);
            screen.logTab.setColor(Color.WHITE);
            screen.storageTab.setColor(.7f, .7f, .7f, 1f);
            scroll = 0;
            return true;
        }

        switch (screen.tab) {
            default:
                for (Person p : Gameplay.people) {
                    if (p.suspect.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
                        p.suspect();
                        return true;
                    }
                }
                break;
            case LOG:
//                for (Log l : Gameplay.logs) {
//                    if (l.close.getBoundingRectangle().contains(screenX, screen.height - screenY)) {
//                        Gameplay.logs.remove(l);
//                        return true;
//                    }
//                }
                break;
            case STORAGE:
                if (screen.storageBuy10.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 10) {
                    Gameplay.storage += 10;
                    Gameplay.money -= 10;
                    return true;
                } else if (screen.storageBuy10.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money < 10) {
                    screen.errorText = "You do not have enough money for this.";
                    screen.errorTextCountdownTimerFloatingPointVariable = 3;
                    return true;
                }

                if (screen.storageBuy100.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 100) {
                    Gameplay.storage += 100;
                    Gameplay.money -= 100;
                    return true;
                } else if (screen.storageBuy100.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money < 100) {
                    screen.errorText = "You do not have enough money for this.";
                    screen.errorTextCountdownTimerFloatingPointVariable = 3;
                    return true;
                }

                if (screen.storageBuy1000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 1000) {
                    Gameplay.storage += 1000;
                    Gameplay.money -= 1000;
                    return true;
                } else if (screen.storageBuy1000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money < 1000) {
                    screen.errorText = "You do not have enough money for this.";
                    screen.errorTextCountdownTimerFloatingPointVariable = 3;
                    return true;
                }

                if (screen.storageBuy10000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 10000) {
                    Gameplay.storage += 10000;
                    Gameplay.money -= 10000;
                    return true;
                } else if (screen.storageBuy10000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money < 10000) {
                    screen.errorText = "You do not have enough money for this.";
                    screen.errorTextCountdownTimerFloatingPointVariable = 3;
                    return true;
                }

                if (screen.storageBuy100000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money >= 100000) {
                    Gameplay.storage += 100000;
                    Gameplay.money -= 100000;
                    return true;
                } else if (screen.storageBuy100000.getBoundingRectangle().contains(screenX, screen.height - screenY) && Gameplay.money < 100000) {
                    screen.errorText = "You do not have enough money for this.";
                    screen.errorTextCountdownTimerFloatingPointVariable = 3;
                    return true;
                }
                break;
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if ((amount == -1) || (amount == 1 && scroll < 100)) {
            scroll += amount * 25;
        }
        return true;
    }

}
