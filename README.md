## 벤치마크 데이터 반환 프로그램

* DB에 저장된 CPU 및 GPU 의 벤치마크 점수를 반환하는 프로그램입니다.
* JAVA SpringBoot 를 통해 작성되었으며, 데이터는 JSON 형식으로 파싱되어 제공됩니다.
* URL을 통해 CPU/GPU 의 제품명을 제공하면, 각 제품의 이름과 벤치마크 점수가 JSON 형태로 반환됩니다.
* Thymeleaf 를 활용하여 데이터를 수집 및 저장할 수 있습니다. (단, 데이터의 수정은 불가합니다.)
* 개발 기간: 2024. 08. 01. ~ (필요 시 지속적으로 수정 및 보완 예정)
---
## [프로그램 작성 목적]
* 블로그를 보다 편리하게 운영할 수 있도록 본 프로그램을 고안하였습니다.
* 벤치마크 자료가 삽입되어야 하는 경우, 데이터를 중복하여 입력할 필요 없이, DB에 저장된 것을 사용합니다.
* 벤치마크 데이터가 수정되어야 하는 경우에도, 모든 게시글을 찾아 수정할 필요 없이, DB만 수정하면 되므로 편리합니다.
* JSON을 통해 데이터를 입력받아 테이블/차트로 표현할 수 있는 워드프레스 플러그인을 함께 활용합니다.
* 해당 플러그인은 오직 URL로만 JSON 데이터를 받을 수 있습니다. 따라서 URL 파라미터로 제품명을 넘겨 값을 받도록 구성하였습니다.

## [접근 방법]
* 본 프로그램은 Docker 로 배포될 예정이며, 사전 구축해둔 도커 내부 네트워크에 소속됩니다.
* 블로그가 구동되고 있는 도커 컨테이너와 같은 네트워크에 위치하며, 별도의 포트 개방은 없습니다.<br>
  (즉, 블로그 컨테이너에서만 본 프로그램에 접근 가능하며, 외부에서는 어떤 형태로든 접근이 불가합니다.)
* 블로그 컨테이너는 도커 사설망을 통해 본 프로그램으로 접근합니다.
* 예외적으로, 데이터를 수집/저장하는 페이지는 VPN 사설망을 통해서만 접근할 수 있습니다.<br>
  (VPN 망이 아닌 곳에서 접근을 시도하면, 본 프로그램을 사용하는 블로그로 강제 리다이렉트됩니다.)

## [API 명세]
* 벤치마크 점수를 불러올 제품명과 벤치마크 플랫폼을 쿼리스트링 형식으로 넘겨줍니다.
* 각 제품명은 콤마(,)로 구분됩니다.
* 벤치마크 플랫폼과 제품명은 대소문자와 공백을 구분하지 않습니다.
* 벤치마크 데이터가 존재하지 않는 경우, 404 Not Found 와 함께 입력된 제품명이 반환됩니다.
#### /CPU?benchmark={Benchmark-Platform}&productNames={Product Name},{Product Name},...
* CPU 벤치마크 점수를 요청합니다.

#### /GPU?benchmark={Benchmark-Platform}&productNames={Product Name},{Product Name},...
* GPU 벤치마크 점수를 요청합니다.

#### /crawling
* 벤치마크 데이터를 크롤링하여 DB에 저장하기 위한 페이지입니다.
* VPN 사설망에서만 접근할 수 있습니다.
* 데이터 수집은 WccfTech 플랫폼만 지원하며, 타 플랫폼은 지원하지 않습니다.<br>
  (필요한 경우 추가 예정)

### Example
* DB에 저장된 벤치마크 점수가 제품명과 함께 반환됩니다.<br>점수가 존재하지 않으면, 다음과 같이 "404 Not found" 가 반환됩니다.<br>
![image](https://github.com/user-attachments/assets/cdadb073-261f-4c06-8c48-9bae6364aa9d)
* 벤치마크 플랫폼 이름을 기재하지 않거나 잘못 기재한 경우, 아래와 같이 "400 Bad Request" 가 반환됩니다.<br>
![image](https://github.com/user-attachments/assets/e62bac97-6fd4-434e-af09-f96a964206a8)
* 제품명을 기재하지 않은 경우, 아래와 같이 "400 Bad Request" 가 반환됩니다..<br>
![image](https://github.com/user-attachments/assets/cb8e7cd1-46ee-4bba-94d5-76cdbd89464d)

## [벤치마크 플랫폼]
DB에 저장되어 있는 CPU/GPU의 벤치마크 플랫폼은 다음과 같습니다.
#### CPU
* Cinebench R23, Multi-Thread (Parameter: CineBenchR23MT)
* Cinebench R23, Single-Thread (Parameter: CineBenchR23ST)

#### GPU
* 3DMark Time-Spy (Parameter: TimeSpy)

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
### Latest Edited on 2024. 08. 20.<br>
* Add HTML library,
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
