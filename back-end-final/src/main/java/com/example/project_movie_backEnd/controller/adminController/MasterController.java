package com.example.project_movie_backEnd.controller.adminController;

import com.example.project_movie_backEnd.dto.admin.*;
import com.example.project_movie_backEnd.dto.reservation.CinemaDto;
import com.example.project_movie_backEnd.model.CinemaSite;
import com.example.project_movie_backEnd.model.Schedule;
import com.example.project_movie_backEnd.model.Theater;
import com.example.project_movie_backEnd.service.adminService.MasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/admin")
public class MasterController {
    @Autowired
    MasterService service;

    @GetMapping("/cinema") // 테스트완료 영화관명으로 검색 like 검색이므로 전체검색도 가능
    public ResponseEntity<Object> getCinemaSite(@RequestParam(required = false) String cinemaName) {
        try {
            List<CinemaSite> list = service.getCinemaSite(cinemaName);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cinema/add") // 테스트 완료 cinemaName 으로 해야함
    public ResponseEntity<Object> saveCinemaSite(@RequestBody CinemaSite cinemaSite) {
        try {
            CinemaSite cinemaSite1 = service.saveCinemaSite(cinemaSite);
            return new ResponseEntity<>(cinemaSite1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cinema/edit/{cid}") // 테스트완료 cinemaName 으로 해야함
    public ResponseEntity<Object> editCinemaSite(@PathVariable int cid,
                                                 @RequestBody CinemaSite cinemaSite) {
        try {
            CinemaSite cinemaSite1 = service.saveCinemaSite(cinemaSite);
            return new ResponseEntity<>(cinemaSite1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cinema/delete/{cid}") // 테스트 완료
    public ResponseEntity<Object> removeCinemaSiteById(@PathVariable int cid) {
        try {
            boolean bSuccess = service.removeCinemaSiteById(cid);
            if (bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/theater") // 테스트완료 영화관 id로 검색 like 검색이므로 전체검색도 가능
    public ResponseEntity<Object> getTheaterByCid(@RequestParam(required = false) Integer cid) {
        try {
            List<Theater> list = service.getTheaterByCid(cid);
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/theater/add") // 테스트 완료
    public ResponseEntity<Object> saveTheater(@RequestBody Theater theater) {
        try {
            Theater theater1 = service.saveTheater(theater);
            return new ResponseEntity<>(theater1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/theater/edit/{thid}") // 테스트 완료
    public ResponseEntity<Object> editTheater(@PathVariable int thid,
                                              @RequestBody Theater theater) {
        try {
            Theater theater1 = service.saveTheater(theater);
            return new ResponseEntity<>(theater1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/theater/delete/{thid}") // 테스트 완료
    public ResponseEntity<Object> removeTheaterById(@PathVariable int thid) {
        try {
            boolean bSuccess = service.removeTheaterById(thid);
            if (bSuccess) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales/cinema")
    public ResponseEntity<Object> findCinemaSales() {
        try {
            List<CinemaDto> cineList = service.findCinema();
            List<CinemaSalesDto>  list = new ArrayList<>();

            for (int i = 0; i < cineList.size(); i++) {

                List<CinemaSalesDto> cinemaSalesDtoList = service.findCinemaSales(cineList.get(i).getcinemaname());
                list.addAll(cinemaSalesDtoList);
            }

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales/movie")
    public ResponseEntity<Object> findMovieSales() {
        try {
            List<movieSales> list = service.findMovieSales();
            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales/ticket")
    public ResponseEntity<Object> yearlyReservationCount(){
        try{
            List<userCountDto> list =service.yearlyReservationCount();
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/vistor/month")
    public ResponseEntity<Object> monthlySales(){
        try{
            List<monthlyVistor> list =service.monthlyVistor();
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/countUser")
    public ResponseEntity<Object> findUserCount(){
        try{
            List<userCountDto> list =service.findUserCount();
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nonmemberTicketCount")
    public ResponseEntity<Object> NonMemberReservasionCount(){
        try{
            List<userCountDto> list =service.nonMemberReservasionCount();
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/memberTicketCount")
    public ResponseEntity<Object> memberReservasionCount(){
        try{
            List<userCountDto> list =service.memberReservasionCount();
            if(list.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
