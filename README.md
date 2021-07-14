# cors-learned

**跨域产生的原因**

浏览器限制

跨域（协议、域名、端口号）

XHR（XMLHttpRequest）请求

**解决方案**

--disable-web-security --user-data-dir="C:/ChromeDevSession"

JSONP

<被调用方> Client -> （被调用方 Apache / Nginx） -> 被调用方 Tomcat [Filter]（Access-Control-Allow-Origin / Access-Control-Allow-Methods / Access-Control-Allow-Headers）

<被调用方> Client -> 被调用方 Apache / Nginx [.conf] -> 被调用方 Tomcat

<被调用方> Spring 框架 @CrossOrigin

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

**简单请求 / 非简单请求**

OPTIONS 预检命令

OPTIONS 预检命令缓存 Access-Control-Max-Age

**Cookie 跨域**

Access-Control-Allow-Origin 不能为 *

Access-Control-Allow-Credentials

`document.cookie`

**Nginx 配置**

C:\Windows\System32\drivers\etc\hosts 映射域名 127.0.0.1 corsserver

nginx.conf 中添加 include vhost/*.conf;

添加 vhost/corsserver.conf

```nginx
server {
    listen 80;
    server_name corsserver;
    location / {
        proxy_pass http://localhost:8080/;

		add_header Access-Control-Allow-Origin $http_origin;
		add_header Access-Control-Allow-Methods *;
		add_header Access-Control-Allow-Headers *;
		add_header Access-Control-Max-Age 3600;
		add_header Access-Control-Allow-Credentials true;

		if ($request_method = OPTIONS) {
		    return 200;
		}
    }
}
```

**Apache 配置**

```apache
Path: conf/httpd.conf

# Virtual hosts
Include conf/extra/httpd-vhosts.conf

LoadModule proxy_module modules/mod_proxy.so
LoadModule proxy_http_module modules/mod_proxy_http.so

Define SRVROOT "C:\projects\cors-learned\Apache24"

LoadModule headers_module modules/mod_headers.so
LoadModule rewrite_module modules/mod_rewrite.so
```

```apache
Path: conf/extra/httpd-vhosts.conf

<VirtualHost *:80>
    ServerName corsserver
    ErrorLog "logs/corsserver-error.log"
    CustomLog "logs/corsserver-access.log" common
	ProxyPass / http://localhost:8080/

	Header always set Access-Control-Allow-Origin "expr=%{req:origin}"
	Header always set Access-Control-Allow-Headers "expr=%{req:Access-Control-Request-Headers}"
	Header always set Access-Control-Allow-Methods "*"
	Header always set Access-Control-Allow-Credentials "true"
	Header always set Access-Control-Max-Age "3600"

	RewriteEngine On
	RewriteCond %{REQUEST_METHOD} OPTIONS
	RewriteRule ^(.*)$ "/" [R=204,L]
</VirtualHost>
```

**参考**

[Spring JSON-P with Jackson](https://www.baeldung.com/spring-jackson-jsonp)

