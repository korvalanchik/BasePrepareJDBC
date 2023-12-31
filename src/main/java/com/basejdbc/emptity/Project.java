package com.basejdbc.emptity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Project {
    private Long id;
    private Long clientId;
    private LocalDate startDate;
    private LocalDate finishDate;
}
