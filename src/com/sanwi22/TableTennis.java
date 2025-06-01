package com.sanwi22;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TableTennis extends Frame implements Runnable, KeyListener {
    final int WIDTH = 1050;
    final int HEIGHT = 750;
    Thread thread;
    HumanPaddle p1;
    HumanPaddle2 p2;
    AIPaddle p3;
    Ball b1;
    int score1;
    int score2;
    double ballXVel;
    boolean gameStarted;

    public TableTennis(String title) {
        super(title);
        this.setSize(1050, 750);
        this.addKeyListener(this);
        this.gameStarted = false;
        this.p1 = new HumanPaddle(1);
        this.p2 = new HumanPaddle2(2);
        this.b1 = new Ball();
        this.score1 = this.p1.getScore();
        this.score2 = this.p2.getScore();
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Color darkergreen = new Color(0, 128, 0);
        g.setColor(darkergreen);
        g.fillRect(0, 0, 1050, 750);
        if (this.p1.getScore() == 11) {
            g.setColor(Color.blue);
            g.drawString("Player 1 wins!", 500, 375);
        } else if (this.p2.getScore() == 11) {
            g.setColor(Color.blue);
            g.drawString("Player 2 wins!", 500, 375);
        } else {
            g.setColor(Color.white);
            g.fillRect(525, 0, 1, 750);
            this.p1.draw(g);
            this.p2.draw(g);
            this.b1.draw(g);
            g.drawString(this.p1.getScore() + " - " + this.p2.getScore(), 50, 50);
            if (this.b1.getX() < -10 || this.b1.getX() > 1060) {
                if (this.b1.getX() < -10) {
                    this.b1.setX(525.0);
                    this.b1.setY(375.0);
                    this.p1.setY(375.0);
                    this.p2.setY(375.0);
                    this.p2.setScore(this.score2++);
                } else if (this.b1.getX() > 1060) {
                    this.b1.setX(525.0);
                    this.b1.setY(375.0);
                    this.p1.setY(375.0);
                    this.p2.setY(375.0);
                    this.p1.setScore(this.score1++);
                }

                this.gameStarted = false;
                g.drawString(this.p1.getScore() + " - " + this.p2.getScore(), 50, 50);
            }
        }

    }

    public void update(Graphics g) {
        this.paint(g);
    }

    public void run() {
        while(true) {
            if (this.gameStarted) {
                this.p1.move();
                this.p2.move();
                this.b1.move();
                this.b1.checkPaddleCollision(this.p1, this.p2);
            }

            this.repaint();

            try {
                Thread.sleep(10L);
            } catch (InterruptedException var2) {
                InterruptedException e = var2;
                e.printStackTrace();
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 87) {
            this.p1.setUpAccel(true);
        } else if (e.getKeyCode() == 83) {
            this.p1.setDownAccel(true);
        } else if (e.getKeyCode() == 10) {
            this.gameStarted = true;
        }

        if (e.getKeyCode() == 38) {
            this.p2.setUpAccel(true);
        } else if (e.getKeyCode() == 40) {
            this.p2.setDownAccel(true);
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 87) {
            this.p1.setUpAccel(false);
        } else if (e.getKeyCode() == 83) {
            this.p1.setDownAccel(false);
        }

        if (e.getKeyCode() == 38) {
            this.p2.setUpAccel(false);
        } else if (e.getKeyCode() == 40) {
            this.p2.setDownAccel(false);
        }

    }
}
