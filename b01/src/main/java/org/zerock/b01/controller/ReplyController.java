package org.zerock.b01.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.service.ReplyService;

import javax.validation.Valid;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/replies")
@RequiredArgsConstructor //의존성 주입을 위한 추가
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 등록
     * @param replyDTO
     * @return
     */
    @ApiOperation(value="Replies POST", notes = "POST 방식으로 댓글 등록")
    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                                      BindingResult bindingResult) throws BindException {
        log.info("Reply Register....");
        log.info(replyDTO);


        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }


        Map<String, Long> resultMap = new HashMap<>();

        Long rno = replyService.register(replyDTO);

        resultMap.put("rno", rno);

        log.info("replyDTO.getBno(): " +replyDTO.getBno());

        log.info(resultMap);

        return resultMap;
    }

    /**
     * 특정 게시물의 댓글 목록
     * @param bno
     * @param pageRequestDTO
     * @return
     */
    @ApiOperation(value="Replies od Board", notes="GET 방식으로 특정 게시물의 댓글 목록")
    @GetMapping(value="/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno,
                                             PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);
        return responseDTO;
    }

    /**
     * 특정 댓글 조회
     * @param rno
     * @return
     */
    @ApiOperation(value="Read Reply", notes = "GET 방식으로 특정 댓글 조회")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno) {
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }

    /**
     * 특정 댓글 삭제
     * @param rno
     * @return
     */
    @ApiOperation(value="Delete Reply", notes="DELETE 방식으로 특정 댓글 삭제")
    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno) {
        replyService.remove(rno);
        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno", rno);

        return resultMap;
    }

    /**
     * 특정 댓글 수정
     * @param rno
     * @param replyDTO
     * @return
     */
    @ApiOperation(value="Modify reply", notes="PUT 방식으로 특정 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(@PathVariable("rno") Long rno,
                                    @RequestBody ReplyDTO replyDTO) {
        replyDTO.setRno(rno);

        replyService.modify(replyDTO);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno", rno);

        return resultMap;
    }

}
