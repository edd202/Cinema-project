import http from "../http-common";

class EventDataService {
    // upload() 함수

    upload(evtTitle, evtContent, imgThumb,imgMain){
        // json 객체 사용하지 않음
        // form 태그로 전송하는 방식을 이용함
        // html <form></form> == js FormData 객체로 사용가능
        //                       .append("속성명",값) 함수 : 데이터를 저장
        // axios 함수 : .post()
        // 헤더 : "Content-Type" : "multipart/form-data"
        console.log(evtTitle)
        console.log(evtContent)
        console.log(imgMain)
        console.log(imgThumb)
        let formData = new FormData();  // 폼(form) 객체 생성

        formData.append("evtTitle", evtTitle);
        formData.append("evtContent", evtContent);
        formData.append("imgThumb", imgThumb);
        formData.append("imgMain", imgMain);

        return http.post(`/event/add`, formData, {
            headers : {
                "Content-Type" : "multipart/form-data"      //  이미지 파일임을 알려주는것
            }
        })
    }

    // 이미지 제목 검색 함수
    getFiles(evtTitle){
        console.log(evtTitle);

        return http.get(`/event?evtTitle=${evtTitle}`)
    }

    // 이미지 삭제 요청 함수
    delete(evtNo){
        return http.delete(`/event/deletion/${evtNo}`);
    }
    
}

export default new EventDataService();