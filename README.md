# 무인 물품 보관함 서비스

## 기능
1. 물품 보관하기
   - 보관함의 현황을 보여준다.
     - 모든 보관함이 차 있으면 물품 보관 자체가 선택 불가능하도록 해야 한다.
   - 비어 있는 보관함 중 하나를 선택한다
   - 사용 중인 보관함을 선택하면 다시 입력 받는다.
   - 보관함의 아이디와 자동 생성된 암호를 보여주고 확인 받는다.
2. 보관한 물품 찾기
   - 보관함 중 하나를 선택한다.
     - 모든 보관함이 비어 있을 때 물품 찾기 자체가 선택 불가능하도록 해야 한다.
   - 비어 있는 보관함을 선택하면 다시 입력 받는다.
   - 암호를 입력받고 확인한다.
      - 암호를 틀리면 메인으로 돌아가도록 한다.
   - 잠금이 풀렸다는 메시지와 요금을 계산하여 출력한다.

## 동작 과정
### 1. 정상 흐름 - 물품 보관
  <img width="300" alt="Image" src="https://github.com/user-attachments/assets/d747ad63-643b-4f6a-aba6-8e07841010e4" />

### 2. 정상 흐름 - 물품 회수
   <img width="310" alt="Image" src="https://github.com/user-attachments/assets/14319010-5f01-4682-838f-0b4d75820170" />

### 3. 정상 흐름 - 종료
   <img width="260" alt="Image" src="https://github.com/user-attachments/assets/35c65e79-7144-4755-942d-b938e3fe48fb" />

### 4. 모든 보관함이 비었을 때 회수
   <img width="180" alt="Image" src="https://github.com/user-attachments/assets/12fad3d5-2053-4957-ba6a-86cc6031ea08" />

### 5. 물품 회수시 이미 빈 보관함을 선택
   <img width="320" alt="Image" src="https://github.com/user-attachments/assets/8a4e2087-e323-4e39-a191-f57ae8a6a6bb" />

### 6. 물품 보관시 이미 사용 중인 보관함을 선택
   <img width="300" alt="Image" src="https://github.com/user-attachments/assets/dd56665a-abee-4a47-9c26-ace4a55e7738" />

### 7. 잘못된 입력일 때 - 수가 아닐 때
   <img width="300" alt="Image" src="https://github.com/user-attachments/assets/782c9a9b-ffac-43cb-b968-a5eda5e52562" />

### 8. 잘못된 입력일 때 - 범위를 넘었을 때
   <img width="280" alt="Image" src="https://github.com/user-attachments/assets/ff8e382a-700b-4662-850a-b1ec1fc5b35f" />

### 9.암호가 틀렸을 때
   <img width="280" alt="Image" src="https://github.com/user-attachments/assets/79521d9f-8ffe-4f27-a301-77c62094c800" />

## 클래스 다이어그램

