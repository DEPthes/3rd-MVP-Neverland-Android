# 3rd-MVP-Neverland-Android
[DEPth-3rd-MVP] 🧚네버랜드🧚 팀의 안드로이드 레포지토리 입니다.
<br>

## 멤버
[문장훈](https://github.com/moondev03)<p>
[김경민](https://github.com/GyeongminKimGyeongminKim)

<br>

## ⚙️ Android App Architecture
![Android App Architecture](https://github.com/user-attachments/assets/da7e13f9-77ab-40db-bb1b-bbbb3af58145)
<br>

## 🗂️ Package Structure
```markdown
📁 thinkerbell
├── 📁 core
│   └── 📁 utils
├── 📁 data
│   ├── 📁 local
│   │   └── 📁 model
│   ├── 📁 mapper
│   ├── 📁 remote
│   │   ├── 📁 model
│   │   └── 📁 service
│   ├── 📁 repository
│   └── 📁 utils
├── 📁 domain
│   ├── 📁 enums
│   ├── 📁 model
│   ├── 📁 repository
│   └── 📁 usecase
└── 📁 presentation
    ├── 📁 base
    ├── 📁 custom
    ├── 📁 extension
    ├── 📁 fcm
    ├── 📁 utils
    └── 📁 view
        ├── 📁 alarm
        ├── 📁 category
        ├── 📁 contact
        ├── 📁 deptUrl
        ├── 📁 home
        ├── 📁 myPage
        ├── 📁 notice
        ├── 📁 search
        └── 📁 splash
```
<br>

## 🛠️ 기술 스택
| **분류** | **내용** |
| --- | --- |
| **로컬 데이터 저장** | DataStore |
| **네트워크 통신** | OkHttp3, Retrofit2 |
| **오브젝트 매핑** | Gson |
| **로깅** | Orhanobut:logger, Okhttp3:logging-interceptor |
| **푸쉬 알림** | Firebase Cloud Messaging(FCM) |
| **쓰레드 처리** | Kotiln Coroutine, WorkManager |
| **이미지 캐싱 및 로드** | Glide |
| **뷰 관련** | nex3z:flow-layout |
| **상태 관리** | LiveData |
