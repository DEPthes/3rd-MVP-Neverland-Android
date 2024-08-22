# 3rd-MVP-Neverland-Android
[DEPth-3rd-MVP] 🧚네버랜드🧚 팀의 안드로이드 레포지토리 입니다.
<br>
<br>

## 🖐️ 서비스 소개
![831634](https://github.com/user-attachments/assets/6e34d086-950f-4c6d-8e1c-16efc32e846d)
![Frame 1000013476](https://github.com/user-attachments/assets/fddf672a-5d81-4547-804f-54ae453c25dc)
![Frame 1000013475](https://github.com/user-attachments/assets/ffecae54-9674-40bf-89f2-b82a242ff01b)
![831637](https://github.com/user-attachments/assets/58f55b63-4258-42ad-bdac-a4839b211e68)
![831639](https://github.com/user-attachments/assets/f713bf17-dcde-4dae-97bb-7fe1c281933e)
![831641](https://github.com/user-attachments/assets/7cf7cf77-58dd-4bc1-9c09-7445c4065669)
![831642](https://github.com/user-attachments/assets/c669c803-3df1-4251-b095-b7b8133877a6)
![831643](https://github.com/user-attachments/assets/37efafea-56d0-47af-bdfd-5e076d7c5009)
![831644](https://github.com/user-attachments/assets/c0cee067-e038-49c6-9e22-8e8da4978af4)
![831645](https://github.com/user-attachments/assets/3e77c92e-d054-4b84-9cda-62056c803101)
![831647](https://github.com/user-attachments/assets/f9d7ccff-fcdc-4baf-b73f-e4465d06be72)

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
