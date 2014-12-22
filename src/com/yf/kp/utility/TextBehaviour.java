/*
 * Copyright (C) 2014 anonymous
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.yf.kp.utility;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author anonymous
 */
public class TextBehaviour {
    
    private static final String BAD_CHARS = "`~!@#$%^&*()_+=\\|\"':;?/>.<, ";

    public static void setNumericOnly(final javax.swing.JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                if (!Character.isDigit(evt.getKeyChar()) || BAD_CHARS.indexOf(evt.getKeyChar()) > -1) {
                    evt.consume();
                }
            }
        });
    }
    
    private static void limitAction(final javax.swing.JTextField textField, java.awt.event.KeyEvent keyEvent, int maxLength) {
        if (textField.getText().length() + 1 > maxLength) {
            keyEvent.consume();
        }
    }
    
    public static void setMaximumLength(final int maximumChar, final javax.swing.JTextField textField) {
        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                limitAction(textField, evt, maximumChar);
            }
        });
    }
    
}
