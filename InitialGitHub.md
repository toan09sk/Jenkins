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
java -jar jenkins.war

### show environment variable
localhost:8080/env-vars.html/

add C:\Program Files\Git\usr\bin to the PATH ---- to using sh

### Create SSH Key
1. Chuyen qua quyen root: sudo su -
2. Tao user ten la yuki: useradd yuki
3. Tao password cho user: passwd yuki
4. Xoa 1 user: sudo userdel -r yuki
5. chuyen qua user yuki: su - yuki
6. Tao public key: ssh-keygen -t rsa
7. cd /home/yuki/.ssh
8. ls -la
9. Doi ssh key thanh authorize key: mv id_rsa.pub authorized_keys
10. Change quyen cho authorized_key: chmod 600 authorized_keys
11. cau hinh lai ssh: vi /etc/ssh/sshd_config
12. Restart ssh: service sshd restart

