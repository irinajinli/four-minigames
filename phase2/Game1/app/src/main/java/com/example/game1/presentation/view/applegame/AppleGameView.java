package com.example.game1.presentation.view.applegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.game1.R;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.AppManager;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.view.common.Background;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

/**
 * Based on Fish Tank's FishTankView.
 */
public class AppleGameView extends GameView {

    // TODO: new for images
    private Bitmap appleBMP = BitmapFactory.decodeResource(getResources(), R.drawable.apple_red);
    private Bitmap starBMP = BitmapFactory.decodeResource(getResources(), R.drawable.star_7);
    private Bitmap basketBMP = BitmapFactory.decodeResource(getResources(), R.drawable.basket_red);

    // TODO: new for colors
    private int skyColorDark;
    private int skyColorLight;
    private int skyColorDefault;

    /**
     * Construct an AppleGameView with the specified Context.
     */
    public AppleGameView(Context context) {
        super(context);
        thread = new GameThread(getHolder(), this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // todo: new for skycolor

        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        // TODO: DELETE NEXT 2 LINES
        charWidth = paintText.measureText("W");
        charHeight = -paintText.ascent() + paintText.descent();

        // use screen height and width to determine the size of the GameManager
        gameManager = AppManager.getInstance().buildGameManager(
                Game.GameName.APPLE,
                getScreenHeight(),
                getScreenWidth(),
                activity);
        gameManager.startMusic();

        // TODO: new for images
        appleBMP = getResizedBitmap(appleBMP, 80, 80);
        starBMP = getResizedBitmap(starBMP, 80, 80);
        basketBMP = getResizedBitmap(basketBMP, 100, 100);

        ((AppleGameManager) gameManager).setBMPFiles(appleBMP, starBMP, basketBMP);

        // TODO: new for colors
        extractSkyColors();
        ((AppleGameManager) gameManager).setSkyColors(skyColorDark, skyColorLight, skyColorDefault);

        gameManager.createGameItems();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // count tap in game's numTaps
        Game game = gameManager.getGame();
        game.setNumTaps(game.getNumTaps() + 1);

        double touchX = event.getX();
        ((AppleGameManager) gameManager).moveBasket((int) touchX);

        return true;

        // TODO: change game.numTaps when the game is over instead of adding 1 each time?
    }

    @Override
    public void drawItem(Canvas canvas, GameItem item) {
        // TODO: new
        if (item instanceof Apple || item instanceof Basket || item instanceof Star) {
            Object appearance2 = item.getAppearance();
            double xCoordinate2 = item.getXCoordinate();
            double yCoordinate2 = item.getYCoordinate();

            if (appearance2.getClass() == Bitmap.class) {
                canvas.drawBitmap(
                        (Bitmap) appearance2,
                        (int) Math.round(xCoordinate2),
                        (int) Math.round(yCoordinate2),
                        paintText);
            }
        }

        paintText = new Paint();
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setTextSize(800);
        if (item instanceof Background) {
            Rect backgroundRect = new Rect(0, 0, 999999, 999999);
            Paint backgroundPaint = new Paint();
            backgroundPaint.setStyle(Paint.Style.STROKE);
            backgroundPaint.setStrokeWidth(5);
            backgroundPaint.setAntiAlias(true);
            backgroundPaint.setColor(Color.DKGRAY);
            backgroundPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(backgroundRect, backgroundPaint);
        } else {

            Object appearance = item.getAppearance();
            double xCoordinate = item.getXCoordinate();
            double yCoordinate = item.getYCoordinate();
            if (appearance.getClass() == String.class) {
                //        if (item instanceof Basket) {
                //          if (gameManager
                //              .getGame()
                //              .getCustomization()
                //              .getCharacterColour()
                //              .equals(Customization.CharacterColour.BLUE)) {
                //            paintText.setColor(Color.BLUE);
                //          } else if (gameManager
                //              .getGame()
                //              .getCustomization()
                //              .getCharacterColour()
                //              .equals(Customization.CharacterColour.RED)) {
                //            paintText.setColor(Color.RED);
                //          } else if (gameManager
                //              .getGame()
                //              .getCustomization()
                //              .getCharacterColour()
                //              .equals(Customization.CharacterColour.YELLOW)) {
                //            paintText.setColor(Color.YELLOW);
                //          }
                //        }
                if (item instanceof PointsCounter) {
                    paintText.setColor(Color.WHITE);
                }
                canvas.drawText((String) appearance, (float) xCoordinate, (float) yCoordinate, paintText);

                // canvas.drawText((String) appearance, x * TappingGameView.charWidth, y *
                // TappingGameView.charHeight, paintText);
                //      } else if (appearance.getClass() == Bitmap.class) {
                //        canvas.drawBitmap(
                //                (Bitmap) appearance,
                //                (int) Math.round(xCoordinate),
                //                (int) Math.round(yCoordinate),
                //                paintText);
                //      }
            }
        }
    }

    public void extractSkyColors() {
        skyColorDark = Color.BLACK;
        skyColorLight = Color.LTGRAY;
        skyColorDefault = Color.BLACK;
    }
}
