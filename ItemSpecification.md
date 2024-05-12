# 소프트웨어 공학 4조 팀프로젝트 아이템 명세서


## 1. 아이템 목록                  

| 아이템                   | 외관                                                                              | 설명               | 등장확률 |
|-----------------------|---------------------------------------------------------------------------------|------------------|:----:|
| 세로줄 폭탄(Vertical Bomb) | <img src="/src/main/resources/images/itemVerticalBomb.jpg" width="25px" height="25px"> | 설치된 부분의 세로줄 삭제   | 24%  |
| 폭탄(Bomb)              | <img src="/src/main/resources/images/itemBomb.jpg" width="25px" height="25px"> | 설치된 부분 기준 3x3 삭제 | 27%  |
| 핵폭탄(Nuclear)         | <img src="/src/main/resources/images/itemNuclear.jpg" width="25px" height="25px"> | 보드의 모든 블럭 삭제     |  1%  |
***
## 2. 아이템 상세

### 2-1. 세로줄 폭탄(Vertical Bomb) <img src="/src/main/resources/images/itemVerticalBomb.jpg" width="25px" height="25px">
    * 세로줄 폭탄(Vertical Bomb) 아이템은 붉은색 V가 그려진 1x1 크기의 블럭입니다.
        * 1x1 블럭이기에 회전이 불가합니다.
    * 블럭이 더 이상 움직이지 못하게 되면(바닥이나 다른 블럭에 닿으면), 해당 x좌표의 세로줄을 전부 삭제합니다.
    * 24%의 확률로 등장합니다.
    * 이 효과로 인해 지워진 블럭에 대한 점수는 없습니다.
***
### 2-2. 폭탄(Bomb) <img src="/src/main/resources/images/itemBomb.jpg" width="25px" height="25px">
    * 폭탄(Bomb) 아이템은 붉은색 B가 그려진 1x1 크기의 블럭입니다.
        * 1x1 블럭이기에 회전이 불가합니다.
    * 블럭이 더 이상 움직이지 못하게 되면(바닥이나 다른 블럭에 닿으면), 해당 좌표를 중심으로 하는 3x3 지역의 블록을 삭제합니다.
    * 27%의 확률로 등장합니다.
    * 이 효과로 인해 지워진 블럭에 대한 점수는 없습니다.
***
### 2-3. 핵폭탄(Nuclear) <img src="/src/main/resources/images/itemNuclear.jpg" width="25px" height="25px">
    * 핵폭탄(Nuclear) 아이템은 붉은색 N이 그려진 1x1 크기의 블럭입니다.
        * 1x1 블럭이기에 회전이 불가합니다.
    * 블럭이 더 이상 움직이지 못하게 되면(바닥이나 다른 블럭에 닿으면), 보드에 존재하는 모든 블럭을 삭제합니다.
    * 1%의 확률로 등장합니다.
    * 이 효과로 인해 지워진 블럭에 대한 점수는 없습니다.
    * 이 효과로 인해 지워진 블럭들은 아이템 생성을 위해 지워야 하는 줄 수 및 배틀 모드에서 상대방에게 공격을 가하기 위한 줄 수에 포함되지 않습니다.