# AEONApp


<img src="https://github.com/DiEvil21/AEONApp/assets/55885322/d20a5e4c-edf2-4792-bf3d-4a9dcab5b25f" height="300"/></h1>
<img src="https://github.com/DiEvil21/AEONApp/assets/55885322/b91cb317-42cd-4cc9-8f2e-38fb99da8e63" height="300"/></h1>


Требования

    Android Studio
    Kotlin
    Retrofit
    Coroutines

Функционал

    Авторизация пользователя: Пользователь может войти в приложение, предоставив свой логин и пароль. В случае успешной авторизации приложение получает токен для последующих запросов.

    Вывод списка платежей: После успешной авторизации приложение отправляет запрос на получение списка платежей с использованием полученного токена. Полученные данные отображаются в виде списка.

    Обработка ошибок: Приложение предусматривает обработку ошибок, таких как неправильные логин/пароль или ошибки при получении списка платежей.

API

Приложение взаимодействует с API, предоставляющим следующие конечные точки:

    POST /login: Авторизация пользователя. Параметры передаются в JSON-формате.

    GET /payments: Получение списка платежей. Необходимо предоставить токен в заголовке запроса.
