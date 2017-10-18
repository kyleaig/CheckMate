package GameState;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenu extends GameState {

    private static final String backgroundPath = "/Backgrounds/MainMenuBg.png";
    private static final String title = "Check Mate";

    private static final int HOME_MENU = 0;
    private static final int NEW_GAME_MENU = 1;
    private static final int OPTIONS_MENU = 2;

    private String[][] options = {{
            // Layer 0 (Home)
            "New Game", "Options", "Quit"
    }, {
            // Layer 1 (New Game)
            "1 Player", "2 Player", "Back"
    }, {
            // Layer 2 (Options)
            "Timer Off", "TBD", "Back"
    }};

    private int menu;
    private int selectedOption;

    private BufferedImage background;
    private Color highlightColor;
    private Color baseColor;
    private Font titleFont;
    private Font menuFont;

    private Settings settings;
    private GameStateManager gameStateManager;

    public MainMenu(GameStateManager gameStateManager) {

        this.gameStateManager = gameStateManager;

        try {
            background = ImageIO.read(getClass().getResourceAsStream(backgroundPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        highlightColor = new Color(100, 100, 100);
        baseColor = Color.BLACK;
        titleFont = new Font("Arial", Font.PLAIN, 72);
        menuFont = new Font("Arial", Font.PLAIN, 36);
    }

    @Override
    public void init() {
        selectedOption = 0;
        menu = HOME_MENU;
        settings = new Settings();
    }

    @Override
    public void update() { }

    /**
     * select() defines a logic tree for the main menu
     */
    private void select() {

        switch (menu) {

            case HOME_MENU:
                switch (selectedOption) {

                    // NEW GAME
                    case 0:
                        menu = NEW_GAME_MENU;
                        selectedOption = 0;
                        break;

                    // SETTINGS
                    case 1:
                        menu = OPTIONS_MENU;
                        selectedOption = 0;
                        break;

                    // QUIT
                    case 2:

                        System.exit(0);
                        break;
                }
                break;

            case NEW_GAME_MENU:
                switch (selectedOption) {

                    // 1 Player
                    case 0:
                        settings.setMultiPlayer(false);
                        gameStateManager.getState(GameStateManager.CHESS_STATE).setSettings(settings);
                        gameStateManager.setState(GameStateManager.CHESS_STATE);
                        // TODO: START A NEW GAME WITH CURRENT SETTINGS
                        break;

                    // 2 Player
                    case 1:
                        settings.setMultiPlayer(true);
                        gameStateManager.getState(GameStateManager.CHESS_STATE).setSettings(settings);
                        gameStateManager.setState(GameStateManager.CHESS_STATE);
                        // TODO: START A NEW GAME WITH CURRENT SETTINGS
                        break;

                    // BACK
                    case 2:
                        menu = HOME_MENU;
                        selectedOption = 0;
                        break;
                }
                break;

            case OPTIONS_MENU:
                switch (selectedOption) {

                    // TOGGLE TIMER
                    case 0:
                        settings.setHasTimer(!settings.hasTimer());
                        String timerStr = settings.hasTimer() ? "Timer On" : "Timer Off";
                        options[menu][selectedOption] = timerStr;
                        break;

                    // TODO: FIGURE OUT WHAT OPTIONS WE WANT
                    // TBD
                    case 1:
                        // NOTHING YET
                        break;

                    // BACK
                    case 2:
                        menu = HOME_MENU;
                        selectedOption = 0;
                        break;
                }
                break;

            default:
                System.out.println("ERROR: Out of bounds menu index... Resetting back to home");
                menu = HOME_MENU;
                break;

        }
    }


    @Override
    public void draw(Graphics2D g) {
        g.drawImage(background, 0, 0, null);
        g.setFont(titleFont);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        int width = titleFontMetrics.stringWidth(title);
        int xPos = Game.WIDTH / 2 - (width / 2);
        g.drawString(title, xPos, Game.HEIGHT / 3);

        g.setFont(menuFont);
        FontMetrics menuFontMetrics = g.getFontMetrics(menuFont);
        int interval = 50;
        for (int i = 0; i < options[menu].length; i++) {

            if (i == selectedOption) {
                g.setColor(highlightColor);
            } else {
                g.setColor(baseColor);
            }

            width = menuFontMetrics.stringWidth(options[menu][i]);
            xPos = Game.WIDTH / 2 - (width / 2);
            g.drawString(options[menu][i], xPos, Game.HEIGHT/2 + (i*interval));
        }
        g.setColor(baseColor);
    }

    @Override
    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_UP:
                // Prevent
                if (selectedOption > 0) selectedOption--;

                break;
            case KeyEvent.VK_DOWN:
                if (selectedOption < options[menu].length - 1) selectedOption++;

                break;

            case KeyEvent.VK_ENTER:
                select();

                break;
        }

    }

    @Override
    public void keyReleased(int k) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO: DETECT MOUSE OVER OPTIONS
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: ALLOW MOUSE TO SELECT OPTIONS
    }


}
