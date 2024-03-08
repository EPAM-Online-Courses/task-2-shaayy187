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
        int proby=0;
        do {
            System.out.println("Zakres gry: <1,"+this.M+">.");
            char[] temp = new char[L];
            for (int i = 0; i < proby + 1; ++i) {
                temp[i] += '*';
            }
            for(int i=proby +1 ;i<L;++i){
                temp[i]+='.';
            }
            System.out.print("[");
            for (int i=0;i<temp.length;++i){
                System.out.print(temp[i]);
            }
            System.out.println("]");
            System.out.println(UsefulConstants.GIVE_ME+" liczbę do zgadywania: ");
            String liczba_uzytkownik = in.nextLine();
            int zgadywanie;
            try {
                zgadywanie = Integer.parseInt(liczba_uzytkownik);

                if(zgadywanie > M){
                    ++proby;
                    System.out.println(zgadywanie + " jest " + UsefulConstants.TO_MUCH);
                }
                else if(zgadywanie < 1){
                    ++proby;
                    System.out.println(zgadywanie + " jest " + UsefulConstants.TO_LESS);
                }
                else if(zgadywanie == liczba){
                    System.out.println(zgadywanie + UsefulConstants.YES+" !");
                    break;
                }else{
                    ++proby;
                }


            }
            catch (NumberFormatException e) {
                ++proby;
                System.out.println(liczba_uzytkownik + " jest " + UsefulConstants.NOT_A_NUMBER);

            }
        }while(proby +1 < L+1);
        if(proby+1>L){
            System.out.println("Skończyły Ci się próby " + UsefulConstants.UNFORTUNATELY + ". Liczba to była: "+ liczba);
        }else{
            System.out.println("Odgadłeś! "+UsefulConstants.YES+" "+UsefulConstants.CONGRATULATIONS);
        }

    }
}
