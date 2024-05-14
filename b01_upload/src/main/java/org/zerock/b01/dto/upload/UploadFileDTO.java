package org.zerock.b01.dto.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 파일 업로드 처리를 위한 DTO
 */

@Data
public class UploadFileDTO {
    private List<MultipartFile> files;
}
