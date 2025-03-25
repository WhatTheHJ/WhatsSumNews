# What's Sum News!

뉴욕타임즈(New York Times)의 Most Popular API를 이용해  
**조회 수가 가장 높은 기사**를 **1일, 7일 단위로** 확인할 수 있는 웹 애플리케이션.

- ✅ **회원가입 없이 사용 가능**
- 🔄 **매일 자동 업데이트**
- 📊 **인기 키워드 시각화 제공**
- 💾 **API 호출 최소화 (DB 캐싱 처리)**
- 🌐 **배포 링크**: [https://sdffd-f75d4.firebaseapp.com](https://sdffd-f75d4.firebaseapp.com)
- 🚀 **Firebase로 배포**

---

## 기능 소개

## 📱 화면 미리보기 (PC & Mobile)

<p align="center">
  <img src="https://github.com/user-attachments/assets/a7c5a0a1-e5ab-48c0-a19d-8971e54a015d" alt="PC Version" width="60%" />
  <img src="https://github.com/user-attachments/assets/b087db25-a653-4852-801e-287b5426a3ce" alt="Mobile Version" width="25%" />
</p>

- 뉴욕타임즈에서 가장 인기 있는 기사 목록 제공 (1일 / 7일 기준)
- 썸네일, 제목, 기사 요약, 기사 원문 링크 제공
- 인기 키워드를 추출하여 메인 화면에서 **시각화**
- **최초 접속 시**: NYT API에서 데이터를 가져와 관계형 DB에 저장  
- **이후 접속 시**: 저장된 DB 데이터를 렌더링하여 **API 호출 낭비 방지**
- 간단하고 직관적인 UI


![image](https://github.com/user-attachments/assets/c83cd372-0267-4e41-b6f2-d93ef6182ead)


---

## 🛠️ 사용 기술

- **React**
- **Firebase Hosting**
- **NYT Most Popular API**
- **MySQL** 
- **Node.js**

## 회고 - 처음 만들어본 개인 프로젝트

회원가입이나 게시판 기능이 없는 간단한 토이 프로젝트였지만,
평소 관심 있던 영어 뉴스 데이터를 활용해서 개발하는 과정이 무척 흥미로웠음.

특히 NYT API로 받아온 중첩된 객체 구조를 직접 분석하고,
그 안에서 필요한 이미지 파일을 추출해 DB에 저장하는 과정에서 많은 시행착오를 겪으며,
RESTful API의 데이터 구조를 파악하는 능력 향상에 큰 도움이 된 것 같다.
