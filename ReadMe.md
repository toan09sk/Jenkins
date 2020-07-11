### Create github
```
o "# Jenkins" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/toan09sk/Jenkins.git
git push -u origin master
```
#### node - using groovy script

### Excute file Hello
```
javac Hello.java
java Hello
```

### Launch jenkins
`java -jar jenkins.war`

### show environment variable
localhost:8080/env-vars.html/

### Using command sh
add C:\Program Files\Git\usr\bin to the PATH ---- to using sh

### Create SSH Key
1. Chuyển qua quyền root: `sudo su -`
2. Tạo user là yuki: `useradd yuki`
3. Tạo password cho user: `passwd yuki`
4. Xóa 1 user: `sudo userdel -r yuki`
5. Chuyển qua user yuki: `su - yuki`
6. Tạo public key: `ssh-keygen -t rsa`
7. `cd /home/yuki/.ssh`
8. `ls -la`
9. Đổi ssh key thành authorize key: `mv id_rsa.pub authorized_keys`
10. Change quyền cho authorized_key: `chmod 600 authorized_keys`
11. cấu hình lại ssh: `vi /etc/ssh/sshd_config`
12. Restart ssh: `service sshd restart`

### User/Group và phân quyền file/folder
1. Tạo nhóm kettoan: `groupadd ketoan`
2. Thêm user vào group: usermod a -G groupname username --> Ex: `usermode -a ketoan bao`
3. Kiểm tra xem user đã nằm trong group chưa: `id toan`

### Phân quyền file và foler ở trên Linux
 - Kiểm tra permission hiện tại: `ls -la`\;
 Example:&nbsp;
  drwxrwx--- 3 root ketoan      15 Mar 16 07:05 data-ketoan\
  -rw-r--r-- root     4 Mar 16 07:43 hellotxt

 - Thứ tự phân quyền ***user/group/other***
 d: là thư mục\
 r = read permission\
 w = write permission\
 x = execute permission\
 \- = no permission\

 - Bảng phân quyền\
| No  | Permission Type | Symbol|
| ------------- | :-------------: | -------------: |
| 0  | No Permission  | --- |
| 1  | Execute  | --x |
| 2  | Write  | -w- |
| 3  | Execute + Write  | -wx |
| 4  | Read  | r-- |
| 5  | Read + Execute  | r-x |
| 6  | Read + Write  | rw- |
| 7  | Read + Write + Execute  | rwx |

- Lệnh **set** quyền cho file và folder\
 chmod \[quyền\][\tên file hoặc thư mục]\
 Ex: `chmod -Rf 770 ketoan-data`&nbsp;
 - Rf: tất cả thư mục bên trong folder đề bị change\
 - 777: user/group/other đều full quyền - toàn quyền truy cập
 - Lệnh gán quyền cho user và group\
 chown [tên user]:[tên nhóm] [tên file hoặc thư mục]\
 Ex: `chown -Rf :ketoan ketoan-data/`
- Khi `cd /home/` sau đó list ra `ls -la` nó sẽ show ra các user

## Phân quyền - Nhóm
usermod a -G groupname username &nbsp;
Ex: `usermod -a -G ketoan toan`

Kiểm tra xem user toan đã nằm trong group ketoan chưa: `id toan`


### Command hay sử dụng
- Tạo thư mục: `mkdir`
- tạo file bên trong có nội dung: `echo "hello my friend">hello.txt`
- Nếu quên lệnh: `Get-Alias` -> powerShell
- Xóa file `unlink README.md`
- Xóa folder `sudo rm -rf jenkins`

### Apache Webserver
1. Cấu hình DNS
2. install Apache
3. Mở firewall port 80
4. Cấu hình tên miền

- Kiểm tra tên miền đã dc add vào chưa :`nslookup tinhocthatladongian.coderhanoi.com

NameVirtualHost *:80
<VirtualHost *:80>
    ServerAdmin info@coderhanoi.com
    DocumentRoot /var/www/html/tinhocthatladongian
    ServerName tinhocthatladongian.coderhanoi.com
    <Directory "/var/www/html/tinhocthatladongian">   
        Order deny,allow
           Allow from all
           AllowOverride All
          Require all granted
   </Directory>
</VirtualHost>

- service httpd là appache, nó chạy trên cổng 80
- Nhưng mặc định amazone chỉ cho mở cổng 22
- Nên mình phải mở cho port 80
- apache có user và group mặc định là apache. Hãy xem file config

- Câu lệnh cài apache:`yum install httpd -y`
- Start apache: `service httpd start`
- cài đặt telnet: `yum install telnet`
- Kiểm tra apache đã chạy hay chưa: `telnet localhost 80`
- Kéo xuống mục ***Security groups*** ***launch-wizard-3***. Sau đó click ***Inbound rules*** tab. Type: HTTP, Port Range:80
- Tạo thư mục mặc định: `cd /var/www/html/`
- Tạo thư mục tinhocthatladongian: `mkdir tinhocthatladongian`
- Xem cấu hình apache: `vi /etc/httpd/conf/httpd.conf`
- Chuyển qua quyền apache mặc định cho folder tinhocthatladongian: `chown -Rf :apache tinhocthatladongian/`
- Cấu hình virtual host - Chính là cấu hình tên miền trên server này
- Khởi động lại server `service httpd restart`
- Chuyển user yuki vào apache: `usermod -a -G apache yuki`
- Cấp thêm quyền cho thư mục tinhocthatladongian: `chmod 775 tinhocthatladongian/`
- Kiểm tra lỗi syntax trong file config : `httpd -t`

### Truy cập máy chủ từ xa
- Truy cập server 3.19.54.241 bằng user yuki và ec2-user
`ssh -T -i /c/tools/yuki-server.pem yuki@3.19.54.241`
`ssh yuki@3.19.54.241 -i /c/tools/yuki-server.pem`
`ssh ec2-user@3.19.54.241 -i /c/tools/tinhhocthatladongian.pem`
`ssh root@3.19.54.241`


### GIT
`yum install git -y`

### Server Httpd apache
1. Web server installation
#!/bin/bash
`yum update -y`
`yum install httpd -y`
`yum install git -y`
`amazon-linux-extras install epel`
`yum install epel-release`
`rpm -Uvh http://rpms.famillecollet.com/enterprise/remi-release-7.rpm`
`yum install -y php70 php70-php php70-php-fpm php70-php-pecl-memcached php70-php-mysqlnd php70-php-xml`
`ln -s /usr/bin/php70 /usr/bin/php`
`service httpd start`
`chkconfig httpd on`


2. Configure Apache
`$ useradd www-user`

`$cd /var/www/html/`
`$mkdir tinhocthatladongian`
`$chown -Rf www-user:apache tinhocthatladongian`


Listen 81
NameVirtualHost *:81
<VirtualHost *:81>
    DocumentRoot /var/www/html/tinhocthatladongian/
    <Directory "/var/www/html/tinhocthatladongian">
      Order deny,allow
      Allow from all
      AllowOverride All
      Require all granted
   </Directory>
</VirtualHost>

3. Creating ssh key
`$ sudo su - www-user`
`$ ssh-keygen -t rsa`
`$ cd /home/www-user/.ssh/`
`$ mv id_rsa.pub authorized_keys`


### Jenkins server
1. EC2 installation
2. Java installation
`$ yum install -y java-1.8.0-openjdk-devel.x86_64`
`$ alternatives --config java`
`$ java -version`

3. Jenkins install
`$ wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat/jenkins.repo`
`$ rpm --import https://pkg.jenkins.io/redhat/jenkins.io.key`
`$ yum install -y jenkins`

4. Start Jenkins
`$ systemctl start jenkins`

5. Check Jenkins
`$ service jenkins status`
`$ telnet localhost 8080`

6. Setting password
`$ less /var/lib/jenkins/secrets/initialAdminPassword`

7. Copy private key to Jenkin server
`cd /home`
`mkdir jenkins`
`vi web-key.pem` -> past id_rsa của server
`chmod 400 web-key.pem`
`chown -Rf jenkins:root jenkins/`

8. Test connect to web server
`vi /etc/passwd` - jenkins:x:996:994:Jenkins Automation Server:/var/lib/jenkins:/bin/bash
`su - jenkins`
`ssh yuki@3.19.54.241 -i /home/jenkins/web-key.pem`

9. Workspace
 `/var/lib/jenkins/workspace/`

10. Web-hook
`http://3.14.6.31:8080/github-webhook/`