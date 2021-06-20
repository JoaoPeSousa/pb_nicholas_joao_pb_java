/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.infnet.at_nicholas_joao_pb_java.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.io.FileUtils;

import javax.persistence.Column;
import java.io.File;
import java.io.IOException;
import java.util.Base64;


/**
 *
 * @author Nicholas
 */
public class Serie {
    @JsonIgnoreProperties(ignoreUnknown = true)
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("image")
    @Column(columnDefinition = "LONGBLOB")
    private String image;


    @JsonProperty("name")
    private String name;
    
    @JsonProperty("description")
    private String description;
    
    public Serie(){
        
    }


    public Long getId(){
        return this.id;
    }
    
    public void setId(Long id){
        this.id = id;
    }


    public String getImage(){
        return this.image;
    }

    public void setImage(String image){
        this.image = image;
    }


    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

    public static byte[] convertFileContentToBlob(String filePath) throws IOException {
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " +
                    e.getMessage());
        }
        return fileContent;
    }
    
    @Override
	public String toString() {
		return "";
	}
}
