package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {
    private String title;
    private List<String> languages;
    @JsonProperty("download_count")
    private Integer downloadCount;
    private java.util.List<AutorDTO> authors;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public Integer getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Integer downloadCount) { this.downloadCount = downloadCount; }

    public java.util.List<AutorDTO> getAuthors() { return authors; }
    public void setAuthors(java.util.List<AutorDTO> authors) { this.authors = authors; }
}
