package org.zerock.b01.dto;

/**
 * 검색 타입과 키워드 처리
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type; //검색 종류: t, c, w, tc, tw, twc

    private String keyword;

    /**
     * type 문자열을 배열로 반환하는 기능
     * @return
     */
    public String[] getTypes() {
        if (type == null || type.isEmpty()) {
            return null;
        }
        return type.split("");
    }

    /**
     * 페이징 처리를 위한 Pageable 타입을 반환하는 기능
     * @param props
     * @return
     */
    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    private  String link;

    /**
     * 검색 조건과 페이징 조건 처리 등을 문자열로 구성하는 기능
     * @return
     */
    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size= " + this.size);

            if(type != null && type.length() > 0) {
                builder.append("&type= " + type);
            }

            if(keyword != null) {
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));

                } catch(UnsupportedEncodingException e) {

                }
                link = builder.toString();
            }
        }
        return link;
    }
}
