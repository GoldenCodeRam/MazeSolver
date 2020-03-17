package com.uptc.prg.maze.view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImagePanel extends JLabel implements MouseListener {
    private OptionsPopupMenu mPopupMenu;

    private BufferedImage mImage = null;

    private BufferedImage mGeneratedGraphImage = null;
    private boolean mShowGeneratedGraphImage;

    private double mScale;

    private Point mInitialPoint;
    private Point mFinalPoint;
    private Point mClickPosition;

    private int mCircleSize;

    public ImagePanel() {
        this.initialize();
        this.initializePopupMenus();
    }

    public final void activatePopupMenu() {
        this.mPopupMenu.setMenuItemsStatus(true);
    }

    public final void setImage(BufferedImage image) {
        this.mImage = image;
    }

    public final void setScale(double scale) {
        this.mScale = scale;
    }

    public final void setCircleSize(int circleSize) {
        this.mCircleSize = circleSize;
    }

    public final BufferedImage getImage() {
        return this.mImage;
    }

    public final Point getInitialPoint() {
        return this.mInitialPoint;
    }

    public final Point getFinalPoint() {
        return this.mFinalPoint;
    }

    public final void setGeneratedGraphImage(BufferedImage generatedGraphImage) {
        this.mGeneratedGraphImage = generatedGraphImage;
    }

    public final void showGeneratedGraphImage() {
        this.mShowGeneratedGraphImage = true;
    }

    public final void hideGeneratedGraphImage() {
        this.mShowGeneratedGraphImage = false;
    }

    @Override
    public final void setIcon(Icon icon) {
        super.setIcon(icon);
        if (icon != null) {
            this.mImage = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB
            );
            Graphics graphics = this.mImage.createGraphics();
            icon.paintIcon(null, graphics, 0, 0);
            graphics.dispose();
        }
    }

    @Override
    public final Dimension getPreferredSize() {
        if (this.mImage != null) {
            int width = (int) (this.mScale * this.mImage.getWidth());
            int height = (int) (this.mScale * this.mImage.getHeight());
            return new Dimension(width, height);
        } else {
            return new Dimension(0, 0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public final void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.mPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            this.mClickPosition = new Point(
                    e.getX() - this.getScalatedX(),
                    e.getY() - this.getScalatedY());
        }
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.mPopupMenu.show(e.getComponent(), e.getX(), e.getY());
            this.mClickPosition = new Point(
                    e.getX() - this.getScalatedX(),
                    e.getY() - this.getScalatedY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    protected final void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.mImage != null) {
            AffineTransform at = AffineTransform.getTranslateInstance(
                    this.getScalatedX(), this.getScalatedY()
            );
            at.scale(this.mScale, this.mScale);
            graphics2D.drawRenderedImage(this.mImage, at);
            this.revalidate();
            this.repaint();
        }
        if (this.mInitialPoint != null) {
            graphics2D.setColor(Color.GREEN);
            graphics2D.fillOval(
                    (this.mInitialPoint.x + this.getScalatedX() - this.mCircleSize / 2),
                    (this.mInitialPoint.y + this.getScalatedY() - this.mCircleSize / 2),
                    this.mCircleSize,
                    this.mCircleSize
            );
        }
        if (this.mFinalPoint != null) {
            graphics2D.setColor(Color.RED);
            graphics2D.fillOval(
                    this.mFinalPoint.x + this.getScalatedX() - this.mCircleSize / 2,
                    this.mFinalPoint.y + this.getScalatedY() - this.mCircleSize / 2,
                    this.mCircleSize,
                    this.mCircleSize
            );
        }
        if (this.mGeneratedGraphImage != null) {
            if (this.mShowGeneratedGraphImage) {
                int width = this.getWidth();
                int height = this.getHeight();
                int imageWidth = this.mGeneratedGraphImage.getWidth();
                int imageHeight = this.mGeneratedGraphImage.getHeight();
                double x = (width - this.mScale * imageWidth) / 2;
                double y = (height - this.mScale * imageHeight) / 2;
                AffineTransform at = AffineTransform.getTranslateInstance(x, y);
                at.scale(this.mScale, this.mScale);
                graphics2D.drawRenderedImage(this.mGeneratedGraphImage, at);
                this.revalidate();
                this.repaint();
            }
        }
    }

    /*====================================== PRIVATE METHODS =====================================*/

    private void initialize() {
        this.mScale = 1.0d;
        this.addMouseListener(this);
        this.setBackground(Color.GRAY);
    }

    private void initializePopupMenus() {
        this.mPopupMenu = new OptionsPopupMenu() {
            @Override
            public void initialPointItemAction() {
                ImagePanel.this.mInitialPoint = ImagePanel.this.mClickPosition;
            }

            @Override
            public void finalPointItemAction() {
                ImagePanel.this.mFinalPoint = ImagePanel.this.mClickPosition;
            }
        };
    }

    private int getScalatedX() {
        int width = this.getWidth();
        int imageWidth = this.mImage.getWidth();
        return (int) ((width - this.mScale * imageWidth) / 2);
    }

    private int getScalatedY() {
        int height = this.getHeight();
        int imageHeight = this.mImage.getHeight();
        return (int) ((height - this.mScale * imageHeight) / 2);
    }
}
