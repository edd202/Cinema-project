package com.example.project_movie_backEnd.controller;

import com.example.project_movie_backEnd.dto.CustomerCenter.ResponseEventDto;
import com.example.project_movie_backEnd.model.*;
import com.example.project_movie_backEnd.service.CustomerCenterService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/movie")
public class CustomerCenterController {
    @Autowired
    CustomerCenterService service;
    ModelMapper modelMapper = new ModelMapper();


    //    ---------------------------------------- QnA Part -------------------------------------------------------

    @GetMapping("/qna") // userID 와 일치하는 질문자(writer)를 찾은후, qna 제목을 받아서 검색 작성없이 검색시 전체검색
    public ResponseEntity<Object> getQnaBySubject(@RequestParam(required = true) Integer id,
                                                  @RequestParam(required = false) String subject,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            System.out.println("11111");
            Pageable pageable = PageRequest.of(page, size);
            Page<Qna> qnas;
            log.debug("111");
            qnas = service.findAllByWriterAndSubjectContainingOrderByQidDesc(id,subject, pageable);
            log.debug("222");
            Map<String, Object> res = new HashMap<>();
            res.put("qna", qnas.getContent());
            res.put("currentPage", qnas.getNumber());
            res.put("totalItems", qnas.getTotalElements());
            res.put("totalPages", qnas.getTotalPages());

            if (!qnas.isEmpty()) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/qna/{qid}")
    public ResponseEntity<Object> getQnaId(@PathVariable int qid) {

        try {
            Optional<Qna> optionalQna = service.findByQid(qid);

            if (optionalQna.isPresent() == true) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(optionalQna.get(), HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/qna/{qid}")
    public ResponseEntity<Object> updateQna(@PathVariable int qid,
                                            @RequestBody Qna qna) {
        if(service.findByQid(qid).isPresent()){
            try {
                Qna qna1 = service.saveQna(qna);

                return new ResponseEntity<>(qna1, HttpStatus.OK);

            } catch (Exception e) {
                log.debug(e.getMessage());

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/qna") // qna 등록 컨트롤러
    public ResponseEntity<Object> qnaAdd(@RequestBody Qna qna) {
        try {
            Qna qna1 = service.saveQna(qna);
            return new ResponseEntity<>(qna1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/qna/delete/{qid}") // qna 삭제
    public ResponseEntity<Object> qnaDelete(@PathVariable int qid) {
        try {
            boolean bSuccess = service.removeByQid(qid);
            if (bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    ---------------------------------------- Notice Part -------------------------------------------------------

    @GetMapping("/notice") // 공지 불러오기 / 제목검색가능
    public ResponseEntity<Object> getNoticeByTitle(@RequestParam(required = false) String ntcTitle,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        try {
            System.out.println("11111");
            Pageable pageable = PageRequest.of(page, size);
            Page<Notice> notices;
            log.debug("111");
            notices = service.findByTitle(ntcTitle, pageable);
            log.debug("222");
            Map<String, Object> res = new HashMap<>();
            res.put("notice", notices.getContent());
            res.put("currentPage", notices.getNumber());
            res.put("totalItems", notices.getTotalElements());
            res.put("totalPages", notices.getTotalPages());

            if (notices.isEmpty() == false) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/notice/{ntcNo}")
    public ResponseEntity<Object> getNoticeId(@PathVariable int ntcNo) {

        try {
            Optional<Notice> optionalNotice = service.findById(ntcNo);

            if (optionalNotice.isPresent() == true) {
//                데이터 + 성공 메세지 전송
                return new ResponseEntity<>(optionalNotice.get(), HttpStatus.OK);
            } else {
//                데이터 없음 메세지 전송(클라이언트)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug(e.getMessage());
            // 서버에러 발생 메세지 전송(클라이언트)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/event")
    public ResponseEntity<Object> getEventByTitle(@RequestParam(required = false) String evtTitle,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ResponseEventDto> events = service
                    .findByEvtTitle(evtTitle, pageable)
                    .map(dbFile -> {
                        String eventThumbDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/event/thumb/")
                                .path(dbFile.getEvtNo().toString())
                                .toUriString();

                        String eventMainImgDownloadUri = ServletUriComponentsBuilder
                                .fromCurrentContextPath()
                                .path("/api/movie/event/img/")
                                .path(dbFile.getEvtNo().toString())
                                .toUriString();

                        ResponseEventDto dto = modelMapper.map(dbFile, ResponseEventDto.class);
                        dto.setThumbUrl(eventThumbDownloadUri);
                        dto.setMainImgUrl(eventMainImgDownloadUri);
                        return dto;
                    });

            Map<String, Object> res = new HashMap<>();
            res.put("events", events.getContent());
            res.put("currentPage", events.getNumber());
            res.put("totalItems", events.getTotalElements());
            res.put("totalPages", events.getTotalPages());

            if (events.isEmpty() == false) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/event/thumb/{evtNo}") // 이벤트 섬네일 불러오기
    public ResponseEntity<Object> getEventThumb(@PathVariable int evtNo) {
        Event event = service.getFile(evtNo).get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + event.getImgThumbFilename() + "\"")
                .body(event.getImgThumb());
    }

    @GetMapping("/event/img/{evtNo}") // 이벤트 이미지 불러오기
    public ResponseEntity<Object> getEventImg(@PathVariable int evtNo) {
        Event event = service.getFile(evtNo).get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + event.getImgMainFilename() + "\"")
                .body(event.getImgMain());
    }

}
