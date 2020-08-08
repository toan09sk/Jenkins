### 🏃🏼‍♀️ Lab document
- Cài docker và docker-compose trên EC2/AWS
```
sudo yum update -y
sudo yum install -y docker
sudo service docker start
sudo usermod -a -G docker ec2-user
```

- Cài docker-compose
```
sudo curl -L "https://github.com/docker/compose/rel... -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

- Trợ giúp trong docker
```
docker
docker image --help
docker image save --help
```

- Xem trong hệ thống có những IMAGE nào, và IMAGE đó lấy từ đâu ra
```
docker images
docker search ubuntu
docker pull ubuntu:16.04
docker pull ubuntu
docker image rm ubuntu:16.04
docker image rm af
```
- Kho docker image https://hub.docker.com/

- Thực hành
```
docker search centos
docker pull centos
```
- Khởi tạo 1 container, với it là nhận input và kết nối terminal
```
docker run -it ubuntu:latest
docker ps
docker ps -a
```
- Khởi tạo container với name và hotname
`docker run -it --name "ABC" -h centos1 centos:latest`
- Xem thông tin ubuntu
`cat /etc/*release`
- Khi container đang dừng ta muốn khởi chạy lại
`docker start 89`
- Khi muốn vào lại ubuntu
`docker attach 89`
- Muốn thoát terminal container mà vẫn muốn container vẫn chạy `ctrl + p + q`
- Ở ngoài container nhưng muốn nó dừng lại: `docker stop 89`
- Muốn xóa 1 container
 ```
 docker rm 89
 docker rm -f ABC
 ```

### Lệnh Docker exec, lưu container thành image với commit, xuất image ra file

- Ta ở ngoài nhưng vẫn muốn thực hiện 1 lệnh cho container đang chạy
```
docker run -it --name U1 -h ubuntu1 ubuntu
docker exec U1 ls -la
docker exec -it U1 bash
```
- Chú ý: khi exit khỏi bash thì container vẫn chạy
- để xem các tiến trình trong container đang chạy ta cần lệnh htop
- Cài thêm 1 số gói cho container U1
```
apt update -y
apt install htop
apt search iputils
apt install iputils-ping
ping google.com
apt install vim
vi abc
```
- container U1 này đã cài đặt 1 số package. Bây h ta muốn lưu container này - ta sẽ commit container thành 1 IMAGE
- Ta chú ý khi commit thì container ấy phải có trạng thái dừng
```
docker commit U1 ubuntu-vim:version1
docker rm U1
docker run -it ubuntu-vim:version1
```

- Lưu IMAGE thành 1 file trong máy host
```
cd /home/imageRep
docker save --output myimage.tar ubuntu-vim:version1
docker image rm ubuntu-vim:version1 -f
docker load -i myimage.tar
docker tag 466 newimage:version2
docker image rm ubuntu-vim:version1
```
### Chia sẻ dữ liệu trong Docker, tạo và quản lý ổ đĩa docker volume
Máy Host là hệ thống bạn đang chạy Docker Engine. Một thư mục của máy Host có thể chia sẻ để các Container đọc, lưu dữ liệu.

### Container - ánh xạ thư mục máy Host
Thông tin:

    -Thư mục cần chia sẻ dữ liệu trên máy host là: path_in_host
    -Khi chạy container thư mục đó được mount - ánh xạ tới path_in_container của container

-Để có kết quả đó, tạo - chạy container với tham số thêm vào -v path_to_data:path_in_container
`docker run -it -v /home/toan-pham/Desktop/dulieu:/home/dulieu --name C1 ubuntu-vim:version1`
-Lúc này, dữ liệu trên thư mục `/home/sitesdata/` của máy Host thì trong container có thể truy cập, cập nhật sửa đổi ... thông qua đường dẫn `/home/data`

### Chia sẻ dữ liệu giữa các Container
`docker run -it --volumes-from container_first ubuntu`

### Quản lý các ổ đĩa với docker volume
- Liệt kê danh sách các ổ đĩa: `docker volume ls`
- Tạo một ổ đĩa: `docker volume create name_volume`
- Xem thông tin chi tiết về đĩa: `docker volume inspect name_volume`
- Xóa một ổ đĩa: `docker volume rm name_volume`

### Mount một ổ đĩa vào container (--mount)
```
# Tạo ổ đĩa có tên firstdisk
docker volume create firstdisk

# Mount ổ đĩa vào container
# container truy cập tại /home/firstdisk

docker run -it --mount source=firstdisk,target=/home/firstdisk  ubuntu
docker run -it --mount source=D1,target=/home/disk1 ubuntu-vim:version1
```

### Gán ổ đĩa vào container khi tạo container (-v)
Nếu muốn ổ đĩa bind dữ liệu đến một thư mục cụ thể của máy HOST thì tạo ổ đĩa với tham số như sau:\
`docker volume create --opt device=path_in_host --opt type=none --opt o=bind  volumename`
- Example: `docker volume create --opt device=/home/toan-pham/Desktop/dulieu --opt type=none --opt o=bind Disk2`
- Sau đó ổ đĩa này gán vào container với tham số -v (không dùng --mount)
```
# Tạo ổ đĩa có tên mydisk (dữ liệu lưu tại /home/mydata)
docker volume create --opt device=/home/mydata --opt type=none --opt o=bind  mydisk
# Gán ổ đĩa vào container tại (/home/sites)
docker run -it -v mydisk:/home/sites ubuntu
```
- Xóa tất cả các ổ đĩa không được sử dụng bởi container nào:`docker volume prune

### Mạng | Networking trong Docker, tạo và quản lý network trong container Docker
- Xóa tất cả container `docker rm $(docker ps -a -q)`
- pull images busybox `docker pull busybox`
- `docker run -it --rm busybox`
- `ls /bin -la`--> show command of busybox
- `docker network ls`
- `docker network inspect bridge`


- `docker run -it --name B1 busybox`
- `docker inspect B1`
- `docker attach B1`
- `ping google.com`

- Thử ping từ B1 sang B2 
` docker attach B1`
` ping 172.17.0.3`
 
 - Trong busybox có sẵn httpd
 `docker attach B2`
 `cd /var/www/`
 `httpd`
 `vi index.html` -> Webserver is running ...
 - Tư B1 ping qua B2 ddee3 kiểm tra
 `docker attach B1`
 `wget -O - 172.17.0.3` --- Tham số  Output ddee3 hiện ra màn hình

 - Thiết lập truy cập đến cổng 80 của B2 thông qua cổng 8888 của ip 127.0.0.1
`exit`
`docker rm B2`
`docker run -it --name B2 -p 8888:80 busybox`
`docker ps`
`docker attach B2`
 `cd /var/www/`
 `httpd`
 `vi index.html` -> Webserver is running ...
 `localhost:8888`

 - Tạo ra mạng cầù
 `docker network create --driver bridge network1`
 `docker network ls`
 `docker network create --driver bridge mynetwork`
 `docker network rm network1`

 `docker run -it --name B3 --network mynetwork busybox`
 `docker network inspect mynetwork`

 `docker run -it --name B4 --network mynetwork -p 9999:80 busybox`
 ...
 `vi index.html` -> Webserver on B4 container!

 - Để container kết nối vào 1 network
 `docker network connect brdige B3`

 ### Cài đặt, tạo và chạy PHP, phiên bản có PHP-FPM bằng Docker
 Tạo một container chạy PHP từ image php:7.3-fpm, đặt tên container này là c-php

`docker run -d --name c-php -h php -v "/mycode/php":/home/phpcode php:7.3-fpm`

- Các tham số tạo, chạy container như đã biết trong phần trước, ở đây cụ thể là:
   - -d : container sau khi tạo chạy luôn ở chế độ nền.
   - --name c-php : container tạo ra và đặt luôn cho nó tên là c-php (Tương tác với container dễ đọc hơn khi sử dụng tên thay vì phải sử dụng mã hash id, nếu không đặt tên thì docker sinh ra tên ngẫu nhiên).
   - -h php đặt tên HOSTNAME của container là php
   - -v "/mycode/php":/home/phpcode thư mục trên máy host /mycode/php (với Windows đường dẫn theo OS này như "c:\path\mycode\php") được gắn vào container ở đường dẫn /home/phpcode.
   - php:7.3-fpm là image khởi tạo ra container, nếu image này chưa có nó tự động tải về.
 ```
docker pull php:7.3-fpm
docker network create --driver bridge www-net
docker run -d --name c-php -h php -v ~/Desktop/mycode/:/home/mycode/ --network www-net php:7.3-fpm
docker exec -it c-php bash

mycode/www
<?php
phpinfo();

php test.php
 ```

 ### Cài đặt APACHE HTTPD trên Docker
 `docker pull httpd`
- Muốn chỉnh sửa file config httpd.conf
   - File config tại: /usr/local/apache2/conf/httpd.conf
   - Nếu muốn cài trình soạn thảo nano gõ lệnh apt-get update & apt-get install nano

- `docker run --rm -v ~/Desktop/mycode/:/home/mycode/ httpd cp /usr/local/apache2/conf/httpd.conf /home/mycode`
- `code ~/Desktop/mycode/htppd.conf`

Do Handler này gọi PHP thông qua proxy, nên Apache phải kích hoạt module liên quan đến proxy, mở file httpd.conf và bỏ commnet trên các dòng:
- LoadModule proxy_module modules/mod_proxy.so
- LoadModule proxy_fcgi_module modules/mod_proxy_fcgi.so
- AddHandler "proxy:fcgi://c-php:9000" .php
```
DocumentRoot "/home/mycode/www"
<Directory "home/mycode/www">
```
- `docker run --network www-net --name c-httpd -h httpd -p 9999:80 -p 443:443 -v ~/Desktop/mycode/:/home/mycode/ -v ~/Desktop/mycode/httpd.conf:/usr/local/apache2/conf/httpd.conf httpd`

### Cài đặt, chạy MySQL bằng Docker
MSQL 8.0
 - port:3304
 - file config: /etc/mysql/my.cnf
 		[mysql]
 		default-authentication-plugin=mysql_native_password
 		
 -root:MYSQL_ROOT_PASSWORD
 -databases: /var/lib/mysql
 ```
 docker run --rm -v ~/Desktop/mycode:/home/mycode mysql cp /etc/mysql/my.cnf /home/mycode
 code ~/desktop/mycode/my.cnf --> default-authentication-plugin=mysql_native_password
 docker run -e MYSQL_ROOT_PASSWORD=abc123 -v ~/Desktop/mycode/my.cnf:/etc/mysql/my.cnf -v ~/Desktop/mycode/db:/var/lib/mysql --name c-mysql mysql
 docker exec -it c-mysql bash
 mysql -uroot -pabc123
 show databases;
 use mysql;
 show tables;
 ```

 ```
 #Từ dấu nhắc MySQL, Tạo một user tên testuser với password là testpass

CREATE USER 'testuser'@'%' IDENTIFIED BY 'testpass';

#Tạo db có tên db_testdb
create database db_testdb;

select User from user;

#Cấp quyền cho user testuser trên db - db_testdb
GRANT ALL PRIVILEGES ON db_testdb.* TO 'testuser'@'%';
flush privileges;

show database;            #Xem các database đang có, kết quả có db bạn vừa tạo
exit;                     #Ra khỏi MySQL Server

testuser:testpass
```

### Cài đặt và chạy WordPress trên Docker
```
docker logs c-httpd
docker logs c-php


// fix error 500
docker exec -it c-php bash
php -m  --> kiem tra extension
docker-php-ext-install mysqli
docker-php-ext-install pdo_mysql
docker restart c-php

docker exec -it c-php bash
apt update
apt search iputils
apt install iputils-ping
ping c-mysql
docker network connect www-net c-mysql
docker exec -it c-mysql bash
mysql -uroot -pabc123
create database wp_wordpress;
GRANT ALL PRIVILEGES ON wp_wordpress.* TO 'testuser'@'%';
flush privileges;

mysql -utestuser -ptestpass;
```
### Tra cứu thông tin Image, Container và giám sát hoạt động container Docker
1. docker images
2. docker image history httpd (image)
3. docker inspect httpd/c-httpd (HostConfig/Binds, Networks)
4. docker diff c-httpd (cau truc file thu muc C, A, D)
5. docker logs c-httpd
6. docker logs --tail 10 c-httpd
7. docker ps -a
8. docker start c-httpd c-php c-mysql
9. docker logs -f c-httpd (realtime)
10. docker stats c-httpd c-php c-mysql --> kiểm tra mức sử dụng tài nguyên

### copy from host to container
`docker cp ~/Desktop/mycode/myimage/test.html cent:/var/www/html/`

### run myimage then httpd 
`docker run --rm -p 9876:80 myimage:v1 httpd -D FOREGROUND`

### Biên tập Dockerfile và sử dụng lệnh docker build để tạo các Image

```
docker run -it --name cent centos:latest
yum update -y
yum install httpd htttpd-tools -y
yum install vim -y
yum install epel-release -y
yum update -y
yum install htop -y

docker cp ~/Desktop/mycode/myimage/test.html
       cent:/var/www/html

docker commit cent myimage:v1
```

```
FROM centos:latest
RUN update -y
RUN install httpd htttpd-tools -y
RUN install vim -y
RUN install epel-release -y
RUN update -y
RUN install htop -y

WORKDIR /var/www/html
EXPOSE 80

ADD ./test.html /var/www/html/

ENTRYPOINT ["httpd"]
CMD ["-D","FOREGROUND"]
```

```
docker build -t myimage:v1 -f Dockerfile .
```

### Sử dụng lệnh docker-compose chạy và quản lý các dịch vụ Docker
- example-docker-compose.yml

PHP:7.3-FPM (php-product)
      - port: 9000
      - cài mysqli, pdo_mysql:
         * docker-php-ext-install mysqli
         * docker-php-ext-install pdo_mysql
      - Thư mục làm việc : /home/sites/site1
APACHE HTTPD: (c-httpd01)
      - port: 80,443
      - config: /etc/local/apache2/conf/httpd.conf
         * Nạp: mod_proxy, mod_proxy_fcgi
         * Thư mục làm việc: /home/sites/site1
         * index mặc định: index.php index.html
         * PHPHANDLER: AddHandler "proxy:fcgi://php-product:9000"

MYSQL: (mysql-product)
      - port: 3304
      - config: /etc/mysql/my.cnf
         * default-authentication-plugin=mysql_native_password
      - databases: /var/lib/mysql -> /mycode/db
      - MYSQL_ROOT_PASSWORD: 123abc
      - MYSQL_DATABASE: db_site
      - MYSQL_USER: siteuser
      - MYSQL_PASSWORD: sitepass

NETWORK:
      - bridge
      - my-network

VOLUME: dir-site
      - bind, device = /mycode/



---* Example:

hoten: ABC
namsinh: 200
cacmonhoc:
  monhocA:9  <--> thụt vào 2 khoảng trắng
  monhocB:10
  monhocC:   <--> mảng giá trị
    - 5
    - 9

# Prepare
```
docker run --rm -v /mycode/:/home/ httpd cp /usr/local/apache2/conf/httpd.conf /home/
code ./httpd.conf

<IfModule dir_module>
	DirectoryIndex index.php index.html
</IfModule>

docker run --rm -v /mycode/:/home/ mysql cp /etc/mysql/my.cnf /home/
code ./my.cnf
mkdir db
touch docker-compose.yml
code ./docker-compose.yml

docker-compose up

```

# Inspect
```
docker network ls
docker volumes ls
docker ps -a

```