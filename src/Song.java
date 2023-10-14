class Song{
    String name, artist, category, timeReleased;
    Song(String name, String artist, String category, String timeReleased){
        this.name = name;
        this.artist = artist;
        this.category = category;
        this.timeReleased = timeReleased;
    }
    void DisplayName(){
        System.out.println(name);
    }
    void DisplayTimeReleased(){
        System.out.println(timeReleased);
    }
    String getName(){
        return name;
    }
}
