enumeration:
  Indicator:
    fields:
      msg: String

    enum:
      Y: "YES"
      N: "NO"

  AaccidentType:
    fields:
      msg: string
    enum:
      D: "피해"
      I: "가해"
      S: "단독"
      R: "정비"
      E: "기타"
      U: "미정"

  NoWayType:
    fields:
      msg: string
    enum:
      R: "수리만진행"
      A: "지역변경"
      E: "기타"

  StatusType:
    fields:
      msg: string
    enum:
      CONFIRM: "확정"
      CHANGE: "변경"
      FAIL: "불가"




entity:

  AuditEntity:
    meta: entity @abstract
    fields:
      createDate: datetime
      createdBy: string
      lastModifiedBy: string
      lastModifiedDate: datetime

  PictureInfo:
    fields:
      uri: string
      fileName: string
      type: string


  CallInfo:
    meta: entity
    fields:
      seq: long @id
      callDate: date
      callTime: time


  LoanCarProcess:
    meta: entity @template(AuditEntity)
    fields:
      seq: long @id
      loanCar: LoanCar
      lcFixCode: StatusType




  LoanCar:
    meta: entity @template(AuditEntity)
    fields:
      lcCode: long @id @label('아이디')
      customName: string @label('고객명')
      customPh: string @label('연락처')
      alarmStatus: Indicator @label('콜센터에서 링크원으로 보낸 알람 수신') @default('N')
      lcReceiveLocation: string @label('입고지')
      content: text @label('문자내용')
      carModel: string @label('차량모델')
      carNumber: string @label('차량번호')
      memo: text @label('메모')
      insuranceCode: string @label('보험사 접수번호')
      lcRepairShopName: string @label('미제휴 공장명')
      lcRepairShopReason: string @label('미제휴 공장기입 사유')
      accidentType: AaccidentType @label('사고타입 피해:D 가해:I 단독:S 정비:R 기타:E 미정:U') @default('U')
      lcReason: string @label('대차미진행 사유')

      rentAlarmStatus: Indicator @label('링크원에서 렌터카으로 보낸 알람 수신') @default('N')
      lcView: Indicator @label('목록 노출') @default('Y')
      noWayType: NoWayType @label('대차미진행타입 수리만진행:R 지역변경:A 기타:E')

      agentName: string @label('소개처(고객사기타)')
      agentPicName: string @label('영업담당자')
      areaName: string @label('지역(고객사기타)')
      capitalName: string @label('고객사명(고객사기타)')
      smsParseType: Indicator @label('문자파싱 성공여부')

      loanCarPic: LoanCarPic @relation('ManyToOne') @joinColumn('lpic_code')
      capital: Capital
      picInfo: PictureInfo @embedded


      lcFixCode: StatusType

      callInfos: List<CallInfo>
      processHistory: List<LoanCarProcess>




  CourtesyCarRequest:
    meta: entity @template(AuditEntity)
    fields:
      id: long @id @label('아이디')
      receptNo: string @label('대차접수번호') @required
      empNo: string @label('요청상담사') @required
      rentKind: string @label('대차용도') @required
      rentCarCode: string @label('대차차량코드')
      rentCarFixCode: string @label('대차확정코드')
      rentCarNm: string @label('대차차량명')
      rentCost: string @label('대차비용')
      insurNm: string @label('보험사')
      indeMoney: string @label('면책금')
      regDate: string @label('신청일') @required
      regTime: string @label('신청시간') @required
      joincode: string @label('제휴사') @required
      custNm: string @label('고객명') @required
      custTel: string @label('연락처') @required
      vipYn: string @label('VIP여부') @required
      bigo: string @label('특이사항')
      reqDate: string @label('요청일자') @required
      reqTime: string @label('요청시간') @required
      reqZip: string @label('요청장소우편번호')
      reqAddr: string @label('요청장소주소')
      reqDaddr: string @label('요청장소상세주소')
      consignYn: string @label('차량탁송여부')
      consignOfficeNm: string @label('탁송장소(공장명)')
      consignZip: string @label('탁송장소(우편번호)')
      consignAddr: string @label('탁송장소(주소)')
      consignDaddr: string @label('탁송장소(상세주소)')
      consignTel: string @label('탁송장소(전화번호)')


