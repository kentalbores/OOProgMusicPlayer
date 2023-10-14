import java.io.*;
import java.util.*;
public class Main{
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Song> songs = new ArrayList<>();

    public static void main(String[] args){
        AskOperation();
    }

    public static void AskOperation(){
        int resp;
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Songs.txt"));
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                songs.add(new Song(data[0], data[1], data[2], data[3]));
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        while(true){
            resp = errorCheckedInput("Operation: [1] Add Song   [2] View Songs  [3] View Song Details: ");
            sc.nextLine();
            if(resp == 1){
                AddSongs();
            }else if(resp == 2){
                ViewSongs();
            }else if(resp == 3){
                ViewSongDetails();
            }else {
                System.out.println("Program Closed!");
                break;
            }
        }
    }

    public static void AddSongs(){
        String name, artist, category, timeReleased;
        Song song;

        name = input("Song Name: ");
        artist = input("Artist: ");
        category = input("Category: ");
        timeReleased = getDate();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Songs.txt", true));
            writer.write(name + "," + artist + "," + category + "," + timeReleased);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        song = new Song(name, artist, category, timeReleased);
        songs.add(song);

    }
    public static void ViewSongs(){
        if (songs.isEmpty()){
            print("There are no songs currently added.");
            return;
        }
        int resp = errorCheckedInput("View Songs by: [1] Category [2] Artist : ");
        sc.nextLine();
        int i = 0;
        if (resp == 1){
            String catName = input("Category Name: ");
            print("\n||===============Songs===============||");
            for(Song song : songs){
                i++;
                if(song.category.equals(catName)){
                    System.out.println("\t" + i + ": " + song.getName());
                }
            }
            print("||===================================||\n");
        }else if (resp == 2) {
            String artistName = input("Artist Name: ");
            print("\n||===============Song===============||");
            for (Song song : songs) {
                i++;
                if (song.artist.equals(artistName)) {
                    System.out.println("\t" + i + ": " + song.getName());
                }
            }
            print("||==================================||\n");
        }else{
            System.out.println("Invalid Input");
        }
    }
    public static void ViewSongDetails(){
        int i = 0;
        if (songs.isEmpty()){
            print("There are no songs currently added.");
            return;
        }
        print("\n||===============Song===============||");
        for (Song song : songs){
            i++;
            System.out.println("\t[" + i + "] " + ": " + song.getName());
        }
        print("||==================================||\n");
        int songID = errorCheckedInput("Enter Song ID to View: ");
        if (songID > songs.size()){
            print("Invalid Input");
            return;
        }
        viewSongDetail(songID);

    }

    public static void print(String word){
        System.out.println(word);
    }
    public static String input(String ask){
        System.out.print(ask);
        return sc.nextLine();
    }
    public static void viewSongDetail(int id){
        print("\n||===============Song===============||");
        print("\tSong Name: " + songs.get(id - 1).getName());
        print("\tCategory: " + songs.get(id - 1).category);
        print("\tArtist: " + songs.get(id - 1).artist);
        print("\tRelease Date: " + songs.get(id - 1).timeReleased);
        print("||==================================||\n");
    }
    public static int errorCheckedInput(String ask){
        while(true){
            System.out.print(ask);
            if (sc.hasNextInt()){
                return sc.nextInt();
            }else {
                sc.nextLine();
                System.out.println("Invalid Input");
            }
        }
    }
    public static String getDate(){
        while (true){
            try {
                String date = input("Time Released (mm/dd/yy): ");
                String[] dateInt = date.split("/");
                int month = Integer.parseInt(dateInt[0]);
                int day = Integer.parseInt(dateInt[1]);
                int year = Integer.parseInt(dateInt[2]);
                if (month <= 12 && day <= 31 && year <= 23 && month >= 0 && day >= 0 && year >= 0 ){
                    return month + "/" + day + "/" + year;
                }
                System.out.println("Please Enter the Correct Date");
            }catch (Exception e){
                System.out.println("Please Enter Date in Correct Format");
            }
        }
    }
}
