package com.example.project_movie_backEnd.controller.adminController;

import com.example.project_movie_backEnd.model.Event;
import com.example.project_movie_backEnd.model.Notice;
import com.example.project_movie_backEnd.model.Qna;
import com.example.project_movie_backEnd.service.adminService.CustomerManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class CustomerManageController {
    @Autowired
    CustomerManagerService service;

    //    관리자 페이지로 이동할 컨트롤러
    @PutMapping("/qna/{qid}") // qna 답변 컨트롤러   테스트 완료
    public ResponseEntity<Object> qnaAnswer(@PathVariable int qid,
                                            @RequestBody Qna qna) {
        try {
            Qna qna1 = service.saveQna(qna);

            return new ResponseEntity<>(qna1, HttpStatus.OK);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @GetMapping("/qna") // qna 작성자명을 받아서 검색 작성없이 검색시 전체검색  테스트 완료
//    public ResponseEntity<Object> getQnaByWriter(@RequestParam(required = false) String writer) {
//        try {
//            List<Qna> list = service.findBySubject(writer);
//            if (list.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            } else {
//                return new ResponseEntity<>(list, HttpStatus.OK);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/qna") // 테스트 완료
    public ResponseEntity<Object> getQnaBySubject(@RequestParam(required = false) String subject,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Qna> qnas = service.findBySubject(subject, pageable);

            Map<String, Object> res = new HashMap<>();
            res.put("qna", qnas.getContent());
            res.put("currentPage", qnas.getNumber());
            res.put("totalItems", qnas.getTotalElements());
            res.put("totalPages", qnas.getTotalPages());

            if (qnas.isEmpty() == false) {
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

    @DeleteMapping("/qna/delete/{qid}") // qna 삭제  테스트 완료
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

//  -------------------------------------- Notice Part -------------------------------------------

    //    공지 추가 컨트롤러
    @PostMapping("/notice") // TODO : 프런트 연동하고 테스트 필요
    public ResponseEntity<Object> noticeAdd(@RequestBody Notice notice) {
        try {
            Notice notice1 = service.saveNotice(notice);
            return new ResponseEntity<>(notice1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/notice/{ntcNo}") // 테스트완료
    public ResponseEntity<Object> noticeEdit(@PathVariable int ntcNo,
                                             @RequestBody Notice notice) {
        try {
            Notice notice1 = service.saveNotice(notice);

            return new ResponseEntity<>(notice1, HttpStatus.OK);

        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notice") // 테스트 완료
    public ResponseEntity<Object> getNoticeByTitle(@RequestParam(required = false) String ntcTitle,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Notice> notices = service.findByTitle(ntcTitle, pageable);

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

    @DeleteMapping("/notice/delete/{ntcNo}") // 테스트 완료
    public ResponseEntity<Object> noticeDelete(@PathVariable int ntcNo) {
        try {
            boolean bSuccess = service.removeByntcNo(ntcNo);
            if (bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/event/add") // TODO : 이미지 때문에 프런트 연동후 테스트 필요
    public ResponseEntity<Object> eventAdd(@RequestParam("evtTitle") String evtTitle,
                                           @RequestParam("evtContent") String evtContent,
                                           @RequestParam("imgThumb") MultipartFile imgThumb,
                                           @RequestParam("imgMain") MultipartFile imgMain) {
        try {
            service.saveEvent(evtTitle, evtContent, imgThumb, imgMain);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/event/edit")// TODO : 이미지 때문에 프런트 연동후 테스트 필요
    public ResponseEntity<Object> eventEdit(@RequestParam("evtNo") int evtNo,
                                            @RequestParam("evtTitle") String evtTitle,
                                            @RequestParam("evtContent") String evtContent,
                                            @RequestParam("imgThumb") MultipartFile imgThumb,
                                            @RequestParam("imgMain") MultipartFile imgMain) {
        try {
            service.editEvent(evtNo, evtTitle, evtContent, imgThumb, imgMain);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/event") // 테스트 완료
    public ResponseEntity<Object> getEventByTitle(@RequestParam(required = false) String evtTitle,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Event> events = service.findByEvtTitle(evtTitle, pageable);

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

    @DeleteMapping("/event/delete/{evtNo}") // 테스트 완료
    public ResponseEntity<Object> eventDelete(@PathVariable int evtNo) {
        try {
            boolean bSuccess = service.removeByEvtNo(evtNo);
            if (bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
