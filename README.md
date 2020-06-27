# KaKaoPay Project - 도서 검색
- 카카오 도서 API를 활용하여 도서를 검색하는 앱입니다.

## 기능요구사항
카카오 도서 검색 api 를 이용하여 도서 검색 

스크롤시 paging 기능을 제공해 연속적으로 검색하는 기능 제공

검색 리스트 결과(메인화면) 및 상세 화면으로 구성

## 기능 설명
1. 앱 진입 시 1초 동안 스플래시 화면이 나온 뒤 메인화면으로 전환됩니다.
2. 메인화면에서 도서를 검색하면, 결과 아이템이 애니메이션 효과와 함께 출력됩니다.
3. 검색된 목록을 스크롤하면, 5개 미만의 아이템이 남았을 때, 비동기로 다음 페이지를 조회합니다.
4. 조회된 도서를 클릭 시, 상세 페이지로 이동합니다.
5. 상세 페이지에서 웹으로 보기를 누르면 외부 브라우저를 호출합니다.

## APK
https://bit.ly/3eFhTz9

## API
https://dapi.kakao.com/v3/search/book/

## Langauge
Kotlin

## Arcitecture
MVVM

## JetPack
AndroidX, DataBinding, Lifecycle, LiveData

## Network
[Retrofit2](https://square.github.io/retrofit/)

[OkHttp](https://square.github.io/okhttp/)

## DI
[Koin](https://github.com/InsertKoinIO/koin)

## Thread
[RxJava](https://github.com/ReactiveX/RxJava)

## UI
Material

## Image Processing
[Glide](https://github.com/bumptech/glide)

## App Icon
Icons made by DinosoftLabs from www.flaticon.com

