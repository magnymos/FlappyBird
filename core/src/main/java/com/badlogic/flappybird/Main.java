package com.badlogic.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    FlappyBird hero;
    Pipes[] pipes;
    boolean isGameOver = false;
    int score=0;
    BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("flappybirdbg.png");
        hero = new FlappyBird();
        font = new BitmapFont();

        pipes = new Pipes[3];
        for (int i = 0; i < pipes.length; i++) {
            pipes[i] = new Pipes();
            pipes[i].positionTop.x = 720 + i * 400;
            pipes[i].positionBot.x = 720 + i * 400;
        }
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);

        ScreenUtils.clear(0, 0, 0, 1f);
        batch.begin();
        batch.draw(image, 0, 0);

        hero.render(batch);
        for (Pipes p : pipes) {
            p.render(batch);
        }

        font.draw(batch, "Score: " + score, 50, 1200);

        if (isGameOver) {
            font.draw(batch, "GAME OVER! Press R", 200, 700);
        }

        batch.end();
    }

    public void update(float dt) {
        if (isGameOver) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                restart();
            }
            return;
        }

        hero.update(dt);
        for (Pipes p : pipes) {
            p.update(dt);

            if (!p.isPassed && p.positionTop.x + p.topBounds.width < hero.position.x){
                p.isPassed = true;
                score++;
            }
        }



        checkCollizions();
    }

    public void checkCollizions() {
        for (Pipes p : pipes) {
            if (p.topBounds.overlaps(hero.bounds) ||
                p.botBounds.overlaps(hero.bounds) ||
                hero.position.y <= 0) {

                isGameOver = true;
            }
        }
    }
    public void restart() {
        isGameOver = false;
        score = 0;

        hero = new FlappyBird();

        for (int i = 0; i < pipes.length; i++) {
            pipes[i] = new Pipes();
            pipes[i].positionTop.x = 720 + i * 400;
            pipes[i].positionBot.x = 720 + i * 400;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
