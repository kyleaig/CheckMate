package GameState;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MainMenu extends GameState {

    private static final String backgroundPath = "/MainMenuBg.png";
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
    private Color highlight;
    private Font titleFont;
    private Font menuFont;


    // Options
    private boolean timer = false;
    private boolean twoPlayer = false;




    private GameStateManager gameStateManager;

    public MainMenu(GameStateManager gameStateManager) {

        this.gameStateManager = gameStateManager;

        try {
            background = ImageIO.read(getClass().getResourceAsStream(backgroundPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        highlight = new Color(100, 100, 100);
        titleFont = new Font("Arial", Font.PLAIN, 72);
        menuFont = new Font("Arial", Font.PLAIN, 36);
    }



    @Override
    public void init() {
        selectedOption = 0;
        menu = HOME_MENU;
    }

    @Override
    public void update() {

    }


    // MAIN MENU LOGIC
    private void select() {
        if (menu == HOME_MENU) {
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
        } else if (menu == NEW_GAME_MENU) {
            switch (selectedOption) {

                // 1 Player
                case 0:
                    twoPlayer = false;
                    // TODO: START A NEW GAME WITH CURRENT SETTINGS
                    break;

                // 2 Player
                case 1:
                    twoPlayer = true;
                    // TODO: START A NEW GAME WITH CURRENT SETTINGS
                    break;

                // BACK
                case 2:
                    menu = HOME_MENU;
                    selectedOption = 0;
                    break;
            }
        } else if (menu == OPTIONS_MENU) {
            switch (selectedOption) {

                // TOGGLE TIMER
                case 0:
                    timer = !timer;
                    String timerStr = timer ? "Timer On" : "Timer Off";
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
        } else {
            System.out.println("ERROR: Out of bounds menu index... Resetting back to home");
            menu = HOME_MENU;
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
                g.setColor(highlight);
            } else {
                g.setColor(Color.BLACK);
            }

            width = menuFontMetrics.stringWidth(options[menu][i]);
            xPos = Game.WIDTH / 2 - (width / 2);
            g.drawString(options[menu][i], xPos, Game.HEIGHT/2 + (i*interval));
        }
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
}
