/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2016 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.almasb.fxgl.physics;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

/**
 * A bounding collision box.
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public final class HitBox {

    /**
     * Name of this hit box.
     */
    private final String name;

    /**
     * Shape of this hit box.
     */
    private final BoundingShape shape;

    /**
     * Bounding box (computed from shape) of this hit box.
     */
    private final Bounds bounds;

    /**
     * Creates a hit box with given name and shape.
     * Local origin is set to default (0, 0).
     *
     * @param name name of the hit box
     * @param shape bounding shape
     */
    public HitBox(String name, BoundingShape shape) {
        this(name, Point2D.ZERO, shape);
    }

    /**
     * Creates a hit box with given name, local origin (top-left) and shape.
     *
     * @param name name of the hit box
     * @param localOrigin origin of hit box
     * @param shape bounding shape
     */
    public HitBox(String name, Point2D localOrigin, BoundingShape shape) {
        this.name = name;
        this.shape = shape;
        this.bounds = new BoundingBox(localOrigin.getX(), localOrigin.getY(),
                shape.getSize().getWidth(), shape.getSize().getHeight());
    }

    /**
     * Computes new bounds based on translated X and Y
     * of an entity.
     *
     * @param x entity x
     * @param y entity y
     * @return computed bounds
     */
    public Bounds translate(double x, double y) {
        return new BoundingBox(x + bounds.getMinX(), y + bounds.getMinY(),
                bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Computes new bounds based on translated X and Y
     * of an entity with X axis being flipped.
     *
     * @param x entity x
     * @param y entity y
     * @param entityWidth entity width
     * @return computed bounds
     */
    public Bounds translateXFlipped(double x, double y, double entityWidth) {
        return new BoundingBox(x + entityWidth - bounds.getMinX() - bounds.getWidth(), y + bounds.getMinY(),
                bounds.getWidth(), bounds.getHeight());
    }

    /**
     * @return bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

    /**
     * @return hit box shape
     */
    public BoundingShape getShape() {
        return shape;
    }

    /**
     * @return min x
     */
    public double getMinX() {
        return bounds.getMinX();
    }

    /**
     * @return min y
     */
    public double getMinY() {
        return bounds.getMinY();
    }

    /**
     * @return maxX of internal bounds (x + width)
     */
    public double getMaxX() {
        return bounds.getMaxX();
    }

    /**
     * @return maxY of internal bounds (y + height)
     */
    public double getMaxY() {
        return bounds.getMaxY();
    }

    /**
     * @return hit box name
     */
    public String getName() {
        return name;
    }

    /**
     * @return center point of this hit box, local to 0,0 (top,left) of the entity
     */
    public Point2D centerLocal() {
        return new Point2D((bounds.getMinX() + bounds.getMaxX()) / 2,
                (bounds.getMinY() + bounds.getMaxY()) / 2);
    }

    /**
     * @param x position x of entity
     * @param y position y of entity
     * @return center point of this hit box in world coordinates
     */
    public Point2D centerWorld(double x, double y) {
        return centerLocal().add(x, y);
    }
}
