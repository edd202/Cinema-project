<template>
  <div>
    <div id="layerReserveStep01" class="layer_wrap layer_reserve layer_reserve01_sum2 active" tabindex="0"
            style="left: 50%; top: 50%; margin-top: -257px; margin-left: -250px;">
            <div class="layer_header">
                <h4 class="hidden">예매 내용 확인 팝업</h4>
                <div id="stepOnePopupHeaderScreen"><strong>18:20~21:42(6관)</strong></div>
                <button type="button" class="btn_close" onclick="closeScreenLayer()">팝업 닫기</button>
            </div>
            <div class="layer_contents">
                <div class="seat_infor_sum">
                    <div class="group_top" id="stepOnePopupContentsSeatCount">
                        <dl>
                            <dt>잔여좌석</dt>
                            <dd><strong>68</strong>/80</dd>
                        </dl>
                    </div>
                    <div class="group_con screen_preview_box">
                        <div class="screen_preview_con">
                            <div class="screen_info cineCont">
                                <!-- 개발영역 -->
                                <div class="smallScreen">
                                    <span class="title_screen1">SCREEN</span>
                                    <!-- 미니맵 -->
                                    <div class="minimap" id="stepOnePopupMiniMap">
                                        <div class="floor_bx seatSet1"> <span class="floor_tit"
                                                style="display: none;">-1F</span>
                                            <div class="seat_area" style="width: 74.8235px; height: 40.4px;"><a
                                                    style="left: 0px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A1" seat-group="grNum1" alt="A1 "
                                                    seat-code="1A01"> <span class=" f1">1</span></a><a
                                                    style="left: 7.6471px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A2" seat-group="grNum1" alt="A2 "
                                                    seat-code="1A02"> <span class=" f2">2</span></a><a
                                                    style="left: 15.2941px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A3" seat-group="grNum1" alt="A3 "
                                                    seat-code="1A03"> <span class=" f2">3</span></a><a
                                                    style="left: 22.9412px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A4" seat-group="grNum1" alt="A4 "
                                                    seat-code="1A04"> <span class=" f2">4</span></a><a
                                                    style="left: 30.5882px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A5" seat-group="grNum1" alt="A5 "
                                                    seat-code="1A05"> <span class=" f2">5</span></a><a
                                                    style="left: 38.2353px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A6" seat-group="grNum1" alt="A6 "
                                                    seat-code="1A06"> <span class=" f2">6</span></a><a
                                                    style="left: 45.8824px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A7" seat-group="grNum1" alt="A7 "
                                                    seat-code="1A07"> <span class=" f2">7</span></a><a
                                                    style="left: 53.5294px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A8" seat-group="grNum1" alt="A8 "
                                                    seat-code="1A08"> <span class=" f2">8</span></a><a
                                                    style="left: 61.1765px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A9" seat-group="grNum1" alt="A9 "
                                                    seat-code="1A09"> <span class=" f2">9</span></a><a
                                                    style="left: 68.8235px; top: 0px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="A10" seat-group="grNum1" alt="A10 "
                                                    seat-code="1A10"> <span class=" f2">10</span></a><a
                                                    style="left: 0px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B1" seat-group="grNum2" alt="B1 "
                                                    seat-code="1B01"> <span class=" f1">1</span></a><a
                                                    style="left: 7.6471px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B2" seat-group="grNum2" alt="B2 "
                                                    seat-code="1B02"> <span class=" f2">2</span></a><a
                                                    style="left: 15.2941px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B3" seat-group="grNum2" alt="B3 "
                                                    seat-code="1B03"> <span class=" f2">3</span></a><a
                                                    style="left: 22.9412px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B4" seat-group="grNum2" alt="B4 "
                                                    seat-code="1B04"> <span class=" f2">4</span></a><a
                                                    style="left: 30.5882px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B5" seat-group="grNum2" alt="B5 "
                                                    seat-code="1B05"> <span class=" f2">5</span></a><a
                                                    style="left: 38.2353px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B6" seat-group="grNum2" alt="B6 "
                                                    seat-code="1B06"> <span class=" f2">6</span></a><a
                                                    style="left: 45.8824px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B7" seat-group="grNum2" alt="B7 "
                                                    seat-code="1B07"> <span class=" f2">7</span></a><a
                                                    style="left: 53.5294px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B8" seat-group="grNum2" alt="B8 "
                                                    seat-code="1B08"> <span class=" f2">8</span></a><a
                                                    style="left: 61.1765px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B9" seat-group="grNum2" alt="B9 "
                                                    seat-code="1B09"> <span class=" f2">9</span></a><a
                                                    style="left: 68.8235px; top: 5.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="B10" seat-group="grNum2" alt="B10 "
                                                    seat-code="1B10"> <span class=" f2">10</span></a><a
                                                    style="left: 0px; top: 10.4px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="C1"
                                                    seat-group="grNum3" alt="C1  예매완료좌석" seat-code="1C01"> <span
                                                        class="f1 completed">1</span></a><a
                                                    style="left: 7.6471px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C2" seat-group="grNum3" alt="C2 "
                                                    seat-code="1C02"> <span class=" f2">2</span></a><a
                                                    style="left: 15.2941px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C3" seat-group="grNum3" alt="C3 "
                                                    seat-code="1C03"> <span class=" f2">3</span></a><a
                                                    style="left: 22.9412px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C4" seat-group="grNum3" alt="C4 "
                                                    seat-code="1C04"> <span class=" f2">4</span></a><a
                                                    style="left: 30.5882px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C5" seat-group="grNum3" alt="C5 "
                                                    seat-code="1C05"> <span class=" f2">5</span></a><a
                                                    style="left: 38.2353px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C6" seat-group="grNum3" alt="C6 "
                                                    seat-code="1C06"> <span class=" f2">6</span></a><a
                                                    style="left: 45.8824px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C7" seat-group="grNum3" alt="C7 "
                                                    seat-code="1C07"> <span class=" f2">7</span></a><a
                                                    style="left: 53.5294px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C8" seat-group="grNum3" alt="C8 "
                                                    seat-code="1C08"> <span class=" f2">8</span></a><a
                                                    style="left: 61.1765px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C9" seat-group="grNum3" alt="C9 "
                                                    seat-code="1C09"> <span class=" f2">9</span></a><a
                                                    style="left: 68.8235px; top: 10.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="C10" seat-group="grNum3" alt="C10 "
                                                    seat-code="1C10"> <span class=" f2">10</span></a><a
                                                    style="left: 0px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D1" seat-group="grNum4" alt="D1 "
                                                    seat-code="1D01"> <span class=" f1">1</span></a><a
                                                    style="left: 7.6471px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D2" seat-group="grNum4" alt="D2 "
                                                    seat-code="1D02"> <span class=" f2">2</span></a><a
                                                    style="left: 15.2941px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D3" seat-group="grNum4" alt="D3 "
                                                    seat-code="1D03"> <span class=" f2">3</span></a><a
                                                    style="left: 22.9412px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D4" seat-group="grNum4" alt="D4 "
                                                    seat-code="1D04"> <span class=" f2">4</span></a><a
                                                    style="left: 30.5882px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D5" seat-group="grNum4" alt="D5 "
                                                    seat-code="1D05"> <span class=" f2">5</span></a><a
                                                    style="left: 38.2353px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D6" seat-group="grNum4" alt="D6 "
                                                    seat-code="1D06"> <span class=" f2">6</span></a><a
                                                    style="left: 45.8824px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D7" seat-group="grNum4" alt="D7 "
                                                    seat-code="1D07"> <span class=" f2">7</span></a><a
                                                    style="left: 53.5294px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D8" seat-group="grNum4" alt="D8 "
                                                    seat-code="1D08"> <span class=" f2">8</span></a><a
                                                    style="left: 61.1765px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D9" seat-group="grNum4" alt="D9 "
                                                    seat-code="1D09"> <span class=" f2">9</span></a><a
                                                    style="left: 68.8235px; top: 15.6px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="D10" seat-group="grNum4" alt="D10 "
                                                    seat-code="1D10"> <span class=" f2">10</span></a><a
                                                    style="left: 0px; top: 20.8px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="E1" seat-group="grNum5" alt="E1 "
                                                    seat-code="1E01"> <span class=" f1">1</span></a><a
                                                    style="left: 7.6471px; top: 20.8px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="E2" seat-group="grNum5" alt="E2 "
                                                    seat-code="1E02"> <span class=" f2">2</span></a><a
                                                    style="left: 15.2941px; top: 20.8px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="E3"
                                                    seat-group="grNum5" alt="E3  예매완료좌석" seat-code="1E03"> <span
                                                        class="f2 completed">3</span></a><a
                                                    style="left: 22.9412px; top: 20.8px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="E4"
                                                    seat-group="grNum5" alt="E4  예매완료좌석" seat-code="1E04"> <span
                                                        class="f2 completed">4</span></a><a
                                                    style="left: 30.5882px; top: 20.8px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="E5" seat-group="grNum5" alt="E5 "
                                                    seat-code="1E05"> <span class=" f2">5</span></a><a
                                                    style="left: 38.2353px; top: 20.8px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="E6" seat-group="grNum5" alt="E6 "
                                                    seat-code="1E06"> <span class=" f2">6</span></a><a
                                                    style="left: 45.8824px; top: 20.8px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="E7" seat-group="grNum5" alt="E7 "
                                                    seat-code="1E07"> <span class=" f2">7</span></a><a
                                                    style="left: 53.5294px; top: 20.8px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="E8" seat-group="grNum5" alt="E8 "
                                                    seat-code="1E08"> <span class=" f2">8</span></a><a
                                                    style="left: 61.1765px; top: 20.8px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="E9" seat-group="grNum5" alt="E9 "
                                                    seat-code="1E09"> <span class=" f2">9</span></a><a
                                                    style="left: 68.8235px; top: 20.8px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="E10"
                                                    seat-group="grNum5" alt="E10  예매완료좌석" seat-code="1E10"> <span
                                                        class="f2 completed">10</span></a><a
                                                    style="left: 0px; top: 26px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="F1" seat-group="grNum6" alt="F1 "
                                                    seat-code="1F01"> <span class=" f1">1</span></a><a
                                                    style="left: 7.6471px; top: 26px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="F2" seat-group="grNum6" alt="F2 "
                                                    seat-code="1F02"> <span class=" f2">2</span></a><a
                                                    style="left: 15.2941px; top: 26px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="F3" seat-group="grNum6" alt="F3 "
                                                    seat-code="1F03"> <span class=" f2">3</span></a><a
                                                    style="left: 22.9412px; top: 26px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="F4" seat-group="grNum6" alt="F4 "
                                                    seat-code="1F04"> <span class=" f2">4</span></a><a
                                                    style="left: 30.5882px; top: 26px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="F5"
                                                    seat-group="grNum6" alt="F5  예매완료좌석" seat-code="1F05"> <span
                                                        class="f2 completed">5</span></a><a
                                                    style="left: 38.2353px; top: 26px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="F6"
                                                    seat-group="grNum6" alt="F6  예매완료좌석" seat-code="1F06"> <span
                                                        class="f2 completed">6</span></a><a
                                                    style="left: 45.8824px; top: 26px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="F7" seat-group="grNum6" alt="F7 "
                                                    seat-code="1F07"> <span class=" f2">7</span></a><a
                                                    style="left: 53.5294px; top: 26px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="F8" seat-group="grNum6" alt="F8 "
                                                    seat-code="1F08"> <span class=" f2">8</span></a><a
                                                    style="left: 61.1765px; top: 26px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="F9" seat-group="grNum6" alt="F9 "
                                                    seat-code="1F09"> <span class=" f2">9</span></a><a
                                                    style="left: 68.8235px; top: 26px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="F10" seat-group="grNum6" alt="F10 "
                                                    seat-code="1F10"> <span class=" f2">10</span></a><a
                                                    style="left: 0px; top: 31.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="G1" seat-group="grNum7" alt="G1 "
                                                    seat-code="1G01"> <span class=" f1">1</span></a><a
                                                    style="left: 7.6471px; top: 31.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="G2" seat-group="grNum7" alt="G2 "
                                                    seat-code="1G02"> <span class=" f2">2</span></a><a
                                                    style="left: 15.2941px; top: 31.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="G3" seat-group="grNum7" alt="G3 "
                                                    seat-code="1G03"> <span class=" f2">3</span></a><a
                                                    style="left: 22.9412px; top: 31.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="G4" seat-group="grNum7" alt="G4 "
                                                    seat-code="1G04"> <span class=" f2">4</span></a><a
                                                    style="left: 30.5882px; top: 31.2px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="G5"
                                                    seat-group="grNum7" alt="G5  예매완료좌석" seat-code="1G05"> <span
                                                        class="f2 completed">5</span></a><a
                                                    style="left: 38.2353px; top: 31.2px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="G6"
                                                    seat-group="grNum7" alt="G6  예매완료좌석" seat-code="1G06"> <span
                                                        class="f2 completed">6</span></a><a
                                                    style="left: 45.8824px; top: 31.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="G7" seat-group="grNum7" alt="G7 "
                                                    seat-code="1G07"> <span class=" f2">7</span></a><a
                                                    style="left: 53.5294px; top: 31.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="G8" seat-group="grNum7" alt="G8 "
                                                    seat-code="1G08"> <span class=" f2">8</span></a><a
                                                    style="left: 61.1765px; top: 31.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="G9" seat-group="grNum7" alt="G9 "
                                                    seat-code="1G09"> <span class=" f2">9</span></a><a
                                                    style="left: 68.8235px; top: 31.2px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="G10" seat-group="grNum7" alt="G10 "
                                                    seat-code="1G10"> <span class=" f2">10</span></a><a
                                                    style="left: 0px; top: 36.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="H1" seat-group="grNum8" alt="H1 "
                                                    seat-code="1H01"> <span class=" f1">1</span></a><a
                                                    style="left: 7.6471px; top: 36.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="H2" seat-group="grNum8" alt="H2 "
                                                    seat-code="1H02"> <span class=" f2">2</span></a><a
                                                    style="left: 15.2941px; top: 36.4px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="H3"
                                                    seat-group="grNum8" alt="H3  예매완료좌석" seat-code="1H03"> <span
                                                        class="f2 completed">3</span></a><a
                                                    style="left: 22.9412px; top: 36.4px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="H4"
                                                    seat-group="grNum8" alt="H4  예매완료좌석" seat-code="1H04"> <span
                                                        class="f2 completed">4</span></a><a
                                                    style="left: 30.5882px; top: 36.4px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="H5"
                                                    seat-group="grNum8" alt="H5  예매완료좌석" seat-code="1H05"> <span
                                                        class="f2 completed">5</span></a><a
                                                    style="left: 38.2353px; top: 36.4px;" href="#none"
                                                    class="sel  p0 completed" block-code="p0" data-seat="H6"
                                                    seat-group="grNum8" alt="H6  예매완료좌석" seat-code="1H06"> <span
                                                        class="f2 completed">6</span></a><a
                                                    style="left: 45.8824px; top: 36.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="H7" seat-group="grNum8" alt="H7 "
                                                    seat-code="1H07"> <span class=" f2">7</span></a><a
                                                    style="left: 53.5294px; top: 36.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="H8" seat-group="grNum8" alt="H8 "
                                                    seat-code="1H08"> <span class=" f2">8</span></a><a
                                                    style="left: 61.1765px; top: 36.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="H9" seat-group="grNum8" alt="H9 "
                                                    seat-code="1H09"> <span class=" f2">9</span></a><a
                                                    style="left: 68.8235px; top: 36.4px;" href="#none" class="sel  p0"
                                                    block-code="p0" data-seat="H10" seat-group="grNum8" alt="H10 "
                                                    seat-code="1H10"> <span class=" f2">10</span></a></div>
                                        </div>
                                    </div>
                                    <!-- //미니맵 -->
                                </div>
                                <!-- //개발영역 -->
                            </div>
                        </div>
                    </div>
                    <div class="group_btm" id="stepOnePopupContentsMsg">
                        <div class="txt_notice gr_12">
                            <p class="tit"><span class="ic_grade gr_12">만 12세 이상 관람가</span>본 영화는 <strong>만 12세 이상
                                    관람가</strong> 영화입니다.</p>
                            <p class="txt">만 12세 미만의 고객님(영, 유아 포함)은 반드시 부모님 또는 성인 보호자의 동반하에<br>관람이 가능합니다. 연령 확인 불가 시 입장이
                                제한될 수 있습니다.</p>
                        </div>
                    </div>
                </div>
                <div class="btn_btm_wrap" id="stepOnePopupContentsButton"><a class="btn_col2 ty5" href="#none">취소</a><a
                        class="btn_col1 ty5" href="#none">인원/좌석 선택</a></div>
            </div>
        </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
element.style {
    left: 50%;
    top: 50%;
    margin-top: -295.5px;
    margin-left: -250px;
}
.layer_wrap.active.layer_reserve01_sum2 {
    width: 500px;
}
.layer_wrap.layer_reserve {
    box-sizing: border-box;
    border-radius: 0;
    border: 1px solid #666;
    background-color: #FFF;
}
.layer_wrap.active {
    display: block;
    position: fixed;
    z-index: 100;
    height: auto;
    border-radius: 10px;
    background-color: #FFF;
}
.layer_wrap {
    overflow: hidden;
    position: absolute;
    z-index: -1;
    width: 0px;
    height: 0px;
    border: none;
}.layer_wrap.layer_reserve .layer_header {
    border-bottom: 1px solid #666;
    color: #FFF;
    background-color: #666;
}
.layer_wrap .layer_header {
    position: relative;
    height: 54px;
    line-height: 58px;
    border-bottom: 1px solid #CCC;
    color: #FFF;
    text-align: center;
}
.layer_wrap.layer_reserve .layer_header strong {
    font-size: 18px;
}
strong {
    font-weight: bold;
}
.layer_wrap.layer_reserve .layer_header .btn_close {
    background-image: url("@/assets/images/icon/close_19_wht.png");
}
.layer_wrap .layer_header .btn_close {
    position: absolute;
    right: 15px;
    top: 15px;
    width: 19px;
    height: 19px;
    border: none;
    text-indent: -9999em;
    background: transparent url("@/assets/images/icon/close_19_wht.png") no-repeat 0 0;
    outline-color: black;
}
.seat_infor_sum {
    text-align: center;
}
.seat_infor_sum .group_top dl dt {
    display: inline-block;
    font-size: 16px;
}
.seat_infor_sum .group_top dl dd strong {
    font-family: 'Roboto';
    font-size: 22px;
    font-weight: bold;
}
.seat_infor_sum .group_con {
    padding: 20px 0;
}
.screen_preview_box .smallScreen {
    width: 100%;
    height: 140px;
    position: relative;
    top: 0;
    left: 0;
    background: #fff;
    padding: 0;
}
.screen_preview_box .smallScreen .title_screen1 {
    position: relative;
    z-index: 1;
    color: #bdbdbd;
    letter-spacing: 5px;
    background: none;
}
.smallScreen .title_screen1 {
    display: block;
    text-align: center;
}
.screen_preview_box .minimap {
    position: relative;
    top: 50%;
    transform: translate(0, -50%);
}
.screen_preview_box .smallScreen .floor_bx {
    position: relative;
    margin: 10px 0;
    padding: 5px 0;
}
.screen_preview_box .smallScreen .floor_bx .floor_tit {
    background: #fff;
    color: #000;
    font-size: 12px;
    font-family: 'Roboto', "Noto Sans KR";
    text-align: right;
}
element.style {
    width: 78.0968px;
    height: 48.9384px;
}
.smallScreen .floor_bx .seat_area {
    position: relative;
    margin: 5px auto 0;
}
element.style {
    left: 0px;
    top: 0px;
}

.smallScreen .floor_bx .seat_area .sel {
    position: absolute;
}

a, a:link, a:visited, a:active, a:hover {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
.screen_preview_box .smallScreen .floor_bx .seat_area .sel > span {
    background: #000 none !important;
}
.smallScreen .floor_bx .seat_area .sel > span {
    overflow: hidden;
    display: block;
    width: 4px;
    height: 3px;
    background: #fff;
    line-height: 0;
    font-size: 0;
    text-indent: -7000000em;
    border-radius: 1px 1px 0 0;
}
.txt_notice {
    padding: 18px 0 26px;
    text-align: center;
}
.seat_infor_sum .group_btm .txt_notice .tit {
    margin-bottom: 5px;
    font-size: 13px;
    color: #111;
    line-height: 1.3;
}
body, h1, h2, h3, h4, h5, h6, fieldset, ul, ol, dl, dt, dd, p, figure {
    margin: 0;
    padding: 0;
}
p {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
.ic_grade.gr_12:before {
    background: #e9b630 url("@/assets/images/common/grade_12.png") 0 0 no-repeat;
    background-size: 100%;
}
.seat_infor_sum .group_btm .txt_notice.gr_12 strong {
    border-bottom: 1px solid #e9b630;
    color: #e9b630;
}
.seat_infor_sum .group_btm .txt_notice .txt {
    line-height: 1.4;
    font-size: 11px;
    color: #666;
    letter-spacing: -0.5px;
}
.layer_wrap .btn_btm_wrap {
    margin: 30px 0 20px;
}
.btn_btm_wrap {
    margin: 60px 0;
    text-align: center;
}
.btn_col1.ty5, .btn_col2.ty5, .btn_col3.ty5, .btn_col4.ty5, .btn_col5.ty5, .btn_col6.ty5, .btn_col7.ty5, .btn_col8.ty5, .btn_col9.ty5, .btn_col10.ty5 {
    height: 40px;
    line-height: 38px;
    padding: 0 18px;
}
.btn_col2 {
    display: inline-block;
    box-sizing: border-box;
    border-radius: 4px;
    border: 1px solid #414141;
    font-size: 14px;
    color: #ffffff !important;
    text-align: center;
    vertical-align: middle;
    background-color: #414141;
}
.btn_col1 {
    display: inline-block;
    box-sizing: border-box;
    border-radius: 4px;
    border: 1px solid #FF243E;
    font-size: 14px;
    color: #ffffff !important;
    text-align: center;
    vertical-align: middle;
    background-color: #FF243E;
}


</style>