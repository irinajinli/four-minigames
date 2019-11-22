package com.example.game1.presentation.model.jumpinggame;

import android.graphics.Bitmap;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

public class Terrain extends GameItem {
    /** The terrain. */

    /**
     * Constructs a Terrain with the specified height and width.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     * @param appearance the appearance of this GameItem
     */
    public Terrain(int height, int width, Bitmap appearance) {

        super(height, width, appearance);
    }

    @Override
    /**
     * @param jumpingImportInfo: import info needed for this terrain to animate
     * @return the info needed by game manager after the animation
     */
    public JumpingResult update(ImportInfo jumpingImportInfo) {
        return (new JumpingResult());
    }
}
