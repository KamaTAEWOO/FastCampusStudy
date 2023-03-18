# FastCampusStudy-1
## 2023-03-07 ~ 2023-03-12

## PART1
=========================================
### 숫자세기 앱 (chapter02)
1. 구현기능
- (+)버튼을 클릭 시, 숫자를 1씩 올림
- 초기화 버튼을 클릭 시 숫자를 0

=========================================
### 단위 변환기 앱 (chapter03)
1. 구현기능
-  cm -> m
- 값을 입력하면, 바로 변환된 값이 노출
- 단위를 반대로 변경
- 단위 변환 연산
  - cm -> m (x 0.01)
 - m -> cm (x 100)

2. 몰랐던 것
- constraintlayout
 - constraintHorizontal_bias = "1.0" // 0이면 시작점, 1이면 끝점(범위는 0 ~ 1까지)
- @+id 와 @id의 차이점
 - +는 추가한다는 뜻이고, +가 없다면 참조를 하겠다라는 뜻임.
- inflate(불러일으키다.)
 - 화면을 만들겠다.
 - 화면을 만들 때 반드시 필요한 것은 layoutInflater
- lateinit
 - 언젠간 반드시 할당할 변수에 붙여줌
- Databinding을 사용해야하는 이유
 - xml마다 중복으로 id사용할 수 있어서 
 - 다른  xml의 id를 가지고 올 수 있음. 동작을 안 함.
- try~catch 사용시
 - 어떤 케이스의 에러가 날 지 예상이 된다면 해당 케이스의 에러를 지정해주는 것이 좋음
- onSaveInstanceState -> 저장 함수
- onRestoreInstanceState -> 불러오는 함수

=========================================
### 응급의료정보 앱 (chapter04)
1. 구현기능
- 다양한 유저 정보
 - 화면 전환
 - 다른 앱 실행
 - 데이터 저장
 - 여러가지 위젯 사용
2. 몰랐던 것
 - layer는 두개의 위젯을 하나로 묶는 것
 - inc() -> +1
 - SharedPreferences -> Context.Mode_private(이 파일을 생성한 앱에서만 접근이 가능함.)
 - SharedPreferences -> editor.commit() 시 사용자는 아무 동작도 할 수 없음.
			  -> editor.apply() 다른 스레드를 열어 거기서 저장함.
 - const 변수들은 하나의 파일로 관리하는 방법도 있음.

==========================================
### 계산기 앱 (chapter05)
1. 구현기능
 - ConstrainLayout-Flow  사용
 - color Theme 따른 색변경

2. 몰랐던 점
 - Flow
 - backgroundHint의 장점
 - String 대신 StringBuilder 사용(둘의 차이점)
 	- string 객체는 한번 공간이 할당되면 변하지 않음
 	- stringbuffer /  stringbuilder는 객체의 공간이 부족해지면 유연하게 늘려줌 (두 개의 차이점은 동기화 지원 유무)
 - 무한대의 값을 받기 위해 toBigDecimal() 사용
 - DevimalFormat 사용해서 값에 콤마를 넣음
 
 ========================================
### 스톱워치 앱 (chapter06)
1. 구현기능
 - 스톱워치 기능
 - 시작 전 카운트 다운 추가
 - 카운트 다운 3초전 알림음
 - 랩타임 기록
2. 몰랐던 것
 - setView와  setContentView의 차이 
 - UI 쓰레드를 차단하면 안 됨. -> 일정시간 동안 반응이 없을 경우  ANR(Applicaion Not Responding)
 - 작업자 스레드 -> 메인스레드 이외의 스레드
 - UI는 UI스레드에서만 사용해라!
 - Handler는 순서를 만들어줌.(데이터를 UI로 보여주는 순서)
   - runOnUiThread(사용 완료)
   - View.post(사용 완료)
   - View.postDelayed
   - Handler(사용 완료)
- Looper :  Handler를 통해 들어온 데이터를 계속 확인해준다.
- ToneGenerator : sound

============================================
### 단어장 앱 (chapter07)
1. 구현기능
 - 단어 추가
 - 단어 수정
 - 단어 삭제
2. 사용 기술
 - Room
 - RecyclerView
 - chipgroup
3. 몰랐던 것
 - textInputEdit
 - textInputLayout
 - recyclerView 에서 선 그리는 방법
	- DividerItemDecoration
 - parcelize
 
 ==============================================
 ### 나만의 갤러리 (chapter08)
1. 구현기능
- 권한처리
- 갤러리 이미지 가져오기
- 여러가지 타입의 리스트 구현
2. 몰랐던 것
- sealed class : 하나로 묶어줌. interface처럼 모두 구현해주지 않아도 되는 장점을 가지고 있음
- DiffUtil
- submitList -> notifyChanged, thread 관련 처리해주는 함수임. RecyclerView에서 유용함
- indicator -> 이미지의 갯수와 위치를 알려주는 함수
- Viewpager2는 RecyclerView.Adapter처럼 연결 시킴 (같음) - Fragment랑 자주 쓰임
- ListAdapter -> 차이점을 알아서 계산해 주는 Adapter(Diffutil을 사용하여 차이점을 알아냄)
3. 어려웠던 것
- Storage Access Framework
- registerForActivityResult
- ListAdapter와 RecyclerView Adapter Study

==============================================
### 음악재생 앱
1. 구현기능
- 백그라운드에서 기능 실행(Service)
- 음원재생 (MediaPlayer)
- 디바이스 이벤트 캐치(네트워크 상태 변경, 전원 연결, 배터리 사용량 체크 등)(Notification, BroadcastReceiver)
2. 몰랐던 것
- Background에서 실행하고 싶다면 Service 사용
- Manifests -> exported -> 다른 앱에서도 이 Service를 사용할 수 있게 할 것 인지? 유무
- Intent -> Flag_activity_single_top -> 기본에 원하는 Activity가 있다면 만드는 것이 아니라 기존에 있는 것을 가지고 옴
- 4대 컴포넌트 Study
- PendingIntent
- onDestory() -> 처리 무조건 해주기

=================================
