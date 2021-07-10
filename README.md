# cors-learned

**跨域产生的原因**

浏览器限制

跨域（协议、域名、端口号）

XHR（XMLHttpRequest）请求

**解决方案**

--disable-web-security --user-data-dir="C:/ChromeDevSession"

JSONP

**JSONP**

Type => script

Response Content-Type => application/javascript

```javascript
http://localhost:8080/hello?callback=jQuery36007420675525950529_1625926590684&_=1625926590685

jQuery36007420675525950529_1625926590684({
  "hello": "world"
});
```

动态创建脚本 => `<script async src="http://localhost:8080/hello?callback=jQuery36007420675525950529_1625926590684&_=1625926590685"></script>`

弊端 => 只支持 GET 请求

**参考**

[Spring JSON-P with Jackson](https://www.baeldung.com/spring-jackson-jsonp)

