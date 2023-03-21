package team99.publicapi.domain;

import javax.persistence.Entity;
import javax.persistence.*;


public class Listing {
    private Integer id;
    private String user_id;
    private Integer price;
    private String listing_type;
    private Long created_at;
    private Long updated_at;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getListing_type() {
        return listing_type;
    }

    public void setListing_type(String listing_type) {
        this.listing_type = listing_type;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getUpdated_at() {
        return updated_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUpdated_at(Long updated_at) {
        this.updated_at = updated_at;
    }
}
