package com.sanwi22;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle {
    double y = 210.0;
    double yVel = 0.0;
    boolean upAccel = false;
    boolean downAccel = false;
    final double GRAVITY = 0.94;
    int player;
    int x;
    int score;

    public HumanPaddle(int player) {
        if (player == 1) {
            this.x = 20;
        } else {
            this.x = 660;
        }

        this.score = 0;
    }

    public void draw(Graphics g) {
        if (this.getY() >= 375) {
            g.setColor(Color.red);
            g.fillRect(this.x, (int)this.y, 20, 80);
        } else {
            g.setColor(Color.black);
            g.fillRect(this.x, (int)this.y, 20, 80);
        }

    }

    public void move() {
        if (this.upAccel) {
            this.yVel -= 2.0;
        } else if (this.downAccel) {
            this.yVel += 2.0;
        } else if (!this.upAccel && !this.downAccel) {
            this.yVel *= 0.94;
        }

        if (this.yVel >= 5.0) {
            this.yVel = 5.0;
        } else if (this.yVel <= -5.0) {
            this.yVel = -5.0;
        }

        this.y += this.yVel;
        if (this.y < 0.0) {
            this.y = 0.0;
        } else if (this.y > 670.0) {
            this.y = 670.0;
        }

    }

    public void setUpAccel(boolean input) {
        this.upAccel = input;
    }

    public void setDownAccel(boolean input) {
        this.downAccel = input;
    }

    public int getY() {
        return (int)this.y;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int s) {
        this.score = s;
    }

    public void setY(double y) {
        this.y = y;
    }
}
