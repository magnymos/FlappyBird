package com.badlogic.flappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Pipes  {

    Texture bottomPipe ;
    Texture topPipe;

    public void setPositionTop(Vector2 positionTop) {
        this.positionTop = positionTop;
    }

    public void setPositionBot(Vector2 positionBot) {
        this.positionBot = positionBot;
    }

    public Vector2 getPositionTop() {
        return positionTop;
    }

    public Vector2 getPositionBot() {
        return positionBot;
    }

    Vector2 positionTop;
    Vector2 positionBot;
    boolean passed = false;
    int velocityX = 200;
    ArrayList<Texture> pipes;
    float gap = 500;



    public Pipes() {
        if (bottomPipe == null){
        this.bottomPipe = new Texture("bottompipe.png");}
        if(topPipe == null){
        this.topPipe = new Texture("toppipe.png");}
        positionTop = new Vector2(500, 550);
        positionBot = new Vector2(500, -800);

    }

    public void render(SpriteBatch batch){
        batch.draw(topPipe, positionTop.x, positionTop.y);
        batch.draw(bottomPipe, positionBot.x, positionBot.y);
    }



    public void update(float dt){
        positionTop.x -= velocityX * dt;
        positionBot.x -= velocityX * dt;
        if (positionBot.x<-150){
            recreate();
        }

    }
    public void recreate(){
        float y = MathUtils.random(300, 1000);

        positionTop.set(positionTop.x + 1200, y + gap/2);
        positionBot.set(positionBot.x + 1200, y - gap/2 - bottomPipe.getHeight()/(1.2f));
    }

}
