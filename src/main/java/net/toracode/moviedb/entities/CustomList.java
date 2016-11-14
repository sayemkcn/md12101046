package net.toracode.moviedb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.*;

@Entity
public class CustomList extends BaseEntity {
    private String title;
    private String description;
    private String type;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Movie> movieList;
    @JsonIgnore
    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> followerList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<User> followerList) {
        this.followerList = followerList;
    }

    @Override
    public String toString() {
        return "CustomList{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", movieList=" + movieList +
                ", user=" + user +
                ", followerList=" + followerList +
                '}';
    }
}
