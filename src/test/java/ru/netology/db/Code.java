package ru.netology.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code {
    private String id;
    public String user;
    public String code;
    public Date created;

}
