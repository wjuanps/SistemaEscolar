/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siseduc.model.vo;

import java.awt.event.KeyEvent;

/**
 *
 * @author Juan Soares
 */
public enum Teclas {

    /**
     *
     */
    SPACE(KeyEvent.VK_BACK_SPACE),

    /**
     *
     */
    ALT(KeyEvent.VK_ALT),
    
    /**
     *
     */
    CAPS_LOCK(KeyEvent.VK_CAPS_LOCK),
    
    /**
     *
     */
    CONTROL(KeyEvent.VK_CONTROL),
    
    /**
     *
     */
    DELETE(KeyEvent.VK_DELETE),
    
    /**
     *
     */
    DOWN(KeyEvent.VK_DOWN),
    
    /**
     *
     */
    KP_DOWN(KeyEvent.VK_KP_DOWN),
    
    /**
     *
     */
    KP_LEFT(KeyEvent.VK_KP_LEFT),
    
    /**
     *
     */
    KP_RIGHT(KeyEvent.VK_KP_RIGHT),
    
    /**
     *
     */
    KP_UP(KeyEvent.VK_KP_UP),
    
    /**
     *
     */
    LEFT(KeyEvent.VK_LEFT),
    
    /**
     *
     */
    RIGHT(KeyEvent.VK_RIGHT),
    
    /**
     *
     */
    SHIFT(KeyEvent.VK_SHIFT),
    
    /**
     *
     */
    UP(KeyEvent.VK_UP);

    /**
     *
     */
    public int key;

    /**
     * 
     * @param key 
     */
    Teclas(int key) {
        this.key = key;
    }
}