package org.zerock.b01.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 업로드 결과의 반환 처리
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String uuid;
    private String fileName;
    private boolean img;

    /**
     * 파일 업로드 결과 link 가져오기
     * @return
     */
    public String getLink() {
        if (img) {
            return "s_" + uuid + "_" + fileName; //이미지인 경우 썸네일
        } else {
            return uuid + "_" + fileName;
        }
    }
}
