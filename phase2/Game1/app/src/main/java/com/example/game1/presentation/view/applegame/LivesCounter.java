package com.example.game1.presentation.view.applegame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;

public class LivesCounter extends GameItem {
    /** A counter for points earned. */

    // variables
        /** How many lives are remaining. */
        private int livesRemaining = 10;

        /** Constructs a LivesCounter with white text. */
        public LivesCounter() {
            super("lives: 0");
        }

        /**
         * Gets livesRemaining.
         *
         * @return number of lives remaining
         */
        public int getLivesRemaining() {
            return livesRemaining;
        }

        /**
         * Removes a life from this LivesCounter.
         *
         * @param points number of points to add
         */
        public void subtractLife(int points) {
            livesRemaining -= 1;
            setAppearance("lives: " + livesRemaining);
        }

        public Result update(ImportInfo jumpingImportInfo) {
            return (new Result());
        }

        public Result animate(ImportInfo importInfo) {
            return new Result();
        }
}
