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
  