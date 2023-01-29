package com.example.project_movie_backEnd.service.adminService;

import com.example.project_movie_backEnd.model.*;
import com.example.project_movie_backEnd.repository.EventRepository;
import com.example.project_movie_backEnd.repository.NoticeRepository;
import com.example.project_movie_backEnd.repository.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerManagerService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    QnaRepository qnaRepository;

    @Autowired
    NoticeRepository noticeRepo;
    @Autowired
    QnaRepository qnaRepo;
    @Autowired
    EventRepository eventRepo;

    public Qna saveQna(Qna qna) {
        Qna qna1 = qnaRepo.save(qna);
        return qna1;
    }

    //    public List<Qna> findQnaByWriter(String writer) {
//        List<Qna> list = qnaRepo.findQnaByTitle(writer);
//        return list;
//    }
    public Page<Qna> findBySubject(String subject, Pageable pageable) {
        Page<Qna> page = qnaRepo.findAllBySubjectContainingOrderByQidDesc(subject, pageable);
        return page;
    }

    public Optional<Qna> findByQid(int qid) {
//        findById(기본키속성)
        Optional<Qna> optionalQna = qnaRepo.findById(qid);

        return optionalQna;
    }

    public boolean removeByQid(int qid) {
        if (qnaRepo.existsById(qid)) {
            qnaRepo.deleteById(qid);
            return true;
        }
        return false;
    }

//    --------------------------------------- Notice Part --------------------------------------------------

//    public Notice saveNotice(String ntcTitle,
//                             String ntcContent,
//                             MultipartFile ntcImage) throws IOException {
//
//        String ntcImageFilename = StringUtils.cleanPath(ntcImage.getOriginalFilename());
//        Notice notice = new Notice(ntcTitle,
//                ntcContent,
//                ntcImage.getBytes(),
//                ntcImageFilename,
//                ntcImage.getContentType());
//        noticeRepo.save(notice);
//        return notice;
//    }

    //    public Notice editNotice(int ntcNo,
//                             String ntcTitle,
//                             String ntcContent,
//                             MultipartFile ntcImage) throws IOException {
//
//        String ntcImageFilename = StringUtils.cleanPath(ntcImage.getOriginalFilename());
//        Notice notice = new Notice(ntcNo,
//                ntcTitle,
//                ntcContent,
//                ntcImage.getBytes(),
//                ntcImageFilename,
//                ntcImage.getContentType());
//        noticeRepo.save(notice);
//        return notice;
//    }
    public Notice saveNotice(Notice notice) {
        Notice notice1 = noticeRepo.save(notice);
        return notice1;
    }

    public Page<Notice> findByTitle(String ntcTitle, Pageable pageable) {
        Page<Notice> page = noticeRepo.findAllByNtcTitleContainingOrderByNtcNoDesc(ntcTitle, pageable);
        return page;
    }

    //    공지번호로 조회하는 함수
    public Optional<Notice> findById(int ntcNo) {
//        findById(기본키속성)
        Optional<Notice> optionalNotice = noticeRepo.findById(ntcNo);

        return optionalNotice;
    }

    public boolean removeByntcNo(int ntcNo) {
        if (noticeRepo.existsById(ntcNo)) {
            noticeRepo.deleteById(ntcNo);
            return true;
        }
        return false;
    }


    public Event saveEvent(String evtTitle,
                           String evtContent,
                           MultipartFile imgThumb,
                           MultipartFile imgMain) throws IOException {
        String imgThumbFilename = StringUtils.cleanPath(imgThumb.getOriginalFilename());
        String imgMainFilename = StringUtils.cleanPath(imgMain.getOriginalFilename());
        Event event = new Event(
                evtTitle,
                evtContent,
                imgThumb.getBytes(),
                imgThumbFilename,
                imgThumb.getContentType(),
                imgMain.getBytes(),
                imgMainFilename,
                imgMain.getContentType());
        eventRepo.save(event);
        return event;
    }


    public Event editEvent(int evtNo,
                           String evtTitle,
                           String evtContent,
                           MultipartFile imgThumb,
                           MultipartFile imgMain) throws IOException {
        String imgThumbFilename = StringUtils.cleanPath(imgThumb.getOriginalFilename());
        String imgMainFilename = StringUtils.cleanPath(imgMain.getOriginalFilename());
        Event event = new Event(
                evtNo,
                evtTitle,
                evtContent,
                imgThumb.getBytes(),
                imgThumbFilename,
                imgThumb.getContentType(),
                imgMain.getBytes(),
                imgMainFilename,
                imgMain.getContentType());
        eventRepo.save(event);
        return event;
    }

    public Page<Event> findByEvtTitle(String evtTitle, Pageable pageable) {
        Page<Event> page = eventRepo.findAllByEvtTitleContainingOrderByEvtNoDesc(evtTitle, pageable);
        return page;
    }

    public boolean removeByEvtNo(int evtNo) {
        if (eventRepo.existsById(evtNo)) {
            eventRepo.deleteById(evtNo);
            return true;
        }
        return false;
    }

}
