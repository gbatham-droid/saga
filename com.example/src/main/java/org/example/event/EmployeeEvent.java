package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEvent {

    private Long id;
    private String name;

    // Saga status: CREATED, B_PROCESSED, COMPLETED, ROLLBACK
    private String status;
}
