package com.project.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
    super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValue));
//                       user              id  2000
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
