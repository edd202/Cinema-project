<template>
    <div>
        <div class="wrap_reserve">
            <h2 class="hidden">예매하기</h2>
            <div id="PersonSeatHeader" class="section_step_tit">
                <ul>
                    <li class="step01 prev"><a href="#reserveStep01"><strong
                                class="tit"><span>01</span><br>상영시간</strong>
                            <div class="bx_con">
                                <dl>
                                    <dt>선택한 영화 제목</dt>
                                    <dd>아바타: 물의 길</dd>
                                    <dt>선택한 상영관</dt>
                                    <dd>노원 1관</dd>
                                    <dt>선택한 상영 날짜</dt>
                                    <dd>2022-12-20 (화)</dd>
                                    <dt>선택한 시간</dt>
                                    <dd>22:35 ~ 25:57</dd>
                                </dl>
                            </div>
                        </a></li>
                    <li class="step02 active"><a href="#reserveStep02"><strong
                                class="tit"><span>02</span><br>인원/좌석</strong>
                            <div class="bx_con">
                                <dl>
                                    <dt>선택한 인원</dt>
                                    <dd><span id="preview_person_info"></span></dd>
                                    <dt>선택한 좌석</dt>
                                    <dd class="seat_list"><span id="preview_seat_info"></span></dd>
                                </dl>
                            </div>
                        </a></li>
                    <li class="step03"><a style="cursor: default;"><strong class="tit"><span>03</span><br>결제</strong>
                            <div class="bx_con">
                                <dl>
                                    <dt>티켓금액</dt>
                                    <dd>0원</dd>
                                    <dt>할인금액</dt>
                                    <dd>0원</dd>
                                    <dt>총합계</dt>
                                    <dd>0원</dd>
                                </dl>
                            </div>
                        </a></li>
                    <li><a style="cursor: default;"><strong class="tit"><span>04</span><br>결제완료</strong></a></li>
                </ul>
            </div>




            <div id="reserveStep01" class="section_step_con step01">
                <h3 class="hidden">상영시간</h3>

            </div>



            <div id="reserveStep02" class="section_step_con step02 active">
                <h3 class="hidden">인원/좌석</h3>
                <!--<div id="PersonSeatHeader"></div>-->

                <div class="article article_seat">
                    <div class="group_top">
                        <h4 class="tit">인원/좌석 선택</h4>
                        <p class="txt" id="txtPeopleMaxcount">인원은 최대 8명까지 선택 가능합니다.</p>
                    </div>
                    <div class="inner">

                        <div id="PersonSeatCount">
                            <div class="select_num_people_wrap">
                                <h5 class="hidden">인원선택</h5>
                                <div class="movie_infor">
                                    <h6 class="hidden">예매 정보</h6><span class="thm"><img
                                            src="https://caching.lottecinema.co.kr//Media/MovieFile/MovieImg/202212/18755_103_1.jpg"></span>
                                    <div class="group_infor">
                                        <div class="bx_tit"><span class="ic_grade gr_12">관람가</span><strong>아바타: 물의 길
                                                (2D)</strong></div>
                                        <dl>
                                            <dt>일시</dt>
                                            <dd class="sub_info1">22.12.20<em>(화)</em><span class="time">22:35 ~
                                                    25:57</span></dd>
                                            <dt>영화관</dt>
                                            <dd class="sub_info1">노원 · 1관 </dd>
                                        </dl>
                                    </div>
                                </div>
                                <div class="count_people">
                                    <h6 class="hidden">인원선택</h6>
                                    <ul>
                                        <li id="person_10" data-code="10" data-peple="성인" data-count="0"><strong
                                                class="tit">성인</strong><span class="bx_num"><button class="btn_mins"
                                                    id="Minus|10">감소</button>
                                                <div class="txt_num">0</div><button class="btn_plus"
                                                    id="Plus|10">증가</button>
                                            </span></li>
                                        <li id="person_20" data-code="20" data-peple="청소년" data-count="0"><strong
                                                class="tit">청소년</strong><span class="bx_num"><button class="btn_mins"
                                                    id="Minus|20">감소</button>
                                                <div class="txt_num">0</div><button class="btn_plus"
                                                    id="Plus|20">증가</button>
                                            </span></li>
                                        <li id="person_12" data-code="12" data-peple="시니어" data-count="0"><strong
                                                class="tit">시니어</strong><span class="bx_num"><button class="btn_mins"
                                                    id="Minus|12">감소</button>
                                                <div class="txt_num">0</div><button class="btn_plus"
                                                    id="Plus|12">증가</button>
                                            </span></li>
                                        <li id="person_11" data-code="11" data-peple="장애인" data-count="0"><strong
                                                class="tit">장애인</strong><span class="bx_num"><button class="btn_mins"
                                                    id="Minus|11">감소</button>
                                                <div class="txt_num">0</div><button class="btn_plus"
                                                    id="Plus|11">증가</button>
                                            </span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>



                        <div class="select_seat_wrap">
                            <h5 class="hidden">좌석선택</h5>
                            <div class="top_txt_info">
                                <p id="ticketMessageInfo">인원을 선택하세요.</p>
                            </div>
                            <div id="PersonSeatSelect"> </div>

                            <div id="container" class="seat_wrap">
                                <article class="mseat_wrap">
                                    <div class="mseat_inner">

                                        <div class="mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar"
                                            data-mcs-theme="minimal-dark"
                                            style="position: relative; overflow: visible;">
                                            <div id="mCSB_1"
                                                class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside"
                                                style="max-height: none;" tabindex="0">
                                                <div id="mCSB_1_container"
                                                    class="mCSB_container mCS_y_hidden mCS_no_scrollbar_y"
                                                    style="position:relative; top:0; left:0;" dir="ltr">
                                                    <div class="meat_door_pos" id="scrollWrap">
                                                        <div class="mseat_hidden">

                                                            <span class="title_screen1">SCREEN</span>

                                                            <div class="showMap">
                                                                <div class="floor_bx seatSet1"> <span class="floor_tit"
                                                                        style="display: none;">10F</span>
                                                                    <div class="seat_area"
                                                                        style="margin-top: 30px; width: 581px; height: 298.208px;">
                                                                        <span class="seat_tit"
                                                                            style="left:-30px; top:0px">A</span><a
                                                                            href="#none" alt="좌석 번호:A4 장애인 발받침석"
                                                                            class="sel handicapped p0 grNum3 no_select"
                                                                            block-code="p0" data-seat="A4"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A04"
                                                                            style="left: 117px; top: 0px;"><span
                                                                                class="handicapped f1 no_select blind"
                                                                                href="#none"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="#none" alt="좌석 번호:A5 장애인 발받침석"
                                                                            class="sel handicapped p0 grNum3 no_select"
                                                                            block-code="p0" data-seat="A5"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A05"
                                                                            style="left: 141.857px; top: 0px;"><span
                                                                                class="handicapped f1 no_select blind"
                                                                                href="#none"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="#none" alt="좌석 번호:A6 장애인 발받침석"
                                                                            class="sel handicapped p0 grNum3 no_select"
                                                                            block-code="p0" data-seat="A6"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A06"
                                                                            style="left: 166.714px; top: 0px;"><span
                                                                                class="handicapped f1 no_select blind"
                                                                                href="#none"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="#none" alt="좌석 번호:A7 장애인 발받침석"
                                                                            class="sel handicapped p0 grNum3 no_select"
                                                                            block-code="p0" data-seat="A7"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A07"
                                                                            style="left: 191.571px; top: 0px;"><span
                                                                                class="handicapped f1 no_select blind"
                                                                                href="#none"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A9 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A9"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A09"
                                                                            style="left: 241.286px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A10 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A10"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A10"
                                                                            style="left: 266.143px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A11 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A11"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A11"
                                                                            style="left: 291px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A12 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A12"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A12"
                                                                            style="left: 315.857px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A13 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A13"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A13"
                                                                            style="left: 340.714px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A14 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A14"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A14"
                                                                            style="left: 365.571px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A15 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A15"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A15"
                                                                            style="left: 390.429px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A16 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A16"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A16"
                                                                            style="left: 415.286px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A17 발받침석"
                                                                            class="sel foot_seat p0 grNum3"
                                                                            block-code="p0" data-seat="A17"
                                                                            seat-statuscode="0" seat-group="grNum3"
                                                                            seat-code="1A17"
                                                                            style="left: 440.143px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A18 발받침석"
                                                                            class="sel foot_seat p0 grNum4"
                                                                            block-code="p0" data-seat="A18"
                                                                            seat-statuscode="0" seat-group="grNum4"
                                                                            seat-code="1A18"
                                                                            style="left: 486.429px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A19 발받침석"
                                                                            class="sel foot_seat p0 grNum4"
                                                                            block-code="p0" data-seat="A19"
                                                                            seat-statuscode="0" seat-group="grNum4"
                                                                            seat-code="1A19"
                                                                            style="left: 511.286px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A20 발받침석"
                                                                            class="sel foot_seat p0 grNum4"
                                                                            block-code="p0" data-seat="A20"
                                                                            seat-statuscode="0" seat-group="grNum4"
                                                                            seat-code="1A20"
                                                                            style="left: 536.143px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:A21 발받침석"
                                                                            class="sel foot_seat p0 grNum4"
                                                                            block-code="p0" data-seat="A21"
                                                                            seat-statuscode="0" seat-group="grNum4"
                                                                            seat-code="1A21"
                                                                            style="left: 561px; top: 0px;"><span
                                                                                class="foot_seat f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:25.291666666666668px">B</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B4 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B4"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B04"
                                                                            style="left: 117px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B5 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B5"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B05"
                                                                            style="left: 141.857px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B6 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B6"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B06"
                                                                            style="left: 166.714px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B7 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B7"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B07"
                                                                            style="left: 191.571px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B8 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B8"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B08"
                                                                            style="left: 216.429px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B9 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B9"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B09"
                                                                            style="left: 241.286px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B10 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B10"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B10"
                                                                            style="left: 266.143px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B11 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B11"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B11"
                                                                            style="left: 291px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B12 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B12"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B12"
                                                                            style="left: 315.857px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B13 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B13"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B13"
                                                                            style="left: 340.714px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B14 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B14"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B14"
                                                                            style="left: 365.571px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B15 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B15"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B15"
                                                                            style="left: 390.429px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B16 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B16"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B16"
                                                                            style="left: 415.286px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B17 일반석" class="sel  p0 grNum5"
                                                                            block-code="p0" data-seat="B17"
                                                                            seat-statuscode="0" seat-group="grNum5"
                                                                            seat-code="1B17"
                                                                            style="left: 440.143px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B18 일반석" class="sel  p0 grNum6"
                                                                            block-code="p0" data-seat="B18"
                                                                            seat-statuscode="0" seat-group="grNum6"
                                                                            seat-code="1B18"
                                                                            style="left: 486.429px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B19 일반석" class="sel  p0 grNum6"
                                                                            block-code="p0" data-seat="B19"
                                                                            seat-statuscode="0" seat-group="grNum6"
                                                                            seat-code="1B19"
                                                                            style="left: 511.286px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B20 일반석" class="sel  p0 grNum6"
                                                                            block-code="p0" data-seat="B20"
                                                                            seat-statuscode="0" seat-group="grNum6"
                                                                            seat-code="1B20"
                                                                            style="left: 536.143px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:B21 일반석" class="sel  p0 grNum6"
                                                                            block-code="p0" data-seat="B21"
                                                                            seat-statuscode="0" seat-group="grNum6"
                                                                            seat-code="1B21"
                                                                            style="left: 561px; top: 25.2917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:50.583333333333336px">C</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C4 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C4"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C04"
                                                                            style="left: 117px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C5 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C5"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C05"
                                                                            style="left: 141.857px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C6 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C6"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C06"
                                                                            style="left: 166.714px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C7 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C7"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C07"
                                                                            style="left: 191.571px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C8 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C8"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C08"
                                                                            style="left: 216.429px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C9 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C9"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C09"
                                                                            style="left: 241.286px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C10 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C10"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C10"
                                                                            style="left: 266.143px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C11 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C11"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C11"
                                                                            style="left: 291px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C12 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C12"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C12"
                                                                            style="left: 315.857px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C13 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C13"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C13"
                                                                            style="left: 340.714px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C14 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C14"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C14"
                                                                            style="left: 365.571px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C15 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C15"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C15"
                                                                            style="left: 390.429px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C16 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C16"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C16"
                                                                            style="left: 415.286px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C17 일반석" class="sel  p0 grNum7"
                                                                            block-code="p0" data-seat="C17"
                                                                            seat-statuscode="0" seat-group="grNum7"
                                                                            seat-code="1C17"
                                                                            style="left: 440.143px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C18 일반석" class="sel  p0 grNum8"
                                                                            block-code="p0" data-seat="C18"
                                                                            seat-statuscode="0" seat-group="grNum8"
                                                                            seat-code="1C18"
                                                                            style="left: 486.429px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C19 일반석" class="sel  p0 grNum8"
                                                                            block-code="p0" data-seat="C19"
                                                                            seat-statuscode="0" seat-group="grNum8"
                                                                            seat-code="1C19"
                                                                            style="left: 511.286px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C20 일반석" class="sel  p0 grNum8"
                                                                            block-code="p0" data-seat="C20"
                                                                            seat-statuscode="0" seat-group="grNum8"
                                                                            seat-code="1C20"
                                                                            style="left: 536.143px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:C21 일반석" class="sel  p0 grNum8"
                                                                            block-code="p0" data-seat="C21"
                                                                            seat-statuscode="0" seat-group="grNum8"
                                                                            seat-code="1C21"
                                                                            style="left: 561px; top: 50.5833px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:75.875px">D</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D4 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D4"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D04"
                                                                            style="left: 117px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D5 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D5"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D05"
                                                                            style="left: 141.857px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D6 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D6"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D06"
                                                                            style="left: 166.714px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D7 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D7"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D07"
                                                                            style="left: 191.571px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D8 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D8"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D08"
                                                                            style="left: 216.429px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D9 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D9"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D09"
                                                                            style="left: 241.286px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D10 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D10"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D10"
                                                                            style="left: 266.143px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D11 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D11"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D11"
                                                                            style="left: 291px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D12 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D12"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D12"
                                                                            style="left: 315.857px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D13 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D13"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D13"
                                                                            style="left: 340.714px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D14 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D14"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D14"
                                                                            style="left: 365.571px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D15 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D15"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D15"
                                                                            style="left: 390.429px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D16 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D16"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D16"
                                                                            style="left: 415.286px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D17 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum9"
                                                                            block-code="p0" data-seat="D17"
                                                                            seat-statuscode="0" seat-group="grNum9"
                                                                            seat-code="1D17"
                                                                            style="left: 440.143px; top: 75.875px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D18 일반석" class="sel  p0 grNum10"
                                                                            block-code="p0" data-seat="D18"
                                                                            seat-statuscode="0" seat-group="grNum10"
                                                                            seat-code="1D18"
                                                                            style="left: 486.429px; top: 75.875px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D19 일반석" class="sel  p0 grNum10"
                                                                            block-code="p0" data-seat="D19"
                                                                            seat-statuscode="0" seat-group="grNum10"
                                                                            seat-code="1D19"
                                                                            style="left: 511.286px; top: 75.875px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D20 일반석" class="sel  p0 grNum10"
                                                                            block-code="p0" data-seat="D20"
                                                                            seat-statuscode="0" seat-group="grNum10"
                                                                            seat-code="1D20"
                                                                            style="left: 536.143px; top: 75.875px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:D21 일반석" class="sel  p0 grNum10"
                                                                            block-code="p0" data-seat="D21"
                                                                            seat-statuscode="0" seat-group="grNum10"
                                                                            seat-code="1D21"
                                                                            style="left: 561px; top: 75.875px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:101.16666666666667px">E</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E4 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E4"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E04"
                                                                            style="left: 117px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E5 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E5"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E05"
                                                                            style="left: 141.857px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E6 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E6"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E06"
                                                                            style="left: 166.714px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E7 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E7"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E07"
                                                                            style="left: 191.571px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E8 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E8"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E08"
                                                                            style="left: 216.429px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E9 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E9"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E09"
                                                                            style="left: 241.286px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E10 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E10"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E10"
                                                                            style="left: 266.143px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E11 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E11"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E11"
                                                                            style="left: 291px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E12 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E12"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E12"
                                                                            style="left: 315.857px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E13 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E13"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E13"
                                                                            style="left: 340.714px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E14 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E14"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E14"
                                                                            style="left: 365.571px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E15 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E15"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E15"
                                                                            style="left: 390.429px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E16 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E16"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E16"
                                                                            style="left: 415.286px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E17 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum11"
                                                                            block-code="p0" data-seat="E17"
                                                                            seat-statuscode="0" seat-group="grNum11"
                                                                            seat-code="1E17"
                                                                            style="left: 440.143px; top: 101.167px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E18 일반석" class="sel  p0 grNum12"
                                                                            block-code="p0" data-seat="E18"
                                                                            seat-statuscode="0" seat-group="grNum12"
                                                                            seat-code="1E18"
                                                                            style="left: 486.429px; top: 101.167px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E19 일반석" class="sel  p0 grNum12"
                                                                            block-code="p0" data-seat="E19"
                                                                            seat-statuscode="0" seat-group="grNum12"
                                                                            seat-code="1E19"
                                                                            style="left: 511.286px; top: 101.167px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E20 일반석" class="sel  p0 grNum12"
                                                                            block-code="p0" data-seat="E20"
                                                                            seat-statuscode="0" seat-group="grNum12"
                                                                            seat-code="1E20"
                                                                            style="left: 536.143px; top: 101.167px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:E21 일반석" class="sel  p0 grNum12"
                                                                            block-code="p0" data-seat="E21"
                                                                            seat-statuscode="0" seat-group="grNum12"
                                                                            seat-code="1E21"
                                                                            style="left: 561px; top: 101.167px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:126.45833333333333px">F</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F4 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F4"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F04"
                                                                            style="left: 117px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F5 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F5"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F05"
                                                                            style="left: 141.857px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F6 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F6"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F06"
                                                                            style="left: 166.714px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F7 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F7"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F07"
                                                                            style="left: 191.571px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F8 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F8"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F08"
                                                                            style="left: 216.429px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F9 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F9"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F09"
                                                                            style="left: 241.286px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F10 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F10"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F10"
                                                                            style="left: 266.143px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F11 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F11"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F11"
                                                                            style="left: 291px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F12 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F12"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F12"
                                                                            style="left: 315.857px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F13 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F13"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F13"
                                                                            style="left: 340.714px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F14 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F14"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F14"
                                                                            style="left: 365.571px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F15 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F15"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F15"
                                                                            style="left: 390.429px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F16 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F16"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F16"
                                                                            style="left: 415.286px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F17 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum13"
                                                                            block-code="p0" data-seat="F17"
                                                                            seat-statuscode="0" seat-group="grNum13"
                                                                            seat-code="1F17"
                                                                            style="left: 440.143px; top: 126.458px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F18 일반석" class="sel  p0 grNum14"
                                                                            block-code="p0" data-seat="F18"
                                                                            seat-statuscode="0" seat-group="grNum14"
                                                                            seat-code="1F18"
                                                                            style="left: 486.429px; top: 126.458px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F19 일반석" class="sel  p0 grNum14"
                                                                            block-code="p0" data-seat="F19"
                                                                            seat-statuscode="0" seat-group="grNum14"
                                                                            seat-code="1F19"
                                                                            style="left: 511.286px; top: 126.458px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F20 일반석" class="sel  p0 grNum14"
                                                                            block-code="p0" data-seat="F20"
                                                                            seat-statuscode="0" seat-group="grNum14"
                                                                            seat-code="1F20"
                                                                            style="left: 536.143px; top: 126.458px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:F21 일반석" class="sel  p0 grNum14"
                                                                            block-code="p0" data-seat="F21"
                                                                            seat-statuscode="0" seat-group="grNum14"
                                                                            seat-code="1F21"
                                                                            style="left: 561px; top: 126.458px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:151.75px">G</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G4 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G4"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G04"
                                                                            style="left: 117px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G5 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G5"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G05"
                                                                            style="left: 141.857px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G6 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G6"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G06"
                                                                            style="left: 166.714px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G7 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G7"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G07"
                                                                            style="left: 191.571px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G8 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G8"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G08"
                                                                            style="left: 216.429px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G9 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G9"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G09"
                                                                            style="left: 241.286px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G10 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G10"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G10"
                                                                            style="left: 266.143px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G11 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G11"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G11"
                                                                            style="left: 291px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G12 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G12"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G12"
                                                                            style="left: 315.857px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G13 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G13"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G13"
                                                                            style="left: 340.714px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G14 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G14"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G14"
                                                                            style="left: 365.571px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G15 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G15"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G15"
                                                                            style="left: 390.429px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G16 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G16"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G16"
                                                                            style="left: 415.286px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G17 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum15"
                                                                            block-code="p0" data-seat="G17"
                                                                            seat-statuscode="0" seat-group="grNum15"
                                                                            seat-code="1G17"
                                                                            style="left: 440.143px; top: 151.75px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G18 일반석" class="sel  p0 grNum16"
                                                                            block-code="p0" data-seat="G18"
                                                                            seat-statuscode="0" seat-group="grNum16"
                                                                            seat-code="1G18"
                                                                            style="left: 486.429px; top: 151.75px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G19 일반석" class="sel  p0 grNum16"
                                                                            block-code="p0" data-seat="G19"
                                                                            seat-statuscode="0" seat-group="grNum16"
                                                                            seat-code="1G19"
                                                                            style="left: 511.286px; top: 151.75px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G20 일반석" class="sel  p0 grNum16"
                                                                            block-code="p0" data-seat="G20"
                                                                            seat-statuscode="0" seat-group="grNum16"
                                                                            seat-code="1G20"
                                                                            style="left: 536.143px; top: 151.75px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:G21 일반석" class="sel  p0 grNum16"
                                                                            block-code="p0" data-seat="G21"
                                                                            seat-statuscode="0" seat-group="grNum16"
                                                                            seat-code="1G21"
                                                                            style="left: 561px; top: 151.75px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:177.04166666666666px">H</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H4 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H4"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H04"
                                                                            style="left: 117px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H5 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H5"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H05"
                                                                            style="left: 141.857px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H6 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H6"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H06"
                                                                            style="left: 166.714px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H7 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H7"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H07"
                                                                            style="left: 191.571px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H8 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H8"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H08"
                                                                            style="left: 216.429px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H9 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H9"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H09"
                                                                            style="left: 241.286px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H10 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H10"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H10"
                                                                            style="left: 266.143px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H11 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H11"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H11"
                                                                            style="left: 291px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H12 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H12"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H12"
                                                                            style="left: 315.857px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H13 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H13"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H13"
                                                                            style="left: 340.714px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H14 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H14"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H14"
                                                                            style="left: 365.571px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H15 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H15"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H15"
                                                                            style="left: 390.429px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H16 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H16"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H16"
                                                                            style="left: 415.286px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H17 일반석 SWEET SPOT"
                                                                            class="sel  sweet_spot p0 grNum17"
                                                                            block-code="p0" data-seat="H17"
                                                                            seat-statuscode="0" seat-group="grNum17"
                                                                            seat-code="1H17"
                                                                            style="left: 440.143px; top: 177.042px;"><span
                                                                                class=" sweet_spot f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H18 일반석" class="sel  p0 grNum18"
                                                                            block-code="p0" data-seat="H18"
                                                                            seat-statuscode="0" seat-group="grNum18"
                                                                            seat-code="1H18"
                                                                            style="left: 486.429px; top: 177.042px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H19 일반석" class="sel  p0 grNum18"
                                                                            block-code="p0" data-seat="H19"
                                                                            seat-statuscode="0" seat-group="grNum18"
                                                                            seat-code="1H19"
                                                                            style="left: 511.286px; top: 177.042px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H20 일반석" class="sel  p0 grNum18"
                                                                            block-code="p0" data-seat="H20"
                                                                            seat-statuscode="0" seat-group="grNum18"
                                                                            seat-code="1H20"
                                                                            style="left: 536.143px; top: 177.042px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:H21 일반석" class="sel  p0 grNum18"
                                                                            block-code="p0" data-seat="H21"
                                                                            seat-statuscode="0" seat-group="grNum18"
                                                                            seat-code="1H21"
                                                                            style="left: 561px; top: 177.042px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:202.33333333333334px">I</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I1 일반석" class="sel  p0 grNum19"
                                                                            block-code="p0" data-seat="I1"
                                                                            seat-statuscode="0" seat-group="grNum19"
                                                                            seat-code="1I01"
                                                                            style="left: 21px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">1</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I2 일반석" class="sel  p0 grNum19"
                                                                            block-code="p0" data-seat="I2"
                                                                            seat-statuscode="0" seat-group="grNum19"
                                                                            seat-code="1I02"
                                                                            style="left: 45.8571px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">2</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I3 일반석"
                                                                            class="sel  p0 completed grNum20"
                                                                            block-code="p0" data-seat="I3"
                                                                            seat-statuscode="50" seat-group="grNum20"
                                                                            seat-code="1I03"
                                                                            style="left: 70.7143px; top: 202.333px;"><span
                                                                                class="f1 blind"
                                                                                style="top: 0px;">3</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I4 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I4"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I04"
                                                                            style="left: 117px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I5 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I5"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I05"
                                                                            style="left: 141.857px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I6 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I6"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I06"
                                                                            style="left: 166.714px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I7 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I7"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I07"
                                                                            style="left: 191.571px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I8 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I8"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I08"
                                                                            style="left: 216.429px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I9 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I9"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I09"
                                                                            style="left: 241.286px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I10 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I10"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I10"
                                                                            style="left: 266.143px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I11 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I11"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I11"
                                                                            style="left: 291px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I12 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I12"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I12"
                                                                            style="left: 315.857px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I13 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I13"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I13"
                                                                            style="left: 340.714px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I14 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I14"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I14"
                                                                            style="left: 365.571px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I15 일반석" class="sel  p0 grNum22"
                                                                            block-code="p0" data-seat="I15"
                                                                            seat-statuscode="0" seat-group="grNum22"
                                                                            seat-code="1I15"
                                                                            style="left: 390.429px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I16 일반석"
                                                                            class="sel  Impossible p0 completed grNum23"
                                                                            block-code="p0" data-seat="I16"
                                                                            seat-statuscode="30" seat-group="grNum23"
                                                                            seat-code="1I16"
                                                                            style="left: 415.286px; top: 202.333px;"><span
                                                                                class="Impossible f1 blind"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I17 일반석"
                                                                            class="sel  Impossible p0 completed grNum23"
                                                                            block-code="p0" data-seat="I17"
                                                                            seat-statuscode="30" seat-group="grNum23"
                                                                            seat-code="1I17"
                                                                            style="left: 440.143px; top: 202.333px;"><span
                                                                                class="Impossible f1 blind"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I18 일반석"
                                                                            class="sel  Impossible p0 completed grNum24"
                                                                            block-code="p0" data-seat="I18"
                                                                            seat-statuscode="30" seat-group="grNum24"
                                                                            seat-code="1I18"
                                                                            style="left: 486.429px; top: 202.333px;"><span
                                                                                class="Impossible f1 blind"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I19 일반석"
                                                                            class="sel  Impossible p0 completed grNum24"
                                                                            block-code="p0" data-seat="I19"
                                                                            seat-statuscode="30" seat-group="grNum24"
                                                                            seat-code="1I19"
                                                                            style="left: 511.286px; top: 202.333px;"><span
                                                                                class="Impossible f1 blind"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I20 일반석" class="sel  p0 grNum25"
                                                                            block-code="p0" data-seat="I20"
                                                                            seat-statuscode="0" seat-group="grNum25"
                                                                            seat-code="1I20"
                                                                            style="left: 536.143px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:I21 일반석" class="sel  p0 grNum25"
                                                                            block-code="p0" data-seat="I21"
                                                                            seat-statuscode="0" seat-group="grNum25"
                                                                            seat-code="1I21"
                                                                            style="left: 561px; top: 202.333px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:227.625px">J</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J1 일반석" class="sel  p0 grNum26"
                                                                            block-code="p0" data-seat="J1"
                                                                            seat-statuscode="0" seat-group="grNum26"
                                                                            seat-code="1J01"
                                                                            style="left: 21px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">1</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J2 일반석" class="sel  p0 grNum26"
                                                                            block-code="p0" data-seat="J2"
                                                                            seat-statuscode="0" seat-group="grNum26"
                                                                            seat-code="1J02"
                                                                            style="left: 45.8571px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">2</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J3 일반석" class="sel  p0 grNum26"
                                                                            block-code="p0" data-seat="J3"
                                                                            seat-statuscode="0" seat-group="grNum26"
                                                                            seat-code="1J03"
                                                                            style="left: 70.7143px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">3</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J4 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J4"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J04"
                                                                            style="left: 117px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J5 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J5"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J05"
                                                                            style="left: 141.857px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J6 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J6"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J06"
                                                                            style="left: 166.714px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J7 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J7"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J07"
                                                                            style="left: 191.571px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J8 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J8"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J08"
                                                                            style="left: 216.429px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J9 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J9"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J09"
                                                                            style="left: 241.286px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J10 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J10"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J10"
                                                                            style="left: 266.143px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J11 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J11"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J11"
                                                                            style="left: 291px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J12 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J12"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J12"
                                                                            style="left: 315.857px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J13 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J13"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J13"
                                                                            style="left: 340.714px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J14 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J14"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J14"
                                                                            style="left: 365.571px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J15 일반석" class="sel  p0 grNum27"
                                                                            block-code="p0" data-seat="J15"
                                                                            seat-statuscode="0" seat-group="grNum27"
                                                                            seat-code="1J15"
                                                                            style="left: 390.429px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J16 일반석"
                                                                            class="sel  Impossible p0 completed grNum28"
                                                                            block-code="p0" data-seat="J16"
                                                                            seat-statuscode="30" seat-group="grNum28"
                                                                            seat-code="1J16"
                                                                            style="left: 415.286px; top: 227.625px;"><span
                                                                                class="Impossible f1 blind"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J17 일반석"
                                                                            class="sel  Impossible p0 completed grNum28"
                                                                            block-code="p0" data-seat="J17"
                                                                            seat-statuscode="30" seat-group="grNum28"
                                                                            seat-code="1J17"
                                                                            style="left: 440.143px; top: 227.625px;"><span
                                                                                class="Impossible f1 blind"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J18 일반석"
                                                                            class="sel  Impossible p0 completed grNum29"
                                                                            block-code="p0" data-seat="J18"
                                                                            seat-statuscode="30" seat-group="grNum29"
                                                                            seat-code="1J18"
                                                                            style="left: 486.429px; top: 227.625px;"><span
                                                                                class="Impossible f1 blind"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J19 일반석"
                                                                            class="sel  Impossible p0 completed grNum29"
                                                                            block-code="p0" data-seat="J19"
                                                                            seat-statuscode="30" seat-group="grNum29"
                                                                            seat-code="1J19"
                                                                            style="left: 511.286px; top: 227.625px;"><span
                                                                                class="Impossible f1 blind"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J20 일반석" class="sel  p0 grNum30"
                                                                            block-code="p0" data-seat="J20"
                                                                            seat-statuscode="0" seat-group="grNum30"
                                                                            seat-code="1J20"
                                                                            style="left: 536.143px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:J21 일반석" class="sel  p0 grNum30"
                                                                            block-code="p0" data-seat="J21"
                                                                            seat-statuscode="0" seat-group="grNum30"
                                                                            seat-code="1J21"
                                                                            style="left: 561px; top: 227.625px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:252.91666666666666px">K</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K1 일반석" class="sel  p0 grNum31"
                                                                            block-code="p0" data-seat="K1"
                                                                            seat-statuscode="0" seat-group="grNum31"
                                                                            seat-code="1K01"
                                                                            style="left: 21px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">1</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K2 일반석" class="sel  p0 grNum31"
                                                                            block-code="p0" data-seat="K2"
                                                                            seat-statuscode="0" seat-group="grNum31"
                                                                            seat-code="1K02"
                                                                            style="left: 45.8571px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">2</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K3 일반석" class="sel  p0 grNum31"
                                                                            block-code="p0" data-seat="K3"
                                                                            seat-statuscode="0" seat-group="grNum31"
                                                                            seat-code="1K03"
                                                                            style="left: 70.7143px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">3</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K4 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K4"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K04"
                                                                            style="left: 117px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K5 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K5"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K05"
                                                                            style="left: 141.857px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K6 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K6"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K06"
                                                                            style="left: 166.714px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K7 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K7"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K07"
                                                                            style="left: 191.571px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K8 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K8"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K08"
                                                                            style="left: 216.429px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K9 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K9"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K09"
                                                                            style="left: 241.286px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K10 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K10"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K10"
                                                                            style="left: 266.143px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K11 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K11"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K11"
                                                                            style="left: 291px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K12 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K12"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K12"
                                                                            style="left: 315.857px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K13 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K13"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K13"
                                                                            style="left: 340.714px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K14 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K14"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K14"
                                                                            style="left: 365.571px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K15 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K15"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K15"
                                                                            style="left: 390.429px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K16 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K16"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K16"
                                                                            style="left: 415.286px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K17 일반석" class="sel  p0 grNum32"
                                                                            block-code="p0" data-seat="K17"
                                                                            seat-statuscode="0" seat-group="grNum32"
                                                                            seat-code="1K17"
                                                                            style="left: 440.143px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K18 일반석" class="sel  p0 grNum33"
                                                                            block-code="p0" data-seat="K18"
                                                                            seat-statuscode="0" seat-group="grNum33"
                                                                            seat-code="1K18"
                                                                            style="left: 486.429px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K19 일반석" class="sel  p0 grNum33"
                                                                            block-code="p0" data-seat="K19"
                                                                            seat-statuscode="0" seat-group="grNum33"
                                                                            seat-code="1K19"
                                                                            style="left: 511.286px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K20 일반석" class="sel  p0 grNum33"
                                                                            block-code="p0" data-seat="K20"
                                                                            seat-statuscode="0" seat-group="grNum33"
                                                                            seat-code="1K20"
                                                                            style="left: 536.143px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:K21 일반석" class="sel  p0 grNum33"
                                                                            block-code="p0" data-seat="K21"
                                                                            seat-statuscode="0" seat-group="grNum33"
                                                                            seat-code="1K21"
                                                                            style="left: 561px; top: 252.917px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a><span
                                                                            class="seat_tit"
                                                                            style="left:-30px; top:278.2083333333333px">L</span><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L1 일반석" class="sel  p0 grNum34"
                                                                            block-code="p0" data-seat="L1"
                                                                            seat-statuscode="0" seat-group="grNum34"
                                                                            seat-code="1L01"
                                                                            style="left: 21px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">1</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L2 일반석" class="sel  p0 grNum34"
                                                                            block-code="p0" data-seat="L2"
                                                                            seat-statuscode="0" seat-group="grNum34"
                                                                            seat-code="1L02"
                                                                            style="left: 45.8571px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">2</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L3 일반석" class="sel  p0 grNum34"
                                                                            block-code="p0" data-seat="L3"
                                                                            seat-statuscode="0" seat-group="grNum34"
                                                                            seat-code="1L03"
                                                                            style="left: 70.7143px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">3</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L4 일반석" class="sel  p0 grNum35"
                                                                            block-code="p0" data-seat="L4"
                                                                            seat-statuscode="0" seat-group="grNum35"
                                                                            seat-code="1L04"
                                                                            style="left: 117px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">4</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L5 일반석" class="sel  p0 grNum35"
                                                                            block-code="p0" data-seat="L5"
                                                                            seat-statuscode="0" seat-group="grNum35"
                                                                            seat-code="1L05"
                                                                            style="left: 141.857px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">5</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L6 일반석" class="sel  p0 grNum35"
                                                                            block-code="p0" data-seat="L6"
                                                                            seat-statuscode="0" seat-group="grNum35"
                                                                            seat-code="1L06"
                                                                            style="left: 166.714px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">6</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L7 일반석" class="sel  p0 grNum35"
                                                                            block-code="p0" data-seat="L7"
                                                                            seat-statuscode="0" seat-group="grNum35"
                                                                            seat-code="1L07"
                                                                            style="left: 191.571px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">7</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L8 일반석"
                                                                            class="sel  p0 completed grNum36"
                                                                            block-code="p0" data-seat="L8"
                                                                            seat-statuscode="50" seat-group="grNum36"
                                                                            seat-code="1L08"
                                                                            style="left: 216.429px; top: 278.208px;"><span
                                                                                class="f1 blind"
                                                                                style="top: 0px;">8</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L9 일반석"
                                                                            class="sel  p0 completed grNum36"
                                                                            block-code="p0" data-seat="L9"
                                                                            seat-statuscode="50" seat-group="grNum36"
                                                                            seat-code="1L09"
                                                                            style="left: 241.286px; top: 278.208px;"><span
                                                                                class="f1 blind"
                                                                                style="top: 0px;">9</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L10 일반석" class="sel  p0 grNum37"
                                                                            block-code="p0" data-seat="L10"
                                                                            seat-statuscode="0" seat-group="grNum37"
                                                                            seat-code="1L10"
                                                                            style="left: 266.143px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">10</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L11 일반석" class="sel  p0 grNum37"
                                                                            block-code="p0" data-seat="L11"
                                                                            seat-statuscode="0" seat-group="grNum37"
                                                                            seat-code="1L11"
                                                                            style="left: 291px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">11</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L12 일반석" class="sel  p0 grNum37"
                                                                            block-code="p0" data-seat="L12"
                                                                            seat-statuscode="0" seat-group="grNum37"
                                                                            seat-code="1L12"
                                                                            style="left: 315.857px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">12</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L13 일반석" class="sel  p0 grNum37"
                                                                            block-code="p0" data-seat="L13"
                                                                            seat-statuscode="0" seat-group="grNum37"
                                                                            seat-code="1L13"
                                                                            style="left: 340.714px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">13</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L14 일반석" class="sel  p0 grNum37"
                                                                            block-code="p0" data-seat="L14"
                                                                            seat-statuscode="0" seat-group="grNum37"
                                                                            seat-code="1L14"
                                                                            style="left: 365.571px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">14</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L15 일반석" class="sel  p0 grNum37"
                                                                            block-code="p0" data-seat="L15"
                                                                            seat-statuscode="0" seat-group="grNum37"
                                                                            seat-code="1L15"
                                                                            style="left: 390.429px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">15</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L16 일반석" class="sel  p0 grNum37"
                                                                            block-code="p0" data-seat="L16"
                                                                            seat-statuscode="0" seat-group="grNum37"
                                                                            seat-code="1L16"
                                                                            style="left: 415.286px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">16</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L17 일반석" class="sel  p0 grNum37"
                                                                            block-code="p0" data-seat="L17"
                                                                            seat-statuscode="0" seat-group="grNum37"
                                                                            seat-code="1L17"
                                                                            style="left: 440.143px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">17</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L18 일반석" class="sel  p0 grNum38"
                                                                            block-code="p0" data-seat="L18"
                                                                            seat-statuscode="0" seat-group="grNum38"
                                                                            seat-code="1L18"
                                                                            style="left: 486.429px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">18</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L19 일반석" class="sel  p0 grNum38"
                                                                            block-code="p0" data-seat="L19"
                                                                            seat-statuscode="0" seat-group="grNum38"
                                                                            seat-code="1L19"
                                                                            style="left: 511.286px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">19</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L20 일반석" class="sel  p0 grNum38"
                                                                            block-code="p0" data-seat="L20"
                                                                            seat-statuscode="0" seat-group="grNum38"
                                                                            seat-code="1L20"
                                                                            style="left: 536.143px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">20</span></a><a
                                                                            href="javascript:void(0);"
                                                                            alt="좌석 번호:L21 일반석" class="sel  p0 grNum38"
                                                                            block-code="p0" data-seat="L21"
                                                                            seat-statuscode="0" seat-group="grNum38"
                                                                            seat-code="1L21"
                                                                            style="left: 561px; top: 278.208px;"><span
                                                                                class=" f1"
                                                                                style="top: 0px;">21</span></a></div>
                                                                    <span data-y="54px" data-x="42px" class="w_center"
                                                                        style="top: 54px; left: 336.5px;">상영관 출입문
                                                                        입니다.</span>
                                                                </div>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="mCSB_1_scrollbar_vertical"
                                                class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical"
                                                style="display: none;">
                                                <div class="mCSB_draggerContainer">
                                                    <div id="mCSB_1_dragger_vertical" class="mCSB_dragger"
                                                        style="position: absolute; min-height: 50px; height: 0px; top: 0px;">
                                                        <div class="mCSB_dragger_bar" style="line-height: 50px;"></div>
                                                    </div>
                                                    <div class="mCSB_draggerRail"></div>
                                                </div>
                                            </div>
                                        </div>


                                    </div>

                                    <div class="seat_btm_box">

                                        <div class="seat_type_box">
                                            <div class="top_info">
                                                <span class="seat_type1">선택좌석</span>
                                                <span class="seat_type2">선택가능</span>
                                                <span class="seat_type3">예매완료</span>
                                                <span class="seat_type4">선택불가</span>
                                            </div>
                                            <div class="btm_info">
                                                <span class="seat_type12">거리두기석</span>
                                                <span class="seat_type5">스위트스팟</span>
                                                <span class="seat_type6">씨네커플</span>
                                                <span class="seat_type7">씨네패밀리</span>
                                                <span class="seat_type8">리클라이너석</span>
                                                <span class="seat_type9">장애인석</span>
                                                <span class="seat_type10">발받침</span>
                                                <span class="seat_type11">출입문</span>
                                            </div>

                                        </div>


                                        <div class="notice_box" style="display:none;">
                                            <h6 class="tit_info">이용안내</h6>
                                            <ul class="list_txt ty3">
                                                <li>12세 미만의 고객님(영,유아 포함)은 부모님 또는 보호자를 동반하여도 관람이 불가합니다.</li>
                                                <li>영화 관람 시, 신분증을 지참하여 주시기 바랍니다.</li>
                                            </ul>
                                        </div>

                                    </div>
                                </article>
                            </div>

                        </div>



                        <div id="PersonSeatSummery">
                            <div class="select_seat_result">
                                <div class="group_lft">
                                    <dl class="total_price">
                                        <dt>총 합계</dt>
                                        <dd><strong>0</strong>원</dd>
                                    </dl>
                                </div>
                                <div class="group_rgt"><a href="#" class="btn_col1" id="link_rpay">결제하기</a><a href="#"
                                        class="btn_col3" id="link_lpay"><span
                                            class="ico_lpay"><em>L.pay</em></span>결제하기</a></div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>



            <div id="reserveStep03" class="section_step_con step03 ">
                <h3 class="hidden">결제</h3>

            </div>



            <div id="reserveStep04" class="section_step_con step04 ">
                <h3 class="hidden">결제완료</h3>

            </div>

        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>
.wrap_reserve {
    overflow: hidden;
    position: relative;
    width: 1280px;
    height: 870px;
    margin: 0 auto;
    background-color: #FFF;
}
.hidden {
    overflow: hidden;
    position: absolute !important;
    width: 1px;
    height: 1px;
    clip: rect(1px, 1px, 1px, 1px);
}
h1, h2, h3, h4, h5, h6, address, caption, em, th {
    font-style: normal;
    font-weight: normal;
}
body, h1, h2, h3, h4, h5, h6, fieldset, ul, ol, dl, dt, dd, p, figure {
    margin: 0;
    padding: 0;
}
h2 {
    display: block;
    font-size: 1.5em;
    margin-block-start: 0.83em;
    margin-block-end: 0.83em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
}
.wrap_reserve .section_step_tit {
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    width: 78px;
}
.wrap_reserve .section_step_tit ul {
    height: 100%;
}
.wrap_reserve .section_step_tit ul li.prev {
    border-color: #333;
    border-bottom-color: #666;
    background-color: #333;
}
.wrap_reserve .section_step_tit ul li {
    position: relative;
    height: 25%;
    box-sizing: border-box;
    border: 1px solid #666;
    border-top: none;
    background-color: #FFF;
    z-index: 10;
}
.wrap_reserve .section_step_tit ul li.prev > a {
    color: #FFF;
}
.wrap_reserve .section_step_tit ul li > a .bx_con {
    display: none;
    position: absolute;
    z-index: 1;
    left: 77px;
    top: -1px;
    bottom: 0;
    width: 172px;
    padding-left: 20px;
    border-top: 1px solid #666;
    background-color: #333;
}
.wrap_reserve .section_step_tit ul li.active {
    border-color: #FF243E;
    border-bottom-color: #666;
    background-color: #FF243E;
}
.wrap_reserve .section_step_tit ul li.active > a:before {
    content: "현재 단계";
    overflow: hidden;
    position: absolute;
    width: 1px;
    height: 1px;
    text-indent: -9999em;
}
.wrap_reserve:after {
    content: "";
    display: block;
    clear: both;
}
.wrap_reserve .section_step_con.active {
    display: block;
    overflow: inherit;
    position: relative;
    width: 1202px;
    height: 100%;
    margin-left: 78px;
}
h3 {
    display: block;
    font-size: 1.17em;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
.wrap_reserve .section_step_con .article.article_seat {
    width: 1202px;
}
.wrap_reserve .section_step_con .article .group_top {
    overflow: hidden;
    position: relative;
    height: 55px;
    line-height: 55px;
    border-right: 1px solid #222;
    text-align: center;
    background-color: #000;
}
.wrap_reserve .section_step_con .article .group_top .tit {
    font-size: 18px;
    color: #FFF;
}
h4 {
    display: block;
    margin-block-start: 1.33em;
    margin-block-end: 1.33em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
.wrap_reserve .section_step_con .article .group_top .txt {
    position: absolute;
    top: 5px;
    right: 30px;
    font-size: 13px;
    color: #fff;
}
p {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
.wrap_reserve .section_step_con .article .group_top {
    overflow: hidden;
    position: relative;
    height: 55px;
    line-height: 55px;
    border-right: 1px solid #222;
    text-align: center;
    background-color: #000;
}
.wrap_reserve .section_step_con .article .group_top .txt:before {
    content: "";
    display: inline-block;
    width: 3px;
    height: 3px;
    margin-right: 5px;
    border-radius: 50%;
    vertical-align: middle;
    background: #666;
}
.wrap_reserve .section_step_con .article.article_seat > .inner {
    overflow: hidden;
    height: 815px;
}
.select_num_people_wrap {
    overflow: hidden;
    height: 117px;
    padding: 0 20px;
    background-color: #FFF;
}
.select_num_people_wrap .movie_infor {
    position: relative;
    float: left;
    width: 270px;
    padding: 30px 0 0 54px;
}
body, textarea {
    line-height: 1.1;
    letter-spacing: -0.03em;
}
body, input, button, select, textarea {
    font-family: "Noto Sans KR", "Roboto", "dotum", "sans-serif";
    color: #000;
}
h6 {
    display: block;
    font-size: 0.67em;
    margin-block-start: 2.33em;
    margin-block-end: 2.33em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
.select_num_people_wrap .movie_infor .thm {
    display: block;
    position: absolute;
    top: 26px;
    left: 0;
    overflow: hidden;
    width: 46px;
    height: 65px;
    border-radius: 4px;
}
.select_num_people_wrap .movie_infor .thm img {
    width: 100%;
}
img {
    border: none;
    vertical-align: top;
}
img {
    overflow-clip-margin: content-box;
    overflow: clip;
}
div {
    display: block;
}
.select_num_people_wrap .movie_infor .group_infor .bx_tit {
    display: block;
    margin-bottom: 10px;
}
.select_num_people_wrap .movie_infor .group_infor .bx_tit .ic_grade {
    margin: -5px 4px 0 0;
}
.ic_grade.gr_12 {
    background-color: transparent;
}
.ic_grade {
    display: inline-block;
    width: 22px;
    height: 22px;
    line-height: 22px;
    border-radius: 50%;
    font-size: 0;
    text-align: center;
    vertical-align: middle;
}
.ic_grade.gr_12:before {
    background: #e9b630 url("@/assets/images/common/grade_12.png") 0 0 no-repeat;
    background-size: 100%;
}
.ic_grade.gr_all:before, .ic_grade.gr_12:before, .ic_grade.gr_15:before, .ic_grade.gr_18:before, .ic_grade.gr_non:before, .ic_grade:before {
    content: "";
    display: block;
    width: 22px;
    height: 22px;
    border-radius: 3px;
}
.ic_grade:before {
    margin-left: -1px;
    font-size: 13px;
    font-weight: bold;
    color: #FFF;
    white-space: nowrap;
}
.select_num_people_wrap .movie_infor .group_infor .bx_tit strong {
    font-size: 17px;
}
strong {
    font-weight: bold;
}
.select_num_people_wrap .movie_infor .group_infor dl {
    position: relative;
}
.select_num_people_wrap .movie_infor .group_infor dl dt {
    overflow: hidden;
    position: absolute !important;
    width: 1px;
    height: 1px;
    clip: rect(1px, 1px, 1px, 1px);
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 {
    display: block;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    margin-bottom: 5px;
    font-size: 11px;
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 em {
    font-size: 10px;
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 .time {
    position: relative;
    padding-left: 9px;
    margin-left: 10px;
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 .time:before {
    content: "";
    display: block;
    position: absolute;
    top: 3px;
    left: 0;
    width: 1px;
    height: 10px;
    background: #ddd;
}
.select_num_people_wrap .movie_infor .group_infor dl dt {
    overflow: hidden;
    position: absolute !important;
    width: 1px;
    height: 1px;
    clip: rect(1px, 1px, 1px, 1px);
}
.select_num_people_wrap .movie_infor .group_infor dl dd.sub_info1 {
    display: block;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    margin-bottom: 5px;
    font-size: 11px;
}
.select_num_people_wrap .count_people {
    float: right;
    width: 825px;
    margin-top: 36px;
}
.select_num_people_wrap .count_people > ul > li:first-child {
    margin-left: 0;
}
.select_num_people_wrap .count_people > ul > li {
    float: left;
    margin-left: 20px;
}
.select_num_people_wrap .count_people > ul > li .tit {
    margin-right: 8px;
    font-size: 13px;
}
strong {
    font-weight: bold;
}
.select_num_people_wrap .count_people > ul > li .bx_num {
    display: inline-block;
    width: 100px;
}
.bx_num {
    position: relative;
    border: 1px solid #DDD;
    background-color: #fff;
    box-sizing: border-box;
}
.bx_num .btn_mins {
    left: 0;
    background-image: url('@/assets/images/icon/minu_12.png');
}
.bx_num .btn_plus, .bx_num .btn_mins {
    position: absolute;
    top: 0;
    bottom: 0;
    background: no-repeat 50% 50%;
    border: 0 none;
    padding: 0 15px;
    text-indent: -9999em;
    font: 0 auto;
}
button {
    cursor: pointer;
}
input, select, button {
    vertical-align: middle;
    appearance: none;
}
button {
    writing-mode: horizontal-tb !important;
    text-rendering: auto;
    letter-spacing: normal;
    word-spacing: normal;
    text-transform: none;
    text-shadow: none;
    display: inline-block;
    text-align: center;
    align-items: flex-start;
    box-sizing: border-box;
    margin: 0em;
}
.bx_num .txt_num {
    padding: 11px 0;
    font-family: 'Roboto';
    font-size: 15px;
    font-weight: bold;
    color: #000;
    text-align: center;
    vertical-align: middle;
}
.bx_num .btn_plus {
    right: 0;
    background-image: url('@/assets/images/icon/plus_12.png');
}
.select_num_people_wrap .count_people > ul:after {
    content: "";
    display: block;
    clear: both;
}
.select_seat_wrap {
    overflow: hidden;
    height: 638px;
    color: #FFF;
    background-color: #000;
}
.select_seat_wrap .top_txt_info {
    margin: 15px 0 25px;
    text-align: center;
}
.select_seat_wrap .top_txt_info p {
    position: relative;
    display: inline-block;
    padding-left: 9px;
    color: #fff;
    font-size: 11px;
}
.select_seat_wrap .top_txt_info p:before {
    content: "";
    display: block;
    position: absolute;
    top: 6px;
    left: 0;
    width: 3px;
    height: 1px;
    background: #fff;
}
.seat_wrap {
    padding-top: 0 !important;
}
.select_seat_wrap {
    overflow: hidden;
    height: 638px;
    color: #FFF;
    background-color: #000;
}
.mseat_wrap {
    position: relative;
    background: #000;
    /* padding-top: 150px; */
}
article {
    display: block;
}
.mseat_inner {
    overflow: hidden;
    width: 1200px;
    height: 470px !important;
    max-height: 470px;
    margin: 0 auto;
    box-sizing: border-box;
    padding: 0 15px;
}
.mCustomScrollBox {
    position: relative;
    overflow: hidden;
    height: 100%;
    max-width: 100%;
    outline: none;
    direction: ltr;
}
.mCSB_container.mCS_no_scrollbar_y.mCS_y_hidden {
    margin-right: 0;
}
.mCSB_container {
    overflow: hidden;
    width: auto;
    height: auto;
}
.meat_door_pos {
    position: relative;
}
.mseat_hidden {
    overflow: hidden;
    margin: 0 auto;
}
.mseat_inner .title_screen1 {
    display: block;
    padding: 5px 0 5px;
    color: #fff;
    font-size: 16px;
    font-family: 'Roboto', "Noto Sans KR";
    text-align: center;
    letter-spacing: 15px;
    margin: 0 0 30px;
    background: #6E6E6E;
}
.mseat_inner .floor_bx {
    position: relative;
    padding: 0 0 35px;
    box-sizing: border-box;
}
.mseat_inner .floor_bx .floor_tit {
    display: block;
    /* padding: 5px 0; */
    text-align: right;
    color: #fff;
    font-size: 17px;
    font-weight: bold;
    font-family: 'Roboto', "Noto Sans KR";
}
.mseat_inner .floor_bx .seat_area {
    position: relative;
    margin: 10px auto 0;
}
element.style {
    margin-top: 30px;
    width: 581px;
    height: 298.208px;
}element.style {
    left: -30px;
    top: 0px;
}
.seat_tit {
    position: absolute;
    display: inline-block;
    width: 20px;
    height: 16px;
    line-height: 16px;
    text-align: left;
    font-size: 11px;
    font-weight: bold;
    font-family: 'Roboto', "Noto Sans KR";
    color: #fff;
}
.mseat_inner .floor_bx .seat_area .sel.no_select {
    width: 21px;
    height: 16px;
    background: url(@/assets/images/common/seat_no_select.png) no-repeat center center;
    cursor: inherit;
}
.mseat_inner .floor_bx .seat_area .sel {
    position: absolute;
    font-size: 10px;
    outline-color: white;
}
a, a:link, a:visited, a:active, a:hover {
    color: #000;
    text-decoration: none;
}
.mseat_inner .floor_bx .seat_area .sel.no_select:not(.disabled):not(.on) span {
    overflow: hidden;
    text-indent: -7000000em;
    font-size: 0;
    line-height: 0;
    cursor: inherit;
    background: #e8e8e8 url(@/assets/images/common/seat_no_select.png) no-repeat center center;
    background-size: 28px 21px;
    opacity: 0.5;
}

.mseat_inner .floor_bx .seat_area .sel > .handicapped {
    color: #fff;
}
.mseat_inner .floor_bx .seat_area .sel > span {
    position: relative;
    display: block;
    width: 21px;
    height: 16px;
    text-align: center;
    letter-spacing: -0.5px;
    box-sizing: border-box;
    border-radius: 7px 7px 2px 0px;
    font-family: 'Roboto', "Noto Sans KR";
}
.mseat_inner .floor_bx .seat_area .sel.no_select:not(.disabled):not(.on) span:before {
    content: '';
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    width: 21px;
    height: 16px;
    background: #000;
    opacity: 0.5;
}
.mseat_inner .floor_bx .seat_area .sel {
    position: absolute;
    font-size: 10px;
    outline-color: white;
}
.mseat_inner .floor_bx .seat_area .sel > span {
    position: relative;
    display: block;
    overflow: hidden;
    width: 21px;
    height: 16px;
    line-height: 17px;
    background: #E8E8E8;
    color: #000;
    font-size: 10px;
    text-align: center;
    letter-spacing: -0.5px;
    box-sizing: border-box;
    border-radius: 7px 7px 2px 0px;
    font-family: 'Roboto', "Noto Sans KR";
}
.mseat_inner .floor_bx .seat_area .sel > .foot_seat:after {
    content: '';
    display: block;
    position: absolute;
    top: 3px;
    left: 50%;
    width: 9px;
    height: 1px;
    background: #A1A1A1;
    -webkit-transform: translate(-50%, 0);
    transform: translate(-50%, 0);
}
.mseat_inner .floor_bx .seat_area .sel.on span {
    background: #FF243E;
    color: #fff;
}


</style>