/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.waterbead.generator

class RandomGenerator {
    private static Random random = new Random();
    
    def static get(int value) {
        random.nextInt(value);
    }
    
    def static getForIndexOf0(int value) {
        random.nextInt(value)+1;
    }
    
    def static get(int value1, int value2) {
        random.nextInt(value2-value1) + value1;
    }
}

