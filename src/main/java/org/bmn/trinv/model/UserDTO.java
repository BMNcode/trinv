package org.bmn.trinv.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @Size(max = 60)
    private String firstName;

    @Size(max = 60)
    private String lastName;

}
