package efs.task.syntax;

import java.util.Random;
import java.util.Scanner;
public class GuessNumberGame {

    public int M;
    public int liczba;
    public int L;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            this.M = Integer.parseInt(argument);
            if(M <1 || M > UsefulConstants.MAX_UPPER_BOUND){
                System.out.println("\'"+argument+"\'" + " wyszło poza zakres : <1,"+UsefulConstants.MAX_UPPER_BOUND+"> - NIEPOPRAWNY ARGUMENT");
                throw new IllegalArgumentException();
            }
        }
        catch (NumberFormatException e){
            System.out.println("\'" + argument + "\'" + ", który podałeś: " + UsefulConstants.WRONG_ARGUMENT + " nie jest liczbą - NIEPOPRAWNY ARGUMENT");
            throw new IllegalArgumentException(e);
        }


    }

    public void play() {
        Random val = new Random();
        Scanner in = new Scanner(System.in);
        this.liczba = val.nextInt(1+M);
        this.L = (int) Math.abs(Math.log(M)/Math.log(2)+1);
        System.out.println("Zakres gry: <1,"+this.M+">.");
        System.out.println("Zacznijmy grę.");
        int proby=0;
        do {
            char[] temp = new char[L];
            System.out.println("Progess bar: ");
            for (int i = 0; i < proby; ++i) {
                temp[i] += '*';
            }
            for(int i=proby;i<temp.length;++i){

                temp[i]+='.';
            }
            System.out.print("[");
            for (int i=0;i<temp.length;++i){
                System.out.print(temp[i]);
            }
            System.out.println("]");
            System.out.println(UsefulConstants.GIVE_ME);
            String liczba_uzytkownik = in.nextLine();
            try {
                int zgadywanie = Integer.parseInt(liczba_uzytkownik);

                if(zgadywanie > UsefulConstants.MAX_UPPER_BOUND){
                    ++proby;
                    System.out.println(zgadywanie + " jest " + UsefulConstants.TO_MUCH);
                }
                else if(zgadywanie < 1){
                    ++proby;
                    System.out.println(zgadywanie + " jest " + UsefulConstants.TO_LESS);
                }
                else if(zgadywanie == liczba){
                    System.out.println(zgadywanie + UsefulConstants.YES+"!");
                    break;
                }else{
                    ++proby;
                }


            }
            catch (NumberFormatException e) {
                ++proby;
                System.out.println(liczba_uzytkownik + " jest " + UsefulConstants.NOT_A_NUMBER);

            }
        }while(proby <= L);
        if(proby>L){
            System.out.println("Skończyły Ci się próby " + UsefulConstants.UNFORTUNATELY + ". Liczba to była: "+ liczba);
        }else{
            System.out.println("Odgadłeś! "+UsefulConstants.CONGRATULATIONS);
        }

    }
}
