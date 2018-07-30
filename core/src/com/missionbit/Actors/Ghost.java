package com.missionbit.Actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.missionbit.States.States;
import java.util.Random;
import java.util.Scanner;

public class Ghost {
    Texture img;
    private float y;
    private float x;
    private Object position;

    public Ghost() {
        super();
    }

//    @Override
//    public void tick() {
//        Object KeyInput;
//        if (KeyInput.isDown(KeyEvent.VK_W)) jump(10);
//        if (KeyInput.isDown(KeyEvent.VK_A)) dx = 2;
//        if (KeyInput.isDown(KeyEvent.VK_D)) dx = 2;
//
//        if (KeyInput.wasReleased(KeyEvent.VK_A) || KeyInput.wasReleased(KeyEvent.VK_D)) dx = 0;
//        super.tick;
//    }

    public static void main(String[] args) {
        //objects(system)
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        //Variables used
        String[] enemies = {"Eagle", "Snake", "Thug"};
        int maxEnemyHealth = 100;
        int enemyAttackDamage = 25;

        //Player Variables
        int health = 100;
        int attackDamage = 25;
        int numHealthElixers = 3;
        int healthPotionHeal = 20;
        int healthPotionDropChance = 50;

        boolean running = true;
        System.out.println("Welcome to the WorldPeace");

        GAME:
        while (running) {
            System.out.println("--------------------------------------------------");
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println(" \t#### " + enemy + " has appeared#### \n");

            String input;
            while (enemyHealth > 0) {
                System.out.println("\t Your Health: " + health);
                System.out.println("\t" + enemy + "'s Health: " + enemyHealth);
                System.out.println("\n \t What would you like to do?");
                System.out.println("\t 1. Attack");
                System.out.println("\t 2. Nothing");
                System.out.println("\t 3. Run");

                input = in.nextLine();
                if (input.equals("1")) {
                    int damageGiven = rand.nextInt(attackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);
                    enemyHealth -= damageGiven;
                    health -= damageTaken;

                    System.out.println("\t You strike the " + enemy + " for " + damageGiven + " damage.");
                    System.out.println("\t You take " + damageTaken + " in combat:");

                    if (health < 1) {
                        System.out.println("\t You have taken too much damage and therefore are too weak to fight!");
                        break;
                    }
                } else if (input.equals("2")) {
                    int damageTaken = rand.nextInt(enemyAttackDamage);
                    if (numHealthElixers > 0) {
                        health -= damageTaken;
                        numHealthElixers--;
                        System.out.println("You taken " + damageTaken + "." + " \n\t You now have " + health + " HP." + "\n");
                } else {
                    System.out.println("\t You have no health Elixir left!, You must defeat enemies for a chance to get more \n");
                }
                } else if (input.equals("3")) {
                    System.out.println("\t You run away from the " + enemy + "!");
                    continue GAME;
                } else {
                    System.out.println("\t\n Invalid Command");
                }
            }
                if (health < 1) {
                    System.out.println("You Flee from the " + enemy + ", weak from battle");
                    break;
                }

        System.out.println("-------------------------------");
        System.out.println(" # " + enemy + " was defeated! # ");
        System.out.println(" # You have " + health + " HP left. #");

            if (rand.nextInt(100) < healthPotionDropChance) {
                numHealthElixers++;
                System.out.println(" # The " + enemy + " dropped a health potion #");
                System.out.println(" # You now have " + numHealthElixers + " Health Elixer(s). #");
            }
        System.out.println("-------------------------------");
        System.out.println("What would you like to do now?");
        System.out.println("1. Continue Fighting");
        System.out.println("2. Exit");

        input = in.nextLine();

        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("invalid command");
            input = in.nextLine();
        }

        if (input.equals("1")) {
            System.out.println("You continue through the battlefield.");
        } else if (input.equals("2")) {
            System.out.println("You exit the battlefield a wiser man, successful on your quest.");
            break;
        }
    }
    System.out.println(" -------------------------- ");
    System.out.println(" ----Thanks for playing----");
    System.out.println(" -------------------------- ");
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public Texture getTexture(float deltaTime) {
        return null;
    }

    public Object getPosition() {
        return position;
    }
}


