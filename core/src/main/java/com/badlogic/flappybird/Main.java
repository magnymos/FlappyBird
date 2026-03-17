package com.badlogic.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import javax.swing.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    FlappyBird hero;
    Pipes[] pipes;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("flappybirdbg.png");
        hero = new FlappyBird();
        pipes = new Pipes[3];
        for (int i = 0; i <pipes.length; i++ ){
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
        for (Pipes p : pipes){
            p.render(batch);
        }
        batch.end();
    }

    public void update(float dt){
        hero.update(dt);
        for (Pipes p : pipes){
            p.update(dt);
        }
        checkCollizions();
    }

    public void checkCollizions(){
        for(Pipes p : pipes){
            if (p.getPositionBot().dst(hero.position)<32 ||p.getPositionTop().dst(hero.position)<32){
                hero.takeOneDamage();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
