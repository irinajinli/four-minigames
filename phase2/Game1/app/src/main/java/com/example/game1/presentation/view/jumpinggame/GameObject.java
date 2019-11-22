package com.example.game1.presentation.view.jumpinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

public class GameObject {
  private int width, height;
  private double px, py, vx, vy, ax, ay;
  public JumpingGameManager jgm;

  public GameObject(int width, int height, JumpingGameManager jgm) {
    this.width = width;
    this.height = height;
    this.jgm = jgm;
  }

  public void setPositionX(double position) {
    this.px = position;
  }

  public void setPositionY(double position) {
    this.py = position;
  }

  public void setVelocityX(double velocity) {
    this.vx = velocity;
  }

  public void setVelocityY(double velocity) {
    this.vy = velocity;
  }

  public void setAccelerationX(double acceleration) {
    this.ax = acceleration;
  }

  public void setAccelerationY(double acceleration) {
    this.ay = acceleration;
  }

  public double getPositionX() {
    return this.px;
  }

  public double getPositionY() {
    return this.py;
  }

  public double getVelocityX() {
    return this.vx;
  }

  public double getVelocityY() {
    return this.vy;
  }

  public double getAccelerationX() {
    return this.ax;
  }

  public double getAccelerationY() {
    return this.ay;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public void update() {
    double t = GameThread.FRAME_DURATION_NS / 1000000000.; // get amount of time in seconds
    px += (0.5 * ax * t * t + vx * t);
    py += (0.5 * ay * t * t + vy * t);
    vx += (ax * t);
    vy += (ay * t);
  }

  public boolean isOverlapping(GameObject other) {
    double x1a = this.px;
    double x2a = this.px + this.width;
    double x1b = other.getPositionX();
    double x2b = other.getPositionX() + other.getWidth();

    double y1a = this.py;
    double y2a = this.py + this.height;
    double y1b = other.getPositionY();
    double y2b = other.getPositionY() + other.getHeight();

    return !(x2a < x1b || x2b < x1a || y2a < y1b || y2b < y1a);
  }
}
