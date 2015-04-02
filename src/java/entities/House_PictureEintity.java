/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.InputStream;
import java.util.UUID;

/**
 *
 * @author Apostolis
 */
public class House_PictureEintity {
    private String HouseId;
    private String Description;
    private  InputStream picture;
    private String UniqueId;
    private byte[] PictureOut;
    

    /**
     * @return the HouseId
     */
    public String getHouseId() {
        return HouseId;
    }

    /**
     * @param HouseId the HouseId to set
     */
    public void setHouseId(String HouseId) {
        this.HouseId = HouseId;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the picture
     */
    public InputStream getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(InputStream picture) {
        this.picture = picture;
    }

    /**
     * @return the UniqueId
     */
    public String getUniqueId() {
        return UniqueId;
    }

    /**
     * @param UniqueId the UniqueId to set
     */
    public void setUniqueId(String UniqueId) {
        this.UniqueId = UniqueId;
    }
    public void GenerateUniqueId()
    {
        UniqueId = UUID.randomUUID().toString(); /* UUID class generates a universally unique identifier used to identify
                                        uniquely each house in database(PK) and system in general*/
    }

    /**
     * @return the PictureOut
     */
    public byte[] getPictureOut() {
        return PictureOut;
    }

    /**
     * @param PictureOut the PictureOut to set
     */
    public void setPictureOut(byte[] PictureOut) {
        this.PictureOut = PictureOut;
    }
}
