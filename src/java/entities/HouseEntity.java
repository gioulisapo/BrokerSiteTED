/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author Apostolis
 */
public class HouseEntity 
{ 
        
    private String uniqueCode;
    private String Name;
    private String Address;
    private String longitude;
    private String latitude;
    private float priceBuy;
    private float priceLent;
    private String region;
    private int squares;
    private float expenses;
    private String dateAvailable;
    private String username;
    private String heat;
    private String description;
    private String type;

    /**
     * @return the uniqueCode
     */
    public String getUniqueCode() {
        return uniqueCode;
    }
    
    /**
     * @param uniqueCode the uniqueCode to set
     */
    public void GenerateUniqueCode() { /* UUID class generates a universally unique identifier used to identify
                                        uniquely each house in database(PK) and system in general*/
        this.uniqueCode = UUID.randomUUID().toString();
    }
    
    public void setUniqueCode(String code)
    {
        this.uniqueCode = code;
    }
    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the priceBuy
     */
    public float getPriceBuy() {
        return priceBuy;
    }

    /**
     * @param priceBuy the priceBuy to set
     */
    public void setPriceBuy(float priceBuy) {
        this.priceBuy = priceBuy;
    }

    /**
     * @return the priceLent
     */
    public float getPriceLent() {
        return priceLent;
    }

    /**
     * @param priceLent the priceLent to set
     */
    public void setPriceLent(float priceLent) {
        this.priceLent = priceLent;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the squares
     */
    public int getSquares() {
        return squares;
    }

    /**
     * @param squares the squares to set
     */
    public void setSquares(int squares) {
        this.squares = squares;
    }

    /**
     * @return the expenses
     */
    public float getExpenses() {
        return expenses;
    }

    /**
     * @param expenses the expenses to set
     */
    public void setExpenses(float expenses) {
        this.expenses = expenses;
    }

    /**
     * @return the dateAvailable
     */
    public String getDateAvailable() {
        return dateAvailable;
    }

    /**
     * @param dateAvailable the dateAvailable to set
     */
    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the heat
     */
    public String getHeat() {
        return heat;
    }

    /**
     * @param heat the heat to set
     */
    public void setHeat(String heat) {
        this.heat = heat;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }



}