package dto;


import models.Entity;

/**
 * Created by Alex on 16.08.2016.
 */
public class FilmDTO extends Entity {
    private String name;
    private int yearOfRelease;
    private int durationMin;
    private String genre;
    private String country;
    private String produser;
    private String cast;
    private String description;
    private String smallPoster;
    private String bigPoster;
    private float raiting;

    public FilmDTO(String name, int yearOfRelease, String genre, int hours, int minutes, String country, String produser, String description, String... cast) {
        setName(name);
        setGenre(genre);
        setYearOfRelease(yearOfRelease);
        setDurationMin(hours, minutes);
        setCountry(country);
        setProduser(produser);
        setCast(cast);
        setDescription(description);
    }

    public FilmDTO() {

    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int minutes) {
        this.durationMin = minutes;
    }

    public void setDurationMin(int hours, int minutes) {
        this.durationMin = (hours * 60) + minutes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProduser() {
        return produser;
    }

    public void setProduser(String produser) {
        this.produser = produser;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setCast(String[] cast) {
        String result = "";
        for (String s : cast) {
            result = result + ", " + s;
        }
        this.cast = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallPoster() {
        return smallPoster;
    }

    public void setSmallPoster(String smallPoster) {
        this.smallPoster = smallPoster;
    }

    public String getBigPoster() {
        return bigPoster;
    }

    public void setBigPoster(String bigPoster) {
        this.bigPoster = bigPoster;
    }

    public float getRaiting() {
        return raiting;
    }

    public void setRaiting(float raiting) {
        this.raiting = raiting;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", duration=" + (durationMin / 60) + "h. " + (durationMin - (durationMin / 60) * 60) + "min." +
                ", country='" + country + '\'' +
                ", produser='" + produser + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmDTO film = (FilmDTO) o;

        if (yearOfRelease != film.yearOfRelease) return false;
        if (!name.equals(film.name)) return false;
        if (name != null ? !name.equals(film.name) : film.name != null) return false;
        return produser != null ? produser.equals(film.produser) : film.produser == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + yearOfRelease;
        result = 31 * result + produser.hashCode();
        return result;
    }
}
