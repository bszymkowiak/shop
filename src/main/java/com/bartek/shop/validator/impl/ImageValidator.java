package com.bartek.shop.validator.impl;

import com.bartek.shop.validator.ImageValid;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ImageValidator implements ConstraintValidator<ImageValid, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile image, ConstraintValidatorContext context) {

        if (image == null) {
            return true;
        }
        String extension = FilenameUtils.getExtension(image.getOriginalFilename());

        if (extension == null) {
            return false;
        }
        extension = extension.toLowerCase();

        return Objects.equals(extension, "jpg") || Objects.equals(extension, "png");
    }
}