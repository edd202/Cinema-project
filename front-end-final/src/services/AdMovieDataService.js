import http from "../http-common";
import authHeader from "./userServices/auth-header";

class AdMovieDataService {
  // 영화제목 검색 요청 함수
  getAll(mtitle, page, size) {
    return http.get(`/admin/movie?mtitle=${mtitle}&page=${page}&size=${size}`, {
      headers: authHeader(),
    });
  }

  // 영화번호로 조회 요청 함수
  get(mid) {
    return http.get(`/admin/movie/${mid}`, { headers: authHeader() });
  }

  update(
    mid,
    mtitle,
    mrunningTime,
    mcode,
    mreleaseDate,
    mclosingDate,
    mdirector,
    mactor,
    msimpleInfo,
    mtrailerLink,
    mage,
    mposter,
    mimage1,
    mimage2,
    mimage3,
    mimage4,
    mimage5,
    onUploadProgress
  ) {
    let formData = new FormData();

    console.log(mid);
    console.log(mtitle);
    console.log(mrunningTime);
    console.log(mcode);    
    
    console.log(mreleaseDate);
    console.log(mclosingDate);
    console.log(mdirector);
    console.log(mactor);

    console.log(msimpleInfo);
    console.log(mtrailerLink);
    console.log(mage);
    console.log(mposter);
    console.log(mimage1);
    console.log(mimage2);
    console.log(mimage3);
    console.log(mimage4);
    console.log(mimage5);

    formData.append("mtitle", mtitle);
    formData.append("mrunningTime", mrunningTime);
    formData.append("mcode", mcode);
    formData.append("mreleaseDate", mreleaseDate);
    formData.append("mclosingDate", mclosingDate);
    formData.append("mdirector", mdirector);
    formData.append("mactor", mactor);
    formData.append("msimpleInfo", msimpleInfo);
    formData.append("mtrailerLink", mtrailerLink);
    formData.append("mage", mage);
    formData.append("mposter", mposter);
    formData.append("mimage1", mimage1);
    formData.append("mimage2", mimage2);
    formData.append("mimage3", mimage3);
    formData.append("mimage4", mimage4);
    formData.append("mimage5", mimage5);

    return http.put(
      `/admin/movie/${mid}`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
        onUploadProgress
      });
  }

  // 영화 삭제 요청 함수
  delete(mid) {
    return http.delete(`/admin/movie/delete/${mid}`, { headers: authHeader() });
  }

  // db 저장함수
  create(
    mtitle,
    mcode,
    mrunningTime,
    mreleaseDate,
    mclosingDate,
    mdirector,
    mactor,
    msimpleInfo,
    mtrailerLink,
    mage,
    mposter,
    mimage1,
    mimage2,
    mimage3,
    mimage4,
    mimage5,
    onUploadProgress
  ) {
    // json 객체 사용하지 않음 (x)
    // form 태그로 전송하는 방식을 이용함
    // html <form></form> == js FormData 객체로 사용가능
    //                       .append("속성명", 값) 함수 : 데이터를 저장
    // axios 함수 : .post()
    // 헤더 : "Content-Type" : "multipart/form-data"
    let formData = new FormData();

    formData.append("mtitle", mtitle);
    formData.append("mcode", mcode);
    formData.append("mrunningTime", mrunningTime);
    formData.append("mreleaseDate", mreleaseDate);
    formData.append("mclosingDate", mclosingDate);
    formData.append("mdirector", mdirector);
    formData.append("mactor", mactor);
    formData.append("msimpleInfo", msimpleInfo);
    formData.append("mtrailerLink", mtrailerLink);
    formData.append("mage", mage);
    formData.append("mposter", mposter);
    formData.append("mimage1", mimage1);
    formData.append("mimage2", mimage2);
    formData.append("mimage3", mimage3);
    formData.append("mimage4", mimage4);
    formData.append("mimage5", mimage5);

    return http.post(
      "/admin/movie",
      formData,
      { headers: { "Content-Type": "multipart/form-data" }, onUploadProgress },
      { headers: authHeader() }
    );
  }
}

export default new AdMovieDataService();
