package org.zerock.b01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.*;
import org.zerock.b01.repository.BoardRepository;
import org.zerock.b01.service.BoardService;

import java.io.File;
import javax.validation.Valid;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    @Value("${org.zerock.b01.upload.path}")
    private String uploadPath;

    private final BoardService boardService;

    private final BoardRepository boardRepository;

    /**
     * 목록 화면: 화면에 데이터를 출력함
     * PageRequestDTO와 PageRequestDTO 객체가 화면으로 전달됨
     * @param pageRequestDTO
     * @param
     */
//    @GetMapping("/list")
//    public void list(PageRequestDTO pageRequestDTO, Model model) {
//        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
//
//        log.info(responseDTO);
//
//        model.addAttribute("responseDTO", responseDTO);
//    }
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        //PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);

        PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    /**
     * 등록 화면 이동
     */
    @GetMapping("/register")
    public void registerGET() {

    }

    /**
     * 등록 화면: title, content, writer 입력 + 입력 데이터 검증
     * @param boardDTO
     * @param bindingResult
     * @param redirectAttributes
     * @return 데이터 검증 실패시 -> 등록 화면 이동, 성공 시 -> 목록 화면 이동
     */
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("board POST register.........");

        //데이터 검증 실패시 에러 처리
        if (bindingResult.hasErrors()) {
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(boardDTO);

        Long bno = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    /**
     * 조회 화면, 수정/삭제 화면
     * @param bno
     * @param pageRequestDTO
     * @param model
     */
    @GetMapping({"/read", "/modify"})
    public void read(Long bno,
                     PageRequestDTO pageRequestDTO,
                     Model model) {
        BoardDTO boardDTO = boardService.readOne(bno);

        log.info(boardDTO);

        model.addAttribute("dto", boardDTO);
    }

    /**
     * 수정 화면
     * @param pageRequestDTO
     * @param boardDTO
     * @param bindingResult
     * @param redirectAttributes
     * @return 데이터 검증 실패 시 -> 수정화면 이동, 성공 시 -> 조회화면 이동
     */
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("board modify post......" + boardDTO);

        //데이터 검증 실패시 에러 처리
        if (bindingResult.hasErrors()) {
            log.info("has errors........");

            String link = pageRequestDTO.getLink();

            System.out.println("link: " + link);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("bno", boardDTO.getBno());

            return "redirect:/board/modify?" + link;
        }

        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result", "modified");

        redirectAttributes.addAttribute("bno", boardDTO.getBno());

        return "redirect:/board/read";
    }

    /**
     * 삭제 처리
     * @param bno
     * @param redirectAttributes
     * @return 목록 화면으로 이동
     */
    @PostMapping("/remove")
    public String remove(BoardDTO boardDTO,
                         RedirectAttributes redirectAttributes) {
        Long bno = boardDTO.getBno();
        log.info("remove post.." + bno);

        boardService.remove(bno);

        //게시물이 데이터베이스상에서 삭제되었다면 첨부파일 삭제
        log.info(boardDTO.getFileNames());
        List<String> fileNames = boardDTO.getFileNames();

        if (fileNames != null && fileNames.size() > 0) {
            removeFiles(fileNames);
        }

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/board/list";
    }

    public void removeFiles(List<String> files) {
        for(String fileName: files) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();

            try {
                String contentType = Files.probeContentType(resource.getFile().toPath());

                resource.getFile().delete();

                //썸네일이 존재한다면
                if(contentType.startsWith("image")) {
                    File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                    thumbnailFile.delete();
                }
            } catch(Exception e) {
                log.error(e.getMessage());
            }
        }
    }

}
