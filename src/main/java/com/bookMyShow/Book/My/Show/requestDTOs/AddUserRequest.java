package com.bookMyShow.Book.My.Show.requestDTOs;

import lombok.Data;

@Data
public class AddUserRequest {
    private Integer age;
    private String userName;
    private String password;
    private String email;
    private String mobile;
}
