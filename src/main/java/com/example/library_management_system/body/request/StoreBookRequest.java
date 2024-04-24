package com.example.library_management_system.body.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreBookRequest {
    private String title;
    private String author;
    private String genre;
    private String isbn ;
    private Float price ;
    private Integer quantity ;
    private Integer unitsSold;
}
