package com.uptc.prg.maze.view.panels;

import com.uptc.prg.maze.view.UIStrings;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class OptionsPopupMenu extends JPopupMenu {
    private JMenuItem addInitialPointItem;
    private JMenuItem addFinalPointItem;

    protected OptionsPopupMenu() {
        this.initializeItems();
        this.initialize();
    }

    public final void setMenuItemsStatus(boolean enable) {
        this.addInitialPointItem.setEnabled(enable);
        this.addFinalPointItem.setEnabled(enable);
    }

    private void initializeItems() {
        this.addInitialPointItem = new JMenuItem(UIStrings.getString("ADD_INITIAL_POINT"));
        this.addInitialPointItem.addActionListener(e -> this.initialPointItemAction());

        this.addFinalPointItem = new JMenuItem(UIStrings.getString("ADD_FINAL_POINT"));
        this.addFinalPointItem.addActionListener(e -> this.finalPointItemAction());

        this.setMenuItemsStatus(false);
    }

    private void initialize() {
        this.add(this.addInitialPointItem);
        this.add(this.addFinalPointItem);
    }

    public abstract void initialPointItemAction();

    public abstract void finalPointItemAction();
}
