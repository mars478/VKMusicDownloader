package vkmusicdownloader;

/**
 *
 * @author mars
 */

/*
id: 141109685,
owner_id: 56091049,
artist: 'Loreen',
title: 'Euphoria',
duration: 181,
url: 'http://cs9-1v4.vk.me/p6/9763c0d2eae117.mp3',
lyrics_id: 23109031,
album_id: 9200275,
genre_id: 18
 */

public class Track {

    private long aid;
    private long owner_id;
    private String artist;
    private String title;
    private long duration;
    private String url;
    private long lyrics_id;
    private long album_id;
    private long genre_id;

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public long getLyrics_id() {
        return lyrics_id;
    }

    public void setLyrics_id(long lyrics_id) {
        this.lyrics_id = lyrics_id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nPerfomer: " + artist + "\n");
        sb.append("Title: " + title + "\n");
        sb.append("Duration: " + duration + "\n");
        sb.append("URL: " + url + "\n");

        return sb.toString();
    }
}
