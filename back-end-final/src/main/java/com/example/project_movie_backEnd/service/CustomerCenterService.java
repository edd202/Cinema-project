package com.example.project_movie_backEnd.service;

import com.example.project_movie_backEnd.model.*;
import com.example.project_movie_backEnd.repository.EventRepository;
import com.example.project_movie_backEnd.repository.NoticeRepository;
import com.example.project_movie_backEnd.repository.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerCenterService {
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

    //    subject like 검색 함수( 페이징 처리 추가 )
    public Page<Qna> findAllBySubjectContaining(String subject, Pageable pageable) {
        Page<Qna> page = qnaRepo.findAllBySubjectContainingOrderByQidDesc(subject, pageable);

        return page;
    }
    public Page<Qna> findAllByWriterAndSubjectContainingOrderByQidDesc(Integer writer,String subject, Pageable pageable) {
        Page<Qna> page = qnaRepo.findAllByWriterAndSubjectContainingOrderByQidDesc(writer,subject, pageable);

        return page;
    }
    public Optional<Qna> findByQid(int qid) {
//        findById(기본키속성)
        Optional<Qna> optionalQna = qnaRepo.findById(qid);

        return optionalQna;
    }

    public boolean removeByQid(int qid) {
        if (qnaRepo.existsById(qid) == true) {
            qnaRepo.deleteById(qid);
            return true;
        }
        return false;
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

    public Page<Event> findByEvtTitle(String evtTitle, Pageable pageable) {
        Page<Event> page = eventRepo.findAllByEvtTitleContainingOrderByEvtNoDesc(evtTitle, pageable);
        return page;
    }

    public Optional<Event> getFile(int evtNo) {
        Optional<Event> optional = eventRepo.findById(evtNo);
        return optional;
    }
}

