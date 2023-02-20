package org.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
public class User {
    private String login;
    private String password;
    private String firstname;
    private String lastname;
}