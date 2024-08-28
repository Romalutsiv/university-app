package org.university.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserProfile {
    private Long id;
    private Long userAuthId;
    private String firstname;
    private String lastname;
    private String middleName;
    private String phoneNumber;
    private String address;
    private String photoUrl;
}
