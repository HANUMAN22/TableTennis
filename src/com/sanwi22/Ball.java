package com.sanwi22;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    double xVel = this.getRandomSpeed() * (double)this.getRandomDirection();
    double yVel = this.getRandomSpeed() * (double)this.getRandomDirection();
    double x = 525.0;
    double y = 375.0;

    public Ball() {
    }

    public double getRandomSpeed() {
        return Math.random() * 3.0 + 2.0;
    }

    public int getRandomDirection() {
        int rand = (int)(Math.random() * 2.0);
        return rand == 1 ? 1 : -1;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval((int)this.x - 10, (int)this.y - 10, 20, 20);
    }

    public void checkPaddleCollision(Paddle p1, Paddle p2) {
        if (this.x <= 50.0) {
            if (this.y >= (double)p1.getY() && this.y <= (double)(p1.getY() + 80)) {
                this.xVel = this.getRandomSpeed() * (double)this.getRandomDirection();
            }

        } else if (this.x >= 1000.0) {
            if (this.y >= (double)p2.getY() && this.y <= (double)(p2.getY() + 80)) {
                this.xVel = this.getRandomSpeed() * (double)this.getRandomDirection();
            }

        } else {
        }
    }

    public void move() {
        this.x += this.xVel;
        this.y += this.yVel;
        if (this.y < 10.0) {
            this.yVel = -this.yVel;
        }

        if (this.y > 740.0) {
            this.yVel = -this.yVel;
        }

    }

    public int getX() {
        return (int)this.x;
    }

    public int getY() {
        return (int)this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getXVel() {
        return this.xVel;
    }

    public void setXVel(double xVel) {
        this.xVel = xVel;
    }

    public double getYVel() {
        return this.yVel;
    }

    public void setYVel(double yVel) {
        this.yVel = yVel;
    }
}
