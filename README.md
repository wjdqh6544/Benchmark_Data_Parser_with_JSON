## 벤치마크 데이터 반환 프로그램

* DB에 저장된 CPU 및 GPU 의 벤치마크 점수를 반환하는 프로그램입니다.
* JAVA SpringBoot 를 통해 작성되었으며, 데이터는 JSON 형식으로 파싱되어 제공됩니다.
* URL 파라미터로 제품명과 벤치마크 플랫폼을 입력하면, 제품명과 벤치마크 점수가 JSON 형태로 반환됩니다.
* Thymeleaf 를 활용하여 데이터를 수집 및 저장할 수 있습니다. (데이터가 이미 존재하는 경우, 덮어쓰기됨)
* 개발 기간: 2024. 08. 01. ~ 2024. 08. 24. (최종 개발완료; 문제상황 발생 시 지속적으로 수정, 보완 예정)
---
## [프로그램 작성 목적]
* 데이터의 수정 내역을 편리하게 반영하기 위해 본 프로그램을 고안하였습니다.
* 표/그래프를 작성할 때, 수동으로 값을 입력하지 않고, API 를 호출하여 데이터를 요청합니다.
* 데이터가 수정되면, API 호출 시 자동으로 반영됩니다. 모든 표/그래프를 수정하지 않아도 되므로 편리합니다.
* 표/그래프 형태로의 표현은 별도의 (워드프레스) 플러그인을 활용합니다.<br>
(해당 플러그인은 JSON 데이터를 기반으로 표/그래프를 만들어 줍니다.)

## [접근 방법]
* 본 프로그램은 Docker 로 배포될 예정이며, 사전 구축해둔 도커 내부 네트워크에 소속됩니다.
* API 요청이 필요한 도커 컨테이너와 같은 네트워크에 할당됩니다. 즉, API 는 외부에서 호출될 수 없습니다.
* 데이터의 크롤링/삽입을 위한 페이지는 오직 VPN 사설망을 통해서 접근 가능합니다.<br>
  (VPN 망이 아닌 곳에서 접근을 시도하면, 이 API 를 활용하는 프론트엔드 페이지로 강제 리다이렉트됩니다.)

## [API 명세]
* 벤치마크 점수를 불러올 제품명과 벤치마크 플랫폼을 쿼리스트링 형식으로 넘겨줍니다.
* 각 제품명은 콤마(,)로 구분됩니다.
* 벤치마크 플랫폼과 제품명은 대소문자와 공백을 구분하지 않습니다.
#### /CPU?benchmark={Benchmark-Platform}&productNames={Product Name},{Product Name},...
* CPU 벤치마크 점수를 요청합니다.

#### /GPU?benchmark={Benchmark-Platform}&productNames={Product Name},{Product Name},...
* GPU 벤치마크 점수를 요청합니다.

#### /crawling
* 벤치마크 데이터를 크롤링하여 DB에 저장하기 위한 페이지입니다.
* VPN 사설망에서만 접근할 수 있습니다.
* 데이터 수집은 WccfTech 플랫폼만 지원하며, 타 플랫폼은 지원하지 않습니다.<br>
  (필요한 경우 추가할 예정입니다.)
  ![image](https://github.com/user-attachments/assets/46539fee-661f-489b-8537-89cc7affd448)
* 최초 페이지 진입 모습입니다. Input URL 박스에 크롤링할 페이지 주소를 입력합니다.
* WccfTech 이외의 플랫폼 / 올바르지 않은 URL을 입력하면 "Entered SIte is not supported." 문구가 출력됩니다.
* 입력한 URL 에 크롤링할 데이터가 없다면 "Benchmark does not exists." 문구가 출력됩니다.
  ![image](https://github.com/user-attachments/assets/33ba6c33-78a2-40c9-b6ef-fece429e9ead)
* 크롤링할 데이터가 존재할 때의 모습입니다.
1. "The number of benchmark:" 부분을 통해 크롤링 할 데이터의 개수를 확인합니다.
2. 크롤링할 데이터가 2개 이상인 경우, "Choose Benchmark" 부분에서 저장할 데이터를 선택하여야 합니다.
3. Type 과 Benchmark Platform 을 선택합니다.<br>두 옵션 선택 없이 저장을 시도하면, "Select Product Type and Benchmark Platform." 문구가 출력됩니다. 
4. "Send to DB" 버튼을 클릭합니다. 저장 성공 시 Saved Successfully, 저장 실패 시 Save Failed 문구가 출력됩니다.
5. "Copy URL invoking API" 버튼을 클릭하면, API 호출 URL 을 클립보드에 복사할 수 있습니다.
6. "Get list of Products" 버튼을 클릭하면, 크롤링된 데이터 중 제품명으로 이루어진 리스트를 얻을 수 있습니다.<br>(클립보드로의 복사는 수동으로 실시하여야 합니다.) 

### Example
* DB에 저장된 벤치마크 점수가 제품명과 함께 반환됩니다.<br>점수가 존재하지 않으면, 다음과 같이 "404 Not found" 가 반환됩니다.<br>
![image](https://github.com/user-attachments/assets/cdadb073-261f-4c06-8c48-9bae6364aa9d)
* 벤치마크 플랫폼 이름을 기재하지 않거나 잘못 기재한 경우, 아래와 같이 "400 Bad Request" 가 반환됩니다.<br>
![image](https://github.com/user-attachments/assets/e62bac97-6fd4-434e-af09-f96a964206a8)
* 제품명을 기재하지 않은 경우, 아래와 같이 "400 Bad Request" 가 반환됩니다..<br>
![image](https://github.com/user-attachments/assets/cb8e7cd1-46ee-4bba-94d5-76cdbd89464d)
* 임의의 페이지를 크롤링하였을 때의 모습입니다.
  ![image](https://github.com/user-attachments/assets/1d1ab771-cbff-4c4b-a768-35d1df6eca78)
* 우측 하단 첫번째 박스에는 크롤링된 데이터 중 제품명만을 나열한 결과입니다.<br>
  ("Get list of Products" 버튼 클릭 시 나타납니다.)
* 우측 하단 두번째 박스는 API 호출에 사용되는 URL 주소입니다.<br>
  ("Copy URL invoking API" 버튼 클릭 시 나타나며, 주소가 클립보드에 자동으로 복사됩니다.)

## [벤치마크 플랫폼]
DB에 저장되어 있는 CPU/GPU의 벤치마크 플랫폼은 다음과 같습니다.
#### CPU
* Cinebench R23, Multi-Thread (Parameter: Cinebench_R23_MT)
* Cinebench R23, Single-Thread (Parameter: Cinebench_R23_ST)

#### GPU
* 3DMark Time-Spy (Parameter: _3DMark_Time_Spy)

## [SpringBoot Dependencies]
* Spring Web
* Lombok
* MariaDB Driver
* Spring Data JPA
* Spring Boot DevTools
* swagger
* thymeleaf
* Gson
* JSoup

## [Used external library for HTML]
* JQuery

---
### Latest Edited on 2024. 08. 24.<br>
* Add the content relative to Crawling page.
* Add and Edit existing content of program explanation.

7th Edit on 2024. 08. 21.<br>
* Edit API Parameter. (Benchmark Platform)

6th Edit on 2024. 08. 20.<br>
* Small modification to README.md.

5th Edit on 2024. 08. 19.<br>
* Edit invoke URL (/insert -> /crawling)
* Add HTML library, jquery.
* Add SpringBoot Dependency, JSoup.

4th Edit on 2024. 08. 14.<br>
* Add new feature - Data crawling and inserting page using thymeleaf.
* Add Dependencies.
* Add API Specification.
* Edit and Add the explanation relative to this program.

3rd Edit on 2024. 08. 11
* Edit Database table name (Have a same name: cpu_name, gpu_name -> product_name)
* Edit Variable name of entity

2nd Edit on 2024. 08. 06.
* Edit and add some sentences.
* Edit Parameter Name (name -> productNames)

1st Edit on 2024. 08. 04.
* Add Example Image
* Doesn't matter for blank and case in Parameter when checking the benchmark platform and product name in DB.

Created on 2024. 08. 01.
