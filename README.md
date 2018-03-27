### 실행방법

- 콘솔:
``` 
$ gradle clean
$ gradle build
```
- 이후, 우측, 인텔리제이 Gradle projects > '새로고침' 클릭 

## 인텔리제이 plugin
IntelliJ Lombok plugin 

## 인텔리제이 setting
Intellij -> Preferences -> Build,Exe... -> Compiler -> Annotation Processors




## darc soap 방식 개선

>1. ifcms-3.5.jar / ifcms-darc-3.5.jar 제거
>1. 기존에 ifcms*.jar 에 캡슐화 되어있던부분, 너무 복잡하고 디버깅및 흐름 파학이 어려워 구조변경 
>1. Spring Security 적 ( Basic Auth 적용 )
>1. 권한 대한 표준화 적용 (Spring Security 방식 )
>1. 설정파일에 permission action 선언부분 제거
>1. rebuildProperties 사용금지 ( 스키마뷰 체크해서 컬럼체크해서 걸러주는 역할이나, 개발상 오류시 감지할 수가 없으므로, 사용금지 )

