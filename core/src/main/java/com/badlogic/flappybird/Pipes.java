package com.badlogic.flappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Pipes {

    Texture bottomPipe;
    Texture topPipe;

    Vector2 positionTop;
    Vector2 positionBot;

    Rectangle topBounds;
    Rectangle botBounds;

    int velocityX = 200;
    float gap = 500;

    boolean isPassed = false;

    public Pipes() {
        bottomPipe = new Texture("bottompipe.png");
        topPipe = new Texture("toppipe.png");

        positionTop = new Vector2(500, 550);
        positionBot = new Vector2(500, -800);

        topBounds = new Rectangle(positionTop.x, positionTop.y,
            topPipe.getWidth(), topPipe.getHeight());

        botBounds = new Rectangle(positionBot.x, positionBot.y,
            bottomPipe.getWidth(), bottomPipe.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(topPipe, positionTop.x, positionTop.y);
        batch.draw(bottomPipe, positionBot.x, positionBot.y);
    }

    public void update(float dt) {
        positionTop.x -= velocityX * dt;
        positionBot.x -= velocityX * dt;

        if (positionBot.x < -150) {
            recreate();
        }

        topBounds.setPosition(positionTop.x, positionTop.y);
        botBounds.setPosition(positionBot.x, positionBot.y);
    }

    public void recreate() {
        isPassed = false;
        float y = MathUtils.random(300, 1000);

        positionTop.set(positionTop.x + 1200, y + gap / 2);
        positionBot.set(positionBot.x + 1200,
            y - gap / 2 - bottomPipe.getHeight() / 1.2f);
    }
}
