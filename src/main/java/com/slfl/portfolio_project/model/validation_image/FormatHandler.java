package com.slfl.portfolio_project.model.validation_image;

import com.slfl.portfolio_project.model.ImageFile;
import org.springframework.web.multipart.MultipartFile;

public class FormatHandler extends BaseImageHandler {
    @Override
    protected boolean doHandle(MultipartFile file) {
        String[] validFormats = {"jpg", "jpeg", "png"};
        String format = ((ImageFile) file).getFormat().toLowerCase();
        for (String validFormat : validFormats) {
            if (format.equals(validFormat)) {
                return true; // Valid format
            }
        }
        return false; // Not valid format
    }
}