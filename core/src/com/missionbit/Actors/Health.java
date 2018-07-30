package com.missionbit.Actors;

import java.util.Random;
import java.util.Scanner;

public class Health {





    class hp {

    }
    public void update(){
        //hp.update();
    }
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

        boolean running = true;
        System.out.println("\t Welcome to the WorldPeace");

        GAME:
        while (running) {
            System.out.println("--------------------------------------------------");
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t #### " + enemy + " has appeared#### \n");

            String input;
            while (enemyHealth > 0) {
                System.out.println("\t Your Health: " + health);
                System.out.println("\t " + enemy + "'s Health: " + enemyHealth);
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
                        System.out.println("\t You taken " + damageTaken + "." + " \n\t You now have " + health + " HP." + "\n");
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
                System.out.println("\t You Flee from the " + enemy + ", weak from battle");
                break;
            }

            System.out.println("\t -------------------------------");
            System.out.println("\t  # " + enemy + " was defeated! # ");
            System.out.println("\t  # You have " + health + " HP left. #");

            System.out.println("\t -------------------------------");
            System.out.println("\t What would you like to do now?");
            System.out.println("\t 1. Continue Fighting");
            System.out.println("\t 2. Exit");

            input = in.nextLine();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.println("\t Invalid command");
                input = in.nextLine();
            }

            if (input.equals("1")) {
                System.out.println("\t You continue through the battlefield.");
            } else if (input.equals("2")) {
                System.out.println("\t You exit the battlefield a wiser man, successful on your quest.");
                break;
            }
        }
        System.out.println("\t  -------------------------- ");
        System.out.println("\t  ----Thanks for playing----");
        System.out.println("\t  -------------------------- ");
    }
}
