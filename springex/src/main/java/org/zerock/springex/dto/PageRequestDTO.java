package org.zerock.springex.dto;

/**
 * 페이징 처리를 위한 DTO
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    //링크 생성에 필요한 변수
    private String link;

    //검색 기능에 필요한 변수
    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;

    /**
     *  건너뛰기 Skip수
     * @return  (page - 1) * 10
     */
    public int getSkip() {
        return (page - 1) * 10;
    }

    /**
     * 링크 생성
     * @return link
     */
//    public String getLink() {
//        if (link == null) {
//            StringBuilder builder = new StringBuilder();
//            builder.append("page=" + this.page);
//            builder.append("&size=" + this.size);
//            link = builder.toString();
//        }
//        return link;
//    }

    /**
     * 링크 생성하기
     * @return
     */
    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);

        if (finished) {
            builder.append("&finished=on");
        }

        if (types != null && types.length > 0) {
            for(int i = 0; i < types.length; i++) {
                builder.append("&types=" + types[i]);
            }
        }

        if (keyword != null) {
           try {
               builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
        }

        if (from != null) {
            builder.append("&from=" + from.toString());
        }

        if (to != null) {
            builder.append(("&to=" + to.toString()));
        }
        return builder.toString();
    }

    /**
     * 화면에 검색 조건 표시하기
     * @param type
     * @return
     */
    public boolean checkType(String type) {
        if (types== null || types.length == 0) {
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }
}
