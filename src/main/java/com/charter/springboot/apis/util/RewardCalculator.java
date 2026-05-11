package com.charter.springboot.apis.util;

public class RewardCalculator {

    public static int calculatePoints(double amount) {

        if (amount <= 50) {
            return 0;
        }

        if (amount <= 100) {
            return (int) (amount - 50);
        }

        return (int) ((amount - 100) * 2 + 50);
    }
}