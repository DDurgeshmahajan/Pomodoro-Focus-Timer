/*
Author: Durgesh Mahajan
Date: 2023-10-01
Project: Pomodoro Focus Timer
*/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PomodoroManager pomodoroManager = new PomodoroManager();
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean running = true;

        System.out.println("Welcome to the Pomodoro Focus Timer!");
        System.out.println("Commands: s (start/resume), p (pause), r (reset session), R (reset cycle), q (quit)");

        while (running) {
            clearScreen();
            displayCurrentSession(pomodoroManager);
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "s":
                    pomodoroManager.startOrResumeSession();
                    break;
                case "p":
                    pomodoroManager.pauseSession();
                    break;
                case "r":
                    pomodoroManager.resetSession();
                    break;
                case "R":
                    pomodoroManager.resetCycle();
                    break;
                case "q":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }

        System.out.println("Thank you for using the Pomodoro Focus Timer!");
        scanner.close();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void displayCurrentSession(PomodoroManager pomodoroManager) {
        System.out.println("Current Session: " + pomodoroManager.getCurrentSessionType() +
                " (Pomodoro " + pomodoroManager.getCurrentPomodoroCount() + "/4)");
        System.out.println("Time Remaining: " + formatTime(pomodoroManager.getRemainingTimeInSeconds()));
    }

    private static String formatTime(int seconds) {
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}