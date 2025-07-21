package constants;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class General {
    public static Font mainFont() {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/bitcount_grid_simple.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        return font;
    }
}
